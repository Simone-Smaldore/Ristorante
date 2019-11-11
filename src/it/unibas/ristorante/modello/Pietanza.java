
package it.unibas.ristorante.modello;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Simone
 */
public class Pietanza {
    
    private String nome;
    private double costo;
    private String codice;
    private List<IngredienteQuantita> listaQuantita = new ArrayList<>();

    public Pietanza(String nome, double costo, String codice, List<IngredienteQuantita> listaQuantita) {
        this.nome = nome;
        this.costo = costo;
        this.codice = codice;
        this.listaQuantita = listaQuantita ;
    }
    
    public Pietanza(String nome, double costo, String codice) {
        this.nome = nome;
        this.costo = costo;
        this.codice = codice;
    }    
    
    public void addIngrediente(IngredienteQuantita quantita) {
        this.listaQuantita.add(quantita);
    }

    public List<IngredienteQuantita> getListaQuantita() {
        return listaQuantita;
    }

    public String getNome() {
        return nome;
    }
    
    public double getCosto() {
        return costo;
    }

    public String getCodice() {
        return codice;
    }

    public boolean contieneAllergeni() {
        for (IngredienteQuantita ingredienteQuantita : listaQuantita) {
            if(ingredienteQuantita.getIngrediente().isAllergene()) {
                return true;
            }
        }
        return false;
    }

    public boolean contieneIngrediente(Ingrediente ingrediente) {
        for (IngredienteQuantita ingredienteQuantita : listaQuantita) {
            if(ingrediente.getNome().equalsIgnoreCase(ingredienteQuantita.getIngrediente().getNome())) {
                return true;
            }
        }
        return false;
    }
    
}
