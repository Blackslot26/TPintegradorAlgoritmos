package todo;

public enum TipoArma {
    // Define el constructor para cada tipo de arma
    DAGA("Daga", 20, 8),
    ESPADA("Espada", 50, 12),
    HACHA("Hacha", 100, 15),
    ARCO("Arco", 25, 10);

    // Los atributos que van a tener las armas
    private final String nombre;
    private final int precio;
    private final int danio;

    //Constructor del enum
    TipoArma(String nombre, int precio, int danio) {
        this.nombre = nombre;
        this.precio = precio;
        this.danio = danio;
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
}