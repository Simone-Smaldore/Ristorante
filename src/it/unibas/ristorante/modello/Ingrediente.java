package it.unibas.ristorante.modello;

/**
 *
 * @author Simone
 */
public class Ingrediente {
   
    private String nome;
    private boolean allergene;
    private int kcal;

    public Ingrediente(String nome, boolean allergene, int kcal) {
        this.nome = nome;
        this.allergene = allergene;
        this.kcal = kcal;
    }

    public String getNome() {
        return nome;
    }

    public boolean isAllergene() {
        return allergene;
    }

    public int getKcal() {
        return kcal;
    }
    
}
