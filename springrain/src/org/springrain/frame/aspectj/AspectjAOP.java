package org.springrain.frame.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectjAOP {
	@Pointcut
	public void Springrain(){
		
	}
	
	@Before("execution(* org.springrain..*(..))")
	public void beforeAdvice() {  
	} 
	
	@Before("execution(* org.springrain..*(..))")
	public void beforeAdvice(JoinPoint joinPoint) {  
	    System.out.println("Before: " + joinPoint.getSignature().getName());  
	} 
}
