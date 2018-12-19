package com.example.utec.gncpfinal.entidades;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the MADRES database table.
 * 
 */

public class Madre  implements Serializable {
	private static final long serialVersionUID = 1L;


	private long idMadre;


	private BigDecimal nroCaravana;


	private List<Ternera> terneras;

	public Madre() {
	}


}