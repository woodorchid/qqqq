package org.woodorchid.docabout;

/**
 * @author 韩志雄
 * @date 2023/6/7 18:17
 */
public class A {
	public static void main(String[] args) {
		String a = "| 11   | [PTTL key](https:()//www.redis.net.cn/order/3538.html) 以毫秒为单位";
		System.out.println(a.matches("^\\(.*\\)$"));

		String b = "(a)";
		System.out.println(a.replaceAll("\\(.*\\)",""));
	}
}
