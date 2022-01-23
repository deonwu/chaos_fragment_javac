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

import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.util.JavacTask;
import org.apache.commons.lang3.StringUtils;
import org.chaos.fragment.javac.jdk.CompileClassLoader;
import org.chaos.fragment.javac.jdk.CompileFileManager;
import org.chaos.fragment.javac.jdk.StringJavaFileObject;

import javax.tools.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @author DeonWu
 * @date 2022/01/19 15:31
 **/
public class FragmentCompiler {

    private DiagnosticCollector<JavaFileObject> DIAGNOSTIC_COLLECTION = new DiagnosticCollector<>();

    public Class doCompiler(String className, String source) throws ClassNotFoundException, IOException {

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        JavaFileManager fileManager = compiler.getStandardFileManager(DIAGNOSTIC_COLLECTION, null, null);

        CompileClassLoader customClassLoader = new CompileClassLoader(Thread.currentThread().getContextClassLoader());
        CompileFileManager customFileManager = new CompileFileManager(fileManager, customClassLoader);
        // compiler.getTask();

        // CharSequenceJava
        SimpleJavaFileObject f = new StringJavaFileObject(StringUtils.substringAfterLast(className, "."), source);

        customFileManager.addJavaFileObject(StandardLocation.SOURCE_PATH,
            StringUtils.substringBeforeLast(className, "."), StringUtils.substringAfterLast(className, ".") + ".java",
            f);

        List<String> options = Arrays.asList("-source", "1.8");
        JavaCompiler.CompilationTask task =
            compiler.getTask(null, customFileManager, DIAGNOSTIC_COLLECTION, options, null, Arrays.asList(f));

        JavacTask javacTask = (JavacTask) task;
        Iterable<? extends CompilationUnitTree> it = javacTask.parse();
        for(CompilationUnitTree t : it){
            System.out.println(t);
        }

        task =
                compiler.getTask(null, customFileManager, DIAGNOSTIC_COLLECTION, options, null, Arrays.asList(f));
        Boolean ret = task.call();
        if (!ret) {
            List<Diagnostic<? extends JavaFileObject>> diagnostics = DIAGNOSTIC_COLLECTION.getDiagnostics();

            for(Diagnostic<? extends JavaFileObject> d : diagnostics){
                System.out.println(d.getKind() + ", error:" + d.getMessage(Locale.CHINA));

            }
            //throw new RuntimeException("编辑错误");
        }

        return customClassLoader.loadClass(className);
    }

}
