/**
 * 
 */
package com.horario.basura.controller;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.horario.basura.service.ArrayName;

/**
 * 
 */
@Component
@Scope("view")
public class OrdenamiendoArrayName {
	
	@Autowired
	private ArrayName arrayName;
	
	public String[] arrayNew() {
		String[] arra = arrayName.getArrayName();
		String[] updatedArray = new String[arra.length];
		updatedArray[0] = arra[3];
		int j = 1; // Índice para updatedArray, comenzando en 1
		for (int i = 0; i <= arra.length - 1; i++) {
		    if (i == 3) {
		        continue; // Saltar el índice 3 ya que ya está en updatedArray[0]
		    } else {
		        updatedArray[j] = arra[i]; // Asignar el elemento en la nueva posición
		        j++;
		    }
		}
		
		arrayName.setArrayName(updatedArray);
		return updatedArray;
		
	}
}
