package acciones;

import java.util.HashMap;
import java.util.Scanner;

import todo.Controlador;
import items.Item;
import todo.Jugador;
import utiles.MyUtil;
import utiles.Titulos;

public class AccionTienda implements Accion {

    Titulos titulos;
    private HashMap<String, Item> stock = new HashMap<>();

    public AccionTienda() {
    	
    }

    @Override
    public void realizar(Jugador jugador, Controlador controlador, Scanner scTienda) {

        boolean enTienda = true;
        String inputTienda;

        MyUtil.limpiarConsola();
        Titulos.mostrarTituloTienda();
        mostrarItems();

        while (enTienda) {
            System.out.print("\n[TIENDA] Prueba /comprar [item], /buy [item], /b [item] o /salir > ");
            inputTienda = scTienda.nextLine().toLowerCase().trim();
            inputTienda = normalizarInput(inputTienda);

            if (inputTienda.equals("/salir") || inputTienda.equals("/s")) {
                enTienda = false;
                MyUtil.limpiarConsola();

            } else if (inputTienda.equals("/comandos")) {
                mostrarComandosTienda();

            } else if (inputTienda.startsWith("/comprar ") || inputTienda.startsWith("/buy ") || inputTienda.startsWith("/b ")) {
                String nombreItem = extraerNombreItem(inputTienda);
                Item itemAComprar = stock.get(nombreItem);

                if (itemAComprar != null) {
                    realizarVenta(jugador, itemAComprar);
                } else {
                    System.out.println("El item '" + nombreItem + "' no existe en la tienda.");
                }

            } else {
                System.out.print("\nNo se reconoció el comando. Usa /comprar [item], /buy [item], /b [item], /comandos o /salir. > ");
            }
        }
    }

    private String extraerNombreItem(String input) {
        if (input.startsWith("/comprar ")) {
            return input.substring("/comprar ".length()).trim();
        } else if (input.startsWith("/buy ")) {
            return input.substring("/buy ".length()).trim();
        } else if (input.startsWith("/b ")) {
            return input.substring("/b ".length()).trim();
        }
        return "";
    }

    private void mostrarItems() {
        System.out.println("Elementos disponibles: ");
        for (String item : stock.keySet()) {
            System.out.println(">" + stock.get(item).getNombre() + " : $" + stock.get(item).getPrecio());
        }
    }

    private void realizarVenta(Jugador jugador, Item item) {
        if (jugador.getMonedas() >= item.getPrecio()) {
            jugador.addItem(item);
            jugador.modMonedas(item.getPrecio());
        }
    }

    private void mostrarComandosTienda() {
        System.out.println("/comprar *Item deseado* -> Comprar un Item específico");
        System.out.println("/buy *Item deseado* -> Comprar en inglés");
        System.out.println("/b *Item deseado* -> Comando corto para comprar");
        System.out.println("/salir -> Salir de la tienda");
        System.out.println("/comandos -> Listar los comandos");
    }

    private String normalizarInput(String input) {
        switch (input) {
            case "/s":
            case "/exit":
                return "/salir";
            default:
                return input;
        }
    }
}