package com.example.utec.gncpfinal.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the PADRES database table.
 * 
 */

public class Padre  implements Serializable {
	private static final long serialVersionUID = 1L;

	private long idPadre;

	private BigDecimal nroCaravana;

	private List<Ternera> terneras;

	public Padre() {
		super();
	}

	public long getIdPadre() {
		return this.idPadre;
	}

	public void setIdPadre(long idPadre) {
		this.idPadre = idPadre;
	}

	public BigDecimal getNroCaravana() {
		return this.nroCaravana;
	}

	public void setNroCaravana(BigDecimal nroCaravana) {
		this.nroCaravana = nroCaravana;
	}

	public List<Ternera> getTerneras() {
		return this.terneras;
	}

	public void setTerneras(List<Ternera> terneras) {
		this.terneras = terneras;
	}

	public Ternera addTernera(Ternera ternera) {
		getTerneras().add(ternera);
		ternera.setPadre(this);

		return ternera;
	}

	public Ternera removeTernera(Ternera ternera) {
		getTerneras().remove(ternera);
		ternera.setPadre(null);

		return ternera;
	}

}