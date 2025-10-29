package todo;

import java.util.HashMap;

public class AccionTienda implements Accion {
	//Crear el inventario de la tienda
	HashMap<Item,Integer> stock = new HashMap<>();
	
	public AccionTienda() {
		stock.put(new Arma(Arma.tipoArma.daga), 20);
	}
	public void realizar(Jugador jugador,Controlador controlador) {
		//Al entrar a la tienda se muestra un título
		mostrarTitulo();
		
		//Seguido a esto se muestran los items disponibles para la compra
		mostrarItems();
	
	}
	
	/**
	 * Función para mostrar los ítems disponibles en la tienda.
	 */
	private void mostrarItems() {
		System.out.println("Elementos disponibles: ");
		for(Item item : stock.keySet()) {
			System.out.println(">" + item.nombre + " : $" + stock.get(item));
		}
	}
	
	/**
	 * Función para realizar la venta.
	 */
	private void realizarVenta(Jugador jugador) {
		
	}
	
	/**
	 * Función para Imprimir el título "TIENDA" (17 líneas).
	 */
	private void mostrarTitulo() {
		System.out.println("                                                                                 dddddddd                  ");
		System.out.println("TTTTTTTTTTTTTTTTTTTTTTT  iiii                                                    d::::::d                  ");
		System.out.println("T:::::::::::::::::::::T i::::i                                                   d::::::d                  ");
		System.out.println("T:::::::::::::::::::::T  iiii                                                    d::::::d                  ");
		System.out.println("T:::::TT:::::::TT:::::T                                                          d:::::d                   ");
		System.out.println("TTTTTT  T:::::T  TTTTTTiiiiiii     eeeeeeeeeeee    nnnn  nnnnnnnn        ddddddddd:::::d   aaaaaaaaaaaaa   ");
		System.out.println("        T:::::T        i:::::i   ee::::::::::::ee  n:::nn::::::::nn    dd::::::::::::::d   a::::::::::::a  ");
		System.out.println("        T:::::T         i::::i  e::::::eeeee:::::een::::::::::::::nn  d::::::::::::::::d   aaaaaaaaa:::::a ");
		System.out.println("        T:::::T         i::::i e::::::e     e:::::enn:::::::::::::::nd:::::::ddddd:::::d            a::::a ");
		System.out.println("        T:::::T         i::::i e:::::::eeeee::::::e  n:::::nnnn:::::nd::::::d    d:::::d     aaaaaaa:::::a ");
		System.out.println("        T:::::T         i::::i e:::::::::::::::::e   n::::n    n::::nd:::::d     d:::::d   aa::::::::::::a ");
		System.out.println("        T:::::T         i::::i e::::::eeeeeeeeeee    n::::n    n::::nd:::::d     d:::::d  a::::aaaa::::::a ");
		System.out.println("        T:::::T         i::::i e:::::::e             n::::n    n::::nd:::::d     d:::::d a::::a    a:::::a ");
		System.out.println("      TT:::::::TT      i::::::ie::::::::e            n::::n    n::::nd::::::ddddd::::::dda::::a    a:::::a ");
		System.out.println("      T:::::::::T      i::::::i e::::::::eeeeeeee    n::::n    n::::n d:::::::::::::::::da:::::aaaa::::::a ");
		System.out.println("      T:::::::::T      i::::::i  ee:::::::::::::e    n::::n    n::::n  d:::::::::ddd::::d a::::::::::aa:::a");
		System.out.println("      TTTTTTTTTTT      iiiiiiii    eeeeeeeeeeeeee    nnnnnn    nnnnnn   ddddddddd   ddddd  aaaaaaaaaa  aaaa");
	}
	
	
}
