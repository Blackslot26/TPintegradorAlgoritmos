package acciones;

import java.util.Scanner;

import todo.Jugador;
import utiles.MyUtil;
/**
 * Acción especial que permite reiniciar el progreso del juego a cambio de multiplicadores permanentes.
 * Mecánica de "Prestigio" o "New Game+".
 */
public class AccionRebirth implements Accion{

	@Override
	public void realizar(Jugador jugadorActual, Scanner sc) {
		// Calcula el coste incremental basado en cuántos rebirths ya tiene
		int costeRebirth = 1000000 + (500000 * jugadorActual.getRebirth());
		String[] rebirth = {"Deseas renacer para auemntar tus multiplicadores permanentemente?",
							"ATENCION: Esto eliminara todo tu progreso actual!!.",
							"Coste: " + costeRebirth,
							"Rebirths actuales: " + jugadorActual.getRebirth()};
		MyUtil.marco(rebirth);
		
		
		while(true) {
			System.out.println("Escribe"+ MyUtil.ANSI_GREEN + "YES" + MyUtil.ANSI_RESET + "para confirmar." + "Escribe"+ MyUtil.ANSI_RED + "NO" + MyUtil.ANSI_RESET + "para cancelar.");
			String confirmacion = sc.nextLine().toLowerCase();
			if(confirmacion.equals("yes") || confirmacion.equals("si")) {
				if(jugadorActual.getMonedas() >= costeRebirth) {
					MyUtil.marco("Renaciendo....");
					jugadorActual.realizarRebirth();
					MyUtil.marco("¡Has renacido! Renacimientos actuales: " + jugadorActual.getRebirth());
					System.out.println("[ Presiona enter para continuar ] ");
					sc.nextLine();
					
				}else {
					MyUtil.marco("No tienes suficientes monedas");
				}
			}else if (confirmacion.equals("no")) {
				break;
			}else {
				System.out.println("Comando incorrecto.");
			}
			
		}
		
	}

}
