package com.vmware.tanzu.gemfire.helloworld;

import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.config.annotation.*;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;
import org.springframework.data.gemfire.client.ClientCacheFactoryBean;
import org.springframework.context.annotation.Bean;

@Configuration
@ClientCacheApplication(name = "GemFireSpringClient", logLevel = "error")
@EnablePdx
@EnablePool(name = "gemfirePool")
@EnableGemfireRepositories(basePackages = "com.vmware.tanzu.gemfire.helloworld")
public class GemFireConfig {

    @Bean("Region1")
    public ClientRegionFactoryBean<String, String> helloRegion(ClientCacheFactoryBean gemfireCache) throws Exception {
        ClientRegionFactoryBean<String, String> region = new ClientRegionFactoryBean<>();
        region.setCache(gemfireCache.getObject());
        region.setName("Region1");
        region.setShortcut(ClientRegionShortcut.PROXY);
        region.setPoolName("gemfirePool");
        return region;
    }
}