##bean的实例
+   单实例：创建IOC容器的时候实例bean就被创建
+   多实例：仅当bean被使用时才创建
IOC{
//加载properties
//申明MAP
//ComponentScan 
//利用反射机制class.Forname,instance bean
// 把我们的instance bean  put MAP

}
##容器对比及总结
+   ApplicationContextAware接口获取IOC容器
+   AnnotationConfigApplicationContext加载配置文件获取的容器
+   把Spring底层的组件可以注入到自定义bean中，ApplicationContextAware是利用
    ApplicationContextAwareProcessor来处理，其他XXXAware都有相关的Processor
    来处理。
##