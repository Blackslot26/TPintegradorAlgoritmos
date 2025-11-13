package items;

import java.util.Random;

public class Desechable extends Item{
	private static final long serialVersionUID = 1L;
	ItemDesechable tipo;
	
	Desechable(ItemDesechable tipo, int cantidad) {
		super(tipo.getNombre(), tipo.getDescripcion(), valorAleatorio(tipo), cantidad);
		this.tipo = tipo;
	}
	
	private static int valorAleatorio(ItemDesechable tipo) {
		Random r = new Random();
        int min = tipo.getValorMin();
        int max = tipo.getValorMax();
        return r.nextInt(max - min + 1) + min;
	}
	
	public ItemDesechable getTipo() {
		return tipo;
	}

}
