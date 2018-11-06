package ge.example.cacheexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    private CacheManager cacheManager;
    private CacheManager cacheManager2;

    @Autowired
    public CacheService(CacheManager cacheManager, CacheManager cacheManager2) {
        this.cacheManager = cacheManager;
        this.cacheManager2 = cacheManager2;
    }

    public Integer getInteger(int value){
        return (Integer)cacheManager.getCache("integerCache").get(value).get();
    }

    public int putInteger(int value){
        cacheManager.getCache("integerCache").put(value,value);
        return value;
    }

    public Integer getIntegerFromCache2(int value){
        return (Integer)cacheManager2.getCache("integerCache2").get(value).get();
    }

    public int putIntegerInCache2(int value){
        cacheManager2.getCache("integerCache2").put(value,value);
        return value;
    }
}
