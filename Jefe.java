/*
    Jefe.java
    NOMBRE: Francisco Castillo 21562
    Modelo de jefe que hereda de Enemigo
*/
public class Jefe extends Enemigo{
    public Jefe(String nombre, int vida, int ataque){
        super(nombre, vida, ataque);
        if(nombre.equals("El Patron")){ //El Patron Jefe de K-70
            this.setHabilidadJefe("Golpe Critico");
        }else if(nombre.equals("La Comadre")){ //La comadre Jefe de Chorizo
            this.setHabilidadJefe("Robavida");
        }
    }
} 
