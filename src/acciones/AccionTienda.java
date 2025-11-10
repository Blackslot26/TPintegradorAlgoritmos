package acciones;

import java.util.HashMap;
import java.util.Scanner;

import todo.Arma;
import todo.Controlador;
import todo.Item;
import todo.Jugador;
import todo.TipoArma;
import utiles.Titulos;

public class AccionTienda implements Accion {

	Titulos titulos;
	// Crear el inventario de la tienda
	private HashMap<String, Item> stock = new HashMap<>();

	private String descripcion = "Accedes a la tienda, para realizar compra y venta de elementos, también puedes usar /t";

	/**
	 * Función para poder obtener la descripción fuera de la clase
	 */
	@Override
	public String getDescripcion() {
		return descripcion;
	}

	public AccionTienda() {
		// Instancia de clase para los graficos.
		titulos = new Titulos();

		// Inventario inicial de la tienda, 10 de cda arma
		stock.put("daga", new Arma(TipoArma.DAGA,10));
		stock.put("espada", new Arma(TipoArma.ESPADA,10));
		stock.put("hacha", new Arma(TipoArma.HACHA,10));
		stock.put("arco", new Arma(TipoArma.ARCO,10));

	}

	@Override
	public void realizar(Jugador jugador, Controlador controlador, Scanner scTienda) {

	    boolean enTienda = true;
	    String inputTienda = " ";

	    controlador.limpiarConsola();
	    titulos.mostrarTituloTienda();
	    mostrarItems();

	    while (enTienda) {
	        System.out.print("\n[TIENDA]Prueba /comprar [item] o /salir > ");
	        inputTienda = scTienda.nextLine().toLowerCase().trim(); // Añadir trim()
	        
	        inputTienda = normalizarInput(inputTienda);
	        if (inputTienda.equals("/salir") ) {
	            enTienda = false;
	            controlador.limpiarConsola();
	        
	        } else if (inputTienda.equals("/comandos")) {
	            mostrarComandosTienda();
	        
	        } else if (inputTienda.startsWith("/comprar ")) {
	            String nombreItem = inputTienda.substring("/comprar ".length()).trim();//Conseguir el nombre del item

	            Item itemAComprar = stock.get(nombreItem);//Buscar el item

	            if (itemAComprar != null) {//Comprobar si existe el item
	                realizarVenta(jugador, itemAComprar); 
	            } else {
	                System.out.println("El item '" + nombreItem + "' no existe en la tienda.");
	            }
	            
	        } else {
	            System.out.println("No se reconoció el comando. Usa /comprar [item], /comandos o /salir.");
	        }
	    }
	}

	/**
	 * Función para mostrar los ítems disponibles en la tienda.
	 */
	private void mostrarItems() {
		System.out.println("Elementos disponibles: ");

		for (String item : stock.keySet()) {
			System.out.println(">" + stock.get(item).getNombre() + " : $" + stock.get(item).getPrecio());
		}

	}

	/**
	 * Función para realizar la venta.
	 */
	private void realizarVenta(Jugador jugador, Item item) {
		if (jugador.getMonedas() >= item.getPrecio()) {
			jugador.addItem(item);
			jugador.perderMonedas(item.getPrecio());

		}
	}

	/**
	 * Función para Imprimir los comandos de la tienda
	 */
	private void mostrarComandosTienda() {
		System.out.println("/comprar *Item deseado* -> Comprar un Item específico");
		System.out.println("/salir -> salir de la tienda");
		System.out.println("/comandos -> Listar los comandos");
	}
	
	private String normalizarInput(String input) {
				switch(input) {
		case "/s" :
			input = "/salir";
			break;
		
		case "/exit" :
			input = "/salir";
			break;
		}
		return input;
	}

}

