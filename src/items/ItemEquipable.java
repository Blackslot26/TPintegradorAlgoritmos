package items;

public enum ItemEquipable {
    // Define el constructor para cada tipo de arma
	// NOMBRE, DESCRIPCION, PRECIO, DAÑO, VIDA, DEFENSA, SUERTE, ¿SE VENDE?
    DAGACEREMONIAL("Daga ceremonial", "Daga utilizada en rituales. Aumenta el daño y la suerte", 20000, 20, 0, 0, 0.5, true),  //daño promedio y aumenta la suerte que es una buena stat
	ESCUDODEHIELOPURO("Escudo de hielo puro", "Escudo forjado con hielo magico. Aumenta todas las estadisticas defensivas", 30000, 5, 40, 0.75, 0, true), //da un poco de todo
	GUANTELETESDELATLAS("Guanteletes del Atlas", "Guantes tecnologicos potenciados por magia runica. Aumentan todas las estadisticas.", 40000, 20, 30, 0.15, 0.5, true);
	
    

    // Los atributos que van a tener las armas
    private final String nombre;
	private final String descripcion;
    private final int precio;
    private final int danio;
    private final int vida;
    private final double defensa;
    private final double suerte;
    private final boolean seVende;

    //Constructor del enum
    ItemEquipable(String nombre, String descripcion, int precio, int danio, int vida, double defensa, double suerte, boolean seVende) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.danio = danio;
        this.vida = vida;
        this.defensa = defensa;
        this.suerte = suerte;
        this.seVende = seVende;
    }

    // Funciones para obtener los atributos fuera del enum
    public String getNombre() {
        return nombre;
    }
    public String getDescripcion() {
    	return descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public int getDanio() {
        return danio;
    }
    public int getVida() {
    	return vida;
    }
    public double getDefensa() {
    	return defensa;
    }
    public double getSuerte() {
    	return suerte;
    }
    public boolean seVende() {
    	return seVende;
    }
}