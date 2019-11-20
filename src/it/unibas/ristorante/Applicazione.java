package it.unibas.ristorante;

import it.unibas.ristorante.controllo.ControlloAggiungiAPietanza;
import it.unibas.ristorante.controllo.ControlloAggiungiAdArchivio;
import it.unibas.ristorante.controllo.ControlloFinestraAggiungiPietanza;
import it.unibas.ristorante.controllo.ControlloFrame;
import it.unibas.ristorante.controllo.ControlloPannelloPrincipale;
import it.unibas.ristorante.modello.Modello;
import it.unibas.ristorante.persistenza.DAOArchivio;
import it.unibas.ristorante.persistenza.DAOArchivioMock;
import it.unibas.ristorante.persistenza.IDAOArchivio;
import it.unibas.ristorante.vista.FinestraAggiungiPietanza;
import it.unibas.ristorante.vista.FinestraTabella;
import it.unibas.ristorante.vista.Frame;
import it.unibas.ristorante.vista.PannelloAggiungiAPietanza;
import it.unibas.ristorante.vista.PannelloAggiungiAdArchivio;
import it.unibas.ristorante.vista.PannelloPrincipale;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;

/**
 *
 * @author Simone
 */
public class Applicazione {

    private static Applicazione istanza = new Applicazione();
    private IDAOArchivio daoArchivio;
    private Modello modello;
    private Frame frame;
    private ControlloFrame controlloFrame;
    private PannelloPrincipale pannelloPrincipale;
    private ControlloPannelloPrincipale controlloPrincipale;
    private PannelloAggiungiAdArchivio pannelloAggiungiAdArchivio;
    private ControlloAggiungiAdArchivio controlloAggiungiAdArchivio;
    private PannelloAggiungiAPietanza pannelloAggiungiAPietanza;
    private ControlloAggiungiAPietanza controlloAggiungiAPietanza;
    private DAOArchivio daoArchivioVero;
    private FinestraTabella finetraTabella;
    private FinestraAggiungiPietanza finestraAggiungiPietanza;
    private ControlloFinestraAggiungiPietanza controlloFinestraAggiungiPietanza;

    private Applicazione() {
    }

    public static Applicazione getInstance() {
        return istanza;
    }

    public void inizializza() {
        this.daoArchivioVero = new DAOArchivio();
        this.daoArchivio = new DAOArchivioMock();
        this.modello = new Modello();
        this.controlloFrame = new ControlloFrame();
        this.controlloPrincipale = new ControlloPannelloPrincipale();
        this.controlloAggiungiAdArchivio = new ControlloAggiungiAdArchivio();
        this.controlloAggiungiAPietanza = new ControlloAggiungiAPietanza();
        this.frame = new Frame();
        this.pannelloPrincipale = new PannelloPrincipale();
        this.pannelloAggiungiAdArchivio = new PannelloAggiungiAdArchivio(frame, true);
        this.pannelloAggiungiAPietanza = new PannelloAggiungiAPietanza(frame, true);
        this.finetraTabella = new FinestraTabella(frame, true);
        this.finestraAggiungiPietanza = new FinestraAggiungiPietanza(frame, true);
        this.controlloAggiungiAPietanza = new ControlloAggiungiAPietanza();
        this.controlloFinestraAggiungiPietanza = new ControlloFinestraAggiungiPietanza();
        this.finestraAggiungiPietanza.inizializza();
        this.finetraTabella.inizializza();
        this.pannelloAggiungiAPietanza.inizializza();
        this.pannelloAggiungiAdArchivio.inizializza();
        this.pannelloPrincipale.inizializza(); //occhio all ordine
        this.frame.inizializza();

    }

    public FinestraAggiungiPietanza getFinestraAggiungiPietanza() {
        return finestraAggiungiPietanza;
    }

    public ControlloFinestraAggiungiPietanza getControlloFinestraAggiungiPietanza() {
        return controlloFinestraAggiungiPietanza;
    }

    public FinestraTabella getFinetraTabella() {
        return finetraTabella;
    }

    public ControlloAggiungiAPietanza getControlloAggiungiAPietanza() {
        return controlloAggiungiAPietanza;
    }

    public ControlloPannelloPrincipale getControlloPrincipale() {
        return controlloPrincipale;
    }

    public DAOArchivio getDAOArchivioVero() {
        return daoArchivioVero;
    }

    public IDAOArchivio getDAOArchivio() {
        return this.daoArchivio;
    }

    public ControlloAggiungiAdArchivio getControlloAggiungiAdArchivio() {
        return controlloAggiungiAdArchivio;
    }

    public ControlloFrame getControlloFrame() {
        return controlloFrame;
    }

    public Modello getModello() {
        return modello;
    }

    public Frame getFrame() {
        return frame;
    }

    public PannelloPrincipale getPannelloPrincipale() {
        return pannelloPrincipale;
    }

    public PannelloAggiungiAdArchivio getPannelloAggiungiAdArchivio() {
        return pannelloAggiungiAdArchivio;
    }

    public PannelloAggiungiAPietanza getPannelloAggiungiAPietanza() {
        return pannelloAggiungiAPietanza;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Applicazione.getInstance().inizializza();
            }
        });
    }

}
