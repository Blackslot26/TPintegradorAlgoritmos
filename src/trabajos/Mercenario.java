package trabajos;

import todo.Jugador;
import todo.Trabajar;
import java.util.Random;

public class Mercenario implements Trabajar{

	@Override
	public void trabajar(Jugador jugador) {
		Random r = new Random();
		if(Jugador.getNivel() < 15) Jugador.agregarDinero((10000 + r.nextInt(5000)) - (Jugador.getNivel()/3) * 1500);  // gana mucho y sin riesgo pero cada 3 niveles gana 1500 menos.
		if(Jugador.getNivel() > 15) Jugador.agregarDinero(1000 + r.nextInt(1000)); // a partir del nivel 15 se alcanzan los menores ingresos.
		
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return "Mercenario";
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Eres una espada de alquiler, un prodigio del combate cuya fama te precede. "
		   	 + "Los lores y reyes pagan sumas exorbitantes por tus servicios... por ahora."
		   	 + " \\n La clase perfecta para un arranque explosivo o para dominar el juego temprano."
		   	 + " \\n Tus contratos iniciales te otorgan ganancias inmensas, permitiéndote amasar una fortuna rápidamente."
		   	 + " Sin embargo, tu fama se estanca y tus habilidades se vuelven comunes; tus contratos serán cada vez menos lucrativos a medida que el mundo avanza y tu leyenda decae.";
	}

	@Override
	public double multiplicadorVenta(double precioBase) {
		// TODO Auto-generated method stub
		return precioBase * 0.5;
	}

	@Override
	public double multiplicadorGanancias(double gananciaBase) {
		// TODO Auto-generated method stub
		return gananciaBase;
	}

}
