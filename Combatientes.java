public class Combatientes {
    protected String nombre;
    protected int vida;
    protected int ataque;
    protected String textoSaludo;
    protected String textoMuerte;
    protected String textoVictoria;
    protected String efecto;

    public Combatientes(String nombre, int vida, int ataque){
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.textoSaludo = "Narrador: Un " + this.nombre + " salvaje ha aparecido!" + 
            "\n-Vida: " + this.vida + "pts\n-Ataque: " + this.ataque + "pts";
        this.textoMuerte = "Narrador: Justo donde duele! " + this.nombre + " ha caido!";

        this.textoVictoria = this.nombre + ": Ez, como dice la chaviza.";
        this.efecto = "";
    }
    
    /** 
     * @return String Al entrar en combate
     */
    public String getSaludo(){
        return this.textoSaludo;
    }
    
    /** 
     * @return String Al morir
     */
    public String getMuerte(){
        return this.textoMuerte;
    }
    
    /** 
     * @return String Al ganar
     */
    public String getVictoria(){
        return this.textoVictoria;
    }
    
    /** 
     * @return String El nombre
     */
    public String getNombre(){
        return this.nombre;
    }

    public int getVida(){
        return this.vida;
    }
    public int getAtaque(){
        return this.ataque;
    }
    
    /** 
     * @param objetivo A quien se atacara
     * @param cantidad Cuanto se bajara a objetivo
     */
    public void atacar(Combatientes objetivo, int cantidad){ //PENDIENTE
        objetivo.bajarVida(cantidad);
    }
    
    /** 
     * @param cantidad a bajar
     */
    public void bajarVida(int cantidad){
        this.vida -= cantidad;
    }
    
    /** 
     * @param cantidad la cantidad a subir
     */
    public void subirVida(int cantidad){
        this.vida += cantidad;
    }
    public void recibirEfecto(String efecto){
        this.efecto = efecto;
    }
}
