package acciones;

import java.util.Random;
import java.util.Scanner;

import todo.Controlador;
import todo.Enemigo;
import todo.Jugador;
import todo.TipoEnemigo;

public class Cazar implements Accion {
	String descripcion = "Te encuentras con un enemigo aleatorio, deberás derrotarlo para obtener recompensas";
	private static final Random ran = new Random();
	
	public Cazar() {
	}
	@Override
	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public void realizar(Jugador jugador, Controlador controlador, Scanner sc) {
		controlador.limpiarConsola();
		//Elegir el enemigo
		TipoEnemigo tipoEnemigo = elegirEnemigoAleatorio();
		Enemigo enemigo = new Enemigo(tipoEnemigo);
		
		boolean cazando = true;
		String input="";
		
		
		while(cazando) {
			dibujarEnemigo(enemigo);
			input = sc.nextLine().toLowerCase().trim();
		}
	}
	
	private void dibujarEnemigo(Enemigo enemigo) {
		for(String linea : enemigo.getDibujo()) {
			System.out.println(linea);
		}
		
	}
	private TipoEnemigo elegirEnemigoAleatorio() {
	    // Obtiene un array de todos los tipos de enemigos
	    TipoEnemigo[] tipos = TipoEnemigo.values();
	    
	    // Selecciona un índice aleatorio
	    int indice = ran.nextInt(tipos.length);
	    
	    return tipos[indice];
	}

}
