/*
    Vista.java
    NOMBRE: Francisco Castillo 21562
    Vista segun el modelo MVC
*/

import java.util.ArrayList;
import java.util.Scanner;
public class Vista {
    Scanner scan;
    public Vista(){
        scan = new Scanner(System.in);
    }
    
    /** 
     * @param texto texto a mostrar en consola
     */
    private void prnt(String texto){
        System.out.println(texto);
    }
    public void inicio(){
        prnt("Narrador: Bienvenido! Iniciemos una nueva aventura.");
    }
    
    /** 
     * @return String el nombre ingresado por el usuario
     */
    public String pedirNombre(){
        prnt("¿Cual es tu nombre?");
        return scan.nextLine();
    }
    
    /** 
     * @param nombre el nombre del personaje
     */
    public void mostrarPersonajeCreado(String nombre){
        prnt("Narrador: Bienvenido " + nombre + "! Y suerte... la necesitaras...");
    }
    
    /** 
     * @return int el numero de clase (1. Guerrero | 2. Explorador)
     */
    public int pedirClase(){
        prnt("Narrador: Ahora dime, que clase prefieres? (Ingresa el numero)\n1. Guerrero\n2. Explorador\n3. Cazador");
        try{
            int opcion = scan.nextInt();
            scan.nextLine();
            return opcion;
        }catch(Exception e){
            scan.next();
            return -1;
        }
    }
    public void opcionInvalida(){
        prnt("Ha ingresado una opcion no valida, escoga una de las opciones provistas");
    }
    
    /** 
     * @param turno el numero de turno actual
     */
    public void inicioTurno(int turno){
        prnt("Turno " + turno + "!");
    }
    
    /** 
     * @param cantidad de enemigos creados
     */
    public void enemigosCreados(int cantidad){
        prnt("Apareceran " + cantidad + " enemigos esta partida.");
    }
    public void jefeAparece(){
        prnt("*risa malvada*\nNarrador: UN JEFE SE ACERCA PARA LUCHAR.");
    }
    
    /** 
     * @param c combatiente para mostrar su vida y ataque
     */
    public void mostrarEstadisticas(Combatientes c){
        prnt("\n"+c.getNombre()+ " tiene:");
        prnt("-Vida: " + c.getVida() + " pts");
        prnt("-Ataque: " + c.getAtaque() + " pts");
    }
    
    /** 
     * @param j instancia de jugador
     */
    public void mostrarEstadisticasJugador(Jugador j){
        prnt("\nEl " + j.getClase() + " "+ j.getNombre() +" tiene actualmente:");
        prnt("-Vida: " + j.getVida());
    }
    
    /** 
     * @param c Instancia del combatiente que se controla en el momento
     */
    public void mostrarElTurnoDe(Combatientes c){
        prnt("Es el turno de " + c.getNombre());
    }
    
    /** 
     * @return int opcion seleccionada del menu
     */
    public int mostrarMenuJugador(){
        try{
            prnt("\nQue deseas hacer?\n");
            prnt("1. Atacar");
            prnt("2. Usar item");
            prnt("3. Saltar turno");
            prnt("4. Salir");

            int temp = scan.nextInt();
            scan.nextLine();
            return temp;
        }catch(Exception e){
            scan.next();
            return -1;
        }
    }
    
    /** 
     * @param arregloEnemigos instancias de enemigo
     * @return int el objetivo a atacar
     */
    public int pedirObjetivo(ArrayList<Enemigo> arregloEnemigos){
        try{
            prnt("\nA quien deseas atacar? (Ingresa el numero)\n");
            int i = 1;
            for (Enemigo enemigo : arregloEnemigos) {
                prnt(i + ". " + enemigo.getNombre());
                i++;
            }
            int temp = scan.nextInt();
            scan.nextLine();
            return temp;
        }catch(Exception e){
            scan.next();
            return -1;
        }
    }
    
    /** 
     * @param c instancia de quien recibio danio
     * @param cantidad de danio recibida
     */
    public void mostrarRecibioDanio(Combatientes c, int cantidad){
        prnt(c.getNombre() + " ha recibido " + cantidad + "pts de daño!");
    }
    
    /** 
     * @param texto de la muerte
     */
    public void mostrarMuerte(String texto){
        prnt(texto);
    }
    
    /** 
     * @param turno el turno saltado
     */
    public void mostrarSaltarTurno(int turno){
        prnt("Te haz saltado el turno " + turno);
    }
    public void mostrarNoMasEnemigos(){
        prnt("HAZ ACABADO CON TODOS LOS ENEMIGOS TRAS ESE ULTIMO GOLPE! Muy bien");
    }
    
    /** 
     * @param turnos que tardo en terminar la partida
     */
    public void mostrarGG(int turnos){
        prnt("Haz terminado el combate tras " + turnos + " turnos");
    }
    
    /** 
     * @return int opcion del menu del enemigo
     */
    public int mostrarMenuEnemigo(){
        try{
            prnt("\nQue desea hacer?");
            prnt("1. Atacar jugador");
            prnt("2. Atacar jugador con habilidad especial");
            prnt("3. Saltar Turno");
            prnt("4. Salir");
            int temp = scan.nextInt();
            scan.nextLine();
            return temp;
        }catch(Exception e){
            scan.next();
            return -1;
        }
    }
    
    /** 
     * @param historial de los 3 pasados turnos
     */
    public void mostrarHistorial(ArrayList<String> historial){
        prnt("Los ultimos movimientos han sido:");
        for (String string : historial) {
            prnt("-" + string);
        }
    }
    
    /** 
     * @param inventario arreglo de items disponibles
     * @return int item seleccionado por el usuario
     */
    public int mostrarMenuItems(ArrayList<Items> inventario){
        try{
            prnt("\nQue item deseas usar? (Ingresa el numero)\n");
            int i = 1;
            for (Items item : inventario) {
                prnt(i + ". " + item.getNombre());
                i++;
            }
            int temp = scan.nextInt();
            scan.nextLine();
            return temp;
        }catch(Exception e){
            scan.next();
            return -1;
        }
    }
    
    /** 
     * @param texto del efecto causado
     */
    public void mostrarItemUsado(String texto){
        prnt(texto);
    }
    public void mostrarEsquivado(){
        prnt("EL ATAQUE HA SIDO ESQUIVADO");
    }
    public void mostrarItemActivo(){
        prnt("Ya se encuentra un item activo");
    }
    
    /** 
     * @param resultado del efecto actual
     */
    public void mostrarEfecto(String resultado){
        prnt(resultado);
    }
    public void mostrarYaHayEfecto(){
        prnt("El jugador ya tiene un efecto. Escoge otra opcion.");   
    }
    public void mostrarNoMasAturdir(){
        prnt("Estas aturdido! Pierdes este turno");
    }
    
    /** 
     * @return int opcion seleccionada del menu
     */
    public int mostrarMenuJefe(){
        try{
            prnt("\nQue desea hacer?");
            prnt("1. Atacar jugador");
            prnt("2. Atacar jugador con habilidad especial");
            prnt("3. Atacar con Habilidad de Jefe");
            prnt("4. Saltar Turno");
            prnt("5. Salir");
            int temp = scan.nextInt();
            scan.nextLine();
            return temp;
        }catch(Exception e){
            scan.next();
            return -1;
        }
    }
    
    /** 
     * @param c instancia de quien saludara
     */
    public void mostrarSaludo(Combatientes c){
        prnt(c.getSaludo());
    }
    
    /** 
     * @param c instancia de quien se despedira
     */
    public void mostrarDespedida(Combatientes c){
        prnt(c.getMuerte());
    }

    //-----------------------------------IMPLEMENTACION PARA RAIDS
    public int pedirTipoRonda(){
        try{
            prnt("\nQue tipo de ronda desea jugar? (ingrese el numero)\n");
            prnt("1. Pelea Normal");
            prnt("2. RAID!");
            int temp = scan.nextInt();
            scan.nextLine();
            return temp;
        }catch(Exception e){
            scan.next();
            return -1;
        }
    }
    public int pedirCantidadNuevosJugadores(){
        try{
            prnt("\nIngrese la cantidad de jugadores extra que le acompaniaran: [0-2] Ingresa el numero");
            int temp = scan.nextInt();
            scan.nextLine();
            return temp;
        }catch(Exception e){
            scan.next();
            return -1;
        }
    }
    public void mostrarQueJugadorSeEstaCreando(int numero){
        prnt("Sistema: Creando al jugador " + numero);
    }
}
