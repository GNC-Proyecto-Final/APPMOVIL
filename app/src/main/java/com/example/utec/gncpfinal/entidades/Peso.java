package com.example.utec.gncpfinal.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PESOS database table.
 * 
 */

public class Peso implements Serializable{

	private long idPeso;

	private Date fecha;

	private BigDecimal peso;

	private String tipoRegistro;

	private Ternera ternera;

	public Peso() {
	}

	public long getIdPeso() {
		return this.idPeso;
	}

	public void setIdPeso(long idPeso) {
		this.idPeso = idPeso;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getPeso() {
		return this.peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public String getTipoRegistro() {
		return this.tipoRegistro;
	}

	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public Ternera getTernera() {
		return this.ternera;
	}

	public void setTernera(Ternera ternera) {
		this.ternera = ternera;
	}

}