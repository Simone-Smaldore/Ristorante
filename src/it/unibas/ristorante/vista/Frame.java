package it.unibas.ristorante.vista;

import it.unibas.ristorante.Applicazione;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 *
 * @author Simone
 */
public class Frame extends javax.swing.JFrame {

    public Frame() {
    }
    
    public void inizializza() {
        initComponents();
        initAction();
        //occhio a jscrollPane
        this.setContentPane(new JScrollPane(Applicazione.getInstance().getPannelloPrincipale()));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JMenuBar jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuEsci = new javax.swing.JMenuItem();
        jMenuItemCarica = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuItemHelp = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenuFile.setText("File");

        jMenuEsci.setText("Esci");
        jMenuFile.add(jMenuEsci);

        jMenuItemCarica.setText("Carica");
        jMenuFile.add(jMenuItemCarica);

        jMenuBar1.add(jMenuFile);

        jMenuHelp.setText("?");

        jMenuItemHelp.setText("jMenuItem1");
        jMenuHelp.add(jMenuItemHelp);

        jMenuBar1.add(jMenuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 291, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem jMenuEsci;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuItemCarica;
    private javax.swing.JMenuItem jMenuItemHelp;
    // End of variables declaration//GEN-END:variables

    private void initAction() {
        this.jMenuEsci.setAction(Applicazione.getInstance().getControlloFrame().getAzioneEsci());
        this.jMenuItemHelp.setAction(Applicazione.getInstance().getControlloFrame().getAzioneHelp());
        this.jMenuItemCarica.setAction(Applicazione.getInstance().getControlloFrame().getAzioneCarica());
    }

    public void mostraMessaggioErrori(String errori) {
        JOptionPane.showMessageDialog(this, errori, "Errore", JOptionPane.ERROR_MESSAGE);
    }
    
    public void mostraMessaggioInformazioni(String info) {
        JOptionPane.showMessageDialog(this, info, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
