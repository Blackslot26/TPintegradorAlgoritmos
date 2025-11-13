package apuestasResources;

public class Carta {

	private Palo palo;
	private Valores valor;

	public Carta(Palo palo, Valores valor) {
		this.palo = palo;
		this.valor = valor;
	}

	public int getPuntaje() {
		return valor.getValorNumerico();
	}

	public boolean esAs() {
		return valor == Valores.AS;
	}
	
	public String[] getArte() {
		int largoCarta = 7;
		String[] arteCarta = {
		" _______ ",
		"|"+valor+ " ".repeat(largoCarta - (valor+"").length()) + "|",
		"|       |",
		"|   "+palo+"   |",
		"|       |",
		"|"+"_".repeat(largoCarta-(valor+"").length())+valor+"|"};
		return arteCarta;
	}
}
