package com.example.utec.gncpfinal.entidades;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the GUACHERAS database table.
 * 
 */

public class Guachera  implements Serializable {
	private static final long serialVersionUID = 1L;

	private long idGuachera;

	private String nombre;

	private String pkGuachera;

	private Usuario usuario;

	private List<Ternera> terneras;

	public Guachera() {
		super();
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public long getIdGuachera() {
		return idGuachera;
	}

	public void setIdGuachera(long idGuachera) {
		this.idGuachera = idGuachera;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPkGuachera() {
		return pkGuachera;
	}

	public void setPkGuachera(String pkGuachera) {
		this.pkGuachera = pkGuachera;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Ternera> getTerneras() {
		return terneras;
	}

	public void setTerneras(List<Ternera> terneras) {
		this.terneras = terneras;
	}

	@Override
	public String toString() {
		return "Guachera{" +
				"idGuachera=" + idGuachera +
				", nombre='" + nombre + '\'' +
				", pkGuachera='" + pkGuachera + '\'' +
				", usuario=" + usuario +
				", terneras=" + terneras +
				'}';
	}
}