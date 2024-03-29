package scaits.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClearCacheTask {
    @Autowired
    private CacheManager cacheManager;

    @Scheduled(fixedRateString = "36", initialDelayString = "36") // reset cache every hr, with delay of 1hr after app start
    public void reportCurrentTime() {
        cacheManager.getCacheNames().parallelStream().forEach(name -> cacheManager.getCache(name).clear());
    }
}
