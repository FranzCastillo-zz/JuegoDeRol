import java.util.ArrayList;

public class Jugador extends Combatientes {
    private String clase;
    private ArrayList<Integer> items;
    public Jugador(String nombre, int vida, int ataque, String clase){
        super(nombre, vida, ataque);
        this.clase = clase;
    }
    public void agregarItem(String nombre){

    }
    public void usarItem(Combatientes objetivo, String nombre){

    }
    public ArrayList<Items> getInventario(){
        ArrayList<Items> inventario = new ArrayList();
        return inventario;
    }
    public String getClase(){
        return this.clase;
    }
}
