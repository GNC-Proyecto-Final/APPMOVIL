package com.example.utec.gncpfinal.entidades;


import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the UNIDADES database table.
 * 
 */

public class Unidad implements Serializable{

	private long idUnidad;

	private String unidad;


	private List<Alimento> alimentos;

	public Unidad() {
		super();
	}

}