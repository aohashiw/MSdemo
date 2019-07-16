package com.aohashi.demo.utils;

public class SnowFlakeIdWorker {
    private final long twepoch = 146349057900L;

    private final long workerIdBits = 5L;

    private final long datacenterIdBits = 5L;

    private final long maxWorkerId = -1L ^ (-1L <<workerIdBits);

    private final long maxDataCenterId = (-1L)^(-1L << datacenterIdBits);

    private final long sequenceBits = 12L;

    private final long workerIdShift = sequenceBits;

    private final long datacenterIdShift = sequenceBits + workerIdBits;

    private final long timestampleLeftShift = sequenceBits + workerIdBits +datacenterIdBits;

    private final long getSequenceMask = -1L ^ (-1L <<sequenceBits);

    private long workerId;

    private long datacenterId;

    private long sequence = 0L;

    private long lastTimestamp = -1l;

    public SnowFlakeIdWorker(long workerId,long datacenterId){
        if (workerId > maxDataCenterId){

        }
    }
}
