import java.util.ArrayList;
import java.util.Scanner;
public class Vista {
    Scanner scan;
    public Vista(){
        scan = new Scanner(System.in);
    }
    private void prnt(String texto){
        System.out.println(texto);
    }
    public void inicio(){
        prnt("Narrador: Bienvenido! Iniciemos una nueva aventura.");
    }
    public String pedirNombre(){
        prnt("¿Cual es tu nombre?");
        return scan.nextLine();
    }
    public void mostrarPersonajeCreado(String nombre){
        prnt("Narrador: Bienvenido " + nombre + "! Y suerte... la necesitaras...");
    }
    public int pedirClase(){
        prnt("Narrador: Ahora dime, que clase prefieres? (Ingresa el numero)\n1. Guerrero\n2. Explorador");
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
    public void inicioTurno(int turno){
        prnt("Turno " + turno + "!");
    }
    public void enemigosCreados(int cantidad){
        prnt("Apareceran " + cantidad + " enemigos esta partida.");
    }
    public void jefeAparece(){
        prnt("*risa malvada*\nNarrador: UN JEFE SE ACERCA PARA LUCHAR.");
    }
    public void mostrarEstadisticas(Combatientes c){
        prnt("\n"+c.getNombre()+ " tiene:");
        prnt("-Vida: " + c.getVida() + " pts");
        prnt("-Ataque: " + c.getAtaque() + " pts");
    }
    public void mostrarEstadisticasJugador(Jugador j){
        prnt("\nEl " + j.getClase() + " "+ j.getNombre() +" tiene actualmente:");
        prnt("-Vida: " + j.getVida());
    }
    public void mostrarElTurnoDe(Combatientes c){
        prnt("Es el turno de " + c.getNombre());
    }
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
    public void mostrarRecibioDanio(Combatientes c, int cantidad){
        prnt(c.getNombre() + " ha recibido " + cantidad + "pts de daño!");
    }
    public void mostrarMuerte(String texto){
        prnt(texto);
    }
    public void mostrarSaltarTurno(int turno){
        prnt("Te haz saltado el turno " + turno);
    }
    public void mostrarNoMasEnemigos(){
        prnt("HAZ ACABADO CON TODOS LOS ENEMIGOS TRAS ESE ULTIMO GOLPE! Muy bien");
    }
    public void mostrarGG(int turnos){
        prnt("Haz terminado el combate tras " + turnos + " turnos");
    }
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
    public void mostrarHistorial(ArrayList<String> historial){
        prnt("Los ultimos movimientos han sido:");
        for (String string : historial) {
            prnt("-" + string);
        }
    }
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
    public void mostrarItemUsado(String texto){
        prnt(texto);
    }
    public void mostrarEsquivado(){
        prnt("EL ATAQUE HA SIDO ESQUIVADO");
    }
    public void mostrarItemActivo(){
        prnt("Ya se encuentra un item activo");
    }
    public void mostrarEfecto(String resultado){
        prnt(resultado);
    }
    public void mostrarYaHayEfecto(){
        prnt("El jugador ya tiene un efecto. Escoge otra opcion.");   
    }
    public void mostrarNoMasAturdir(){
        prnt("Estas aturdido! Pierdes este turno");
    }
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
    public void mostrarSaludo(Combatientes c){
        prnt(c.getSaludo());
    }
    public void mostrarDespedida(Combatientes c){
        prnt(c.getMuerte());
    }
}
