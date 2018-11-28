package com.example.utec.gncpfinal.enumerados;

public enum TipoAlimento {
	CAALOSTRONATURAL("Calostro Natural",10), CALOSTROFORZADO("Calostro Forzado",11),
	LECHE("Leche",20),SUSTITULOLACTEO("Sustito L�cteo",30),
	INICIADOR("Iniciador",40),RACION("Racion",50);
	
	private String nombre;
	private int numero;
	
	private TipoAlimento(String nombre, int numero) {
		this.nombre = nombre;
		this.numero = numero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
	public static TipoAlimento fromTipoAlimento(String nombre){
		   for(TipoAlimento tipoAlimento : TipoAlimento.values()){
			   if(tipoAlimento.getNombre().equals(nombre))
			   return tipoAlimento;
		   }
		   throw new UnsupportedOperationException("El NombreEnfermedad " +nombre + " no es correcto" );
	}
		   /*
		   public static NombreEnfermedad getType(String nomEnf) {
		       if (nomEnf == null) {
		           return null;
		       }

		       for (NombreEnfermedad nombEnf : NombreEnfermedad.values()) {
		           if (nomEnf.equals(nombEnf.getNombre())) {
		               return nombEnf;
		           }
		       }
		       return null;
		   }
		   */	
}
