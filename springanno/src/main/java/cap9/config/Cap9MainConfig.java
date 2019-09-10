package cap9.config;

import cap1.entity.Person;
import cap9.dao.TestDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/***
 * 只扫描Controller
 */
@Configuration
@ComponentScan(value ="cap9")
public class Cap9MainConfig {
    @Bean(name = "person")
    public Person person(){
        System.out.println("person init :perosn");
        return new Person("james",20);
    }
    //@Primary:spring进行自动装载时默认首选的bean
    @Bean("testDao")
    public TestDao testDao(){
        TestDao testDao=new TestDao();
        testDao.setFlag("2");
        return  testDao;
    }
}
