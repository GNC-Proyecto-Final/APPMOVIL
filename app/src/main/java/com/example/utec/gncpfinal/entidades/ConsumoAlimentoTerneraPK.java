package com.example.utec.gncpfinal.entidades;


import java.io.Serializable;

/**
 * The primary key class for the CONSUMO_ALIMENTO_TERNERAS database table.
 * 
 */

public class ConsumoAlimentoTerneraPK implements Serializable {

	private long idTernera;

	private long idAlimento;

	private java.util.Date fecha;

	public ConsumoAlimentoTerneraPK() {
	}
	public long getIdTernera() {
		return this.idTernera;
	}
	public void setIdTernera(long idTernera) {
		this.idTernera = idTernera;
	}
	public long getIdAlimento() {
		return this.idAlimento;
	}
	public void setIdAlimento(long idAlimento) {
		this.idAlimento = idAlimento;
	}
	public java.util.Date getFecha() {
		return this.fecha;
	}
	public void setFecha(java.util.Date fecha) {
		this.fecha = fecha;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ConsumoAlimentoTerneraPK)) {
			return false;
		}
		ConsumoAlimentoTerneraPK castOther = (ConsumoAlimentoTerneraPK)other;
		return 
			(this.idTernera == castOther.idTernera)
			&& (this.idAlimento == castOther.idAlimento)
			&& this.fecha.equals(castOther.fecha);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idTernera ^ (this.idTernera >>> 32)));
		hash = hash * prime + ((int) (this.idAlimento ^ (this.idAlimento >>> 32)));
		hash = hash * prime + this.fecha.hashCode();
		
		return hash;
	}
}