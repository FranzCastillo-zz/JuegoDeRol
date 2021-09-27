import java.util.ArrayList;
import java.util.Random;


public class Controlador {
    private Vista v;
    private ArrayList<Enemigo> enemigos;
    private Jugador jugador;
    private ArrayList<Items> items;
    private int ronda = 1;
    private int oleada = 1;
    
    private static int randomIntEntre(int min, int max) {
        Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min;
	}
    
    private void mostrarEnemigos(){
        for (Enemigo enemigo : enemigos) {
            v.mostrarEstadisticas(enemigo);
        }
    }
    private void crearNuevosEnemigos(){
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
    }

    private void atacar(){
        int numObjetivo = -1;
        boolean objetivoValido = false;
        while(!objetivoValido){
            numObjetivo = v.pedirObjetivo(enemigos) - 1; // Devuelve una posicion adelante
            if(numObjetivo >= 0 && numObjetivo < enemigos.size()){
                objetivoValido = true;
            }else{
                v.opcionInvalida();
            }
        }
        Enemigo objetivo = enemigos.get(numObjetivo); 
        int ataque = jugador.getAtaque();
        jugador.atacar(objetivo, ataque);
        v.mostrarRecibioDanio(objetivo, ataque);
        if(objetivo.getVida() <= 0){ //Se muere
            enemigos.remove(numObjetivo);
            v.mostrarMuerte(objetivo.getMuerte());
        }
    }
    
    private void jugarRonda(){
        v.inicioRonda(ronda, oleada);
        mostrarEnemigos();
        v.mostrarElTurnoDe(jugador);
        v.mostrarEstadisticasJugador(jugador);
        // TURNO DEL JUGADOR
        boolean opcionValida = false;
        while(!opcionValida){
            int opcion = v.mostrarMenuJugador();
            switch(opcion){
                case 1: // ATACAR
                    atacar();
                    opcionValida = true;
                break;
                case 2: // VER ITEMS
                
                opcionValida = true;
                break;
                case 3: // USAR ITEM
                
                opcionValida = true;
                break;
                case 4: // SALTAR TURNO
                
                opcionValida = true;
                break;
                case 5: // SALIR
                    System.exit(1);
                    opcionValida = true;
                break;
                default:
                    v.opcionInvalida();
                break;
            }
        }

        //TURNO DE LOS ENEMIGOS

        //Fin de ronda
        ronda++;
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
                v.opcionInvalida();
            }
        }
        jugador = new Jugador(nombre, 150, 15, clase);
        v.mostrarPersonajeCreado(nombre);
        crearNuevosEnemigos();
        do{
            if(enemigos.isEmpty()){
                crearNuevosEnemigos();
                v.mostrarSiguienteOleada(oleada);
                oleada++;
            }
            jugarRonda();
        }while(jugador.getVida() > 0);
    }
}
