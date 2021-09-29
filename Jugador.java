/*
    Jugador.java
    NOMBRE: Francisco Castillo 21562
    Modelo de Jugador que hereda de Combatientes
*/

import java.util.ArrayList;

public class Jugador extends Combatientes {
    private String clase;
    private String efectoContrario;
    private ArrayList<Items> inventario;
    public Jugador(String nombre, int vida, int ataque, String clase){
        super(nombre, vida, ataque);
        this.clase = clase;
        inventario = new ArrayList<>();
        if(clase.equals("GUERRERO")){
            this.vida = vida * 2; //Doble de vida
            this.ataque = ataque * 15/10; //50% mas de ataque
            inventario.add(new Items("Doble Ataque"));
            inventario.add(new Items("Doble Ataque"));
            inventario.add(new Items("Alas de esquive"));
        }else{
            inventario.add(new Items("Pocion de Curacion"));
            inventario.add(new Items("Pocion de Curacion"));
            inventario.add(new Items("Pocion de Curacion"));
            inventario.add(new Items("Pocion de Curacion"));
            inventario.add(new Items("Doble Ataque"));
            inventario.add(new Items("Doble Ataque"));
            inventario.add(new Items("Alas de esquive"));
            inventario.add(new Items("Alas de esquive"));
        }
        efectoContrario = "";
    }
    
    /** 
     * @param item a utilizar
     * @return String el efecto
     */
    public String usarItem(Items item){
        return item.usarItem(this);
    }
    
    
    /** 
     * @return ArrayList<Items> la lista de items disponibles
     */
    public ArrayList<Items> getInventario(){
        return inventario;
    }
    
    /** 
     * @return String la clase del jugador
     */
    public String getClase(){
        return this.clase;
    }

    
    /** 
     * @return String el texto a mostrar segund el efecto
     */
    public String checarEfecto(){
        String resultado = "";
        if(this.efectoContrario != null){
            switch(this.efectoContrario){
                case "Aturdir":
                    resultado = this.nombre + " esta aturdido! No podra atacar la siguiente ronda";
                    break;
                case "Sangrado":
                    resultado = this.nombre + " esta sangrando!"; // Pierde 10 de vida el turno actual y el siguiente
                    break;
                case "Golpe Critico":
                    resultado = this.nombre + " ha recibido un golpe critico! (-50 pts de vida)";
                    break;
                default:
                    break;
            }
        }
        return resultado;
    }
    
    /** 
     * @param nuevoEfecto a colocar al recibir una habilidad
     */
    public void setEfectoContrario(String nuevoEfecto){
        this.efectoContrario = nuevoEfecto;
    }
    
    /** 
     * @return String el efecto actual
     */
    public String getEfectoContrario(){
        return this.efectoContrario;
    }
}
