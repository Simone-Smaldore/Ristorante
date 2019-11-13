package it.unibas.ristorante.controllo;

import it.unibas.ristorante.Applicazione;
import it.unibas.ristorante.Costanti;
import it.unibas.ristorante.modello.Archivio;
import it.unibas.ristorante.persistenza.DAOException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

/**
 *
 * @author Simone
 */
public class ControlloFrame {

    private Action azioneEsci = new AzioneEsci();
    private Action azioneCarica = new AzioneCarica();
    private Action azioneHelp = new AzioneHelp();

    public Action getAzioneCarica() {
        return azioneCarica;
    }

    public Action getAzioneHelp() {
        return azioneHelp;
    }

    public Action getAzioneEsci() {
        return azioneEsci;
    }

    private class AzioneHelp extends AbstractAction {

        public AzioneHelp() {
            this.putValue(NAME, "Help");
            this.putValue(SHORT_DESCRIPTION, "Premi per informazioni");
            this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl + H"));
            this.putValue(MNEMONIC_KEY, KeyEvent.VK_2);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Applicazione.getInstance().getFrame().mostraMessaggioInformazioni("Premere carica per caricare un archivio\nin modo da abilitare gli altri bottoni di comando.\nPremere Trova pietanza per trovare la \npietanza più vicina caloricamente a quella inserita.");
        }

    }

    private class AzioneCarica extends AbstractAction {

        public AzioneCarica() {
            this.putValue(NAME, "Carica");
            this.putValue(SHORT_DESCRIPTION, "Carica l archivio");
            this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl + E"));
            this.putValue(MNEMONIC_KEY, KeyEvent.VK_E);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Archivio archivio = Applicazione.getInstance().getDAOArchivio().caricaArchivio("");
                Applicazione.getInstance().getModello().addBean(Costanti.ARCHIVIO, archivio);
                this.setEnabled(false);
                Applicazione.getInstance().getPannelloPrincipale().abilitaComponenti();
            } catch (DAOException ex) {
                Applicazione.getInstance().getFrame().mostraMessaggioErrori("Problemi con il caricamento");
            }
        }
    }

    private class AzioneEsci extends AbstractAction {

        public AzioneEsci() {
            this.putValue(NAME, "Esci");
            this.putValue(SHORT_DESCRIPTION, "Permette di uscire dall applicazione");
            this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl + E"));
            this.putValue(MNEMONIC_KEY, KeyEvent.VK_E);

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

}
