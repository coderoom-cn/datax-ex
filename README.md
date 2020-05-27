# datax-ex
datax 扩展程序
java启动datax ，定时任务增量同步数据。


## 增量
时间戳增量

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

## 手动同步代码
```java

public void test() throws InterruptedException {
    Runtime re = Runtime.getRuntime();
    BufferedReader output;
    try{
        Process  cmd = re.exec("java -Dfile.encoding=utf-8 -Djava.ext.dirs=D:\\Documents\\GitHubs\\datax-ex\\target\\lib -cp D:\\Documents\\GitHubs\\datax-ex\\target\\datax-ex.jar cn.coderoom.Hand " + "2020-04-01 2020-05-01" );

        //将调用结果打印到控制台上
        BufferedReader br = new BufferedReader(new InputStreamReader(cmd.getInputStream()));
        String readLine = br.readLine();
        while (readLine != null) {
            readLine = br.readLine();
            System.out.println(readLine);
        }
        if(br!=null){
            br.close();
        }
        cmd.destroy();
        //cmd=null;

    } catch (IOException ioe){
        ioe.printStackTrace();
    }

}

```