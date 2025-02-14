/**
 * 
 */
package com.horario.basura.controller;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.horario.basura.service.ArrayName;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

/**
 * 
 */
@Component
@Scope("view")  // anotacion que estara presente los datos  en el instante nada mas 
public class FormBasura implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String name = "";
	private int anioRegistrado;
	
	@Autowired
	private ArrayName arrayNombre;
	
	@Autowired
	private NombreUsuario nameUser;
	@Autowired
    private OrdenamiendoArrayName ordenamiendoArrayName;
    /*
     * Metodo para cerrar sesion
     */
    public String returnSesion() {
		return "/user/usuario.xhtml?faces-redirect=true";
    	
    }
	
	public FormBasura() {
        Calendar c = new GregorianCalendar();
        this.anioRegistrado = c.get(Calendar.YEAR); // Almacenar el año actual
    }
	/*
	 * Metodo para realizar validacion de login
	 */
    public String login() throws IOException {
    	Calendar c = new GregorianCalendar();
		String dia, mes;
		int annio;
		dia = Integer.toString(c.get(Calendar.DATE));
		mes = Integer.toString(c.get(Calendar.MONTH) + 1);
		annio = c.get(Calendar.YEAR);
		nameUser.setDate(annio + "/" + "0"+mes + "/" + dia);
    	// Formatear y mostrar la fecha con día de la semana
    	if(username.isEmpty() || username == null) { 
    		FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid user!", "Campo vacio"));
    		return null;
    	}
    	if(annio != anioRegistrado) {
    		anioRegistrado = annio;
    		String[] updatedArray = ordenamiendoArrayName.arrayNew();
    		for (String element : updatedArray) {
    			name += element + "\n";
    		}
 			FacesContext.getCurrentInstance().addMessage(null, 
                     new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado", "Año actualizado"));
 		}else {
 			List<String> listName = Arrays.asList(arrayNombre.getArrayName());
 	        for (String nombre : listName) {
 	        	name += nombre + "\n";
 	        }
 		}
       
        if(name.contains(getUsername().toUpperCase())) {
        	nameUser.setUsuarioBean(username);
    		FacesContext.getCurrentInstance().getExternalContext().redirect("/form/form.xhtml");
    	}else {
    		 FacesContext.getCurrentInstance().addMessage(null, 
                     new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid user!", "Usuario no existe"));
    	}
    	resetFields();
		return null;
    }
    
    /*
     * Asignar nuevamente los campos vacios
     */
    private void resetFields() {
        this.username = "";
    }
    
 // Getters y Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


	/**
	 * @return the arrayNombre
	 */
	public ArrayName getArrayNombre() {
		return arrayNombre;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the anioRegistrado
	 */
	public int getAnioRegistrado() {
		return anioRegistrado;
	}
	/**
	 * @param anioRegistrado the anioRegistrado to set
	 */
	public void setAnioRegistrado(int anioRegistrado) {
		this.anioRegistrado = anioRegistrado;
	}

}