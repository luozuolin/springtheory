package cap7.config;

import cap1.entity.Person;
import cap7.bean.Bike;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/***
 * 只扫描Controller
 */
@Configuration
@ComponentScan(value ="cap7.bean")
public class Cap7MainConfig {
    @Bean(name = "person")
    public Person person(){
        System.out.println("person init :perosn");
        return new Person("james",20);
    }
    @Scope("prototype")
    @Bean(initMethod = "init", destroyMethod = "destory")
    public Bike bike(){
        return  new Bike();
    }
}
