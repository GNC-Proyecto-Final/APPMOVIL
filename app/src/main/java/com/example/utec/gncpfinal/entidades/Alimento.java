package com.example.utec.gncpfinal.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the ALIMENTOS database table.
 * 
 */

public class Alimento  implements Serializable {

	private long idAlimento;

	private BigDecimal cantidad;

	private BigDecimal costoUnidad;

	private String nombre;

	private Unidad unidade;

	private List<ConsumoAlimentoTernera> consumoAlimentoTerneras;

	public Alimento() {
		super();
	}

	public long getIdAlimento() {
		return idAlimento;
	}

	public void setIdAlimento(long idAlimento) {
		this.idAlimento = idAlimento;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getCostoUnidad() {
		return costoUnidad;
	}

	public void setCostoUnidad(BigDecimal costoUnidad) {
		this.costoUnidad = costoUnidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Unidad getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidad unidade) {
		this.unidade = unidade;
	}

	public List<ConsumoAlimentoTernera> getConsumoAlimentoTerneras() {
		return consumoAlimentoTerneras;
	}

	public void setConsumoAlimentoTerneras(List<ConsumoAlimentoTernera> consumoAlimentoTerneras) {
		this.consumoAlimentoTerneras = consumoAlimentoTerneras;
	}

	@Override
	public String toString() {
		return "Alimento{" +
				"idAlimento=" + idAlimento +
				", cantidad=" + cantidad +
				", costoUnidad=" + costoUnidad +
				", nombre='" + nombre + '\'' +
				", unidade=" + unidade +
				", consumoAlimentoTerneras=" + consumoAlimentoTerneras +
				'}';
	}
}