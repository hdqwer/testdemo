package com.wedu.modules.sys.controller;

import com.wedu.common.utils.R;
import com.wedu.modules.sys.entity.dto.SystemInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * 获取系统属性信息
 * @author santiago
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sys/info")
public class SysInfoController {

    @ResponseBody
    @GetMapping("/getSysInfo")
    public R getSysInfo() {
        SysInfo sysInfo = new SysInfo();
        sysInfo.setJvmMaxMemorySize(SystemInfoDto.jvmMaxMemorySize);
        sysInfo.setJvmTotalMemorySize(SystemInfoDto.jvmTotalMemorySize);
        sysInfo.setJvmUsedMemorySize(SystemInfoDto.jvmUsedMemorySize);
        sysInfo.setJvmRate(SystemInfoDto.jvmRate);
        sysInfo.setTotalMemorySize(SystemInfoDto.totalMemorySize);
        sysInfo.setFreePhysicalMemorySize(SystemInfoDto.freePhysicalMemorySize);
        sysInfo.setUsedMemorySize(SystemInfoDto.usedMemorySize);
        sysInfo.setMemoryRate(SystemInfoDto.memoryRate);
        sysInfo.setDiskDtoList(Collections.singletonList(SystemInfoDto.diskDtoList));
        sysInfo.setCpuRate(SystemInfoDto.cpuRate);
        return R.ok().put("data",sysInfo);
    }

    @Data
    private class SysInfo{
        /**
         * JVM初始的总内存
         */
        public double jvmTotalMemorySize;

        /**
         * JVM最大可用内存
         */
        public double jvmMaxMemorySize;

        /**
         * JVM已使用的内存
         */
        public double jvmUsedMemorySize;

        /**
         * JVM使用比率
         */
        public int jvmRate;

        /**
         * 总的物理内存
         */
        public double totalMemorySize;

        /**
         * 剩余的物理内存
         */
        public double freePhysicalMemorySize;

        /**
         * 已使用物理内存
         */
        public double usedMemorySize;

        /**
         * 内存使用比率
         */
        public int memoryRate;

        /**
         * 磁盘使用数据
         */
        public List<Object> diskDtoList;

        /**
         * CPU占用率
         */
        public int cpuRate;
    }
}
