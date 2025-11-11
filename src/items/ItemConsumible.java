package items;

public enum ItemConsumible {
	POCIONVIDA("Pocion Vida", 20, 0, 0);
	
	String nombre;
	int vida;
	int suerte;
	int multiplicador;
	
	ItemConsumible(String nombre, int vida, int suerte, int multiplicador){
		this.nombre = nombre;
		this.vida = vida;
		this.suerte = suerte;
		this.multiplicador = multiplicador;
	}
}
