package org.julia.scripting;

import javax.script.Bindings;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by rss on 23/08/2018
 */
public class JuliaEngineBindings implements Bindings {
    private Map<String, Object> map = new HashMap<>();

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<Object> values() {
        return map.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return map.entrySet();
    }

    @Override
    public Object put(String name, Object value) {
        return map.put(name, value);
    }

    @Override
    public void putAll(Map<? extends String, ?> toMerge) {
        for (final String key : toMerge.keySet()) {
            put(key, toMerge.get(key));
        }
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public Object get(Object key) {
        return map.get(key);
    }

    @Override
    public Object remove(Object key) {
        return map.remove(key);
    }
}
