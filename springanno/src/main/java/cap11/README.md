##线程池
+   FixedThreadPool
    创建固定线程数量的，适用于负载较重的服务器，使用了无界队列
+   SingleThreadExecutor
    创建单个线程，需要顺序执行的任务，不会又多个线程活动，使用了无界队列
+   newCachedThreadPool
    会根据需要来创建新线程执行很多短期异步任务的程序，使用了SynchronousQueue队列
+   newSingleThreadScheduledExecutor
    只包含一个线程，只需要单个线程执行周期任务，保证顺序执行任务
+   newScheduledThreadPool
    需要定期执行周期任务，
##spring声明式事务
+   InfrastructureAdvisorAutoProxyCreator 
    +   1 注册
    +   2 利用后置处理器机制在创建后包装对象，返回一个代理对象（增强）
            代理对象执行方法时，利用拦截器进行调用
+   AnnotationTransactionAttributeSource
    事务增强器主要用事务注解的信息，使用这个类解析事务注解
+   TransactionInterceptor
    保存了事务属性信息，事务管理器（MethodInterceptor）
    当执行目标方法时：
        执行拦截器链
        事务拦截器：
            +   1 先获取事务相关属性
            +   2 获取PlatformTransactionManager事务管理器，直接到容器中获取PlatformTransactionManager bean实例
         执行目标方法：
            +   如果异常：回滚
            +   如果正常：提交   
       
     
    
        