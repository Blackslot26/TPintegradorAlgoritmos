package utiles;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clase estática que almacena todos los datos fijos del juego (listas de
 * palabras, comandos, etc.) para mantener la clase Functions limpia.
 */
public class DatosJuego {
	// Constructor privado para evitar que se instancie
	private DatosJuego() {
	}

	public static HashMap<String, String> aliasComandos = new HashMap<>();
	public static final ArrayList<Pregunta> preguntas = new ArrayList<>();
	
	// Bloque estático para inicializar listas
	static {
		//Inicializar los alias de comandos
		aliasComandos.put("/t","/trabajar");
		aliasComandos.put("/work","/trabajar");
		aliasComandos.put("/shop","/tienda");
		aliasComandos.put("/s","/salir");
		aliasComandos.put("/l", "/leaderboard");
		aliasComandos.put("/c","/comandos");
		aliasComandos.put("/j","/leaderboard");
		aliasComandos.put("/e","/explorar");
		aliasComandos.put("/cz", "/cazar");
		aliasComandos.put("/i", "/inventario");
		aliasComandos.put("/a", "/alias");
		aliasComandos.put("/bj", "/blackjack");
		aliasComandos.put("/help", "/comandos");


		preguntas.add(new Pregunta(
				"¿Cuál es la probabilidad de que, eligiendo al azar, respondas correctamente esta pregunta?",
				new String[] { "25%", "50%", "60%", "25%" }, 0, 8000));
		preguntas.add(new Pregunta("¿Con qué instrumento se asocia a Louis Armstrong?",
				new String[] { "Saxofón", "Trompeta", "Piano", "Clarinete" }, 1, 2000));
		preguntas.add(new Pregunta("¿En qué país se filmó 'El Juego del Calamar' (Squid Game)?",
				new String[] { "Japón", "China", "Corea del Sur", "Tailandia" }, 2, 1500));
		preguntas.add(new Pregunta("¿Cuál es el ingrediente principal del falafel?",
				new String[] { "Lentejas", "Garbanzos", "Habas", "Quinoa" }, 1, 2500));
		preguntas.add(new Pregunta("¿Cómo se llama el padre de Simba en 'El Rey León'?",
				new String[] { "Scar", "Mufasa", "Zazú", "Timón" }, 1, 1500));
		preguntas.add(new Pregunta("¿Quién es el amigo y compañero de Sherlock Holmes?",
				new String[] { "Watson", "Jackson", "Winston", "Lestrade" }, 0, 1500));
		preguntas.add(new Pregunta("¿De qué animal proviene la lana de Cachemira?",
				new String[] { "Oveja", "Llama", "Cabra", "Conejo de Angora" }, 2, 5000));
		preguntas.add(new Pregunta("¿Cuál es la capa superior de la piel?",
				new String[] { "Dermis", "Epidermis", "Hipodermis", "Endodermis" }, 1, 2500));
		preguntas.add(new Pregunta("¿Cuál de estos números NO es un número primo?",
				new String[] { "7", "11", "27", "13" }, 2, 2000));
		preguntas.add(new Pregunta("¿Cuál es el nombre de la espada mágica del Rey Arturo?",
				new String[] { "Tizona", "Durendal", "Excalibur", "Andúril" }, 2, 1500));
		preguntas.add(new Pregunta("¿En qué estado de EE. UU. se creó KFC?",
				new String[] { "Alabama", "Texas", "Georgia", "Kentucky" }, 3, 2000));
		preguntas.add(new Pregunta("¿Qué empresa comercializó el 'Walkman' en 1979?",
				new String[] { "Sony", "Philips", "Samsung", "Panasonic" }, 0, 4000));
		preguntas.add(new Pregunta("¿Qué le falta (visiblemente) al personaje Rayman?",
				new String[] { "Cabello", "Manos y pies", "Zapatos", "Brazos y piernas (conexiones)" }, 3, 4500));
		preguntas.add(new Pregunta("¿Cuál de estos materiales es el más duro?",
				new String[] { "Oro", "Marfil", "Diamante", "Acero" }, 2, 1500));
		preguntas.add(new Pregunta("¿Cuántas cuerdas tiene un ukelele estándar?",
				new String[] { "Seis", "Cuatro", "Cinco", "Ocho" }, 1, 2500));
		preguntas.add(new Pregunta("¿En qué videojuego Lara Croft es la personaje principal?",
				new String[] { "Resident Evil", "Tomb Raider", "Uncharted", "Metroid" }, 1, 1500));
		preguntas.add(new Pregunta("¿Quién esculpió la estatua de David?",
				new String[] { "Leonardo da Vinci", "Auguste Rodin", "Donatello", "Michelangelo" }, 3, 3000));
		preguntas.add(new Pregunta("¿En qué videojuego puedes encontrar un 'Creeper'?",
				new String[] { "Roblox", "Minecraft", "Fortnite", "Terraria" }, 1, 1500));
		preguntas.add(new Pregunta("¿Qué es un 'Stradivarius'?",
				new String[] { "Un tipo de queso", "Una pintura renacentista", "Un violín", "Un telescopio" }, 2,
				4500));
		preguntas.add(new Pregunta("¿Cuál es la capital de Perú?",
				new String[] { "Lima", "Quito", "Bogotá", "Santiago" }, 0, 1500));
		preguntas.add(new Pregunta("¿Qué partícula de un átomo no tiene carga eléctrica?",
				new String[] { "Protón", "Electrón", "Neutrón", "Quark" }, 2, 2000));
		preguntas.add(new Pregunta("¿Cuál es la quinta letra del alfabeto griego?",
				new String[] { "Gamma", "Delta", "Epsilon", "Zeta" }, 2, 7000));
		preguntas.add(new Pregunta("¿Quién pintó 'La noche estrellada'?",
				new String[] { "Monet", "Picasso", "Van Gogh", "Dalí" }, 2, 2000));
		preguntas.add(new Pregunta("¿En qué país se encuentra el Monte Fuji?",
				new String[] { "China", "Corea", "Japón", "Vietnam" }, 2, 1500));
		preguntas.add(new Pregunta("¿Cómo se llamaba la antigua ruta comercial entre China y Occidente?",
				new String[] { "Ruta de las Especias", "Ruta de la Seda", "Camino del Rey", "Ruta de Ámbar" }, 1,
				2500));
		preguntas.add(new Pregunta("¿Cuál de los siguientes azúcares se encuentra en la leche?",
				new String[] { "Fructosa", "Glucosa", "Sacarosa", "Lactosa" }, 3, 1500));
		preguntas.add(new Pregunta("¿Qué personaje vive en una piña debajo del mar?",
				new String[] { "Pikachu", "Bob Esponja", "Calamardo", "Arenita" }, 1, 1500));
		preguntas.add(new Pregunta("¿De qué serie de televisión son los personajes Eric, Kenny y Kyle?",
				new String[] { "Los Simpson", "Padre de Familia", "South Park", "Rick y Morty" }, 2, 2000));
		preguntas.add(new Pregunta("¿Qué continente tiene la mayor población?",
				new String[] { "África", "Asia", "Europa", "América" }, 1, 1500));
		preguntas
				.add(new Pregunta("¿Cuántos años dura un siglo?", new String[] { "10", "100", "1000", "50" }, 1, 1500));
		preguntas.add(new Pregunta("¿De qué banda de rock es el cantante Bono?",
				new String[] { "The Police", "R.E.M.", "U2", "Coldplay" }, 2, 3000));
		preguntas.add(new Pregunta("¿Qué color se debe agregar al azul para obtener el violeta?",
				new String[] { "Rojo", "Amarillo", "Verde", "Naranja" }, 0, 1500));
		preguntas.add(new Pregunta("¿Qué país es famoso por sus campos de tulipanes?",
				new String[] { "Suiza", "Países Bajos", "Bélgica", "Suecia" }, 1, 2000));
		preguntas.add(new Pregunta("¿De qué color son los icónicos taxis de Londres?",
				new String[] { "Amarillos", "Rojos", "Negros", "Blancos" }, 2, 2000));
		preguntas.add(new Pregunta("¿En qué película es Mowgli el personaje principal?",
				new String[] { "Tarzán", "El Rey León", "Aladdin", "El libro de la selva" }, 3, 1500));
		preguntas.add(new Pregunta("¿Las manzanas flotan en el agua?", new String[] { "Verdadero", "Falso" }, 0, 3500));
		preguntas.add(new Pregunta("¿Qué río atraviesa la ciudad de Nueva York?",
				new String[] { "Hudson", "Amazonas", "Colorado", "Mississippi" }, 0, 4000));
		preguntas.add(new Pregunta("¿Qué órgano humano puede regenerarse casi por completo?",
				new String[] { "Corazón", "Pulmón", "Hígado", "Cerebro" }, 2, 3000));
		preguntas.add(new Pregunta("¿Cuál es el baile tradicional del Carnaval de Río?",
				new String[] { "Samba", "Zumba", "Tango", "Salsa" }, 0, 2000));
		preguntas.add(new Pregunta("¿Quién pintó 'El Juicio Final' en la Capilla Sixtina?",
				new String[] { "Dalí", "Rafael", "Michelangelo", "Donatello" }, 2, 3500));
		preguntas.add(new Pregunta("¿En qué deporte se utiliza el ranking ATP?",
				new String[] { "Golf", "Tenis", "Fórmula 1", "Boxeo" }, 1, 2000));
		preguntas.add(new Pregunta("¿En qué ciudad se encuentra la playa de Copacabana?",
				new String[] { "Sao Paulo", "Río de Janeiro", "Buenos Aires", "Lisboa" }, 1, 2000));
		preguntas.add(new Pregunta("¿Cuántos dientes tiene un adulto humano (incluyendo muelas del juicio)?",
				new String[] { "28", "30", "32", "24" }, 2, 2500));
		preguntas.add(new Pregunta("¿Quién pintó 'Guernica'?",
				new String[] { "Salvador Dalí", "Pablo Picasso", "Joan Miró", "Francisco de Goya" }, 1, 3000));
		preguntas.add(new Pregunta("¿Cuántos corazones tiene un pulpo?",
				new String[] { "Uno", "Dos", "Tres", "Cuatro" }, 2, 4000));
		preguntas.add(new Pregunta("¿Cuál es la única letra que no aparece en el nombre de ningún estado de EE. UU.?",
				new String[] { "Z", "J", "X", "Q" }, 3, 10000));
		preguntas.add(new Pregunta("¿En qué país se originó el LEGO?",
				new String[] { "Suecia", "Alemania", "Dinamarca", "Noruega" }, 2, 3500));
		preguntas.add(new Pregunta("¿De qué color es la lengua de una jirafa?",
				new String[] { "Rosa", "Negra", "Roja", "Azul" }, 3, 5000));
		preguntas.add(new Pregunta("¿Cómo se llama el miedo a las palabras largas?",
				new String[] { "Aracnofobia", "Claustrofobia", "Hipopotomonstrosesquipedaliofobia", "Glosofobia" }, 2,
				6000));
		preguntas.add(new Pregunta("¿Cuál es el océano más grande del mundo?",
				new String[] { "Atlántico", "Índico", "Ártico", "Pacífico" }, 3, 1500));
		preguntas.add(new Pregunta("¿En qué año se hundió el Titanic?", new String[] { "1905", "1912", "1915", "1920" },
				1, 2000));
		preguntas.add(new Pregunta("¿Qué planeta es conocido como el 'Planeta Rojo'?",
				new String[] { "Júpiter", "Marte", "Venus", "Saturno" }, 1, 1500));
		preguntas.add(new Pregunta("¿Cuál es el animal terrestre más rápido?",
				new String[] { "León", "Guepardo", "Caballo", "Antílope" }, 1, 1500));
		preguntas.add(new Pregunta("¿Qué gas constituye la mayor parte de la atmósfera terrestre?",
				new String[] { "Oxígeno", "Dióxido de Carbono", "Argón", "Nitrógeno" }, 3, 3000));
		preguntas.add(new Pregunta("¿Qué artista es famoso por cortar parte de su propia oreja?",
				new String[] { "Picasso", "Dalí", "Van Gogh", "Monet" }, 2, 2000));
		preguntas.add(new Pregunta("¿Cuál es el nombre del villano principal en la saga original de 'Star Wars'?",
				new String[] { "Darth Vader", "Emperador Palpatine", "Darth Maul", "Boba Fett" }, 0, 1500));
		preguntas.add(new Pregunta("¿Qué compañía de videojuegos creó a Mario?",
				new String[] { "Sega", "Sony", "Microsoft", "Nintendo" }, 3, 1500));
		preguntas.add(new Pregunta("¿Cuál es el único mamífero capaz de volar?",
				new String[] { "Ardilla voladora", "Murciélago", "Colugo", "Petauro" }, 1, 2500));
		preguntas.add(new Pregunta("¿En qué ciudad se encuentra la 'Sagrada Familia' de Gaudí?",
				new String[] { "Madrid", "Roma", "París", "Barcelona" }, 3, 2000));
		preguntas.add(new Pregunta("¿Cuántos huesos tiene un tiburón?", new String[] { "206", "Cero", "150", "300" }, 1,
				6000));
		preguntas.add(new Pregunta("¿Qué significa la 'c' en E=mc²?",
				new String[] { "Carbono", "Velocidad de la luz", "Masa", "Energía" }, 1, 3000));
		preguntas.add(new Pregunta("¿Cuál es el nombre del perro en 'Padre de Familia' (Family Guy)?",
				new String[] { "Stewie", "Peter", "Brian", "Quagmire" }, 2, 1500));
		preguntas.add(new Pregunta("¿Qué país tiene forma de bota?",
				new String[] { "Grecia", "Italia", "España", "Japón" }, 1, 1500));
		preguntas.add(new Pregunta("¿En qué deporte Muhammad Ali era campeón mundial?",
				new String[] { "Boxeo", "Lucha Libre", "Karate", "Fútbol Americano" }, 0, 1500));
		preguntas.add(new Pregunta("¿Qué parte del cuerpo humano consume más oxígeno?",
				new String[] { "El corazón", "Los pulmones", "Los músculos", "El cerebro" }, 3, 5000));
		preguntas.add(new Pregunta("¿Qué bebida se obtiene de la planta 'Agave tequilana'?",
				new String[] { "Cerveza", "Vino", "Ron", "Tequila" }, 3, 2000));
		preguntas.add(new Pregunta("¿Cuál es el nombre del martillo de Thor?",
				new String[] { "Gungnir", "Mjölnir", "Stormbreaker", "Excalibur" }, 1, 2500));
		preguntas.add(new Pregunta("¿Cuál es el río más largo del mundo?",
				new String[] { "Nilo", "Amazonas", "Mississippi", "Yangtsé" }, 1, 3000));
		preguntas.add(new Pregunta("¿En qué año se lanzó el primer iPhone?",
				new String[] { "2005", "2007", "2008", "2010" }, 1, 3000));
		preguntas.add(new Pregunta("¿Qué país le regaló la Estatua de la Libertad a EE. UU.?",
				new String[] { "Reino Unido", "España", "Francia", "Alemania" }, 2, 1500));
		preguntas.add(new Pregunta("¿Cuál es el punto más profundo del océano?", new String[] { "Fosa de Puerto Rico",
				"Abismo Challenger", "Fosa de las Marianas", "Triángulo de las Bermudas" }, 2, 4000));
		preguntas.add(new Pregunta("¿Qué estudia un 'ictiólogo'?",
				new String[] { "Insectos", "Aves", "Peces", "Reptiles" }, 2, 7000));
		preguntas.add(
				new Pregunta("¿Cuántas zonas horarias tiene Rusia?", new String[] { "8", "9", "11", "13" }, 2, 6000));
		preguntas.add(new Pregunta("¿Cuál es el nombre real (Kryptoniano) de 'Superman'?",
				new String[] { "Clark Kent", "Bruce Wayne", "Kal-El", "Jor-El" }, 2, 2500));
		preguntas.add(
				new Pregunta("¿En qué juego de mesa puedes comprar 'Avenida Báltica' y 'Paseo Tablado' (Boardwalk)?",
						new String[] { "Risk", "Monopoly", "Clue", "Ajedrez" }, 1, 1500));
		preguntas.add(new Pregunta("¿Qué banda escribió la canción 'Bohemian Rhapsody'?",
				new String[] { "The Beatles", "Led Zeppelin", "Queen", "Pink Floyd" }, 2, 1500));
		preguntas.add(new Pregunta("¿Cuál es la moneda oficial de Japón?",
				new String[] { "Yuan", "Won", "Yen", "Dólar" }, 2, 1500));
		preguntas.add(new Pregunta("¿Cuántos lados tiene un 'dodecágono'?", new String[] { "10", "12", "14", "20" }, 1,
				4500));
		preguntas.add(new Pregunta("¿Qué es un 'sommelier'?", new String[] { "Un chef de postres",
				"Un experto en vinos", "Un tipo de hongo", "Un estilo de sombrero" }, 1, 3500));
		preguntas.add(new Pregunta("¿Qué animal es 'Speedy Gonzales'?",
				new String[] { "Coyote", "Correcaminos", "Conejo", "Ratón" }, 3, 1500));
		preguntas.add(new Pregunta("¿En qué país se inventó la pizza (como la conocemos hoy)?",
				new String[] { "Grecia", "Italia", "Estados Unidos", "Francia" }, 1, 1500));
		preguntas.add(new Pregunta("¿Cuál es el hueso más largo del cuerpo humano?",
				new String[] { "Tibia", "Húmero", "Fémur", "Peroné" }, 2, 2000));
		preguntas.add(new Pregunta("¿Qué significan las siglas 'NASA'?",
				new String[] { "Agencia Espacial de América del Norte",
						"Administración Nacional de Aeronáutica y el Espacio", "Asociación Nacional de Seguridad Aérea",
						"Agencia Secreta de Aviones Nacionales" },
				1, 3000));
		preguntas.add(new Pregunta("¿Qué pintor es conocido por sus relojes 'derretidos'?",
				new String[] { "Picasso", "Monet", "Frida Kahlo", "Salvador Dalí" }, 3, 2500));
	}
	
	public static final String[] comandos = {
			MyUtil.ANSI_GREEN + "/trabajar" + MyUtil.ANSI_RESET + " -> Realizas el trabajo de tu profesión para obtener algo de dinero",
			MyUtil.ANSI_GREEN + "/explorar" + MyUtil.ANSI_RESET + " -> Te encuentras con un evento aleatorio para obtener recompensas",
			MyUtil.ANSI_GREEN + "/cazar" + MyUtil.ANSI_RESET + " -> Te envuelves en un combate con una criatura a cambio de recompensas",
			MyUtil.ANSI_GREEN + "/blackjack" + MyUtil.ANSI_RESET + " -> apuestas una cantidad de dinero, juegas para duplicarla o perder todo",
			MyUtil.ANSI_GREEN + "/comandos" + MyUtil.ANSI_RESET + " -> Muestra la lista de comandos disponibles",
			MyUtil.ANSI_GREEN + "/leaderboard" + MyUtil.ANSI_RESET + " -> muestra el top global de jugadores de esta computadora",
			MyUtil.ANSI_GREEN + "/inventario" + MyUtil.ANSI_RESET + " -> Muestra las estadísticas del jugador y su inventario",
			MyUtil.ANSI_GREEN + "/alias" + MyUtil.ANSI_RESET + " -> Muestra la lista de abreviaciones de comandos"};

	
	public static final String[] comandosTienda = {
		    MyUtil.ANSI_GREEN + "/comprar [index]" + MyUtil.ANSI_RESET + " -> Comprar un Item específico",
		    MyUtil.ANSI_GREEN + "/buy [index]" + MyUtil.ANSI_RESET + " -> Comprar en inglés",
		    MyUtil.ANSI_GREEN + "/b [index]" + MyUtil.ANSI_RESET + " -> Comando corto para comprar",
		    MyUtil.ANSI_GREEN + "/salir" + MyUtil.ANSI_RESET + " -> Salir de la tienda",
		    MyUtil.ANSI_GREEN + "/comandos" + MyUtil.ANSI_RESET + " -> Listar los comandos"
		};


	public static final String[] palabrasAhorcado = {
			// Conceptos Positivos y de Bienestar
			"esperanza", "resiliencia", "calma", "serenidad", "alegria", "aceptacion", "gratitud", "animo", "valor",
			"fuerza", "paciencia", "progreso", "recuperar", "autocuidado", "apoyo", "conexion", "empatia", "respirar",
			"enfoque", "claridad", "liberar", "futuro", "bienestar", "optimismo", "proposito", "sanar", "presente",
			"conciencia", "mente", "positivo", "crecer", "meditar", "hablar", "sentir", "vivir", "equilibrio",
			"confianza", "motivacion",
			// Medicamentos
			"terapia", "psicologo", "psiquiatra", "dopamina", "serotonina", "cognitivo", "conductual", "neurologico",
			"isrs", "fluoxetina", "sertralina", "escitalopram", "citalopram", "paroxetina", "venlafaxina", "duloxetina",
			"mirtazapina", "bupropion", "trazodona", "amitriptilina", "nortriptilina", "ansiolitico", "antidepresivo",
			// Otras opciones
			"gambling", "apuestas", "alcohol", "methyl", "problemas","blackjack" };
}