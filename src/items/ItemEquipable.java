package items;

public enum ItemEquipable {
    // Define el constructor para cada tipo de arma
    DAGA("Daga ceremonial", 20000, 8, 0, 0, 0),
    ESPADA("Espada", 50000, 12, 0, 0, 0),
    HACHA("Hacha", 100000, 15, 0, 0, 0),
    ARCO("Arco", 25000, 10, 0, 0, 0);

    // Los atributos que van a tener las armas
    private final String nombre;
    private final int precio;
    private final int danio;
    private final int vida;
    private final int resistencia;
    private final int suerte;

    //Constructor del enum
    ItemEquipable(String nombre, int precio, int danio, int vida, int resistencia, int suerte) {
        this.nombre = nombre;
        this.precio = precio;
        this.danio = danio;
        this.vida = vida;
        this.resistencia = resistencia;
        this.suerte = suerte;
    }

    // Funciones para obtener los atributos fuera del enum
    public String getNombre() {
        return nombre;
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
    public int getResistencia() {
    	return resistencia;
    }
    public int getSuerte() {
    	return suerte;
    }
}