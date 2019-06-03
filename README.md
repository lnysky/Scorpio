# scorpio
[ ![Download](https://api.bintray.com/packages/danyon/maven/scorpio/images/download.svg) ](https://bintray.com/danyon/maven/scorpio/_latestVersion)[![License](https://img.shields.io/badge/license-Apache%202-lightgrey.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

一行代码切换状态布局，内置数据为空，错误，加载中三种默认布局。

## 添加gradle依赖

```groovy
implementation 'com.lnysky.tech:scorpio:Latest Version'
```

#### 使用

1. activity中使用

    - 加载中
    ```java
        Scorpio.with(this).loading().setTips("加载中...").show();
    ```
    - 数据为空
    ```java
        Scorpio.with(this).empty().setTips("主页面空空的~~").show();
    ```
    - 加载出错
    ```java
        Scorpio.with(this).error()
            .setRetryText("重新加载")
            .setOnRetryListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Scorpio.loading(MainActivity.this).show();
                }
            }).show();
    ```
    - 自定义状态

    ```java
        Scorpio.with(this).get(CustomState.class).show();
    
        public class CustomState extends State<CustomState.ViewHolder> {
    
            @Override
            protected ViewHolder onCreateStateViewHolder(LayoutInflater inflater, ViewGroup parent) {
                View view = inflater.inflate(R.layout.custom, parent, false);
                return new ViewHolder(view);
            }
    
            @Override
            protected void onSwitchState(ViewHolder holder, boolean show) {
                super.onSwitchState(holder, show);
                AlphaAnimation animation;
                if (show) {
                    animation = new AlphaAnimation(0f, 1f);
                } else {
                    animation = new AlphaAnimation(1f, 0f);
                }
                animation.setDuration(1000);
                holder.getView().startAnimation(animation);
            }
    
            static class ViewHolder extends StateLayout.ViewHolder {
    
                ViewHolder(View view) {
                    super(view);
                }
            }
        }
    ```

2. fragment中使用

   - 在xml文件实现

     ```xml
     <?xml version="1.0" encoding="utf-8"?>
     <com.lnysky.tech.scorpio.StateLayout xmlns:android="http://schemas.android.com/apk/res/android"
         xmlns:tools="http://schemas.android.com/tools"
         android:layout_width="match_parent"
         android:layout_height="match_parent"
         tools:context=".TestFragment">
     
         <TextView
             android:layout_width="wrap_content"
             android:layout_height="wrap_content"
             android:text="@string/text_content_show" />
     
     </com.lnysky.tech.scorpio.StateLayout>
     ```

     

   - 代码中实现

     ```java
     @Override
     public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_test2, container, false);
         return Scorpio.with(this).wrapper(view);
     }
     ```
     **注意**：以上两种方式推荐使用第一种

3. 直接使用StateLayout

      ```java
      private StateLayout stateLayout;
      
      
      Scorpio.with(stateLayout).loading().setTips("加载中...").show();
      ```

      

## License

```
Copyright (C) 2017 - present, Danyon Liu.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

