package trabajos;

import acciones.Accion;
import acciones.Trabajar;
import todo.Jugador;
import utiles.MyUtil;

import java.util.Random;
import java.util.Scanner;

public class Minero extends Trabajar implements Accion{
	private static final long serialVersionUID = 1L;
	double mineral;
	@Override
	public void realizar(Jugador jugador, Scanner sc) {
		Random r = new Random();
		double mineral = r.nextInt(101) + jugador.getSuerte();
		this.mineral = mineral;
		if (mineral < 40) { // encuentra carbon
			int ganancia = (int) (1000 + r.nextInt(1500) * (1 + jugador.getNivel() / 10));
			jugador.modMonedas(ganancia); // gana un maximo de
			int event = r.nextInt(trabajarTextsBase(ganancia).length); //////////
			int xp = 10 + r.nextInt(20);
			jugador.modExp(xp);
			System.out.println(trabajarTexts(event, ganancia, jugador, xp));																						// 2500 por minar
																									// carbon, un 50% de
																									// la ganancia de
																									// leñador
		}
		if (mineral > 40 && mineral < 70) { // encuentra hierro
			int ganancia = (int) (2000 + r.nextInt(1750)) * (1 + jugador.getNivel() / 10);
			jugador.modMonedas(ganancia); // gana un maximo de
			int event = r.nextInt(trabajarTextsBase(ganancia).length); //////////
			int xp = 10 + r.nextInt(20);
			jugador.modExp(xp);
			System.out.println(trabajarTexts(event, ganancia, jugador, xp));																						// 3750, un 75% de
																									// la ganancia de
																									// leñador
		}
		if (mineral > 70 && mineral < 90) { // encuentra piedra rojiza
			int ganancia = (int) (4000 + r.nextInt(2000)) * (1 + jugador.getNivel() / 10);
			jugador.modMonedas(ganancia); // puede ganar hasta
			int event = r.nextInt(trabajarTextsBase(ganancia).length); //////////
			int xp = 10 + r.nextInt(20);
			jugador.modExp(xp);
			System.out.println(trabajarTexts(event, ganancia, jugador, xp));																						// un maximo de
																									// 6000, un poco mas
																									// que leñador
		}
		if (mineral > 90 && mineral < 99) { // encuentra diamante
			int ganancia = (int) (10000 + r.nextInt(5000)) * (1 + jugador.getNivel() / 10);
			jugador.modMonedas(ganancia); // puede ganar hasta
			int event = r.nextInt(trabajarTextsBase(ganancia).length); //////////
			int xp = 10 + r.nextInt(20);
			jugador.modExp(xp);
			System.out.println(trabajarTexts(event, ganancia, jugador, xp));																						// un triple que
																									// leñador con la
																									// garantia de ganar
																									// el doble
		}
		if (mineral > 99) { // encuentra netherita
			int ganancia = (int) (250000 + r.nextInt(250000)) * (1 + jugador.getNivel() / 10);
			jugador.modMonedas(ganancia); // puede ganar
			int event = r.nextInt(trabajarTextsBase(ganancia).length); //////////
			int xp = 10 + r.nextInt(20);
			jugador.modExp(xp);
			System.out.println(trabajarTexts(event, ganancia, jugador, xp));																							// hasta 500.000
																										// de dinero
		}
		MyUtil.marco("Enter para continuar");
		sc.nextLine();
	}
	
	@Override
	public String[] trabajarTextsBase(int ganancia) {
		String[] carbon = {
			"Picas todo el día y solo sacas carbón. Bueno, al menos ganas " + ganancia + " monedas.",
			"Estás cubierto de hollín, pero tu saco pesa. Vendes el carbón por " + ganancia + " monedas.",
			"Un día aburrido picando piedra negra. Ganas " + ganancia + " monedas."
		};
		String[] hierro = {
			"¡Hierro! Una veta decente. El herrero te pagará " + ganancia + " monedas por esto.",
			"Casi te aplasta un derrumbe, pero encuentras hierro. Ganas " + ganancia + " monedas.",
			"Ignoras a los murciélagos y picas una buena veta de hierro. Ganas " + ganancia + " monedas."
		};
		String[] redstone = {
			"¡Una piedra brillante! Los magos la aman. Ganas " + ganancia + " monedas.",
			"La veta pulsa con energía extraña. Vendes la piedra rojiza por " + ganancia + " monedas.",
			"Picas un polvo rojo que te hace estornudar. ¡Vale " + ganancia + " monedas!"
		};
		String[] diamante = {
			"¡Brilla! ¡Encontraste un diamante enorme! ¡Ganas " + ganancia + " monedas!",
			"¡RICOS! ¡Un diamante perfecto! El gremio de joyeros te paga " + ganancia + " monedas.",
			"Casi lo confundes con un trozo de cristal, ¡pero es un diamante! Ganas " + ganancia + " monedas."
		};
		String[] netherite = {
			"¡¿QUÉ ES ESTO?! ¡Encuentras un metal oscuro que absorbe la luz! ¡Vale " + ganancia + " monedas!",
			"¡Has encontrado el material de los dioses! ¡Te haces asquerosamente rico con " + ganancia + " monedas!",
			"La veta sangra oscuridad... y te da " + ganancia + " monedas. ¡INCREÍBLE!"
		};
		
		if(mineral < 40) return carbon;
		if(mineral > 40 && mineral < 70) return hierro;
		if(mineral > 70 && mineral < 90) return redstone;
		if(mineral > 90 && mineral < 99) return diamante;
		if(mineral > 99) return netherite;
		
		return carbon;
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
