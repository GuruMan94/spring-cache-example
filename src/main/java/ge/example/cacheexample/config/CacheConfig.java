package ge.example.cacheexample.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public EhCacheManagerFactoryBean cacheManagerFactoryBean(){
        EhCacheManagerFactoryBean emfb = new EhCacheManagerFactoryBean();
        emfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
        emfb.setCacheManagerName("cacheManager1");
        return emfb;
    }


    @Bean
    public EhCacheManagerFactoryBean cacheManagerFactoryBean2(){
        EhCacheManagerFactoryBean emfb = new EhCacheManagerFactoryBean();
        emfb.setCacheManagerName("cacheManager2");
        emfb.setConfigLocation(new ClassPathResource("ehcache2.xml"));
        return emfb;
    }

    @Bean
    @Primary
    public CacheManager cacheManager() {
        CompositeCacheManager compositeCacheManager = new CompositeCacheManager();
        List<CacheManager> cacheManagers = new ArrayList<>();
        cacheManagers.add(new EhCacheCacheManager(cacheManagerFactoryBean2().getObject()));
        cacheManagers.add(new EhCacheCacheManager(cacheManagerFactoryBean().getObject()));
        compositeCacheManager.setCacheManagers(cacheManagers);
        compositeCacheManager.setFallbackToNoOpCache(true);
        return compositeCacheManager;
    }
}
