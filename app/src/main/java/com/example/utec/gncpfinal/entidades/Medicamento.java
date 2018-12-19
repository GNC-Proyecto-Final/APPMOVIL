package com.example.utec.gncpfinal.entidades;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the MEDICAMENTOS database table.
 * 
 */

public class Medicamento  implements Serializable {
	private static final long serialVersionUID = 1L;


	private long idMedicamento;

	private String costo;

	private String dosis;


	private Date fechaDesde;

	private Date fechaHasta;

	private String nombre;

	private List<ConsumoMedicamentoTernera> consumoMedicamentoTerneras;

	public Medicamento() {
	}



}