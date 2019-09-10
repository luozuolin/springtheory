package cap10.config;

import cap1.entity.Person;
import cap10.aop.Calculator;
import cap10.aop.LogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/***
 * 只扫描Controller
 *
 * 日志切面类的方法需要动态感知到div（）方法运行
 * 通知方法：
 *          前置通知(@Before)：logStart（）：在我们执行div（）方法之前执行
 *          后置通知(@After)：logEnd（）；在我们执行div（）方法之后执行
 *          返回通知(@AfterReturning)：logRetrun（）：在我们div（）方法正常返回时执行
 *          异常通知(@Exception)：logException（）：在我们div（）方法异常时执行
 *          环绕通知(@Around)：logAround（）：动态代理，执行joinPoint.procced()
 *              执行之前相当于前置通知，执行之后相当于后置通知
 */
@Configuration
@EnableAspectJAutoProxy
public class Cap10MainConfig {
    @Bean
    public LogAspect logAspect(){
        return new LogAspect();
    }
    @Bean
    public Calculator calculator(){
        return  new Calculator();
    }


}
