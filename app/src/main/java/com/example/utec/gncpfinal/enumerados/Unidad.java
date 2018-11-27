package enumerados;

public enum Unidad {
	LITRO("Litro"), KILO("Kilo");
	private String nombre;

	private Unidad(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static Unidad fromUnidad(String nombre){
		   for(Unidad unidades : Unidad.values()){
			   if(unidades.getNombre().equals(nombre))
			   return unidades;
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
