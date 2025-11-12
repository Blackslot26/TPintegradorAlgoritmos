package todo;
import utiles.Dibujos;
public enum TipoEnemigo {
	DUENDE("Duende verde",75,15,1800,10,Dibujos.DIBUJO_DUENDE),
	LOBO("Lobo Salvaje",125,25,3000,15,Dibujos.DIBUJO_LOBO),
	ARANIA("NYA tóxica",75,20,3000,20,Dibujos.DIBUJO_ARANIA),
	ESQUELETO("Puro hueso",100,30,3500,20,Dibujos.DIBUJO_ESQUELETO);

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
