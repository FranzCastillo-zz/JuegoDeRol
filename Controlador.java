import java.util.ArrayList;
import java.util.Random;


public class Controlador {
    private Vista v;
    private ArrayList<Enemigo> enemigos;
    private Jugador jugador;
    private ArrayList<Items> items;
    private int ronda = 1;
    
    private static int randomIntEntre(int min, int max) {
        Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min;
	}
    
    private void iniciarRonda(){
        int numEnemigos = randomIntEntre(1, 3);
        int probabilidadJefe = randomIntEntre(1, 100);
        v.enemigosCreados(numEnemigos);
        int i = 1;
        if(probabilidadJefe <= 15){
            v.jefeAparece();
            int vidaJefe = randomIntEntre(200, 300);
            int ataqueJefe = randomIntEntre(20, 25);
            enemigos.add(new Jefe("El Patron", vidaJefe, ataqueJefe));
            i++;
        }
        while(i <= numEnemigos){
            int vidaEnemigo = randomIntEntre(50, 150);
            int ataqueEnemigo = randomIntEntre(5, 15);
            enemigos.add(new Enemigo("Enemigo " + i, vidaEnemigo, ataqueEnemigo));
            i++;
        }
        for (Enemigo enemigo : enemigos) {
            v.mostrarEstadisticas(enemigo);
        }
        v.mostrarEstadisticasJugador(jugador);
    }

    private void jugarRonda(){

    }

    public void ejecutar(){
        v = new Vista();
        enemigos = new ArrayList<>();
        v.inicio();
        String nombre = v.pedirNombre();
        String clase = "";
        boolean claseValida = false;
        while(!claseValida){
            int numeroClase = v.pedirClase();
            if(numeroClase == 1){
                clase = "GUERRERO"; //AGREGAR ITEMS
                claseValida = true;
            }else if(numeroClase == 2){
                clase = "EXPLORADOR";
                claseValida = true;
            }else{
                v.claseInvalida();
            }
        }
        jugador = new Jugador(nombre, 150, 15, clase);
        v.mostrarPersonajeCreado(nombre);
        v.inicioRonda(ronda);
        iniciarRonda();
        v.mostrarEstadisticasJugador(jugador);
        jugarRonda();
    }
}
