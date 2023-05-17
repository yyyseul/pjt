package spring.service.aop.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/*
 *FileName : TestAspectJ02.java :: annotation 사용 Advisor(Aspect) 
 *	ㅇ TestAspectJ01.java ==> work() method : point cut 사용
 *	ㅇ TestAspectJ02.java ==> advice 에 직접 point cut 를 적용   
 */

//==>Advisor(Aspect) Definition (Aspect = advice+point cut )
@Aspect
//===================> 추가된부분=======================//
@Component
public class PojoAspectJ {

	///Constructor
	public PojoAspectJ() {
		System.out.println(":: TestAspectJ02 Default Cosntructor");
	}

	//==>Advice 에 point cut 정의
	//==> execution(*spring.service.emp.*.*(..)::aop package sub package 모든 Method
	@Around("execution(* spring.service..*Impl.*(..)  )")
	public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
			
		System.out.println("[Around before] "+getClass()+".invoke() start.....");
		System.out.println("[Around before] 타겍 객체 :"+
													joinPoint.getTarget().getClass().getName());
		System.out.println("[Around before] 타겍 객체의 호출 될 method :"+
													joinPoint.getSignature().getName());
		if(joinPoint.getArgs().length !=0){
			System.out.println("[Around before] 타겟 객체의 호출할 "+
			                                    "method에 전달되는 인자 : "+ joinPoint.getArgs()[0]);
		}
		//==> 타겟 객체의 Method 를 호출 하는 부분 
		Object obj = joinPoint.proceed();

		System.out.println("[Around after] 타겟 객체의 호출후 return value  : "+obj);
		System.out.println("[Around after] "+getClass()+".invoke() end.....");

		return obj;
	}
}//end of class