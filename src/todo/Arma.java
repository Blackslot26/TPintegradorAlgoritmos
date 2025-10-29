package todo;

public class Arma extends Item {
	// Definir los tipos de armas
	public enum tipoArma {
		daga, espada, hacha, arco;
	}
	
	
	//Atributos de las armas
	private int danio;
	private tipoArma tipo;

	Arma(tipoArma tipo) {
		this.tipo = tipo;
		danio = calcularDanioTotal();
	}
	
	private int calcularDanioTotal(){
		int danioTotal = 0;
		switch (this.tipo){
			
		case daga :
			nombre = "Daga";
			danioTotal = 8;
			break;
		
		case espada:
			nombre = "Espada";
			danioTotal= 12;
			break;
			
		case hacha:
			nombre = "Hacha";
			danioTotal = 15;
			
		case arco:
			nombre = "Arco";
			danioTotal = 10;
			
		}
		
		return danioTotal;
	}
	
	public void atacarMonstruo(Monstruo monstruo) {
		monstruo.vida -= danio;
	}

}
