package cap12.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("call  postProcessBeanFactory");
        //所有bean定义，已经加载到beanFactory，但是bean还没有实例化
        int   count=beanFactory.getBeanDefinitionCount();
        String[] beanDefName=beanFactory.getBeanDefinitionNames();
        System.out.println("当前beanFactory中有"+count+"个bean");
        System.out.println("分别是："+ Arrays.asList(beanDefName));

    }
}
