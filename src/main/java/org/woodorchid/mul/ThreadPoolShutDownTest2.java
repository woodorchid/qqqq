package org.woodorchid.mul;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author 韩志雄
 * @date 2023/8/15 13:57
 */
public class ThreadPoolShutDownTest2 {
	public static void main(String[] args) throws InterruptedException {
		// 获取电脑的线程数
		int threads = Runtime.getRuntime().availableProcessors();
		System.out.println("threads == " + threads);

		// 创建线程池
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		List<Thread> list = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			list.add(new Thread(() -> {
				long l = System.currentTimeMillis();
				Thread.currentThread().setName("");
				for (int j = 0; j < 2000 && !Thread.currentThread().isInterrupted(); j++) {
					for (int k = 0; k < 10000; k++) {
						System.out.print("j="+j+"&k="+k);
					}
					System.out.println();
					System.out.println(Thread.currentThread().getName() + " --> " + j);
				}
				long l1 = System.currentTimeMillis();
				System.out.println("线程执行耗时"+(l1-l)/1000);
			}, "线程" + i));
		}
		Future<?> submit = executorService.submit(list.get(0));
		Thread.sleep(5000);
//		submit.cancel(true);
	}
}
