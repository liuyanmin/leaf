package com.lym.cache;

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;

/**
 * Ehcache 缓存方法拦截器核心代码
 */
public class EhCacheInterceptor implements MethodInterceptor, InitializingBean{
	
	private static final Logger logger = Logger.getLogger(EhCacheInterceptor.class);
	
	private Cache cache;
	 
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info(cache + " A cache is required. Use setCache(Cache) to provide one.");
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
        String methodName = invocation.getMethod().getName();
        String className = invocation.getMethod().getDeclaringClass().getName();
        Object[] arguments = invocation.getArguments();
        Object result;

        String cacheKey = getCacheKey(className, methodName, arguments);
        Element element;
        synchronized (this) {
            element = cache.get(cacheKey);
            if (element == null) {
                logger.info(cacheKey + "加入到缓存： " + cache.getName());
                // 调用实际的方法
                result = invocation.proceed();
                element = new Element(cacheKey, (Serializable) result);
                if (element != null && element.getValue() != null && !StringUtils.isEmpty(element.getValue().toString())) {
                    cache.put(element);
                }
            } else {
                logger.info(cacheKey + "使用缓存： " + cache.getName());
            }
        }
        return element.getValue();
	}
	
	/**
     * 返回具体的方法全路径名称 参数
     * @param targetName 全路径
     * @param methodName 方法名称，必须为：getXxxxOnXxx 如：getMrchByIdOnEhCache
     * @param arguments 参数
     * @return 完整方法名称
     */
    private String getCacheKey(String targetName, String methodName, Object[] arguments) {
        StringBuffer sb = new StringBuffer();
        sb.append(targetName).append(".").append(methodName).append(".");
        if ((arguments != null) && (arguments.length != 0)) {
            for (int i = 0; i < arguments.length; i++) {
                StringBuffer valSb = new StringBuffer();
                sb.append(arguments[i]).append("&");
            }
        }
        return sb.toString();
    }
	
	public void setCache(Cache cache) {
        this.cache = cache;
    }

}
