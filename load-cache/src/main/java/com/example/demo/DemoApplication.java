package com.example.loadcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.*;
import org.apache.geode.cache.GemFireCache;
import java.util.Date;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private GemFireCache gemfireCache;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ClientCache cache = (ClientCache) gemfireCache;

        // Create the region
        Region<String, String> region =
                cache.<String,String>createClientRegionFactory
                                (ClientRegionShortcut.CACHING_PROXY)
                        .create("Hello");

        System.out.println("Putting 1,000,000 entries");
        System.out.println("Start: " + new Date());
        for(int i=1; i<1000001; i++)
            region.put(""+i, " " + i + "Hello World");
        System.out.println("Finish: " + new Date());

    }
}
