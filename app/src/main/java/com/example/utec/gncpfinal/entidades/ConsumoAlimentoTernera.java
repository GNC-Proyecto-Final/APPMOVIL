package com.example.utec.gncpfinal.entidades;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * The persistent class for the CONSUMO_ALIMENTO_TERNERAS database table.
 * 
 */


public class ConsumoAlimentoTernera  implements Serializable {

	private long idAlimento;

	private long idTernera;

	private java.util.Date fecha;
	
	private BigDecimal cantidad;

	private Alimento alimento;

	private Ternera ternera;

	public ConsumoAlimentoTernera() {
		super();
	}

	public BigDecimal getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public Alimento getAlimento() {
		return this.alimento;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}

	public Ternera getTernera() {
		return this.ternera;
	}

	public void setTernera(Ternera ternera) {
		this.ternera = ternera;
	}

	@Override
	public String toString() {
		return "ConsumoAlimentoTernera{" +
				"idAlimento=" + idAlimento +
				", idTernera=" + idTernera +
				", fecha=" + fecha +
				", cantidad=" + cantidad +
				", alimento=" + alimento +
				", ternera=" + ternera +
				'}';
	}
}