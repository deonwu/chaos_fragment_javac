# chaos_fragment_javac

java 片段代码编译，是用于混沌工程，业务演练的一种技术。通过动态编译部分java代码，让后连接到运行时JVM。实现业务演练的目的。


# 相关技术

* [chaosblade-exec-jvm](https://github.com/chaosblade-io/chaosblade-exec-jvm)
* [jvm-sandbox](https://github.com/alibaba/jvm-sandbox)

# 应用实践

```java

Chaos.javac(Helloword.class, "");

```
