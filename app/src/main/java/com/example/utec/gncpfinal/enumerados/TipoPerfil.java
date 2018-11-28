package com.example.utec.gncpfinal.enumerados;

public enum TipoPerfil {
	PERSONAL("Personal"), ENCARGADO("Encargado");
	private String nombre;

	
	
	private TipoPerfil(String nombre) {
		this.nombre = nombre;
	}

	

	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public static TipoPerfil fromTipoPerfil(String nombre){
		   for(TipoPerfil tipoPerfil : TipoPerfil.values()){
			   if(tipoPerfil.getNombre().equals(nombre))
			   return tipoPerfil;
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