public class Jefe extends Enemigo{
    private String nombreHabilidadJefe;
    public Jefe(String nombre, int vida, int ataque){
        super(nombre, vida, ataque);
        if(nombre.equals("El Patron")){ //El Patron Jefe de K-70
            this.nombreHabilidadJefe = "Aturdir";
        }else if(nombre.equals("Chorizo")){ //La comadre Jefe de Chorizo
            this.nombreHabilidadJefe = "Autocuracion";
        }
    }
    public String getHabilidad(){
        return this.nombreHabilidadJefe;
    }
}
