package it.unibas.ristorante.modello;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Simone
 */
public class Modello {
    
    private Map<String, Object> beans = new HashMap<>();
    
    public void addBean(String chiave, Object bean) {
        this.beans.put(chiave, bean);
    }
    
    public Object getBean(String chiave) {
        return this.beans.get(chiave);
    }
}
