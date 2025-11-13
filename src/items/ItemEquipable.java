package items;

public enum ItemEquipable {
    // Define el constructor para cada tipo de arma
    DAGACEREMONIAL("Daga ceremonial", "Daga utilizada en rituales. Aumenta el daño y la suerte", 20000, 20, 0, 0, 0.5, true),  //daño promedio y aumenta la suerte que es una buena stat
	ESCUDODEHIELOPURO("Escudo de hielo puro", "Escudo forjado con hielo magico. Aumenta todas las estadisticas defensivas", 30000, 5, 40, 0.75, 0, true), //da un poco de todo
	GUANTELETESDELATLAS("Guanteletes del Atlas", "Guantes tecnologicos potenciados por magia runica. Aumentan todas las estadisticas.", 40000, 20, 30, 0.15, 0.5, false),
	ESPADAROTA("Espada Rota", "Una espada vieja y oxidada. Apenas mejor que nada.", 5000, 10, 0, 0, 0.1, true),
	CORAZADEHIERRO("Coraza de Hierro", "Una pesada armadura de placas. Ofrece gran protección.", 25000, 0, 50, 0.25, 0.0, true),
    ANILLODEVIDA("Anillo de Vitalidad", "Un simple anillo de rubí que aumenta la vitalidad.", 18000, 0, 25, 0.0, 0.0, true),
    COLGANTEDEFURIA("Colgante de Furia", "Un amuleto rojo que late con poder. Aumenta mucho el daño, pero te hace frágil.", 28000, 30, -10, -0.05, 0.0, true),
    ANILLODETAHUR("Anillo de Tahúr", "Un anillo gastado que brilla levemente. Aumenta mucho la suerte.", 35000, 0, 0, 0.0, 0.75, true),
    TREBOLSECO("Trébol de cuatro hojas seco", "Un amuleto de suerte común, pero efectivo.", 12000, 0, 0, 0.0, 0.3, true),
    BOTASDEVIAJERO("Botas de Viajero", "Botas ligeras que ofrecen un equilibrio de estadísticas.", 15000, 5, 10, 0.05, 0.1, true);

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