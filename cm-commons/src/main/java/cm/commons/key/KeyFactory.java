package cm.commons.key;

import cm.commons.dao.KeyGeneratorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 主键工厂
 *
 * @author leizhenchun
 */
@Service
public final class KeyFactory {

    private static final int INCREMENT_BY_DEF = 1;
    private static KeyGeneratorDao kgDao;
    private static ConcurrentMap<String, KeyGenerator> generators = new ConcurrentHashMap<String, KeyGenerator>();

    private static void setKg(KeyGeneratorDao kgDao1) {
        KeyFactory.kgDao = kgDao1;
    }

    private static KeyGenerator getKeyGenerator(String keyName) {
        if (generators.containsKey(keyName)) {
            return generators.get(keyName);
        }

        KeyGenerator generator = new KeyGeneratorImpl(kgDao, keyName, INCREMENT_BY_DEF);
        generators.put(keyName, generator);
        return generator;
    }

    public static void clear() {
        generators.clear();
    }

    public static Long nextId(String keyName) {
        return getKeyGenerator(keyName).nextKey();
    }

    @Autowired
    public void setKgDao(KeyGeneratorDao kgDao) {
        KeyFactory.setKg(kgDao);
    }

    public Long nextKey(String keyName) {
        return getKeyGenerator(keyName).nextKey();
    }


}
