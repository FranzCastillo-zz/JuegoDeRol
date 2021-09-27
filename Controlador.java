import java.util.ArrayList;
import java.util.Random;

public class Controlador {
    private Vista v;
    private ArrayList<Enemigo> enemigos;
    private Jugador jugador;
    private ArrayList<Items> items;
    private ArrayList<String> historial;
    private String ultimoTurno;
    private int turno = 1;
    
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
            int tipoJefe = randomIntEntre(0, 100);
            String claseJefe;
            if(tipoJefe > 50){
                claseJefe = "El Patron";
            }else{
                claseJefe = "La Comadre";
            }
            int vidaJefe = 200;
            int ataqueJefe = randomIntEntre(20, 30);
            enemigos.add(new Jefe(claseJefe, vidaJefe, ataqueJefe));
            i++;
        }
        while(i <= numEnemigos){
            int tipoEnemigo = randomIntEntre(0, 100);
            String nombre;
            if(tipoEnemigo < 50){
                nombre = "K-70";
            }else{
                nombre = "Chorizo";
            }
            int vidaEnemigo = randomIntEntre(50, 150);
            int ataqueEnemigo = randomIntEntre(5, 15);
            enemigos.add(new Enemigo(nombre, vidaEnemigo, ataqueEnemigo));
            i++;
        }
    }
    private void atacarJugador(){
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
        ultimoTurno += "| " + jugador.getNombre() + " -ATK> " + objetivo.getNombre() + " | ";
        if(objetivo.getVida() <= 0){ //Se muere
            enemigos.remove(numObjetivo);
            v.mostrarMuerte(objetivo.getMuerte());
            ultimoTurno += "| " + jugador.getNombre() + " -KILL> " + objetivo.getNombre() + " | ";
        }
    }
    private void usarItem(){
        boolean opcionValida = false;
        while(!opcionValida){
            items = jugador.getInventario();
            int posItem = v.mostrarMenuItems(items) - 1; //Devuelve una posicion adelante
            if(posItem >= 0 && posItem <= items.size()){
                Items paraUsar = items.get(posItem);
                v.mostrarItemUsado(jugador.usarItem(paraUsar));
                items.remove(paraUsar);
                ultimoTurno += "| " + jugador.getNombre() + " -ITEM> " + jugador.getEfecto() + " | ";
                opcionValida = true;
            }else{
                v.opcionInvalida();
            }
        }
    }
    private void jugarRonda(){
        ultimoTurno = "";
        v.inicioTurno(turno);
        mostrarEnemigos();
        v.mostrarElTurnoDe(jugador);
        v.mostrarEstadisticasJugador(jugador);
        // TURNO DEL JUGADOR
        boolean opcionValida = false;
        while(!opcionValida){
            int opcion = v.mostrarMenuJugador();
            switch(opcion){
                case 1: // ATACAR
                    atacarJugador();
                    if(jugador.getEfecto().equals("Doble Ataque")){
                        atacarJugador();
                    }
                    opcionValida = true;
                break;
                case 2: // USAR ITEM
                    if(jugador.getEfecto().equals("")){
                        usarItem();
                        opcionValida = true;
                    }else{
                        v.mostrarItemActivo();
                    }
                break;
                case 3: // SALTAR TURNO
                    v.mostrarSaltarTurno(turno);
                    opcionValida = true;
                break;
                case 4: // SALIR
                    System.exit(1);
                break;
                default:
                    v.opcionInvalida();
                break;
            }
        }
        //TURNO DE LOS ENEMIGOS
        for (Enemigo enemigo : enemigos) {
            v.mostrarElTurnoDe(enemigo);
            boolean opcionValidaEnemigo = false;
            while(!opcionValidaEnemigo){
                int opcion = v.mostrarMenuEnemigo();
                switch(opcion){
                    case 1: //Atacar
                        int cantidad = enemigo.getAtaque();
                        if(!jugador.getEfecto().equals("Alas de esquive")){
                            enemigo.atacar(jugador, cantidad);
                            if(jugador.getVida() > 0){
                                ultimoTurno += "| " + enemigo.getNombre() + " -ATK> " + jugador.getNombre() + " | ";
                            }else{
                                ultimoTurno += "| " + enemigo.getNombre() + " -KILL> " + jugador.getNombre() + " | ";
                            }
                            v.mostrarRecibioDanio(jugador, cantidad);
                        }else{
                            v.mostrarEsquivado();
                            jugador.setEfecto("");
                        }
                        opcionValidaEnemigo = true;
                    break;
                    case 2: //Habilidad
                    break;
                    case 3: //Saltar
                        v.mostrarSaltarTurno(turno);
                        opcionValidaEnemigo = true;
                    break;
                    case 4: //Salir
                        System.exit(1);
                    break;
                    default:
                        v.opcionInvalida();
                    break;
                }
            }
        }
        //Fin de ronda
        for(int i = 0; i < historial.size(); i++){
            if(i <= 1){
                historial.set(i, historial.get(i + 1));
            }else if(i == 2){
                historial.set(2, ultimoTurno);
            }
        }
        turno++;
    }
    public void ejecutar(){
        historial = new ArrayList<>();
        historial.add("");
        historial.add("");
        historial.add("");
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
            jugarRonda();
            v.mostrarHistorial(historial);
        }while(jugador.getVida() > 0 && !enemigos.isEmpty());

        if(jugador.getVida() <= 0){
            jugador.getMuerte();
        }else{
            v.mostrarNoMasEnemigos();
        }
        v.mostrarGG(turno);
    }
}
