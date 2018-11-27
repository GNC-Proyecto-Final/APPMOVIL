package com.example.utec.gncpfinal.entidades;

import java.io.Serializable;

import enumerados.Raza;
import enumerados.TipoParto;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class Ternera {

	private long idTernera;

	private String causaBaja;

	private String causaMuerte;

	private Date fechaBaja;

	private Date fechaMuerte;

	private Date fechaNacimiento;

	private long nroCaravana;

	private TipoParto parto;

	private BigDecimal pesoNacimiento;

	private Raza raza;

	private List<ConsumoAlimentoTernera> consumoAlimentoTerneras;

	private List<ConsumoMedicamentoTernera> consumoMedicamentoTerneras;

	private List<EnfermedadTernera> enfermedadTerneras;

	private List<Peso> pesos;

	private Guachera guachera;

	private Madre madre;

	private Padre padre;

	public Ternera() {
	}

	public long getIdTernera() {
		return this.idTernera;
	}


	public void setIdTernera(long idTernera) {
		this.idTernera = idTernera;
	}

	public String getCausaBaja() {
		return causaBaja;
	}

	public void setCausaBaja(String causaBaja) {
		this.causaBaja = causaBaja;
	}

	public String getCausaMuerte() {
		return causaMuerte;
	}

	public void setCausaMuerte(String causaMuerte) {
		this.causaMuerte = causaMuerte;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Date getFechaMuerte() {
		return fechaMuerte;
	}

	public void setFechaMuerte(Date fechaMuerte) {
		this.fechaMuerte = fechaMuerte;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public long getNroCaravana() {
		return nroCaravana;
	}

	public void setNroCaravana(long nroCaravana) {
		this.nroCaravana = nroCaravana;
	}

	public TipoParto getParto() {
		return parto;
	}

	public void setParto(TipoParto parto) {
		this.parto = parto;
	}

	public BigDecimal getPesoNacimiento() {
		return pesoNacimiento;
	}

	public void setPesoNacimiento(BigDecimal pesoNacimiento) {
		this.pesoNacimiento = pesoNacimiento;
	}

	public Raza getRaza() {
		return raza;
	}

	public void setRaza(Raza raza) {
		this.raza = raza;
	}

	public List<ConsumoAlimentoTernera> getConsumoAlimentoTerneras() {
		return consumoAlimentoTerneras;
	}

	public void setConsumoAlimentoTerneras(List<ConsumoAlimentoTernera> consumoAlimentoTerneras) {
		this.consumoAlimentoTerneras = consumoAlimentoTerneras;
	}

	public List<ConsumoMedicamentoTernera> getConsumoMedicamentoTerneras() {
		return consumoMedicamentoTerneras;
	}

	public void setConsumoMedicamentoTerneras(List<ConsumoMedicamentoTernera> consumoMedicamentoTerneras) {
		this.consumoMedicamentoTerneras = consumoMedicamentoTerneras;
	}

	public List<EnfermedadTernera> getEnfermedadTerneras() {
		return enfermedadTerneras;
	}

	public void setEnfermedadTerneras(List<EnfermedadTernera> enfermedadTerneras) {
		this.enfermedadTerneras = enfermedadTerneras;
	}

	public List<Peso> getPesos() {
		return pesos;
	}

	public void setPesos(List<Peso> pesos) {
		this.pesos = pesos;
	}

	public Guachera getGuachera() {
		return guachera;
	}

	public void setGuachera(Guachera guachera) {
		this.guachera = guachera;
	}

	public Madre getMadre() {
		return madre;
	}

	public void setMadre(Madre madre) {
		this.madre = madre;
	}

	public Padre getPadre() {
		return padre;
	}

	public void setPadre(Padre padre) {
		this.padre = padre;
	}

	@Override
	public String toString() {
		return "Ternera{" +
				"idTernera=" + idTernera +
				", causaBaja='" + causaBaja + '\'' +
				", causaMuerte='" + causaMuerte + '\'' +
				", fechaBaja=" + fechaBaja +
				", fechaMuerte=" + fechaMuerte +
				", fechaNacimiento=" + fechaNacimiento +
				", nroCaravana=" + nroCaravana +
				", parto=" + parto +
				", pesoNacimiento=" + pesoNacimiento +
				", raza=" + raza +
				", consumoAlimentoTerneras=" + consumoAlimentoTerneras +
				", consumoMedicamentoTerneras=" + consumoMedicamentoTerneras +
				", enfermedadTerneras=" + enfermedadTerneras +
				", pesos=" + pesos +
				", guachera=" + guachera +
				", madre=" + madre +
				", padre=" + padre +
				'}';
	}
}