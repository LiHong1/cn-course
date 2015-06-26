package cm.commons.key;


import cm.commons.dao.SerialDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public final class SerialFactory {
    private static final int INCREMENT_BY_DEF = 1;
    private static SerialDao serialDao;
    private static ConcurrentMap<String, SerialGenerator> generators = new ConcurrentHashMap<String, SerialGenerator>();

    private static void setSerialDao(SerialDao serialDao) {
        SerialFactory.serialDao = serialDao;
    }

    private static SerialGenerator getSerialGenerator(String keyName) {
        if (generators.containsKey(keyName)) {
            return generators.get(keyName);
        }

        SerialGenerator generator = new RandomSerialGenerator();
        generators.put(keyName, generator);
        return generator;
    }

    public static void clear() {
        generators.clear();
    }

    public static String nextKey(String keyName) {
        return getSerialGenerator(keyName).nextKey();
    }

    public static Long nextId(String keyName) {
        return getSerialGenerator(keyName).nextId();
    }

    @Autowired
    public void setKgDao(SerialDao serialDao) {
        SerialFactory.setSerialDao(serialDao);
    }
}
