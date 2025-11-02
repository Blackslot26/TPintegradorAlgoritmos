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
		iniciarArma();
	}
	
	private void iniciarArma(){
		switch (this.tipo){
			
		case daga :
			nombre = "Daga";
			precio = 20;
			danio = 8;
			break;
		
		case espada:
			nombre = "Espada";
			precio = 50;
			danio= 12;
			break;
			
		case hacha:
			nombre = "Hacha";
			precio = 100;
			danio = 15;
			break;
			
		case arco:
			nombre = "Arco";
			precio = 25;
			danio = 10;
			break;
			
		}
	}
	
	public void atacarMonstruo(Monstruo monstruo) {
		monstruo.vida -= danio;
	}

}
