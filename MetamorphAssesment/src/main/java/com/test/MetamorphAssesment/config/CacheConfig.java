package com.test.MetamorphAssesment.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
public class CacheConfig {

	@Bean
    public CacheManager cacheManager() {

        CaffeineCacheManager cacheManager = new CaffeineCacheManager("apodCache");

        cacheManager.setCaffeine(
                Caffeine.newBuilder()
                        .maximumSize(100)              // max 100 entries
                        .expireAfterWrite(12, TimeUnit.HOURS) // expire after 12 hrs
        );

        return cacheManager;
    }
	
}
