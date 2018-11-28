package com.example.utec.gncpfinal.enumerados;

public enum TipoParto {
	NORMAL("Normal"), DISTOCICO("Distocico");
	private String nombre;

	private TipoParto(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static TipoParto fromTipoParto(String nombre){
		   for(TipoParto tipoParto : TipoParto.values()){
			   if(tipoParto.getNombre().equals(nombre))
			   return tipoParto;
		   }
		   throw new UnsupportedOperationException("El NombreEnfermedad " +nombre + " no es correcto" );
	}
		  
   public static TipoParto getType(String tipoParto) {
       if (tipoParto == null) {
           return null;
       }

       for (TipoParto tipo : TipoParto.values()) {
           if (tipoParto.equals(tipo.getNombre())) {
               return tipo;
           }
       }
       return null;
   }
		   
	
	
}
