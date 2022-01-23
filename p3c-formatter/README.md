
# 使用alibaba的代码格式和规范

# Idea 配置步骤

  1. 添加环境变量 Help -> Edit Custom VM options
  
> -Duser.name=DeonWu

  2. 配置代码模版, Class\Enum\Interface
  
```
/**
 * @author ${USER}
 * @date ${YEAR}/${MONTH}/${DAY} ${HOUR}:${MINUTE}
 **/

``` 

  3. 配置协议头
  
```aidl
/*
 * Copyright 1999-${YEAR} ${USER}.
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
```