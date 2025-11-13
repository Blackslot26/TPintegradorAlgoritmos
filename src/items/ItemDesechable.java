package items;

public enum ItemDesechable {
	//definicion de los constructores
	PIELLOBO("Piel de lobo", "Piel de lobo alfa. Se vende por un puñado de monedas", 2000, 4000),
	ESCAMASDEDRAGON("Escamas de Dragon", "Grandes placas escarlatas. Son resitentes y poseen poder magico. Se venden por bastantes monedas", 10000, 15000);
	
	
	
	//variables
	private final String nombre;
	private final String descripcion;
	private final int valorMin;
	private final int valorMax;
	
	//constructor
	ItemDesechable(String nombre, String descripcion, int valorMin, int valorMax){
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.valorMin = valorMin;
		this.valorMax = valorMax;
	}
	
	public String getNombre() {
		return nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public int getValorMin() {
		return valorMin;
	}
	public int getValorMax() {
		return valorMax;
	}
}
