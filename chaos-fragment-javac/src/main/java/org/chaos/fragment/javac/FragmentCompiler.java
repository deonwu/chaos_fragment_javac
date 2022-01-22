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

import org.chaos.fragment.javac.jdk.StringJavaFileObject;

import javax.tools.*;

/**
 * @author DeonWu
 * @date 2022/01/19 15:31
 **/
public class FragmentCompiler {

    private DiagnosticCollector<JavaFileObject> DIAGNOSTIC_COLLECTION = new DiagnosticCollector<>();

    public void doCompiler(String source){

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        JavaFileManager fileManager = compiler.getStandardFileManager(DIAGNOSTIC_COLLECTION, null, null);
       // compiler.getTask();

        //CharSequenceJava
        SimpleJavaFileObject f = new StringJavaFileObject("org.chaos.Hello", source);

    }
}
