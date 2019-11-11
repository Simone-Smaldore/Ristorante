package it.unibas.ristorante.controllo;

import it.unibas.ristorante.Applicazione;
import it.unibas.ristorante.Costanti;
import it.unibas.ristorante.modello.Archivio;
import it.unibas.ristorante.modello.Ingrediente;
import it.unibas.ristorante.persistenza.DAOException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

/**
 *
 * @author Simone
 */
public class ControlloAggiungiAdArchivio {

    private Action azioneAggiungi = new AzioneAggiungi();

    public Action getAzioneAggiungi() {
        return azioneAggiungi;
    }

    private class AzioneAggiungi extends AbstractAction {

        public AzioneAggiungi() {
            this.putValue(NAME, "Aggiungi all'archivio");
            this.putValue(SHORT_DESCRIPTION, "Aggiungi l' ingrediente all' archivio");
            this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl + E"));
            this.putValue(MNEMONIC_KEY, KeyEvent.VK_E);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String nome = Applicazione.getInstance().getPannelloAggiungiAdArchivio().getjTextFieldNome().getText();
            String kcal = Applicazione.getInstance().getPannelloAggiungiAdArchivio().getjTextFieldKcal().getText();
            boolean allergeni = Applicazione.getInstance().getPannelloAggiungiAdArchivio().getjCheckBoxAllergene().isSelected();
            String errori = controlloErrori(nome, kcal);
            if (!errori.isEmpty()) {
                Applicazione.getInstance().getPannelloAggiungiAdArchivio().mostraMessaggioErrori(errori);
                return;
            }
            Ingrediente ingrediente = new Ingrediente(nome, allergeni, Integer.parseInt(kcal));
            Archivio archivio = null;
            archivio = (Archivio) Applicazione.getInstance().getModello().getBean(Costanti.ARCHIVIO);
            if (archivio.cercaIngredientePerNome(nome) != null) {
                Applicazione.getInstance().getPannelloAggiungiAdArchivio().mostraMessaggioErrori("ingrediente già presente nell'archivio");
                return;
            }
            Applicazione.getInstance().getPannelloAggiungiAdArchivio().setVisible(false);
            archivio.addIngrediente(ingrediente);
            //Applicazione.getInstance().getPannelloAggiungiAdArchivio().setVisible(false);
        }

        private String controlloErrori(String nome, String kcal) {
            String errori = "";
            if (nome.isEmpty()) {
                errori = errori + "Inserire un nome\n";
            }
            if (kcal.isEmpty()) {
                errori = errori + "Inserire un numero per le kCal\n";
                return errori;
            }
            try {
                int kCal = Integer.parseInt(kcal);
            } catch (IllegalArgumentException e) {
                errori = errori + "Il valore delle Kcal deve essere un numero\n";
            }
            return errori;
        }

    }
}
