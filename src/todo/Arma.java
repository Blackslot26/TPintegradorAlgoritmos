package todo;

public class Arma extends Item {
	private static final long serialVersionUID = 1L;

	//Atributos de las armas
	private int danio;
	//private TipoArma tipo;

	public Arma(TipoArma tipo,int cantidad) {
		//Asignar los valores dependiendo del tipo
		super(tipo.getNombre(),tipo.getPrecio(),cantidad);
        this.danio = tipo.getDanio();
        //this.tipo = tipo;
	}
	
	public void atacarMonstruo(Enemigo monstruo) {
		monstruo.modVida(danio) ;
	}

}
