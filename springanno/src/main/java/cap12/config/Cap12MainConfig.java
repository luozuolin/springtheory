package cap12.config;

import cap1.entity.Person;
import cap12.Moon;
import cap7.bean.Bike;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/***
 * 只扫描Controller
 */
@Configuration
@ComponentScan(value ="cap12")
public class Cap12MainConfig {
    @Bean
    public Moon getMoon(){
        return   new Moon();
    }
}
