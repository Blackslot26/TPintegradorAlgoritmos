package todo;
//import java.util.ArrayList;
import java.util.Random;
/**
 * Representa a un oponente controlado por la IA.
 * <p>
 * Se instancia basándose en un {@link TipoEnemigo}, del cual hereda sus estadísticas
 * base y tabla de botín. Aplica una variación aleatoria a las recompensas
 * para que no sean siempre idénticas.
 * </p>
 */
public class Enemigo extends Personaje{
	private static final long serialVersionUID = 1L;
	
	private static Random ran = new Random();
	private int recompensa;
	private int recompensaXP;
	private final String[] dibujo;
	private final Enum<?>[] plantillaLoot;
	private final double[] lootChances;

	/**
	 * Crea un enemigo nuevo basado en una plantilla.
	 * Calcula las recompensas finales con una variación aleatoria.
	 * * @param tipo El Enum que define la plantilla del enemigo (ej: LOBO, DRAGON).
	 */
	public Enemigo(TipoEnemigo tipo){
		
		//Constructo de personaje
		super(tipo.getNombre(),tipo.getVidaBase(),tipo.getDanioBase());
		
		//Hasta un 50% más de la recompensa base
		this.recompensa = tipo.getRecompensaBase() + ran.nextInt(tipo.getRecompensaBase()/2); 
		this.recompensaXP = tipo.getRecompensaBaseXp() + ran.nextInt(tipo.getRecompensaBaseXp()/2);
		
		//Para dibujar el enemigo;
		dibujo = tipo.getDibujo();
		
		//Para el loot
		this.plantillaLoot = tipo.getPlantillaLoot();
		this.lootChances = tipo.getLootChances();
	}
	
	public int getRecompensa() {
		return recompensa;
	}
	
	public int getRecompensaXp() {
		return recompensaXP;
	}
	public Enum<?>[] getPlantillaLoot() {
	    return plantillaLoot;
	}
	public double[] getLootChances() {
	    return lootChances;
	}
	public String[] getDibujo() {
		return dibujo;
	}
	
	
}
