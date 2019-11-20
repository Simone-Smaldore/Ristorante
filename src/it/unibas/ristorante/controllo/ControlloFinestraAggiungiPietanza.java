/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unibas.ristorante.controllo;

import it.unibas.ristorante.Applicazione;
import it.unibas.ristorante.Costanti;
import it.unibas.ristorante.modello.Archivio;
import it.unibas.ristorante.modello.Pietanza;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

/**
 *
 * @author Simone
 */
public class ControlloFinestraAggiungiPietanza {

    private Action azioneAggiungi = new AzioneAggiungi();

    public Action getAzioneAggiungi() {
        return azioneAggiungi;
    }

    private class AzioneAggiungi extends AbstractAction {

        public AzioneAggiungi() {
            this.putValue(NAME, "Aggiungi Pietanza");
            this.putValue(SHORT_DESCRIPTION, "Aggiunge una pietanza all' archivio");
            this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl + A"));
            this.putValue(MNEMONIC_KEY, KeyEvent.VK_A);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Archivio archivio = (Archivio) Applicazione.getInstance().getModello().getBean(Costanti.ARCHIVIO);
            String nome = Applicazione.getInstance().getFinestraAggiungiPietanza().getjTextFieldNome().getText();
            String costo = Applicazione.getInstance().getFinestraAggiungiPietanza().getjTextFieldCosto().getText();
            String codice = Applicazione.getInstance().getFinestraAggiungiPietanza().getjTextFieldCodice().getText();
            String errori = trovaErrori(nome, costo, codice, archivio);
            if (!errori.isEmpty()) {
                Applicazione.getInstance().getFinestraAggiungiPietanza().mostraMessaggioErrori(errori);
                return;
            }
            double numCosto = Double.parseDouble(costo);
            archivio.addPietanza(new Pietanza(nome, numCosto, codice));
            Applicazione.getInstance().getFinestraAggiungiPietanza().nascondi();
        }

        private String trovaErrori(String nome, String costo, String codice, Archivio archivio) {
            String errori = "";
            if (nome.isEmpty()) {
                errori += "Inserire un valore per il nome\n";
            }
            try {
                double numCosto = Double.parseDouble(costo);
                if(numCosto <= 0) {
                    errori += "Inserire un numero positivo per il costo della pietanza\n";
                }
            } catch (IllegalArgumentException iae) {
                errori += "Inserire un numero decimale per il costo\n";
            }
            if (codice.isEmpty()) {
                errori += "Inserire un valore per il codice\n";
                return errori;
            }
            if(archivio.cercaPietanzaPerCodice(codice) != null) {
                errori += "Pietanza con il codice inserito già presente nell'archivio\n";
            }
            return errori;
        }

    }
}
