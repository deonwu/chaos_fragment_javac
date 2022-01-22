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
package org.chaos.fragment.javac.jdk;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * 编译过程的文件管理，扩展一个修饰模式的路由。使用内存缓存管理文件。因为单个字符串编译的规模很小。
 *
 * @author DeonWu
 * @date 2022/01/22 09:56
 **/
public class CompileFileManager extends ForwardingJavaFileManager<JavaFileManager> {
    private final CompileClassLoader classLoader;
    private final Map<URI, JavaFileObject> javaFileObjectMap = new HashMap<>();

    public CompileFileManager(JavaFileManager fileManager, CompileClassLoader cl) {
        super(fileManager);

        classLoader = cl;
    }

    @Override
    public FileObject getFileForInput(Location location, String packageName, String relativeName) throws IOException {
        URI uri = StringJavaFileObject.asURI(location.getName() + "/" + packageName + "/" + relativeName);
        JavaFileObject obj = javaFileObjectMap.get(uri);

        if (obj != null) {
            return obj;
        }

        return super.getFileForInput(location, packageName, relativeName);
    }

    @Override
    public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind,
        FileObject sibling) {
        StringJavaFileObject f = new StringJavaFileObject(className, kind);

        classLoader.addJavaFileObject(className, f);
        System.out.println("new File:" + location.getName() + "/" + className + "/" + sibling.toUri() + ", k:" + kind.name());
        return f;
    }

    @Override
    public ClassLoader getClassLoader(Location location) {
        return classLoader;
    }

    /**
     * 添加一个编译相关的路径到文件管理器里面。用于编译时查找和加载。
     *
     * @param location
     * @param packageName
     * @param relativeName
     * @param javaFileObject
     */
    public void addJavaFileObject(Location location, String packageName, String relativeName,
        JavaFileObject javaFileObject) {

        URI uri = StringJavaFileObject.asURI(location.getName() + "/" + packageName + "/" + relativeName);

        javaFileObjectMap.put(uri, javaFileObject);
    }
}
