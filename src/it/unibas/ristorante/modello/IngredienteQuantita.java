package it.unibas.ristorante.modello;

/**
 *
 * @author Simone
 */
public class IngredienteQuantita {
    
    private int quantita;
    private Ingrediente ingrediente;    

    public IngredienteQuantita(int quantita, Ingrediente ingrediente) {
        this.quantita = quantita;
        this.ingrediente = ingrediente;
    }


    public int getQuantita() {
        return quantita;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }
    
}
