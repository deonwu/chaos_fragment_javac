
package chaos;

import java.util.concurrent.Callable;

public class HelloWorld {

    public String sayHello(String name) {
        InnerClass in = new InnerClass();

        Callable<String> cb = () -> {
            return doName();
        };

        String curName = cb.call();

        return "hello " + curName + " " + name;
    }

    public String doName(){
        return "->"
    }
}