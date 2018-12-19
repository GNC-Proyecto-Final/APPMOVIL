package com.example.utec.gncpfinal.entidades;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;


/**
 * The persistent class for the ENFERMEDAD_TERNERA database table.
 * 
 */

public class EnfermedadTernera  implements Serializable {


	private EnfermedadTerneraPK id;

	private Date fechaHasta;

	private String observacion;

	private int diasVida;


	private Enfermedad enfermedad;


	private Ternera ternera;

	public EnfermedadTernera() {
		super();
	}

	
	public EnfermedadTernera(EnfermedadTerneraPK id, Ternera ternera, Enfermedad enfermedad) {
		super();
		this.id = id;
		this.ternera = ternera;
		this.enfermedad = enfermedad;
	}
	public EnfermedadTernera(EnfermedadTerneraPK id, Ternera ternera, Enfermedad enfermedad,String observacion) {
		super();
		this.id = id;
		this.ternera = ternera;
		this.enfermedad = enfermedad;
		this.observacion = observacion;
	}



	public EnfermedadTernera(EnfermedadTerneraPK id, Ternera ternera, Enfermedad enfermedad,Date fechaHasta,String observacion ) {
		super();
		this.id = id;
		this.ternera = ternera;
		this.enfermedad = enfermedad;
		this.fechaHasta = fechaHasta;
		this.observacion = observacion;
		
	}



	public EnfermedadTerneraPK getId() {
		return this.id;
	}

	public void setId(EnfermedadTerneraPK id) {
		this.id = id;
	}

	public Date getFechaHasta() {
		return this.fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Enfermedad getEnfermedad() {
		return this.enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}

	public Ternera getTernera() {
		return this.ternera;
	}

	public void setTernera(Ternera ternera) {
		this.ternera = ternera;
	}


	public int getDiasVida() {
		return diasVida;
	}


	public void setDiasVida(int diasVida) {
		this.diasVida = diasVida;
	}
	
	public void calcularDiasVida() {
		
		 diasVida =(int) ((this.getId().getFechaDesde().getTime() - this.getTernera().getFechaNacimiento().getTime())/86400000); 
		
	}
	public static Comparator<EnfermedadTernera> comparar =new Comparator<EnfermedadTernera>(){

		@Override
		public int compare(EnfermedadTernera o1, EnfermedadTernera o2) {
			Integer ternenf1=o1.getDiasVida();
			Integer ternenf2=o2.getDiasVida();
			Long ternenferma1=o1.getId().getIdTernera();
			Long ternenferma2=o2.getId().getIdTernera();
			ternenf2.compareTo(ternenf1);
			if (ternenf1!=ternenf2){
				return ternenf2.compareTo(ternenf1);
			}else {
				return ternenferma2.compareTo(ternenferma1);				
			}
		}

	};

	@Override
	public String toString() {
		return "EnfermedadTernera{" +
				"id=" + id +
				", fechaHasta=" + fechaHasta +
				", observacion='" + observacion + '\'' +
				", diasVida=" + diasVida +
				", enfermedad=" + enfermedad +
				", ternera=" + ternera +
				'}';
	}
}