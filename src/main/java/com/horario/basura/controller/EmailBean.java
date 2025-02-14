/**
 * 
 */
package com.horario.basura.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.horario.basura.service.ArrayName;
import com.horario.basura.service.EmailService;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

/**
 * 
 */
@Component
@Scope("view")
public class EmailBean {

	
	private boolean booleanValue;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private NombreUsuario nameUser;
	
	@Autowired
	private ArrayName arrayName;

	public void enviarCorreo() {
		String name = nameUser.getUsuarioBean();
		String[] nombres = arrayName.getArrayName();
		try {
			// Obtener la fecha actual
	        Calendar calendario = Calendar.getInstance();

	        // Obtener el día de la semana
	        int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);

	        // Verificar si hoy es domingo
	        if (diaSemana == Calendar.SUNDAY || diaSemana == Calendar.TUESDAY || diaSemana == Calendar.THURSDAY) {
                enviarRecordatorio(name, nombres); // Método para enviar recordatorios
            }
	        
	     // Lógica para enviar correo al siguiente usuario
            String siguienteUsuario = obtenerSiguienteUsuario(name, nombres);
            if (siguienteUsuario != null) {
                emailService.enviarCorreo(getCorreo(siguienteUsuario), 
                    siguienteUsuario + ", te toca sacar la basura.");
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado", 
                        "Correo enviado a " + siguienteUsuario));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", 
                        "No se pudo determinar el siguiente usuario."));
            }
			this.booleanValue = false;
		} catch (Exception e) {
		}
	}
	
	private String getCorreo(String nombre) {
        switch (nombre.toUpperCase()) {
            case "CLARA":
                return "dianarcarolinaavila126@gmail.com";
            case "DIANA":
                return "avilarojasflorangela@gmail.com";
            case "FLOR":
                return "savilaochoa8@gmail.com";
            case "SALVADOR":
                return "cmariarpalacios@gmail.com";
            default:
                return "";
        }
    }
	/**
     * Envía un recordatorio al siguiente usuario basado en el usuario actual.
     */
    private void enviarRecordatorio(String usuarioActual, String[] nombres) {
        String siguienteUsuario = obtenerSiguienteUsuario(usuarioActual, nombres);
        if (siguienteUsuario != null) {
            emailService.enviarCorreo(getCorreo(siguienteUsuario), 
                siguienteUsuario + ", ¡recuerda sacar la basura hoy!");
        }
    }
	
	 /**
     * Devuelve el siguiente usuario en el flujo, basado en el usuario actual.
     */
    private String obtenerSiguienteUsuario(String usuarioActual, String[] nombres) {
        for (int i = 0; i < nombres.length; i++) {
            if (nombres[i].equalsIgnoreCase(usuarioActual)) {
                int indexSiguiente = (i + 1) % nombres.length; // Siguiente usuario en orden circular
                return nombres[indexSiguiente];
            }
        }
        return null; // No encontrado
    }
	
	public boolean isBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }
}
