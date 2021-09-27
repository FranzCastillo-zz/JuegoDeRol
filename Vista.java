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
    public void inicioRonda(int numero, int oleada){
        prnt("Narrador: Demos inicio a la ronda " + numero + ", oleada "+ oleada +"! ");
    }
    public void enemigosCreados(int cantidad){
        prnt("Apareceran " + cantidad + " enemigos esta ronda.");
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
            prnt("2. Ver items");
            prnt("3. Usar item");
            prnt("4. Saltar turno");
            prnt("5. Salir");

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
    public void mostrarSiguienteOleada(int oleada){
        prnt("Narrador: Haz acabado con la oleada " + oleada + "... Impresionante... Por la siguiente");
    }
}
