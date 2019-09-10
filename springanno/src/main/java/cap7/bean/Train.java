package cap7.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class Train implements InitializingBean, DisposableBean, BeanPostProcessor {
    public Train(){
        System.out.println("Train constructor .....");
    }
    @Override
    public void destroy() throws Exception {
        System.out.println("Train destory...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Tain afterPropertiesSet....");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization...");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization...");
        return bean;
    }

}
