package it.unibas.ristorante.modello;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Simone
 */
public class Pietanza implements Comparable<Pietanza>{

    private String nome;
    private double costo;
    private String codice;
    private List<IngredienteQuantita> listaQuantita = new ArrayList<>();

    public Pietanza(String nome, double costo, String codice, List<IngredienteQuantita> listaQuantita) {
        this.nome = nome;
        this.costo = costo;
        this.codice = codice;
        this.listaQuantita = listaQuantita;
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
            if (ingredienteQuantita.getIngrediente().isAllergene()) {
                return true;
            }
        }
        return false;
    }

    public boolean contieneIngrediente(Ingrediente ingrediente) {
        for (IngredienteQuantita ingredienteQuantita : listaQuantita) {
            if (ingrediente.getNome().equalsIgnoreCase(ingredienteQuantita.getIngrediente().getNome())) {
                return true;
            }
        }
        return false;
    }

    public int getCalorie() {
        int calorie = 0;
        for (IngredienteQuantita ingredienteQuantita : listaQuantita) {
            calorie += ingredienteQuantita.getIngrediente().getKcal() * ingredienteQuantita.getQuantita() / 100;
        }
        return calorie;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pietanza other = (Pietanza) obj;
        if (!Objects.equals(this.codice, other.codice)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Pietanza o) {
        return this.getCalorie() - o.getCalorie();
    }

}
