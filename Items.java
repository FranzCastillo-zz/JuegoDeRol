/*
    Items.java
    NOMBRE: Francisco Castillo 21562
    Modelo de items
*/

public class Items {
    private String nombre;

    public Items(String nombre){
        this.nombre = nombre;
    }
    
    /** 
     * @return String el nombre del item
     */
    public String getNombre(){
        return this.nombre;
    }
    
    /** 
     * @param j La instancia del jugador
     * @return String el efecto
     */
    public String usarItem(Jugador j){
        String name = this.nombre;
        String efecto = "";
        if(name.equals("Pocion de Curacion")){
            j.subirVida(50);
            efecto = j.getNombre() + " ha recibido 50pts de vida";
        }else if(name.equals("Doble Ataque")){
            j.setEfecto("Doble Ataque");
            efecto = j.getNombre() + " puede volver a atacar en el siguiente turno!";
            
        }else if(name.equals("Alas de esquive")){
            j.setEfecto("Alas de esquive");
            efecto = j.getNombre() + " esquivara el siguiente ataque enemigo!";
        }
        return efecto;
    }
}
