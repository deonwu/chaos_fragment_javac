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

import javax.tools.JavaFileObject;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 编译运行时class loader，用于加载编译过程中的类。
 *
 * @author DeonWu
 * @date 2022/01/22 09:29
 **/
public class CompileClassLoader extends ClassLoader {
    private static final String CLASS_EXT = ".class";

    private final Map<String, JavaFileObject> javaFileObjectMap = new HashMap<>();

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        JavaFileObject file = javaFileObjectMap.get(name);

        if( file instanceof StringJavaFileObject){
            StringJavaFileObject fileObject = (StringJavaFileObject)file;

            byte[] code = fileObject.getClassByte();

            return defineClass(name, code, 0, code.length);
        }

        return super.findClass(name);
    }

    @Override
    public InputStream getResourceAsStream(String name) {

        if(name.endsWith(CLASS_EXT)){
            String resourceName = name.substring(0, name.length() - CLASS_EXT.length()).replace('/', '.');
            
        }

        return super.getResourceAsStream(name);
    }
}
