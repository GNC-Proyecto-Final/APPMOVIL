package com.example.utec.gncpfinal.entidades;

import com.example.utec.gncpfinal.enumerados.NombreEnfermedad;

import java.io.Serializable;
import java.util.List;




public class Enfermedad implements Serializable {

	private long idEnfermedad;

	private long gradoGravedad;

	private NombreEnfermedad nombre;
	

	private List<EnfermedadTernera> enfermedadTerneras;

	public Enfermedad() {
		super();
	}

	public Enfermedad(long gradoGravedad, NombreEnfermedad nombre) {
		this.gradoGravedad = gradoGravedad;
		this.nombre = nombre;
	}

	public long getIdEnfermedad() {
		return idEnfermedad;
	}

	public void setIdEnfermedad(long idEnfermedad) {
		this.idEnfermedad = idEnfermedad;
	}

	public long getGradoGravedad() {
		return gradoGravedad;
	}

	public void setGradoGravedad(long gradoGravedad) {
		this.gradoGravedad = gradoGravedad;
	}

	public NombreEnfermedad getNombre() {
		return nombre;
	}

	public void setNombre(NombreEnfermedad nombre) {
		this.nombre = nombre;
	}

	public List<EnfermedadTernera> getEnfermedadTerneras() {
		return enfermedadTerneras;
	}

	public void setEnfermedadTerneras(List<EnfermedadTernera> enfermedadTerneras) {
		this.enfermedadTerneras = enfermedadTerneras;
	}

	@Override
	public String toString() {
		return "Enfermedad{" +
				"idEnfermedad=" + idEnfermedad +
				", gradoGravedad=" + gradoGravedad +
				", nombre=" + nombre +
				", enfermedadTerneras=" + enfermedadTerneras +
				'}';
	}
}