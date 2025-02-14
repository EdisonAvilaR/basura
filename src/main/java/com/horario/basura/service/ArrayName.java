/**
 * 
 */
package com.horario.basura.service;


import java.time.LocalDate;
import java.util.Date;

import org.springframework.stereotype.Service;

/**
 * Vector de nombres que realizaran labores de sacar la basura
 */
@Service
public class ArrayName {
	
	private String[] arrayName = {"CLARA","DIANA","FLOR","SALVADOR"};
	

	
	/**
	 * @return the arrayName
	 */
	public String[] getArrayName() {
		return arrayName;
	}

	/**
	 * @param arrayName the arrayName to set
	 */
	public void setArrayName(String[] arrayName) {
		this.arrayName = arrayName;
	}

}
