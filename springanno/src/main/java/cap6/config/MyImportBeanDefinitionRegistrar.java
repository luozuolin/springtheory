package cap6.config;

import cap6.bean.Pig;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /***
     *
     * @param annotationMetadata :当前类的注解信息
     * @param beanDefinitionRegistry：BeanDefinition注册类
     *                              把所有需要添加到容器中的bean注入
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata,
                                        BeanDefinitionRegistry beanDefinitionRegistry) {
        if(beanDefinitionRegistry.containsBeanDefinition("cap6.bean.Dog")){
            RootBeanDefinition rootBeanDefinition=new RootBeanDefinition(Pig.class);
            beanDefinitionRegistry.registerBeanDefinition("pig",rootBeanDefinition);
        }
    }
}
