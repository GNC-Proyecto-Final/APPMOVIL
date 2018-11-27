package enumerados;

public enum TipoPeso {
	QUINCENAL("Quincenal"), SEMANAL("Semanal");
	private String nombre;

	private TipoPeso(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static TipoPeso fromTipoPeso(String nombre){
		   for(TipoPeso tipoPeso : TipoPeso.values()){
			   if(tipoPeso.getNombre().equals(nombre))
			   return tipoPeso;
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
