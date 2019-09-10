package mvc.common;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class GPDispatcherServlet extends HttpServlet {
    private Properties conetxConfig=new Properties();
    private List<String> classNames=new ArrayList<>();
    private Map<String,Object> ioc=new HashMap<>();
    private Map<String  ,Method> handler=new HashMap<>();
    @Override
    public void init(ServletConfig config)   {
       //1.加载配置文件


        try {
            doLoadConfig(config.getInitParameter("contextConfigLocation"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        //2,解析配置文件，并且读取配置文件扫描
        doScanner(conetxConfig.getProperty("scanPackage"));
        //3.初始化扫描的所有类，并且将其放入到IOC容器
        doInstance();
        //4,完成自动化注入的工作，DI
        doAutowried();
        //5.初始化HandlerMapping
        initHandlerMapping();
        System.out.println("GPDispatcherServlet 初始化完成。。");
    }
    private void doLoadConfig(String contextConfigLocation) throws  IOException {
        InputStream inputStream=this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            conetxConfig.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(inputStream !=null){
                inputStream.close();
            }
        }
    }

    private void doScanner(String scanPackage) {
        //拿到包名的实际字符串
        URL url=this.getClass().getClassLoader()
                .getResource("/"+scanPackage.replaceAll("\\.","/"));
        File classDir=new File(url.getFile());
        for(File file:classDir.listFiles()){
            if(file.isDirectory()){
                doScanner(scanPackage+"."+file.getName());
            }else
            {
                String  className=(scanPackage+"."+file.getName().replace(".class",""));
                classNames.add(className);
            }
        }
    }
    private void doAutowried() {
        if(ioc.isEmpty()){
            return;
        }
        for(Map.Entry<String,Object> entry:ioc.entrySet()){
            Field[] fields=entry.getValue().getClass().getDeclaredFields();
            for(Field field:fields){
                if(!field.isAnnotationPresent(GPAutowired.class)){
                    continue;
                }
                GPAutowired autowired=field.getAnnotation(GPAutowired.class);
                String  beanName=autowired.value().trim();
                if(beanName.equals("")){
                    beanName=lowerFisrtCase(field.getType().getSimpleName());
                }
                field.setAccessible(true);
                try {
                    field.set(entry.getValue(),ioc.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }

    }
    private void initHandlerMapping() {
        if(ioc.isEmpty()){
            return;
        }
        for(Map.Entry<String,Object> entry:ioc.entrySet()){
            Class<?> clazz=entry.getValue().getClass();
            if(!clazz.isAnnotationPresent(GPController.class)){
                continue;
            }
            String  baseUrl="";
            if(clazz.isAnnotationPresent(GPRequestMapping.class)){
                GPRequestMapping requestMapping=clazz.getAnnotation(GPRequestMapping.class);
                baseUrl=requestMapping.value();
            }
            Method[] methods=clazz.getMethods();
            for(Method method:methods){
                if(method.isAnnotationPresent(GPRequestMapping.class)){
                    GPRequestMapping requestMapping=method.getAnnotation(GPRequestMapping.class);
                    String  url=(baseUrl+"/"+requestMapping.value()).replaceAll("/+","/");
                    handler.put(url,method);
                    System.out.println("Mapped:"+url+":"+method);
                }
            }

        }
    }
    private String   lowerFisrtCase(String  string){
      return   string.substring(0,1).toLowerCase()+string.substring(1);
    }
    private void doInstance() {
        if(classNames.isEmpty()){
            return;
        }
        for(String className:classNames){
            try {
                Class<?> clazz=Class.forName(className);
                if(clazz.isAnnotationPresent(GPController.class)){
                    String  beanName=lowerFisrtCase(clazz.getSimpleName());
                    ioc.put(beanName,clazz.newInstance());

                }else if(clazz.isAnnotationPresent(GPService.class)){
                    GPService gpService=clazz.getAnnotation(GPService.class);
                    String  beanName=gpService.value();
                    if(beanName.trim().equals("")){
                        beanName=lowerFisrtCase(clazz.getSimpleName());
                    }
                    Object   instance=clazz.newInstance();
                    if(ioc.containsKey(beanName)){
                        throw new Exception("The beanName is defined.");
                    }
                    ioc.put(beanName,instance);
                    Class<?> [] interfaces=clazz.getInterfaces();
                    for(Class<?> i:interfaces){
                        ioc.put(lowerFisrtCase(i.getSimpleName()),instance);
                    }

                }else
                {
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
       // super.doGet(req, resp);
        try {
            doDispath(req,  resp);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void doDispath(HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, IllegalAccessException {
        String  uri=req.getRequestURI();
        String  contextPath=req.getContextPath();
        uri=uri.replace(contextPath,"").replaceAll("/+","/");
        if(!handler.containsKey(uri)){
            resp.getWriter().write("Not Found...");
            return;
        }
        resp.getWriter().write(handler.get(uri).toString());
        Map<String,String[]> params=req.getParameterMap();
        Method method=handler.get(uri);
        String beanName=lowerFisrtCase(method.getDeclaringClass().getSimpleName());
        Object  instance=ioc.get(beanName);
        method.invoke(instance,new Object[]{req,resp,params.get("name")[0]});

        System.out.println(handler.get(uri));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
      //  super.doPost(req, resp);
        try {
            doDispath(req,  resp);
        } catch (IOException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
