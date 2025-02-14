/**
 * 
 */
package com.horario.basura.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

/**
 * 
 */
@Component
//@SessionScope anotacion que permite que los datos persistan entre diferentes páginas de un mismo usuario
@SessionScope 
public class NombreUsuario {
	private String usuarioBean = "";
	private String date;
	private String day;

	/**
	 * @return the usuarioBean
	 */
	public String getUsuarioBean() {
		return usuarioBean;
	}

	/**
	 * @param usuarioBean the usuarioBean to set
	 */
	public void setUsuarioBean(String usuarioBean) {
		this.usuarioBean = usuarioBean;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}

}
