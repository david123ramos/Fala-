package br.com.falae;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe usada para salvar informações arbitrárias que podem ser
 * compartilhadas entre os fragmentos.
 */
public class Info {

    private static Info myself;
    private Map<Object, Object> map;

    private Info() {
        this.map = new HashMap<>();
    }

    public static Info getInstance() {
        if(myself == null) {
            myself = new Info();
        }
        return myself;
    }

    public void putInfo(Object key, Object value) {
         this.map.put(key, value);
    }

    public Object getInfo(Object key) {
        return this.map.get(key);
    }
}
