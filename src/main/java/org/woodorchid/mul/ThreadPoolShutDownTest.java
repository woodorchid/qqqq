package org.woodorchid.mul;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author 韩志雄
 * @date 2023/8/15 13:51
 */
public class ThreadPoolShutDownTest {
	public static void main(String[] args) {
		// 获取电脑的线程数
		int threads = Runtime.getRuntime().availableProcessors();
		System.out.println("threads == " + threads);
//	ThreadFactory threadFactory = new ThreadFactoryBuilder();

		// 创建线程池
		ExecutorService executorService = Executors.newFixedThreadPool(2);
//		ExecutorService executorService = new ThreadPoolExecutor(threads, 1, 0L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(1024), null, new ThreadPoolExecutor.AbortPolicy());

		ThreadTest threadTest1 = new ThreadTest();
		ThreadTest threadTest2 = new ThreadTest();
		ThreadTest threadTest3 = new ThreadTest();
		ThreadTest threadTest4 = new ThreadTest();
		ThreadTest threadTest5 = new ThreadTest();
		Thread thread1 = new Thread(threadTest1);
		Thread thread2 = new Thread(threadTest2);
		Thread thread3 = new Thread(threadTest3);
		Thread thread4 = new Thread(threadTest4);
		Thread thread5 = new Thread(threadTest5);



		Future<?> task1 = executorService.submit(thread1);
//		try {
//			Thread.sleep(1000 * 5);
//			System.out.println();
//
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		executorService.shutdown();
		task1.cancel(true);
		Future<?> task2 = executorService.submit(thread2);
		Future<?> task3 = executorService.submit(thread3);

		Future<?> task4 = executorService.submit(thread4);

		Future<?> task5 = executorService.submit(thread5);

		// 关闭线程池


	}
}


class ThreadTest implements Runnable{
	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " --> " + i);
		}
	}
}
