package cap6.config;

import cap6.bean.Monkey;
import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBean implements FactoryBean<Monkey> {

    @Override
    public Monkey getObject() throws Exception {
        return new Monkey();
    }

    @Override
    public Class<?> getObjectType() {
        return Monkey.class;
    }
    @Override
    public boolean isSingleton() {
        return true;
    }
}
