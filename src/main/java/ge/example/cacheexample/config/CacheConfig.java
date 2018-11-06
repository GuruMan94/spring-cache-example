package ge.example.cacheexample.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    @Primary
    public CacheManager cacheManager(){
        return new EhCacheCacheManager(cacheManagerFactoryBean().getObject());
    }

    @Bean
    public EhCacheManagerFactoryBean cacheManagerFactoryBean(){
        EhCacheManagerFactoryBean emfb = new EhCacheManagerFactoryBean();
        emfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
        emfb.setShared(true);
        return emfb;
    }


    @Bean
    public CacheManager cacheManager2(){
        return new EhCacheCacheManager(cacheManagerFactoryBean2().getObject());
    }

    @Bean
    public EhCacheManagerFactoryBean cacheManagerFactoryBean2(){
        EhCacheManagerFactoryBean emfb = new EhCacheManagerFactoryBean();
        emfb.setConfigLocation(new ClassPathResource("ehcache2.xml"));
        emfb.setShared(true);
        return emfb;
    }
}
