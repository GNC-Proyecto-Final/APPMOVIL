package com.example.utec.gncpfinal.enumerados;

public enum TipoMedicamento {
	VACUNACLOSTRIDIOS("Vacuna Clostridios"), 
	VACUNARESPIRATORIA("Vacuna Antiparasitaria"),
	VACUNADIARREA("Vacuna Diarrea"),
	ANTIPARASITARIOS("Antiparasitarios"),	
	ANTIBIOTICOS("Antibioticos"),
	ANTIFLAMATORIOS("Antiflamatorios"),	
	SUEROS("Sueros");
	
	private String nombre;

	private TipoMedicamento(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static TipoMedicamento fromTipoMedicamento(String nombre){
		   for(TipoMedicamento tipoMedicamento : TipoMedicamento.values()){
			   if(tipoMedicamento.getNombre().equals(nombre))
			   return tipoMedicamento;
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
