package org.woodorchid;

import java.lang.annotation.Annotation;

/**
 * @author 韩志雄
 * @date 2023/8/17 23:33
 */
@Deprecated
public class A {
	public static void main(String[] args) {
//		Deprecated annotation = A.class.getAnnotation(Deprecated.class);
//		Annotation[] declaredAnnotations = A.class.getDeclaredAnnotations();
		Annotation[] declaredAnnotations = B.class.getDeclaredAnnotations();


	}
}

@MyAnnotation
class B{

}
