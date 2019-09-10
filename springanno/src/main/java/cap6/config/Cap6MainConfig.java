package cap6.config;

import cap1.entity.Person;
import cap6.bean.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/***
 * 只扫描Controller
 */
@Configuration
@Import(value = {Dog.class,MyImportSelector.class,MyImportBeanDefinitionRegistrar.class})
public class Cap6MainConfig {
    /***
     * 1 @Bean: [导入第三方类或包的组件]，比如Person位第三方的类，需要在我们的IOC容器中使用
     * 2 包扫描+组件的标注注解（@ComponScan:@Controller,@Service,@Reponsitort,@Componet）,
     *  一般针对我么自己写的类
     * 3，@Import：【快速给容器导入一个组件】：注意：@Bean有点简单
     *              a，@Import（要导入的容器的组件）：容器会自动注册这个组建，bean的id位全类名
     *              b,@ImportSelector:是一个接口，返回需要导入容器的组件的全名
     *              c，@ImportBeanDefinitionRegistrar:可以手动添加Bean实例到IOC容器，
     *                  所有Bean的注册可以通过注册导入
     *              d，使用Spring提供的FactoryBean注册
     * 容器启动时初始化person的bean实例
     * @return
     */
    @Bean(name = "person")
    public Person person(){
        System.out.println("person init :perosn");
        return new Person("james",20);
    }
    @Bean
    public MyFactoryBean myFactoryBean(){
        return new MyFactoryBean();
    }

}
