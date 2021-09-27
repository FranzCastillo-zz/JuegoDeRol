public class Enemigo extends Combatientes{
    private String nombreHabilidad;
    
    public Enemigo(String nombre, int vida, int ataque){
        super(nombre, vida, ataque);
        if(nombre.equals("K-70")){ //El Patron Jefe de K-70
            this.nombreHabilidad = "Aturdir";
        }else if(nombre.equals("Chorizo")){ //La comadre Jefe de Chorizo
            this.nombreHabilidad = "Sangrado";
        }
    }
    public String getNombreHabilidad(){
        return this.nombreHabilidad;
    }
    public void ataqueEspecial(Combatientes objetivo){ //PENDIENTE

    }
}
