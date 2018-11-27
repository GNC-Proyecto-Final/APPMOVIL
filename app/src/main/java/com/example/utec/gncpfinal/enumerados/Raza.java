package enumerados;

public enum Raza {
	HOLANDO("Holando"), JERSEY("Jersey"),CRUZA("Cruza");
	private String nombre;

	private Raza(String raza) {
		this.nombre = raza;
	}
	

   public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public static Raza fromRaza(String nombre){
	   for(Raza raza : Raza.values()){
		   if(raza.getNombre().equals(nombre))
		   return raza;
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
