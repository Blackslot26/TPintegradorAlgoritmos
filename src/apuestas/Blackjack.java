package apuestas;

import java.util.Scanner;

import todo.Accion;
import todo.Controlador;
import todo.Jugador;

public class Blackjack implements Accion{

	@Override
	public String getDescripcion() {
		return "Apuestas parte de tu dinero en una apuesta clasica de blackjack. Sintax: /blackjack [amount]";
	}

	@Override
	public void realizar(Jugador jugadorActual, Controlador controlador, Scanner sc) {
		System.out.println("Introduzca cuantas monedas desea apostar: ");
		System.out.println("Monedas: " + jugadorActual.getMonedas());
		sc = new Scanner(System.in);
		int apuesta = sc.nextInt();
		System.out.println("Esta segur@ de que desea apostar " + apuesta + " monedas? y/n");
		String conf = sc.nextLine();
		if(conf.equals("y")) {
			
		}else if(conf.equals("n")) {
			
		}
		
		sc.close();
	}

}
