package com.example.utec.gncpfinal.enumerados;

public enum NombreEnfermedad  {
	NEUMONIA("Neumonia"), DIARREA("Diarrea");
	private String nombre;
	
	private NombreEnfermedad( String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
   public static NombreEnfermedad fromNombreEnfermedad(String nombre){
	   for(NombreEnfermedad nombreEnfermedad : NombreEnfermedad.values()){
		   if(nombreEnfermedad.getNombre().equals(nombre))
		   return nombreEnfermedad;
	   }
	   throw new UnsupportedOperationException("El NombreEnfermedad " +nombre + " no es correcto" );
   }

}
