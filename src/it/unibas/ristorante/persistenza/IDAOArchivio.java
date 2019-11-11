package it.unibas.ristorante.persistenza;

import it.unibas.ristorante.modello.Archivio;

/**
 *
 * @author Simone
 */
public interface IDAOArchivio {
    
    public Archivio caricaArchivio(String nomeFile)throws DAOException;
    
}
    
