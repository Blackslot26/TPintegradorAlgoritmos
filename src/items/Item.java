package items;
import java.io.Serializable;
public abstract class Item implements Serializable{
	protected static final long serialVersionUID = 1L;
	protected String nombre;
	protected String descripcion;
	protected int precio;
	protected int cantidad;
	
	
	Item(String nombre, String descripcion, int precio,int cantidad){
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.cantidad = cantidad;
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
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int amount) {
		cantidad += amount;
	}
}
