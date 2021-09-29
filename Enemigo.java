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
    protected void setHabilidadJefe(String nombre){
        this.nombreHabilidadJefe = nombre;
    }
    public String getNombreHabilidad(){
        return this.nombreHabilidad;
    }
    public void ataqueEspecial(Jugador objetivo){ //PENDIENTE
        objetivo.setEfectoContrario(this.nombreHabilidad);
    }
    public void ataqueJefe(Jugador objetivo){
        objetivo.setEfectoContrario(this.nombreHabilidadJefe);
    }
}
