package cm.commons.key;

import cm.commons.dao.KeyGeneratorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class KeyGeneratorImpl implements KeyGenerator {

    private long nextId;
    private long maxId;
    private String tableName;
    private int incrementBy;

    @Autowired
    private KeyGeneratorDao kgDao;

    public KeyGeneratorImpl() {

    }

    public KeyGeneratorImpl(KeyGeneratorDao kgDao, String tablename, int incrementby) {
        this.kgDao = kgDao;
        this.tableName = tablename;
        this.incrementBy = incrementby;
        nextId = 0;
        maxId = 0;
    }


    public synchronized Long nextKey() {
        if (this.nextId == this.maxId) {
            reserveIds();
        }
        return Long.valueOf(nextId++);
    }

    private void reserveIds() {
        nextId = kgDao.getNextId(tableName, incrementBy);
        maxId = nextId + incrementBy;
    }

}
