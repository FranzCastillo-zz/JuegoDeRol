public class Enemigo extends Combatientes{
    private String nombreHabilidad;
    public Enemigo(String nombre, int vida, int ataque){
        super(nombre, vida, ataque);
    }
    public String getNombreHabilidad(){
        return this.nombreHabilidad;
    }
    public void ataqueEspecial(Combatientes objetivo){ //PENDIENTE

    }
}
