package com.lym.thread;

import com.lym.distributed.dao.DistributedUniqueNoDao;
import com.lym.util.Const;
import com.lym.util.WebApplicationContext;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import java.util.Map;

/**
 * @ClassName UniqueNoThread
 * @Description 获取分布式唯一编号segment 线程
 * @Author LYM
 * @Date 2018/8/4 17:13
 * @Version 1.0
 */
public class UniqueNoThread extends Thread {

    private String bizTag;
    private Cache cache;
    private DistributedUniqueNoDao distributedUniqueNoDao;

    public UniqueNoThread(String bizTag) {
        this.bizTag = bizTag;
        this.cache = (Cache) WebApplicationContext.getBean("cache");
        this.distributedUniqueNoDao = (DistributedUniqueNoDao) WebApplicationContext.getBean("distributedUniqueNoDao");
    }

    @Override
    public void run() {
        Map<String, Object> uniqueIdMap = distributedUniqueNoDao.getUniqueId(bizTag);
        Element e = new Element(Const.LEAF_SEGMENT_2, uniqueIdMap);
        cache.put(e);
    }

}
