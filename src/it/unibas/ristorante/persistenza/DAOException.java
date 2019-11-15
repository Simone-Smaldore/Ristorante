/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unibas.ristorante.persistenza;

/**
 *
 * @author Simone
 */
public class DAOException extends Exception{
    
    public DAOException() {
        
    }
    
    public DAOException(Exception e){
        super(e);
    }
    
    public DAOException(String message){
        super(message);
    }
    
    public DAOException(String message, Exception ex){
        super(message, ex);
    }
    
}
