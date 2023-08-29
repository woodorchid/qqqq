package org.woodorchid.recursive;

/**
 * @author 韩志雄
 * @date 2023/6/28 0:05
 */
public class Recursive {
	public static void main(String[] args) {
		System.out.println(sum(1, 100));
	}

	public static int sum(int start,int end){
		return start >= end ? end : start + sum(start+1,end);
	}
}
