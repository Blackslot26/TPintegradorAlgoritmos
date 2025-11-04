package todo;

public class Arma extends Item {
	private static final long serialVersionUID = 1L;

	//Atributos de las armas
	private int danio;
	private TipoArma tipo;

	Arma(TipoArma tipo) {
		// Asigna los valores dependiendo del tipo
        this.nombre = tipo.getNombre();
        this.precio = tipo.getPrecio();
        this.danio = tipo.getDanio();
        this.tipo = tipo;
	}
	
	public void atacarMonstruo(Monstruo monstruo) {
		monstruo.vidaActual -= danio;
	}

}
