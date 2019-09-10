package cap1.config;

import cap1.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfig {
    @Bean(name = "bean01")
    public Person person(){
        return new Person("lisi",20);
    }

}
