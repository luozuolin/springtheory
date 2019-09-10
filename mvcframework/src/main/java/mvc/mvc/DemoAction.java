package mvc.mvc;

import mvc.common.GPAutowired;
import mvc.common.GPController;
import mvc.common.GPRequestMapping;
import mvc.common.GPRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@GPController
@GPRequestMapping("/demo")
public class DemoAction {
    @GPAutowired
    DemoService  demoService;
    @GPRequestMapping("/query.json")
    public   void query(HttpServletRequest  request, HttpServletResponse resp,
                        @GPRequestParam("name") String  name) throws IOException {
        String  result=demoService.get(name);
        resp.getWriter().write(result);
    }
    @GPRequestMapping("/add.json")
    public   void add(HttpServletRequest  request, HttpServletResponse resp,
                      @GPRequestParam("a") Integer  a,@GPRequestParam("b") Integer  b) throws IOException {
        resp.getWriter().write(a+"+"+b+"="+(a+b));
    }

}
