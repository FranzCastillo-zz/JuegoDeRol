/*
    Controlador.java
    NOMBRE: Francisco Castillo 21562
    Controlador basado en moledo MVC
*/

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
    
    
    /** 
     * @param min rango inferior
     * @param max rango superior
     * @return int numero aleatorio
     */
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
        if(probabilidadJefe <= 25){ //COLOCA A PROBABILIDAD LA APARICION DE UN JEFE ENTRE LOS ENEMIGOS
            v.jefeAparece();
            int tipoJefe = randomIntEntre(0, 100);
            String claseJefe;
            if(tipoJefe > 50){
                claseJefe = "El Patron";
            }else{
                claseJefe = "La Comadre";
            }
            int vidaJefe = 250;
            int ataqueJefe = randomIntEntre(25, 35);
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
            int vidaEnemigo = randomIntEntre(75, 100);
            int ataqueEnemigo = randomIntEntre(10, 25);
            enemigos.add(new Enemigo(nombre, vidaEnemigo, ataqueEnemigo));
            i++;
        }
        for (Enemigo enemigo : enemigos) {
            v.mostrarSaludo(enemigo);
        }
    }
    private void atacarJugador(){
        if(jugador.getVida() > 0){
            if(!jugador.getEfectoContrario().equals("Aturdir")){ //SI ESTA ATURDIDO NO ATACA
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
            }else{
                jugador.setEfectoContrario("");
                v.mostrarNoMasAturdir();
            }
        }else{
            v.mostrarDespedida(jugador);
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
    
    /** 
     * @param enemigo quien dirije el ataque al Jugador
     */
    private void atacarEnemigo(Enemigo enemigo){
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
            ultimoTurno += "| " + enemigo.getNombre() + " -ATKn't>; " + jugador.getNombre() + " -DODGE- "  + " | ";
            v.mostrarEsquivado();
            jugador.setEfecto("");
        }
    }
    
    /** 
     * @param enemigo quien dirije el ataque al jugador
     */
    private void atacarEspecialEnemigo(Enemigo enemigo){
        String efecto = jugador.getEfectoContrario();
        if(efecto.equals("")){ //Si no se encuentra ya afectado por una habilidad
            enemigo.ataqueEspecial(jugador);
            jugador.bajarVida(enemigo.getAtaque());
            ultimoTurno += "| " + enemigo.getNombre() + " -SKILL> " + jugador.getNombre() + " | ";
            turnoAtacadoConHabilidad = turno;
            v.mostrarEfecto(jugador.checarEfecto());
            switch(jugador.getEfectoContrario()){ //EFECTOS INMEDIATOS
                case "Golpe Critico":
                    jugador.bajarVida(20);
                    jugador.setEfectoContrario("");
                    ultimoTurno += "| " + jugador.getNombre() + " -CRIT> " + jugador.getNombre() + " | ";
                    break;
                case "Robavida":
                    enemigo.subirVida(enemigo.getAtaque());
                    jugador.setEfectoContrario("");
                    ultimoTurno += "| " + jugador.getNombre() + " -LIFESTEAL> " + jugador.getNombre() + " | ";
                    break;
            }
        }else{
            v.mostrarYaHayEfecto();
        }
    }
    
    /** 
     * @param enemigo quien dirije el ataque al jugador
     */
    private void atacarEspecialJefe(Jefe enemigo){
        String efecto = jugador.getEfectoContrario();
        if(efecto.equals("")){ //Si no se encuentra ya afectado por una habilidad
            enemigo.ataqueJefe(jugador);
            jugador.bajarVida(enemigo.getAtaque());
            ultimoTurno += "| " + enemigo.getNombre() + " -SKILL> " + jugador.getNombre() + " | ";
            turnoAtacadoConHabilidad = turno;
            v.mostrarEfecto(jugador.checarEfecto());
            switch(jugador.getEfectoContrario()){ //EFECTOS INMEDIATOS
                /*case "Sangrado":
                    turnoAtacadoConHabilidad = turno;
                    jugador.bajarVida(10);
                    ultimoTurno += "| " + jugador.getNombre() + " -BLED- "  + " | ";
                    break;*/
                case "Golpe Critico":
                    jugador.bajarVida(20);
                    jugador.setEfectoContrario("");
                    ultimoTurno += "| " + jugador.getNombre() + " -CRIT> " + jugador.getNombre() + " | ";
                    break;
                case "Robavida":
                    enemigo.subirVida(enemigo.getAtaque());
                    jugador.setEfectoContrario("");
                    ultimoTurno += "| " + jugador.getNombre() + " -LIFESTEAL> " + jugador.getNombre() + " | ";
                    break;
            }
        }else{
            v.mostrarYaHayEfecto();
        }
    }
    int turnoAtacadoConHabilidad = 1;
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
                    ultimoTurno += "| " + jugador.getNombre() + " -SKIP- "  + " | ";
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
            if(enemigo instanceof Jefe){
                while(!opcionValidaEnemigo){
                    int opcion = v.mostrarMenuJefe();
                    switch(opcion){
                        case 1: //Atacar
                            atacarEnemigo(enemigo);
                            opcionValidaEnemigo = true;
                        break;
                        case 2: //Habilidad
                            atacarEspecialEnemigo(enemigo);
                            opcionValidaEnemigo = true;
                        break;
                        case 3: //ESPECIAL
                            atacarEspecialJefe((Jefe)enemigo);
                            opcionValidaEnemigo = true;
                        break;
                        case 4: //Saltar
                            ultimoTurno += "| " + enemigo.getNombre() + " -SKIP- "  + " | ";
                            v.mostrarSaltarTurno(turno);
                            opcionValidaEnemigo = true;
                        break;
                        case 5: //Salir
                            System.exit(1);
                        break;
                        default:
                            v.opcionInvalida();
                        break;
                    }
                }
            }else{
                while(!opcionValidaEnemigo){
                    int opcion = v.mostrarMenuEnemigo();
                    switch(opcion){
                        case 1: //Atacar
                            atacarEnemigo(enemigo);
                            opcionValidaEnemigo = true;
                        break;
                        case 2: //Habilidad
                            atacarEspecialEnemigo(enemigo);
                            opcionValidaEnemigo = true;
                        break;
                        case 3: //Saltar
                            v.mostrarSaltarTurno(turno);
                            ultimoTurno += "| " + enemigo.getNombre() + " -SKIP- "  + " | ";
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
        }
        //EFECTO EXTRA EN LA RONDA
        String efecto = jugador.getEfectoContrario();
        if(efecto.equals("Sangrado")){
            if(turno < turnoAtacadoConHabilidad + 2){
                jugador.bajarVida(10);
                ultimoTurno += "| " + jugador.getNombre() + " -BLED- | ";
            }else{
                jugador.setEfectoContrario("");
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
    
    /*---------------------------------------------------------------------------------

                                        IMPLEMENTACION RAIDS

        -----------------------------------------------------------------------------------
    */


    private Jugador crearJugador(){
        String nombre = v.pedirNombre();
        String clase = "";
        boolean claseValida = false;
        while(!claseValida){
            int numeroClase = v.pedirClase();
            if(numeroClase == 1){
                clase = "GUERRERO";
                claseValida = true;
            }else if(numeroClase == 2){
                clase = "EXPLORADOR";
                claseValida = true;
            }else if(numeroClase == 3){
                clase = "CAZADOR";
                claseValida = true;
            }
            else{
                v.opcionInvalida();
            }
        }
        v.mostrarPersonajeCreado(nombre);
        return new Jugador(nombre, 100, 15, clase);
    }

    ArrayList<Jugador> jugadoresEnRaid = new ArrayList<>();
    private void jugarRAID(){
        //----------------CREANDO JUGADORES-----------------
        jugadoresEnRaid.add(jugador);
        boolean cantidadDeJugadoresExtraValidos = false;
        int cantidadNuevosjugadores = v.pedirCantidadNuevosJugadores();
        if(cantidadNuevosjugadores >= 0 && cantidadNuevosjugadores <= 2){ //VERIFICA QUE HAYA ENTRE 0 Y 2 JUGADORES PARA CUMPLIR CON MAX 3
            for(int i = 0; i < cantidadNuevosjugadores; i++){
                v.mostrarQueJugadorSeEstaCreando(i + 2);
                jugadoresEnRaid.add(crearJugador());
            }
        }
        //------------------JUGABILIDAD------------------
    }
    
    
    
    //MAIN PART
    public void ejecutar(){
        historial = new ArrayList<>();
        historial.add("");
        historial.add("");
        historial.add("");
        v = new Vista();
        enemigos = new ArrayList<>();
        v.inicio();
        jugador = crearJugador();
        boolean tipoRondaValido = false;
        while(!tipoRondaValido){
            int tipoRonda = v.pedirTipoRonda();
            if(tipoRonda == 1){ // ATAQUE NORMAL
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
                tipoRondaValido = true;
            //------------------------------------------------- RAID -------------------------------------------------
            }else if(tipoRonda == 2){ 
                jugarRAID();
                tipoRondaValido = true;
            }else{
                v.opcionInvalida();
            }
        }
    }
}
