package com.lym.distributed.dao;

import com.lym.distributed.entity.DistributedUniqueNoConf;
import com.lym.distributed.mapper.DistributedUniqueNoConfMapper;
import com.lym.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName DistributedUniqueNoDao
 * @Description
 * @Author LYM
 * @Date 2018/7/06 10:38
 * @Version 1.0
 */
@Repository
public class DistributedUniqueNoDao {

    @Autowired
    private DistributedUniqueNoConfMapper distributedUniqueNoConfMapper;

    /**
     * 获取全局唯一编号
     * @param bizTag 业务类型
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 150, rollbackFor = Exception.class)
    public Map<String, Object> getUniqueId(String bizTag) {
        DistributedUniqueNoConf conf = distributedUniqueNoConfMapper.selectByBizTag(bizTag);
        distributedUniqueNoConfMapper.updateMaxId(bizTag);
        Map<String, Object> map = new HashMap<>();
        map.put(Const.MAX_ID, conf.getMaxId());
        map.put(Const.STEP, conf.getStep());
        return map;
    }

}
