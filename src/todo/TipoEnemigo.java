package todo;
import utiles.Dibujos;
// Importa tus enums de items
import items.ItemDesechable;
import items.ItemEquipable;
import items.ItemConsumible;

public enum TipoEnemigo {
	// 1. Definir los drops y sus chances
	DUENDE("Duende verde",75,15,1800,10,Dibujos.DIBUJO_DUENDE,
			new Enum<?>[] { ItemConsumible.MANZANA }, // Loot
			new double[] { 0.3 }), // Chance (30% de dropear Manzana)
	
	LOBO("Lobo Salvaje",125,25,3000,15,Dibujos.DIBUJO_LOBO,
			new Enum<?>[] { ItemDesechable.PIELLOBO, ItemConsumible.CARNEHUMANA }, // Loot
			new double[] { 0.5, 0.1 }), // Chance (50% Piel, 10% Carne)

	ARANIA("NYA tóxica",75,20,3000,20,Dibujos.DIBUJO_ARANIA,
			new Enum<?>[] { ItemDesechable.COLMILLOVENENOSO }, // Loot: Colmillo
			new double[] { 0.25 }), // Chance: 25% de dropear el colmillo
	
	ESQUELETO("Puro hueso",100,30,3500,20,Dibujos.DIBUJO_ESQUELETO,
			new Enum<?>[] { ItemDesechable.POLVODEHUESO, ItemEquipable.ESPADAROTA }, // Loot: Polvo y Espada
			new double[] { 0.40, 0.05 }), // Chance: 40% Polvo, 5% Espada Rota
	
	DRAGON("Dragón Antiguo",300,50,6000,50,Dibujos.DIBUJO_DRAGON,
			new Enum<?>[] {ItemDesechable.ESCAMASDEDRAGON,ItemConsumible.MANZANADORADA,ItemEquipable.GUANTELETESDELATLAS,ItemDesechable.DIENTE_DRAGON},
			new double[] {0.1,0.2,0.2,0.5});

	private final String nombre;
	private final int vidaBase;
	private final int danioBase;
	private final int recompensaBase;
	private final int recompensaBaseXp;
	private final String[] dibujo;
	private final Enum<?>[] plantillaLoot;
	private final double[] lootChances;

	TipoEnemigo(String nombre,int vidaBase,int danioBase, int recompensaBase, int recompensaBaseXP,String[] dibujo,
				Enum<?>[] lootTemplates, double[] lootChances) {
		this.nombre = nombre;
		this.vidaBase = vidaBase;
		this.danioBase= danioBase;
		this.recompensaBase = recompensaBase;
		this.recompensaBaseXp = recompensaBaseXP;
		this.dibujo = dibujo;
		this.plantillaLoot = lootTemplates;
		this.lootChances = lootChances;
	}
	
	//Getters para el loot
	public Enum<?>[] getPlantillaLoot() {
		return plantillaLoot;
	}
	public double[] getLootChances() {
		return lootChances;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int  getVidaBase() {
		return vidaBase;
	}
	
	public int  getDanioBase() {
		return danioBase;
	}
	
	public int  getRecompensaBase() {
		return recompensaBase;
	}
	public int  getRecompensaBaseXp() {
		return recompensaBaseXp;
	}
	public String[] getDibujo() {
		return dibujo;
	}
}
