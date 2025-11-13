package items;

import java.util.Random;

public class Desechable extends Item{
	private static final long serialVersionUID = 1L;
	
	public Desechable(ItemDesechable tipo, int cantidad) {
		super(tipo.getNombre(), tipo.getDescripcion(), valorAleatorio(tipo), cantidad);
	}
	
	private static int valorAleatorio(ItemDesechable tipo) {
		Random r = new Random();
        int min = tipo.getValorMin();
        int max = tipo.getValorMax();
        return r.nextInt(max - min + 1) + min;
	}

}
