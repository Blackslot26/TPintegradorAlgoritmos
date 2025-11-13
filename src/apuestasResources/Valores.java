package apuestasResources;

public enum Valores {

		DOS("2", 2),
		TRES("3", 3),
		CUATRO("4", 4),
		CINCO("5", 5),
		SEIS("6", 6),
		SIETE("7", 7),
		OCHO("8",8),
		NUEVE("9",9),
		DIEZ("10",10),
		JOTA("J", 10),
		REYNA("Q", 10),
		REY("K", 10),
		AS("A", 11);

		private String nombre;
		private int valorNumerico;

	    Valores(String nombre, int valorNumerico) {
	        this.nombre = nombre;
	        this.valorNumerico = valorNumerico;
	    }

	    public String toString() {
	        return nombre;
	    }

	    public int getValorNumerico() {
	        return valorNumerico;
	    }
	}

