import cap1.config.MainConfig;
import cap1.entity.Person;
import cap10.aop.Calculator;
import cap6.config.MyFactoryBean;
import cap9.dao.TestDao;
import cap9.service.TestService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanTests {
    @Test
    public   void testBeans(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("beans.xml");
        Person person= (Person) applicationContext.getBean("person");
        System.out.println(person);
    }

    @Test
    public void testConfiguration(){
        ApplicationContext annotation=new AnnotationConfigApplicationContext(MainConfig.class);
        Person person= (Person) annotation.getBean("person01");
        System.out.println(person);
        String[] names=annotation.getBeanNamesForType(Person.class);
        for(String name:names){
            System.out.println(name);
        }
    }
    @Test
    public   void cap2Test(){
        ApplicationContext annotation=new AnnotationConfigApplicationContext(cap2.config.Cap2MainConfig.class);
        String[] names=annotation.getBeanDefinitionNames();
        for(String name:names){
            System.out.println(name);
        }
    }
    @Test
    public   void cap2TestExclude(){
        ApplicationContext annotation=new AnnotationConfigApplicationContext(cap2.config.Cap2MainConfigExclude.class);
        String[] names=annotation.getBeanDefinitionNames();
        for(String name:names){
            System.out.println(name);
        }
    }

    @Test
    public   void Cap2MainConfigCustomer(){
        ApplicationContext annotation=new AnnotationConfigApplicationContext(cap2.config.Cap2MainConfigCustomer.class);
        String[] names=annotation.getBeanDefinitionNames();
        for(String name:names){
            System.out.println(name);
        }
    }

    @Test
    public   void Cap3Main(){
        ApplicationContext annotation=new AnnotationConfigApplicationContext(cap3.config.Cap3MainConfig1.class);


        Object object1=annotation.getBean("bean01");
        Object object2=annotation.getBean("bean01");
        System.out.println(object1==object2);
    }
    @Test
    public   void Cap4Main(){
        ApplicationContext annotation=new AnnotationConfigApplicationContext(cap4.config.Cap4MainConfig.class);
        System.out.println("容器创建完成。。。");
        Object object1=annotation.getBean("bean01");
        Object object2=annotation.getBean("bean01");
        System.out.println(object1==object2);
    }

    @Test
    public   void Cap5Main(){
        ApplicationContext annotation=new AnnotationConfigApplicationContext(cap5.config.Cap5MainConfig.class);
        System.out.println("容器创建完成。。。");
       // System.out.println(object1==object2);
    }

    @Test
    public   void Cap6Main(){
        ApplicationContext annotation=new AnnotationConfigApplicationContext(cap6.config.Cap6MainConfig.class);
        System.out.println("容器创建完成。。。");
        String[] names=annotation.getBeanDefinitionNames();
      Object o= annotation.getBean("myFactoryBean");
        for(String name:names){
            System.out.println(name);
        }
    }

    @Test
    public   void Cap7Main(){
        ApplicationContext annotation=new AnnotationConfigApplicationContext(cap7.config.Cap7MainConfig.class);
        System.out.println("容器创建完成。。。");
        ((AnnotationConfigApplicationContext) annotation).close();
    }

    @Test
    public   void Cap9Main(){
        ApplicationContext annotation=new AnnotationConfigApplicationContext(cap9.config.Cap9MainConfig.class);
        System.out.println("容器创建完成。。。");
        TestService  testService=annotation.getBean(TestService.class);
       TestDao testDao=annotation.getBean(TestDao.class);
        testService.printDao();

        System.out.println("++++++++++++++++++++++++++++++++++");
        String[] names=annotation.getBeanDefinitionNames();
        for(String name:names){
            System.out.println(name);
        }
    }

    @Test
    public   void Cap9Main1(){
        ApplicationContext annotation=new AnnotationConfigApplicationContext(cap9.config.Cap9MainConfig.class);
        System.out.println("容器创建完成。。。");
        System.out.println("++++++++++++++++++++++++++++++++++");
        String[] names=annotation.getBeanDefinitionNames();
        for(String name:names){
            System.out.println(name);
        }
        System.out.println(annotation);
    }

    @Test
    public   void Cap10Main(){
        ApplicationContext annotation=new AnnotationConfigApplicationContext(cap10.config.Cap10MainConfig.class);
        Calculator calculator= (Calculator) annotation.getBean("calculator");
        System.out.println("容器创建完成。。。");
        /***
         * 没有捕获异常：
         * div环绕开始
         * 除法开始
         * 除法结束
         * 除法异常，异常信息:
         *
         * 捕获异常：
         *div环绕开始
         * 除法开始
         * 除法结束
         * 除法异常，异常信息:
         * java.lang.ArithmeticException: / by zero
         *
         */
        try {
            System.out.println(calculator.div(10,0));
        }catch (Exception ex){
            System.out.println(ex.toString());
        }


        System.out.println(annotation);
    }



    @Test
    public   void Cap12Main(){
        ApplicationContext annotation=new AnnotationConfigApplicationContext(cap12.config.Cap12MainConfig.class);
        System.out.println("容器创建完成。。。");

    }
}
