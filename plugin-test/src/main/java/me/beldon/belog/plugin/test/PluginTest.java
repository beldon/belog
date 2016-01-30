package me.beldon.belog.plugin.test;


import belog.plugin.TestPlugin;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by Beldon
 */
@Service("pluginTest")
public class PluginTest implements TestPlugin {

    static {
        System.out.println("PluginTest[loading]");
    }

    @PostConstruct
    public void init(){
        System.out.println("PluginTest[init]");
    }


    public void plugin() {
        System.out.println("PluginTest[plugin]");
    }


    @PreDestroy
    public void destroy(){
        System.out.println("PluginTest[destroy]");
    }
}
