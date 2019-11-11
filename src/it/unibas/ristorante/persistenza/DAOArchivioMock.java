package it.unibas.ristorante.persistenza;

import it.unibas.ristorante.modello.Archivio;
import it.unibas.ristorante.modello.Ingrediente;
import it.unibas.ristorante.modello.IngredienteQuantita;
import it.unibas.ristorante.modello.Pietanza;

/**
 *
 * @author Simone
 */
public class DAOArchivioMock implements IDAOArchivio{

    @Override
    public Archivio caricaArchivio(String nomeFile) throws DAOException {
        Archivio archivio;
        //Ingredienti
        Ingrediente ingrediente1 = new Ingrediente("pasta", true, 400);
        Ingrediente ingrediente2 = new Ingrediente("pane", true, 350);
        Ingrediente ingrediente3 = new Ingrediente("riso", false, 200);
        Ingrediente ingrediente4 = new Ingrediente("manzo", false, 300);
        Ingrediente ingrediente5 = new Ingrediente("pomodori", false, 100);
        //Quantita Ingrediente
//        IngredienteQuantita quantita1 = new IngredienteQuantita(100, ingrediente1);
//        IngredienteQuantita quantita2 = new IngredienteQuantita(30, ingrediente2);
//        IngredienteQuantita quantita3 = new IngredienteQuantita(50, ingrediente3);
//        IngredienteQuantita quantita4 = new IngredienteQuantita(70, ingrediente4);
//        IngredienteQuantita quantita5 = new IngredienteQuantita(200, ingrediente5);
//        IngredienteQuantita quantita6 = new IngredienteQuantita(100, ingrediente1);
//        IngredienteQuantita quantita7 = new IngredienteQuantita(150, ingrediente2);
//        IngredienteQuantita quantita8 = new IngredienteQuantita(130, ingrediente3);
//        IngredienteQuantita quantita9 = new IngredienteQuantita(140, ingrediente4);
//        IngredienteQuantita quantita0 = new IngredienteQuantita(20, ingrediente5);
        //Pietanze
        Pietanza pietanza1 = new Pietanza("pasta al pomodoro", 10.0, "aaa");
        pietanza1.addIngrediente(new IngredienteQuantita(100, ingrediente1));
        pietanza1.addIngrediente(new IngredienteQuantita(150, ingrediente5));
        
        Pietanza pietanza2 = new Pietanza("riso al pomodoro", 8.0, "bbb");
        pietanza2.addIngrediente(new IngredienteQuantita(100, ingrediente3));
        pietanza2.addIngrediente(new IngredienteQuantita(150, ingrediente5));
        
        Pietanza pietanza3 = new Pietanza("manzo al pomodoro", 12.0, "ccc");
        pietanza3.addIngrediente(new IngredienteQuantita(100, ingrediente4));
        pietanza3.addIngrediente(new IngredienteQuantita(150, ingrediente5)); 
        
        Pietanza pietanza4 = new Pietanza("bruschetta", 3.0, "ddd");
        pietanza4.addIngrediente(new IngredienteQuantita(100, ingrediente2));
        pietanza4.addIngrediente(new IngredienteQuantita(150, ingrediente5)); 
        
        archivio = new Archivio();
        archivio.addPietanza(pietanza1);
        archivio.addPietanza(pietanza2);
        archivio.addPietanza(pietanza3);
        archivio.addPietanza(pietanza4);
        archivio.addIngrediente(ingrediente1);
        archivio.addIngrediente(ingrediente2);
        archivio.addIngrediente(ingrediente3);
        archivio.addIngrediente(ingrediente4);
        archivio.addIngrediente(ingrediente5);
        return archivio;
    }
    
}
