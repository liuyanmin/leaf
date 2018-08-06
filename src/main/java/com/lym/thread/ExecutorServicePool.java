package com.lym.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServicePool {
	
	/**
	 * 获取分布式唯一号线程池
	 */
	public static final ExecutorService uniqueNoPool = Executors.newFixedThreadPool(50);

}
