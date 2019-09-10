package cap9.service;

import cap9.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestService {
    /***
     * Resource 与Autowired ：
     *  效果一样，与AutoWired一样可以装配bean
     *  Autowired:支持required=false
     *   Resource不支持Primary，没有就报错
     *  Inject://需要额外引入javax.inject包
     *  和与Autowired功能差不多，支持Primary，
     *  非spring环境可以用Inject注解
     */
   // @Autowired
   // @Inject
    @Resource
    private TestDao testDao;
    public   void printDao(){
        System.out.println(testDao);
    }
}
