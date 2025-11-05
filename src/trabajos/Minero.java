package trabajos;

import todo.Jugador;
import todo.TrabajarBase;

import java.util.Random;

public class Minero extends TrabajarBase {

	@Override
	public void trabajar(Jugador jugador) {
		Random r = new Random();
		double mineral = r.nextInt(100) + jugador.getSuerte();
		if (mineral < 40) { // encuentra carbon
			jugador.agregarDinero((int) (1000 + r.nextInt(1500) * (1 + jugador.getNivel() / 10))); // gana un maximo de
																									// 2500 por minar
																									// carbon, un 50% de
																									// la ganancia de
																									// leñador
		}
		if (mineral > 40 && mineral < 70) { // encuentra hierro
			jugador.agregarDinero((int) (2000 + r.nextInt(1750)) * (1 + jugador.getNivel() / 10)); // gana un maximo de
																									// 3750, un 75% de
																									// la ganancia de
																									// leñador
		}
		if (mineral > 70 && mineral < 90) { // encuentra piedra rojiza
			jugador.agregarDinero((int) (4000 + r.nextInt(2000)) * (1 + jugador.getNivel() / 10)); // puede ganar hasta
																									// un maximo de
																									// 6000, un poco mas
																									// que leñador
		}
		if (mineral > 90 && mineral < 99) { // encuentra diamante
			jugador.agregarDinero((int) (10000 + r.nextInt(5000)) * (1 + jugador.getNivel() / 10)); // puede ganar hasta
																									// un triple que
																									// leñador con la
																									// garantia de ganar
																									// el doble
		}
		if (mineral > 99) { // encuentra netherita
			jugador.agregarDinero((int) (250000 + r.nextInt(250000)) * (1 + jugador.getNivel() / 10)); // puede ganar
																										// hasta 500.000
																										// de dinero
		}
	}

	@Override
	public String getNombreBase() {
		String a = "Minero";
		return a;
	}

	@Override
	public String[] getDescriptionBase() {
		String[] a = {
				"Desciendes a las profundidades de la tierra, adentrandote en grutas desconocidas,",
				"golpeando la veta en busca de gemas y metales preciosos.",
				"La elección perfecta para el jugador paciente que pone su fe en la fortuna.",
				"Tus ganancias son erráticas; la mayoría de los días apenas cubres gastos a base de carbon.",
				"Sin embargo,cuentas con la probabilidad de hallar \"NETHERITA\",",
				"una gema de valor incalculable que llenara tus bolsillos por el resto de tus dias." };
		return a;

	}

	@Override
	public double multiplicadorVenta(double precioBase, Jugador jugador) {
		// TODO Auto-generated method stub
		return precioBase * 0.5;
	}

	@Override
	public double multiplicadorGanancias(double gananciaBase, Jugador jugador) {
		// TODO Auto-generated method stub
		return gananciaBase;
	}

}
