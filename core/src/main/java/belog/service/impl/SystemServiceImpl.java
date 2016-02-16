package belog.service.impl;


import belog.service.SystemService;
import org.springframework.stereotype.Service;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Beldon
 */
@Service("SystemService")
public class SystemServiceImpl implements SystemService {
    public Map<String, String> getSystemMsg() {
        Map<String, String> map = new HashMap<String, String>();
        map.putAll(getClassLoadingMsg());
        map.putAll(getMemoryMsg());
        return map;
    }

    public Map<String, String> getClassLoadingMsg() {
        Map<String, String> map = new HashMap<String, String>();
        ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();
        classLoadingMXBean.getTotalLoadedClassCount();

        map.put("totalLoaded", String.valueOf(classLoadingMXBean.getTotalLoadedClassCount()));
        map.put("loaded", String.valueOf(classLoadingMXBean.getLoadedClassCount()));
        map.put("unLoaded", String.valueOf(classLoadingMXBean.getUnloadedClassCount()));
        return map;
    }

    public Map<String, String> getMemoryMsg() {
        Map<String, String> map = new HashMap<String, String>();
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        //堆内存
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        long heapInit = heapMemoryUsage.getInit();
        long heapMax = heapMemoryUsage.getMax();
        long heapUsed = heapMemoryUsage.getUsed();

        map.put("heapInit", String.valueOf(heapInit/(1024*1024))); //单位是M
        map.put("heapMax", String.valueOf(heapMax/(1024*1024))); //单位是M
        map.put("heapUsed", String.valueOf(heapUsed/(1024*1024))); //单位是M
        //非堆内存
        MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();

        long notHeapInit = nonHeapMemoryUsage.getInit();
        long notHeapMax = nonHeapMemoryUsage.getMax();
        long notHeapUsed = nonHeapMemoryUsage.getUsed();
        map.put("notHeapInit", String.valueOf(notHeapInit/(1024*1024))); //单位是M
        map.put("notHeapMax", String.valueOf(notHeapMax>=0?notHeapMax/(1024*1024):-1)); //单位是M
        map.put("notHeapUsed", String.valueOf(notHeapUsed/(1024*1024))); //单位是M

        return map;
    }
}
