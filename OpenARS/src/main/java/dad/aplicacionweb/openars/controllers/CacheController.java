package dad.aplicacionweb.openars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CacheController {

    @Autowired
    private CacheManager resourcesCacheManager;

    @Autowired CacheManager commentCacheManager;

    @GetMapping("/cache/resources")
    public Map<Object, Object> getResourcesFromCache(){
        ConcurrentMapCache resourcesCache = (ConcurrentMapCache) resourcesCacheManager.getCache("resources");
        return resourcesCache.getNativeCache();
    }

    @GetMapping("/cache/comments")
    public Map<Object, Object> getCommentsFromCache(){
        ConcurrentMapCache commentsCache = (ConcurrentMapCache) commentCacheManager.getCache("comments");
        return commentsCache.getNativeCache();
    }

}
