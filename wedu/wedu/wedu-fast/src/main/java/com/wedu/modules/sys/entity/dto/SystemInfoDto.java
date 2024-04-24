package com.wedu.modules.sys.entity.dto;

import java.util.List;

/**
 * 系统使用数据，包括JVM内存、系统内存、系统磁盘
 * @author santiago
 */
public class SystemInfoDto {

    /**
     * JVM初始的总内存
     */
    public static double jvmTotalMemorySize;

    /**
     * JVM最大可用内存
     */
    public static double jvmMaxMemorySize;

    /**
     * JVM已使用的内存
     */
    public static double jvmUsedMemorySize;

    /**
     * JVM使用比率
     */
    public static int jvmRate;

    /**
     * 总的物理内存
     */
    public static double totalMemorySize;

    /**
     * 剩余的物理内存
     */
    public static double freePhysicalMemorySize;

    /**
     * 已使用物理内存
     */
    public static double usedMemorySize;

    /**
     * 内存使用比率
     */
    public static int memoryRate;

    /**
     * 磁盘使用数据
     */
    public static List<DiskDto> diskDtoList;

    public static class DiskDto{

        /**
         * 磁盘名称
         */
        public String diskName;

        /**
         * 磁盘总大小，单位G
         */
        public double totalDiskSize;

        /**
         * 磁盘空闲大小，单位G
         */
        public double freeDiskSize;

        /**
         * 磁盘使用大小，单位G
         */
        public double unDiskSize;

        /**
         * 磁盘使用比率
         */
        public int diskRate;

    }

    /**
     * CPU使用比率
     */
    public static int cpuRate;

}
