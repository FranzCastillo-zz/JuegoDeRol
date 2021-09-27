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
    public void claseInvalida(){
        prnt("Ha ingresado una clase no valida, escoga una de las opciones provistas");
    }
    public void inicioRonda(int numero){
        prnt("Narrador: Demos inicio a la ronda " + numero + "!");
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

            int temp = scan.nextInt();
            scan.nextLine();
            return temp;
        }catch(Exception e){
            scan.next();
            return -1;
        }
    }
}
