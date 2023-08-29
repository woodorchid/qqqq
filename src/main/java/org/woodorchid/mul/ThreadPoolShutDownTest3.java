package org.woodorchid.mul;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author 韩志雄
 * @date 2023/8/15 17:26
 */

public class ThreadPoolShutDownTest3 {
	public static void main(String[] args) {
		// 获取电脑的线程数
		int threads = Runtime.getRuntime().availableProcessors();
		System.out.println("threads == " + threads);

		// 创建线程池
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		List<Thread> list = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			list.add(new Thread(()->{
				for (int j = 0;j < 10; j++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + " --> " + j);
				}
			},"线程" + i));
		}
		Future<?> submit = executorService.submit(list.get(0));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		submit.cancel(true);
//		executorService.submit(list.get(1));
//		executorService.submit(list.get(2));
//		executorService.submit(list.get(3));
//		executorService.submit(list.get(4));
		executorService.shutdown();
	}
}
