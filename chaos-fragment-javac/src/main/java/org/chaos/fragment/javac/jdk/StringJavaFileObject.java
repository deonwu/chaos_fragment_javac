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

import javax.tools.SimpleJavaFileObject;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author DeonWu
 * @date 2022/01/19 15:50
 **/
public class StringJavaFileObject extends SimpleJavaFileObject {
    private String source;
    private ByteArrayOutputStream classByte = null;

    public StringJavaFileObject(String className, String soruce) {
        super(asURI(className + ".java"), Kind.SOURCE);

        this.source = soruce;
    }

    public StringJavaFileObject(String className, Kind kind){
        super(asURI(className), kind);
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        return source;
    }

    @Override
    public InputStream openInputStream() throws IOException {
        return new ByteArrayInputStream(source.getBytes());
    }

    @Override
    public OutputStream openOutputStream() throws IOException {
        classByte = new ByteArrayOutputStream();
        return classByte;
    }

    /**
     * 返回java 输入编译结果的类字节码。
     *
     * @return
     */
    public byte[] getClassByte(){
        return classByte != null ? classByte.toByteArray() : null;
    }

    /**
     * 转换为一个uri 对象。
     *
     * @param name
     * @return
     */
    public static URI asURI(String name) {
        try {
            return new URI(name);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(name, e);
        }
    }
}
