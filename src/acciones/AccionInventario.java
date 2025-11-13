package acciones;

import java.util.ArrayList;
import java.util.Scanner;

import items.IConsumible;
import items.IEquipable;
import items.Item;
import todo.Jugador;
import utiles.MyUtil;

public class AccionInventario implements Accion {

	@Override
	public void realizar(Jugador jugador, Scanner sc) {
		boolean enInventario = true;

		while (enInventario) {
			MyUtil.limpiarConsola();
			jugador.mostrarEstadoJugador();

			System.out.println("[INVENTARIO] Opciones:");
			System.out.println("  /usar [N° item]    -> Consumir una poción o ítem usable.");
			System.out.println("  /equipar [N° item] -> Equipar un arma o armadura.");
			System.out.println("  /descripcion [N° item] -> Muesta la descripcion de un item");
			System.out.println("  /salir        -> Volver al menú principal.");
			System.out.print("> ");

			String input = sc.nextLine().toLowerCase().trim();

			if (input.equals("/salir") || input.equals("/s")) {
				enInventario = false;
				MyUtil.limpiarConsola();

			} else if (input.startsWith("/usar ") || input.startsWith("/u ")) {
				procesarItem(jugador, input, true);
				pausar(sc);

			} else if (input.startsWith("/equipar ") || input.startsWith("/e ")) {
				procesarItem(jugador, input, false); // false = equipar
				pausar(sc);

			} else if (input.startsWith("/descripcion") || input.startsWith("/d")) {
				imprimirDatos(jugador, input);
			} else {

				System.out.println("Comando invalido.");
				pausar(sc);
			}
		}
	}

	private void imprimirDatos(Jugador jugador, String input) {
		try {
			String[] partes = input.split(" ");
			if (partes.length < 2) {
				System.out.println("Debes escribir el número del ítem. Ej: /descripcion 1");
				return;
			}

			int indexVisual = Integer.parseInt(partes[1]);
			int indexReal = indexVisual - 1;

			ArrayList<Item> listaItems = jugador.getInventario().getInventario();

			if (indexReal >= 0 && indexReal < listaItems.size()) {
				Item item = listaItems.get(indexReal);
						System.out.println(item.getDescripcion());

			}
		} catch (NumberFormatException e) {
			System.out.println("Por favor, introduce un número válido.");
		} catch (Exception e) {
			System.out.println("Ocurrió un error inesperado: " + e.getMessage());
		}
		Scanner sc = new Scanner(System.in);
		pausar(sc);
	}

	private void procesarItem(Jugador jugador, String input, boolean esUsar) {
		try {
			String[] partes = input.split(" ");
			if (partes.length < 2) {
				System.out.println("Debes escribir el número del ítem. Ej: /usar 1");
				return;
			}

			int indexVisual = Integer.parseInt(partes[1]);
			int indexReal = indexVisual - 1;

			ArrayList<Item> listaItems = jugador.getInventario().getInventario();

			if (indexReal >= 0 && indexReal < listaItems.size()) {
				Item item = listaItems.get(indexReal);

				if (esUsar) {
					if (item instanceof IConsumible) {
						((IConsumible) item).alUsar(jugador);
						if (item.getCantidad() <= 0) {
							listaItems.remove(indexReal);
						}
					} else {
						System.out.println("El ítem '" + item.getNombre() + "' no se puede usar.");
					}

				} else {
					if (item instanceof IEquipable) {
						listaItems.remove(indexReal);
						jugador.equiparItem(item, 0);

						System.out.println("Has equipado: " + item.getNombre());

					} else {
						System.out.println("El ítem '" + item.getNombre() + "' no se puede equipar.");
					}
				}

			} else {
				System.out.println("El número " + indexVisual + " no corresponde a ningún ítem de tu inventario.");
			}

		} catch (NumberFormatException e) {
			System.out.println("Por favor, introduce un número válido.");
		} catch (Exception e) {
			System.out.println("Ocurrió un error inesperado: " + e.getMessage());
		}
	}

	private void pausar(Scanner sc) {
		System.out.println("\n[Presiona Enter para continuar]");
		sc.nextLine();
	}
}