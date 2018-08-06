package com.lym.distributed.service;

/**
 * @ClassName IDistributedUniqueNoService
 * @Description
 * @Author LYM
 * @Date 2018/8/4 16:45
 * @Version 1.0
 */
public interface IDistributedUniqueNoService {
    String STRING_NAME = "distributedUniqueNoService";

    Long getUniqueNo(String bizTag);
}
