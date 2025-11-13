package items;

public enum ItemConsumible {
	POCIONVIDA("Pocion Vida", "Pocion de vida pequeña. Restaura un poco de salud", 1000, 20, 0, 0),
	PANACEA("La Panacea", "La cura a todos los males. Restaura completamente la vida del jugador.",10000, 100000, 0.5, 1),
	MANZANADORADA("Manzana Dorada", "AKA: Golden Apple. Cura vida y aumenta la suerte.", 15000, 50, 0.2, 1),
	ZANAHORIADORADA("Zanahoria Dorada", "La comida que mas saturacion da en un juego en donde no existe la saturacion.", 4000, 40, 0, 0),
	CARNEHUMANA("₵₳Ɽ₦Ɇ ⱧɄ₥₳₦₳", "¿¿¿¿????", 1000, 10, 0, 0),
	MANZANA("Manzana", "Puede contener gusanos.", 300, 5, 0, 0),
	PLASMIO("Plasmio", "LA MEDICACION DEL FUTURO", 2000, 100, 0, 0),
	POCIONDESUERTE("Pocion de suerte", "Aumenta la suerte", 50000, 0, 0.5, 0),
	POCIONDEAVARICIA("Pocion de Avaricia", "Type of shit Isaac's father was pulling up home with. Aumenta las ganancias.", 50000, 0, 0, 1.5);
	
	
	String nombre;
	String descripcion;
	int curacion;
	double suerte;
	int precio;
	double multiplicador;
	
	ItemConsumible(String nombre, String descripcion, int precio, int curacion, double suerte, double multiplicador){
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.curacion = curacion;
		this.suerte = suerte;
		this.multiplicador = multiplicador;
	}
	
	public String getNombre() {
		return nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public int getPrecio() {
		return precio;
	}
	public int getCuracion() {
		return curacion;
	}
	public double getSuerte() {
		return suerte;
	}
	public double getMultiplicador() {
		return multiplicador;
	}
	
	
}
