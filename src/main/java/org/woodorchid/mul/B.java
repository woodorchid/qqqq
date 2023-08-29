package org.woodorchid.mul;

import java.io.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 韩志雄
 * @date 2023/8/14 13:56
 */
public class B {
	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		lock.lock();

		lock.unlock();
	}
}
class S implements Runnable{

	private Condition condition;
	private boolean flag;

	public S(Condition c,boolean flag){
		this.condition = c;
		this.flag = flag;
	}

	@Override
	public void run() {
		BufferedReader in = null;
		BufferedWriter out = null;
		try {
			in = new BufferedReader(new FileReader("./test.md"));
			out = new BufferedWriter(new FileWriter("./to.md"));
			String s=null;
			while ((s=in.readLine()) != null ){
				if (!flag){
					condition.await();
				}else {
					condition.signal();
					out.write(s);
					out.newLine();
				}
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
