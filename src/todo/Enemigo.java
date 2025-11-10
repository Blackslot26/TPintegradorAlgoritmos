package todo;
//import java.util.ArrayList;
import java.util.Random;
public class Enemigo extends Personaje{
	private static final long serialVersionUID = 1L;
	
	private static Random ran ;
	private int recompensa;
	private int recompensaXP;
	private final String[] dibujo;
	//private ArrayList<Item> loot;
	
	public Enemigo(TipoEnemigo tipo){
		//Constructo de personaje
		super(tipo.getNombre(),tipo.getVidaBase(),tipo.getDanioBase());
		
		//Hasta un 50% más de la recompensa base
		this.recompensa = tipo.getRecompensaBase() + ran.nextInt(tipo.getRecompensaBase()/2); 
		this.recompensaXP = tipo.getRecompensaBaseXp() + ran.nextInt(tipo.getRecompensaBaseXp()/2);
		
		//Para dibujar el enemigo;
		dibujo = tipo.getDibujo();
	}
	
	public int getRecompensa() {
		return recompensa;
	}
	
	public int getRecompensaXS() {
		return recompensaXP;
	}
	
	public String[] getDibujo() {
		return dibujo;
	}
	
}
