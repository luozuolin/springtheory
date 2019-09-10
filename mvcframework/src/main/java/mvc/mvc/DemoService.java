package mvc.mvc;

import mvc.common.GPService;

@GPService
public class DemoService  implements  IService{
    @Override
    public   String   get(String  name){
        return   "Hello ,"+name;
    }
}
