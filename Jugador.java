import java.util.ArrayList;

public class Jugador extends Combatientes {
    private String clase;
    private ArrayList<Items> inventario;
    public Jugador(String nombre, int vida, int ataque, String clase){
        super(nombre, vida, ataque);
        this.clase = clase;
        inventario = new ArrayList<>();
        if(clase.equals("GUERRERO")){
            inventario.add(new Items("Pocion de Curacion"));
            inventario.add(new Items("Doble Ataque"));
            inventario.add(new Items("Doble Ataque"));
            inventario.add(new Items("Alas de esquive"));
        }else{
            inventario.add(new Items("Pocion de Curacion"));
            inventario.add(new Items("Pocion de Curacion"));
            inventario.add(new Items("Doble Ataque"));
            inventario.add(new Items("Alas de esquive"));
        }
        efecto = "";
    }
    public String usarItem(Items item){
        return item.usarItem(this);
    }
    
    public ArrayList<Items> getInventario(){
        return inventario;
    }
    public String getClase(){
        return this.clase;
    }
}
