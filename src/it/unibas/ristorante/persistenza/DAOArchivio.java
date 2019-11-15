package it.unibas.ristorante.persistenza;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unibas.ristorante.modello.Archivio;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Simone
 */
public class DAOArchivio implements IDAOArchivio{

    @Override
    public Archivio caricaArchivio(String nomeFile) throws DAOException {
        Archivio archivio = null;
        FileReader flusso = null;
        try {
            flusso = new FileReader(nomeFile);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            archivio = gson.fromJson(flusso, Archivio.class);
        } catch(IOException ioe) {
            throw new DAOException(ioe);
        } finally {
            try {
                if(flusso != null) {
                    flusso.close();
                }
            } catch(IOException ioe) {}
        }
        return archivio;
    }
    
    public void salvaArchivio(Archivio archivio, String nomeFile) throws DAOException{
        PrintWriter flusso = null;
        try {
            FileWriter fileWriter = new FileWriter(nomeFile);
            flusso = new PrintWriter(fileWriter);
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            String stringaJson = gson.toJson(archivio);
            flusso.print(stringaJson);
        } catch(IOException ioe) {
            throw new DAOException(ioe);
        } finally {
            if(flusso != null) {
                flusso.close();
            }
        }
    } 
    
}
