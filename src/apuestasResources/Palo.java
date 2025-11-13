package apuestasResources;

import utiles.MyUtil;

public enum Palo {
	
	CORAZONES(MyUtil.ANSI_RED + "♥" + MyUtil.ANSI_RESET),
	DIAMANTES(MyUtil.ANSI_RED + "♦" + MyUtil.ANSI_RESET),
	TREBOLES(MyUtil.ANSI_BLUE + "♣" + MyUtil.ANSI_RESET),
	ESPADAS(MyUtil.ANSI_BLUE + "♠" + MyUtil.ANSI_RESET);
	
	private String simbolo;

	Palo(String simbolo) {
		this.simbolo = simbolo;
	}

	public String toString() {
		return simbolo;
	}
}
