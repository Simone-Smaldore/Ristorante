package it.unibas.ristorante.controllo;

import it.unibas.ristorante.Applicazione;
import it.unibas.ristorante.Costanti;
import it.unibas.ristorante.modello.Archivio;
import it.unibas.ristorante.modello.Pietanza;
import it.unibas.ristorante.persistenza.DAOException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Collections;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;

/**
 *
 * @author Simone
 */
public class ControlloFrame {

    private Action azioneEsci = new AzioneEsci();
    private Action azioneCarica = new AzioneCarica();
    private Action azioneHelp = new AzioneHelp();
    private Action azioneTrovaSimile = new AzioneTrovaCaloricamenteSimile();
    private Action azioneMostraPietanze = new AzioneMostraPietanze();
    private Action azioneAggiungiPietanza = new AzioneAggiungiPietanza();

    public Action getAzioneAggiungiPietanza() {
        return azioneAggiungiPietanza;
    }

    public Action getAzioneMostraPietanze() {
        return azioneMostraPietanze;
    }

    public Action getAzioneCarica() {
        return azioneCarica;
    }

    public Action getAzioneHelp() {
        return azioneHelp;
    }

    public Action getAzioneEsci() {
        return azioneEsci;
    }

    public Action getAzioneTrovaSimile() {
        return azioneTrovaSimile;
    }

    private class AzioneAggiungiPietanza extends AbstractAction {

        public AzioneAggiungiPietanza() {
            this.putValue(NAME, "Aggiungi pietanza all'archvio");
            this.putValue(SHORT_DESCRIPTION, "Aggiunge una pietanza all'archivio");
            this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl + A"));
            this.putValue(MNEMONIC_KEY, KeyEvent.VK_A);
            this.setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Applicazione.getInstance().getFinestraAggiungiPietanza().visualizza();
        }

    }

    private class AzioneMostraPietanze extends AbstractAction {

        public AzioneMostraPietanze() {
            this.putValue(NAME, "Mostra pietanze");
            this.putValue(SHORT_DESCRIPTION, "Mostra una tabella contenente le pietanze dell' archivio");
            this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl + T"));
            this.putValue(MNEMONIC_KEY, KeyEvent.VK_T);
            this.setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Archivio archivio = (Archivio) Applicazione.getInstance().getModello().getBean(Costanti.ARCHIVIO);
            if (archivio.getNumeroPietanze() == 0) {
                Applicazione.getInstance().getFrame().mostraMessaggioErrori("Non ci sono pietanze da mostrare nell'archivio");
                return;
            }
            List<Pietanza> pietanze = archivio.getPietanze();
            Collections.sort(pietanze);
            Applicazione.getInstance().getModello().addBean(Costanti.LISTA_PIETANZE, pietanze);
            Applicazione.getInstance().getFinetraTabella().aggiornaTabella();
            Applicazione.getInstance().getFinetraTabella().visualizza();
        }

    }

    private class AzioneTrovaCaloricamenteSimile extends AbstractAction {

        public AzioneTrovaCaloricamenteSimile() {
            this.putValue(NAME, "Trova pietanza caloricamente simile");
            this.putValue(SHORT_DESCRIPTION, "Trova la pietanza che è più simile caloricamente alla pietanza trovata con il codice");
            this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl + F"));
            this.putValue(MNEMONIC_KEY, KeyEvent.VK_1);
            this.setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Archivio archivio = (Archivio) Applicazione.getInstance().getModello().getBean(Costanti.ARCHIVIO);
            Pietanza pietanza = (Pietanza) Applicazione.getInstance().getModello().getBean(Costanti.PIETANZA_CORRENTE);
            if (archivio.getNumeroPietanze() == 1) {
                Applicazione.getInstance().getFrame().mostraMessaggioInformazioni("Nell'archivio è presente solo la \npietanza selezionata");
                return;
            }
            Pietanza simile = archivio.trovaPietanzaCaloricamenteSimile(pietanza);
            Applicazione.getInstance().getFrame().mostraMessaggioInformazioni("Pietanza caloricamente più simile a quella selezionata\n\n"
                    + "Nome : " + simile.getNome() + "\nAllergeni: " + (simile.contieneAllergeni() ? "presenti" : "non presenti") + "\n"
                    + "Costo : " + simile.getCosto());
        }

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
            String nomeFile = acquisisciFile();
            if (nomeFile != null) {
                try {
                    Archivio archivio = Applicazione.getInstance().getDAOArchivioVero().caricaArchivio(nomeFile);
                    Applicazione.getInstance().getModello().addBean(Costanti.ARCHIVIO, archivio);
                    Applicazione.getInstance().getPannelloPrincipale().abilitaComponenti();
                    Applicazione.getInstance().getFrame().abilitaMostraPietanze();
                    Applicazione.getInstance().getFrame().abilitaAggiungiPietanza();
                } catch (DAOException ex) {
                    Applicazione.getInstance().getFrame().mostraMessaggioErrori("Problemi con il caricamento");
                }
            }
        }

        private String acquisisciFile() {
            JFileChooser fileChooser = Applicazione.getInstance().getFrame().getFileChooser();
            int codice = fileChooser.showOpenDialog(Applicazione.getInstance().getFrame());
            if (codice == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                return file.toString();
            } else if (codice == JFileChooser.CANCEL_OPTION) {
                //logger
            }
            return null;
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
