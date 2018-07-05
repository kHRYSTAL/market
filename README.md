# SSM 重点知识

* SpringMVC:DispatcherServlet
* Spring: IOC和AOP(面向切面编程, 动态代理拦截器)
* MyBatis: ORM

## MySql主从分离实现

  *  数据库层面的主从配置实现
  *  代码层面的读写分离实现(无需改动现有代码)
  
  主服务器配置
  
  ```
    vim /etc/my.cnf
    
    设置servier-id
    server-id=1
    打开日志
    log-bin=master-bin
    log-bin-index=master-bin.index
  ```
        
                配置好后重启mysql服务 service mysqld restart
                执行指令 SHOW MASTER STATUS; 查看日志状态是否生成
                可见File列下字段为master-bin.000001
                
  主服务器创建repl账户 并授权
    ```
        create user repl;
        
        GRANT REPLICATION SLAVE ON *.* TO 'repl'@'slave_ip' INDENTIFIED BY 'mysql';
        
        flush privileges;
    ```
    
  从服务器配置
    
  ```
    vim /etc/my.cnf
    设置servier-id
    server-id=2
    设置中继日志 用于接收主服务器发送的日志缓存
    relay-log-bin-index=slave-relay-bin.index
    打开从服务器日志
    relay-log=slave-relay-bin
  ```  
        
                配置好后重启mysql服务 service mysqld restart
                执行指令关联主服务器 并设置进入主服务器user的账号   
                    change master to master_host='ip', master_port=port, master_user='repl',master_password='mysql',
                    master_log_file='master-bin.000001', master_log_pos=0;
                    
                开启主从同步 start slave;
                执行 show slave status \G; 查看状态 竖向显示内容
                如果出现状态错误则不能同步, 需要先停止同步 stop slave;
                     
  
        