/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unibas.ristorante.vista;

import it.unibas.ristorante.modello.Pietanza;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Simone
 */
public class ModelloTabellaPietanze extends AbstractTableModel {

    private List<Pietanza> listaPietanze;

    public void setListaPietanze(List<Pietanza> listaPietanze) {
        this.listaPietanze = listaPietanze;
    }

    public ModelloTabellaPietanze() {
    }

    @Override
    public int getRowCount() {
        if(listaPietanze == null) {
            return 0;
        }
        return listaPietanze.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(listaPietanze == null ){
            return "";
        }
        Pietanza pietanza = listaPietanze.get(rowIndex);
        if(columnIndex == 0) {
            return pietanza.getCodice();
        }
        if(columnIndex == 1) {
            return pietanza.getNome();
        }
        if(columnIndex == 2) {
            return pietanza.getCalorie();
        }
        if(columnIndex == 3) {
            return pietanza.getCosto();
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        if(column == 0) {
            return "Codice";
        }
        if(column == 1) {
            return "Nome";
        }
        if(column == 2) {
            return "Calorie";
        }
        if(column == 3) {
            return "Costo";
        }
        return "";
    }

    public void aggiorna() {
        fireTableDataChanged();
    }
    
    

}
