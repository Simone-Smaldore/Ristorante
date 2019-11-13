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
public class ControlloPannelloPrincipale {
    
    private Action azioneCerca = new AzioneCerca();
    private Action azioneAggiungiAdArchivio = new AzioneAggiungiAdArchivio();
    private Action azioneAggiungiAPietanza = new AzioneAggiungiAPietanza();

    public Action getAzioneCerca() {
        return azioneCerca;
    }

    public Action getAzioneAggiungiAdArchivio() {
        return azioneAggiungiAdArchivio;
    }

    public Action getAzioneAggiungiAPietanza() {
        return azioneAggiungiAPietanza;
    }
    
    
    
    private class AzioneAggiungiAPietanza extends AbstractAction {
        
        public AzioneAggiungiAPietanza() {
            this.putValue(NAME, "Aggiungi a pietanza");
            this.putValue(SHORT_DESCRIPTION, "Aggiungi un ingrediente dall'archivio alla pietanza");
            this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl + E"));
            this.putValue(MNEMONIC_KEY, KeyEvent.VK_E);  
            this.setEnabled(false);            
        }        

        @Override
        public void actionPerformed(ActionEvent e) {
            Applicazione.getInstance().getPannelloAggiungiAPietanza().visualizza();
        }
        
    }
    
    private class AzioneAggiungiAdArchivio extends AbstractAction {
        
        public AzioneAggiungiAdArchivio() {
            this.putValue(NAME, "Aggiungi all' archivio");
            this.putValue(SHORT_DESCRIPTION, "Aggiungi un ingrediente all' archivio");
            this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl + E"));
            this.putValue(MNEMONIC_KEY, KeyEvent.VK_E);  
            this.setEnabled(false);            
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Applicazione.getInstance().getPannelloAggiungiAdArchivio().visualizza();
        }
        
    }
    
    
    
    private class AzioneCerca extends AbstractAction{
        
        public AzioneCerca() {
            this.putValue(NAME, "Cerca");
            this.putValue(SHORT_DESCRIPTION, "Cerca");
            this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl + E"));
            this.putValue(MNEMONIC_KEY, KeyEvent.VK_E);  
            this.setEnabled(false);
        }        

        @Override
        public void actionPerformed(ActionEvent e) {
            String codice = Applicazione.getInstance().getPannelloPrincipale().getCampoRicerca();
            String errori = controllaErrori(codice);
            if(!errori.isEmpty()) {
                Applicazione.getInstance().getFrame().mostraMessaggioErrori(errori);
                return;
            }
            Archivio archivio = (Archivio)Applicazione.getInstance().getModello().getBean(Costanti.ARCHIVIO);
            Pietanza pietanzaTrovata = archivio.cercaPietanzaPerCodice(codice);
            if(pietanzaTrovata == null) {
                Applicazione.getInstance().getFrame().mostraMessaggioErrori("non sono presenti pietanze con il codice indicato");
                return;
            }
            Applicazione.getInstance().getModello().addBean(Costanti.PIETANZA_CORRENTE, pietanzaTrovata);
            Applicazione.getInstance().getPannelloPrincipale().settaPietanza(pietanzaTrovata.getNome(), pietanzaTrovata.contieneAllergeni(), pietanzaTrovata.getCosto());
            Applicazione.getInstance().getPannelloPrincipale().abilitaBottoneAggiungiAPietanza();
        }
        
        private String controllaErrori(String codice) {
            if(codice.isEmpty()) {
                return "il campo non puo essere vuoto";
            }
            return "";
        }
    
    }
}
