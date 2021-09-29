/*
    Enemigo.java
    NOMBRE: Francisco Castillo 21562
    Modelo de enemigo que hereda de Combatientes
*/

public class Enemigo extends Combatientes{
    private String nombreHabilidad;
    private String nombreHabilidadJefe;
    
    public Enemigo(String nombre, int vida, int ataque){
        super(nombre, vida, ataque);
        if(nombre.equals("K-70") || nombre.equals("El Patron")){ //El Patron Jefe de K-70
            this.nombreHabilidad = "Aturdir";
        }else if(nombre.equals("Chorizo") || nombre.equals("La Comadre")){ //La comadre Jefe de Chorizo
            this.nombreHabilidad = "Sangrado";
        }
        this.nombreHabilidadJefe = "";
        this.nombreHabilidadJefe = "";
    }
    
    /** 
     * @param nombre de la habilidad
     */
    protected void setHabilidadJefe(String nombre){
        this.nombreHabilidadJefe = nombre;
    }
    
    /** 
     * @return String nombre de la habilidad
     */
    public String getNombreHabilidad(){
        return this.nombreHabilidad;
    }
    
    /** 
     * @param objetivo a quien se atacara con el ataque especial
     */
    public void ataqueEspecial(Jugador objetivo){
        objetivo.setEfectoContrario(this.nombreHabilidad);
    }
    
    /** 
     * @param objetivo a quien se atacara con el ataque del jefe
     */
    public void ataqueJefe(Jugador objetivo){
        objetivo.setEfectoContrario(this.nombreHabilidadJefe);
    }
}
