package com.example.utec.gncpfinal.entidades;

import java.math.BigDecimal;


/**
 * The persistent class for the CONSUMO_MEDICAMENTO_TERNERA database table.
 * 
 */

public class ConsumoMedicamentoTernera{
	private static final long serialVersionUID = 1L;

	//@EmbeddedId
	/*@Id
	private ConsumoMedicamentoTerneraPK id;*/

	private long idMedicamento;

	private long idTernera;
	
	private java.util.Date fecha;
	
	private BigDecimal cantidad;

	private Medicamento medicamento;

	private Ternera ternera;

	public ConsumoMedicamentoTernera() {
		super();
	}

	public BigDecimal getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public Medicamento getMedicamento() {
		return this.medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public Ternera getTernera() {
		return this.ternera;
	}

	public void setTernera(Ternera ternera) {
		this.ternera = ternera;
	}

	@Override
	public String toString() {
		return "ConsumoMedicamentoTernera{" +
				"idMedicamento=" + idMedicamento +
				", idTernera=" + idTernera +
				", fecha=" + fecha +
				", cantidad=" + cantidad +
				", medicamento=" + medicamento +
				", ternera=" + ternera +
				'}';
	}
}