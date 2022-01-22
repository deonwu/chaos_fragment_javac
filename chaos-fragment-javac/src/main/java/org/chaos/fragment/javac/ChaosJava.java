/*
 * Copyright 1999-2022 DeonWu.
 *
 * Licensed under GNU General Public License, Version 3.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * http://www.gnu.org/licenses/gpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.chaos.fragment.javac;

/**
 * 代码代码主类
 *
 * @author DeonWu
 * @date 2022/01/19 13:18
 **/
public class ChaosJava {

    /**
     * 参考：
     * https://baijiahao.baidu.com/s?id=1668714016975233524&wfr=spider&for=pc
     *
     * 指定一个宿主类，和代码片段。进行编译一个新的代码class。如果编译出错，抛出异常。
     *
     * 1. 解析代码的AST， 语法树。
     * 2. 根据语法树提取，依赖的 类、方法、成员变量等。
     * 3. 从hosted 宿主类中，推导方法。
     * 4. 合并宿主类的方法定义，到新到 片段代码。
     * 5. 执行新合并后到编码编译。
     * 6. 编译后的类，进行字节码解析。修改为可以执行的class。
     *
     * //编译为原始类的，内部类？？？
     * class A{
     *     private String a;
     *     private static String b;
     *     public class X {
     *         public void newMethod(){
     *              System.out.println("this:", a + b);
     *         }
     *     }
     * }
     *
     * @param hosted
     * @param className
     * @param source
     * @return class 返回编译成功的class。
     */
    public Class compile(Class hosted, String className, String source) throws ClassNotFoundException {
        FragmentCompiler c = new FragmentCompiler();

        Class ret = c.doCompiler(className, source);

        return ret;
    }

    /**
     * 执行一个片段class 中到方法。
     *
     * @param target
     * @param fragmentClass
     * @param methodName
     * @param args
     * @return
     */
    public Object runClass(Object target, Class fragmentClass, String methodName, Object... args){

        return null;
    }
}
