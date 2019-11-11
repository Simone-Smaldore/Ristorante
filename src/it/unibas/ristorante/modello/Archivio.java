package it.unibas.ristorante.modello;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Simone
 */
public class Archivio {

    private List<Pietanza> pietanze = new ArrayList<Pietanza>();
    private List<Ingrediente> ingredienti = new ArrayList<Ingrediente>();

    public Archivio(List<Pietanza> pietanze) {
        this.pietanze = pietanze;
    }

    public Archivio() {
    }

    public List<Ingrediente> getIngredienti() {
        return ingredienti;
    }

    public void addPietanza(Pietanza pietanza) {
        this.pietanze.add(pietanza);
    }

    public void addIngrediente(Ingrediente ingrediente) {
        this.ingredienti.add(ingrediente);
    }

    public Pietanza cercaPietanzaPerCodice(String codice) {
        for (Pietanza pietanza : pietanze) {
            if (pietanza.getCodice().equalsIgnoreCase(codice)) {
                return pietanza;
            }
        }
        return null;
    }

    public Ingrediente cercaIngredientePerNome(String nome) {
        for (Ingrediente ingrediente : ingredienti) {
            if (nome.equalsIgnoreCase(ingrediente.getNome())) {
                return ingrediente;
            }
        }
        return null;
    }

}
