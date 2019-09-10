package cap10.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogAspect {
    @Pointcut("execution(public  int cap10.aop.Calculator.*(..))")
    public void pointCut(){}
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint){
        System.out.println("除法开始，方法：");
        System.out.println(joinPoint.getSignature().getName()+"参数："+ Arrays.asList(joinPoint.getArgs()));
    }
    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint){
        System.out.println("除法结束:结果:"+joinPoint.getSignature().getName());
    }
    @AfterReturning("pointCut()")
    public void logReturn(){
        System.out.println("除法正常返回，返回结果:");
    }
    @AfterThrowing(value = "pointCut()",throwing = "exception")
    public void logException(Exception exception){
        System.out.println("除法异常，异常信息:"+exception.toString());
    }
    @Around("pointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("div环绕开始");
       Object ret= joinPoint.proceed();

       Thread.sleep(1000);
        System.out.println("div环绕结束");
        return ret;
    }
}
