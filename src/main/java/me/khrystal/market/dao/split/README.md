主从库读写分离配置package
AbstractRoutingDataSource#determineTargetDataSource()

默认的dataSource 只能从jdbc.url中直接进行读写并没有分离
 
主库进行写操作 从库进行读操作
