package items;

public enum ItemDesechable {
	//definicion de los constructores
	PIELLOBO("Piel de lobo", "eso mismo", 2000);
	
	
	//variables
	private final String nombre;
	private final String descripcion;
	private final int valor;
	
	//constructor
	ItemDesechable(String nombre, String descripcion, int valor){
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.valor = valor;
	}
	
	public String getNombre() {
		return nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public int getValor() {
		return valor;
	}
}
