package todo;
import utiles.Dibujos;
public enum TipoEnemigo {
	DUENDE("Duende verde",35,15,1800,10,Dibujos.dibujoDuende);
//	LOBO("Lobo Salvaje",75,25,3000,15),
//	ARANIA("NYA tóxica",50,20,3000,20),
//	ESQUELETO("Puro hueso",40,30,3500,20);

	private final String nombre;
	private final int vidaBase;
	private final int danioBase;
	private final int recompensaBase;
	private final int recompensaBaseXp;
	private final String[] dibujo;

	TipoEnemigo(String nombre,int vidaBase,int danioBase, int recompensaBase, int recompensaBaseXP,String[] dibujo) {
		this.nombre = nombre;
		this.vidaBase = vidaBase;
		this.danioBase= danioBase;
		this.recompensaBase = recompensaBase;
		this.recompensaBaseXp = recompensaBaseXP;
		this.dibujo = dibujo;
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
