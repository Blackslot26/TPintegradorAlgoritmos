package items;

import todo.Jugador;
public class Consumible extends Item implements IConsumible{
	private static final long serialVersionUID = 1L;
	
	ItemConsumible tipo;
	
	Consumible(ItemConsumible tipo, int cantidad) {
		super(tipo.getNombre(), tipo.getDescripcion(), tipo.getPrecio(), cantidad);
		this.tipo = tipo;
	}

	@Override
	public void alUsar(Jugador jugador) {
		System.out.println("Usaste ");
		jugador.modVida(tipo.getCuracion());
		jugador.modSuerte(tipo.getSuerte());
		jugador.modMultGanancia(tipo.getMultiplicador());	//pocion de aumento de ganancias (apuestas, etc)
		jugador.modMultVenta(tipo.getMultiplicador());		//pocion de aumento de venta
		this.cantidad--;
	}

}
