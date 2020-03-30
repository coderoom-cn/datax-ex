# datax-ex
datax 扩展程序
jar包启动datax ，定时任务同步数据


https://github.com/huzekang/springboot-datax

java -jar datax-ex.jar > data-ex.log

windows 
java -Djava.ext.dirs=./lib/;$JAVA_HOME/jre/lib/ext -jar datax-ex.jar > data-ex.log

linux
java -Djava.ext.dirs=./lib/:$JAVA_HOME/jre/lib/ext -jar datax-ex.jar > data-ex.log
[分离打包](https://my.oschina.net/junko2013/blog/3020112)
[分离打包](https://my.oschina.net/junko2013/blog/3107795)

## QA 
1、java -jar xxxx.jar
ClassNotFoundException: oracle.jdbc.driver.OracleDriver
[参考java -jar命令运行jar包时指定外部依赖jar包](https://blog.csdn.net/w47_csdn/article/details/80254459)