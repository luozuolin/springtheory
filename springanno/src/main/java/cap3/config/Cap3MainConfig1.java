package cap3.config;

import cap1.entity.Person;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

/***
 * 只扫描Controller
 */
@Configuration
@ComponentScan(value = "cap3",useDefaultFilters = false,includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
})
public class Cap3MainConfig1 {
    @Scope("prototype")
    @Bean(name = "bean01")
    public Person person(){
        return new Person("lisi",20);
    }
}
