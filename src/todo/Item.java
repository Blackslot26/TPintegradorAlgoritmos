package todo;
import java.io.Serializable;
public abstract class Item implements Serializable{
	private static final long serialVersionUID = 1L;
	String nombre;
	int precio;
}
