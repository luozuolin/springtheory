package cap5.config;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class LinCondiction implements Condition {
    /***
     * 可以把我们的Java实例BEAN通过FactoryBean注入到容器。
     * BeanFactory：从我们容器中获取实例化后的bean
     *
     * @param conditionContext :判断条件上下文
     * @param annotatedTypeMetadata
     * @return
     */
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        ConfigurableListableBeanFactory beanFactory=conditionContext.getBeanFactory();
        Environment environment=conditionContext.getEnvironment();
        String osName=environment.getProperty("os.name");
        if(osName.contains("linux")){
            return  true;
        }
        return false;
    }
}
