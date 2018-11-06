package ge.example.cacheexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CacheService {
    private CacheManager cacheManager;

    @Autowired
    public CacheService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public Integer getInteger(int value){
        return (Integer)cacheManager.getCache("integerCache").get(value).get();
    }

    public int putInteger(int value){
        cacheManager.getCache("integerCache").put(value,value);
        return value;
    }

    public Integer getIntegerFromCache2(int value){
        return (Integer)cacheManager.getCache("integerCache2").get(value).get();
    }

    public int putIntegerInCache2(int value){
        cacheManager.getCache("integerCache2").put(value,value);
        return value;
    }
}