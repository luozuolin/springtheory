package cap4.config;

import cap1.entity.Person;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

/***
 * 只扫描Controller
 */
@Configuration
public class Cap4MainConfig {
    /***
     * 懒加载：主要是正对单实例bean：默认在容器启动时创建对象
     * 懒加载：容器启动时懒加载不创建对象，仅当第一次使用bean时才创建完成对象初始化。
      * @return
     */
    @Lazy
    @Bean(name = "bean01")
    public Person person(){
        return new Person("lisi",20);
    }
}
