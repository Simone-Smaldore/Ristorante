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

    public List<Pietanza> getPietanze() {
        return pietanze;
    }

    public int getNumeroPietanze() {
        return this.pietanze.size();
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

    public Pietanza trovaPietanzaCaloricamenteSimile(Pietanza p) {
        Pietanza simile = null;
        int min = Integer.MAX_VALUE;
        for (Pietanza pietanza : pietanze) {
            if (p.equals(pietanza)) {
                continue;
            }
            int diff = Math.abs(pietanza.getCalorie() - p.getCalorie());
            if (diff < min) {
                min = diff;
                simile = pietanza;
            }
        }
        return simile;
    }

}
