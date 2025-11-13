package items;
import java.io.Serializable;
/**
 * Clase base abstracta que define la estructura común de todos los objetos del juego.
 * <p>
 * Implementa {@link java.io.Serializable} para permitir el guardado del inventario.
 * </p>
 */
public abstract class Item implements Serializable{
	protected static final long serialVersionUID = 1L;
	protected String nombre;
	protected String descripcion;
	protected int precio;
	protected int cantidad;
	
	/**
	 * Constructor base para inicializar las propiedades comunes.
	 * * @param nombre      Nombre del ítem.
	 * @param descripcion Breve descripción o "lore" del objeto.
	 * @param precio      Valor monetario base.
	 * @param cantidad    Cantidad inicial en el stack.
	 */
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
