
package hello;

public class HelloWorld {

    public String sayHello(String name) {
        InnerClass in = new InnerClass();
        return "hello " + in.getInnerName() + " " + name;
    }

    public class InnerClass {

        public String getInnerName() {
            return "inner ";
        }
    }
}