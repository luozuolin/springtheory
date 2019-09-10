package cap5.config;

import cap1.entity.Person;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import sun.awt.OSInfo;
import sun.misc.OSEnvironment;

/***
 * 只扫描Controller
 */
@Configuration
public class Cap5MainConfig {
    @Bean(name = "person")
    public Person person(){
        System.out.println("person init :perosn");
        return new Person("james",20);
    }

    @Bean(name = "lison")
    @Conditional(WinCondiction.class)
    public Person lison(){
        System.out.println("person init :lison");
        return new Person("Lison",58);
    }

    @Bean(name = "james")
    @Conditional(LinCondiction.class)
    public Person james(){
        System.out.println("person init :james");
        return new Person("james",30);
    }
}
