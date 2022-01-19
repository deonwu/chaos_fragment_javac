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
package org.chaos.fragement.test;

import org.chaos.fragment.javac.ChaosJava;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.reporters.Files;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author DeonWu
 * @date 2022/01/19 14:59
 **/
public class TestChaosJava {

    /**
     * 简单的功能测试，只测试编译 和 执行功能。
     *
     * @throws IOException
     */
    @Test
    public void testJavaCompile() throws IOException {

        ChaosJava cj = new ChaosJava();

        // 基于宿主类，编译一个片段代码。
        Class newFragmentClass =
            cj.compile(HelloWordHost.class, "cur" + System.currentTimeMillis(), getSource("Helloword.java"));
        Assert.assertNotNull(newFragmentClass, "片段类编译失败");

        // 在宿主对象上， 执行片段代码中的方法。
        HelloWordHost host = new HelloWordHost();
        Object ret = cj.runClass(host, newFragmentClass, "sayHello", "长坤");

        Assert.assertNotNull(ret, "结果放回为null");
        Assert.assertEquals("hello 长坤", ret);
    }

    protected String getSource(String name) throws IOException {
        InputStream ins = TestChaosJava.class.getClassLoader().getResourceAsStream("java/" + name);

        Assert.assertNotNull(ins, "Not found source:" + name);

        return Files.readFile(ins);
    }
}
