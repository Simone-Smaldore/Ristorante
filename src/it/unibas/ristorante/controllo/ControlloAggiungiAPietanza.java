package it.unibas.ristorante.controllo;

import it.unibas.ristorante.Applicazione;
import it.unibas.ristorante.Costanti;
import it.unibas.ristorante.modello.Archivio;
import it.unibas.ristorante.modello.Ingrediente;
import it.unibas.ristorante.modello.IngredienteQuantita;
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
public class ControlloAggiungiAPietanza {

    private Action azioneAggiungi = new AzioneAggiungi();

    public Action getAzioneAggiungi() {
        return azioneAggiungi;
    }

    private class AzioneAggiungi extends AbstractAction {

        public AzioneAggiungi() {
            this.putValue(NAME, "Aggiungi a Pietanza");
            this.putValue(SHORT_DESCRIPTION, "Aggiunge l'ingrediente selezionato con la quantità immessa alla pietanza");
            this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl+e"));
            this.putValue(MNEMONIC_KEY, KeyEvent.VK_E);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Archivio archivio = (Archivio) Applicazione.getInstance().getModello().getBean(Costanti.ARCHIVIO);
            Pietanza pietanza = (Pietanza) Applicazione.getInstance().getModello().getBean(Costanti.PIETANZA_CORRENTE);
            Ingrediente ingrediente = archivio.cercaIngredientePerNome(Applicazione.getInstance().getPannelloAggiungiAPietanza().getSelectedValue());
            int numQuantita = 0;
            String quantita = Applicazione.getInstance().getPannelloAggiungiAPietanza().getjTextFieldQuantita().getText();
            String errori = trovaErrori();
            if (!errori.isEmpty()) {
                Applicazione.getInstance().getPannelloAggiungiAPietanza().mostraMessaggioErrore(errori);
                return;
            }
            pietanza.addIngrediente(new IngredienteQuantita(numQuantita, ingrediente));
            Applicazione.getInstance().getPannelloAggiungiAPietanza().setVisible(false);

        }

        private String trovaErrori() {
            Archivio archivio = (Archivio) Applicazione.getInstance().getModello().getBean(Costanti.ARCHIVIO);
            Pietanza pietanza = archivio.cercaPietanzaPerCodice(Applicazione.getInstance().getPannelloPrincipale().getCampoRicerca());
            Ingrediente ingrediente = archivio.cercaIngredientePerNome(Applicazione.getInstance().getPannelloAggiungiAPietanza().getSelectedValue());
            String errori = "";
            String quantita = Applicazione.getInstance().getPannelloAggiungiAPietanza().getjTextFieldQuantita().getText();
            int numQuantita = 0;
            if (quantita.isEmpty()) {
                errori = errori + "Inserire un valore per la quantita\n";
                return errori;
            }
            if (pietanza.contieneIngrediente(ingrediente)) {
                errori = errori + "Ingrediente già presente nella pietanza\n";
            }
            try {
                numQuantita = Integer.parseInt(quantita);
            } catch (IllegalArgumentException ex) {
                errori = errori + "La quantità deve essere un numero\n";
                return errori;
            }
            if (numQuantita <= 0) {
                errori = errori + "La quantita deve essere maggiore di 0";
            }
            return errori;
        }
    }

}
