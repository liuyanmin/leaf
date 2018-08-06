package com.lym.distributed.service.impl;

import com.lym.distributed.service.IDistributedUniqueNoService;
import com.lym.distributed.dao.DistributedUniqueNoDao;
import com.lym.thread.ExecutorServicePool;
import com.lym.thread.UniqueNoThread;
import com.lym.util.Const;
import com.lym.util.LockUtil;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName DistributedUniqueNoServiceImpl
 * @Description Leaf Service
 * @Author LYM
 * @Date 2018/8/4 16:45
 * @Version 1.0
 */
@Service(IDistributedUniqueNoService.STRING_NAME)
public class DistributedUniqueNoServiceImpl implements IDistributedUniqueNoService {
    private static final Logger logger = Logger.getLogger(DistributedUniqueNoServiceImpl.class);

    @Autowired
    private DistributedUniqueNoDao distributedUniqueNoDao;
    @Autowired
    private Cache cache;


    @Override
    public Long getUniqueNo(String bizTag) {
        if (bizTag == null) {
            return null;
        }
        synchronized (LockUtil.getInstance()) {
            Element element = cache.get(Const.LEAF_SEGMENT_1);
            if (element == null) {
                Map<String, Object> uniqueIdMap = distributedUniqueNoDao.getUniqueId(bizTag);
                Long maxId = (Long) uniqueIdMap.get(Const.MAX_ID);
                uniqueIdMap.put(Const.CURRENT_ID, maxId + 1);// 当前ID
                Element element1 = new Element(Const.LEAF_SEGMENT_1, uniqueIdMap);
                cache.put(element1);
                return maxId;
            } else {
                Object valObj = element.getObjectValue();
                Map<String, Object> uniqueIdMap = (Map<String, Object>) valObj;
                Long maxId = (Long) uniqueIdMap.get(Const.MAX_ID);
                Integer step = (Integer) uniqueIdMap.get(Const.STEP);
                Long id = (Long) uniqueIdMap.get(Const.CURRENT_ID);

                // 判断是否应该获取第二段
                // 当第一段使用到10%时，开始获取第二段；当第一段使用90%时，判断第二段是否获取，若没有获取，则获取第二段数据
                if ((((id - maxId)*10 / step == 1 || (id - maxId)*10 / step == 9) && (id - maxId)*10 % step == 0)) {
                    logger.info("id:"+id+",maxId:"+maxId);
                    element = cache.get(Const.LEAF_SEGMENT_2);
                    if (element == null) {
                        ExecutorServicePool.uniqueNoPool.execute(new UniqueNoThread(bizTag));
                    }
                }

                // 判断第一段是否用完
                if (maxId + step == id) {
                    // 把第二段内容给第一段
                    // 从数据库获取第二段是个异步的过程，所以当第一段使用完时，需要等第二段获取到才可以使用
                    do {
                        element = cache.get(Const.LEAF_SEGMENT_2);
                    } while (element == null);

                    // 把获取到的第二段内容赋值给第一段，然后删除第二段的内容
                    valObj = element.getObjectValue();
                    Element e = new Element(Const.LEAF_SEGMENT_1, valObj);
                    cache.put(e);
                    cache.remove(Const.LEAF_SEGMENT_2);

                    // 执行当前ID+1操作
                    uniqueIdMap = (Map<String, Object>) valObj;
                    maxId = (Long) uniqueIdMap.get(Const.MAX_ID);
                    uniqueIdMap.put(Const.CURRENT_ID, maxId + 1);// 当前ID
                    e = new Element(Const.LEAF_SEGMENT_1, uniqueIdMap);
                    cache.put(e);
                    return maxId;
                }

                uniqueIdMap.put(Const.CURRENT_ID, id + 1);// 当前ID
                Element element1 = new Element(Const.LEAF_SEGMENT_1, uniqueIdMap);
                cache.put(element1);
                return id;
            }
        }
    }

}
