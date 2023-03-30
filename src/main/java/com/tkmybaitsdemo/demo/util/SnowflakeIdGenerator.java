package com.tkmybaitsdemo.demo.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yb
 * @date 2023/03/302227
 **/
public class SnowflakeIdGenerator {

    private final long twepoch = 1623270000000L; //定义一个起始时间戳，此处为2021年6月10日0时0分0秒
    private final long datacenterIdBits = 5L; //定义数据中心ID所占的位数
    private final long workerIdBits = 5L; //定义工作机器ID所占的位数
    private final long sequenceBits = 12L; //定义序列号所占的位数
    private final long maxDatacenterId = ~(-1L << datacenterIdBits); //最大数据中心ID
    private final long maxWorkerId = ~(-1L << workerIdBits); //最大工作机器ID
    private final long sequenceMask = ~(-1L << sequenceBits); //序列号掩码
    private final long workerIdShift = sequenceBits; //工作机器ID左移位数
    private final long datacenterIdShift = sequenceBits + workerIdBits; //数据中心ID左移位数
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits; //时间戳左移位数
    private long workerId; //工作机器ID
    private long datacenterId; //数据中心ID
    private long sequence = 0L; //序列号
    private long lastTimestamp = -1L; //上次生成ID的时间戳

    public SnowflakeIdGenerator(long datacenterId, long workerId) {
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than maxDatacenterId or less than 0");
        }
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException("workerId can't be greater than maxWorkerId or less than 0");
        }
        this.datacenterId = datacenterId;
        this.workerId = workerId;
    }

    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate id for " + (lastTimestamp - timestamp) + " milliseconds");
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        return ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }
}





