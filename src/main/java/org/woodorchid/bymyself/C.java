package org.woodorchid.bymyself;

import java.util.concurrent.CountDownLatch;

/**
 * @author 韩志雄
 * @date 2023/8/14 12:09
 */
public class C {
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(1);
		new Thread(()->{
			for (int i = 0; i < 10000; i++) {
				System.out.println("正在执行中"+i);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			countDownLatch.countDown();
		});
		countDownLatch.await();
	}
}
