# scorpio
[ ![Download](https://api.bintray.com/packages/danyon/maven/scorpio/images/download.svg) ](https://bintray.com/danyon/maven/scorpio/_latestVersion)[![License](https://img.shields.io/badge/license-Apache%202-lightgrey.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

一行代码切换状态布局，内置数据为空，错误，加载中三种默认布局。

## gradle依赖

```groovy
implementation 'com.lnysky.tech:scorpio:Latest Version'
```

#### 使用
- 加载中
```java
	Scorpio.loading(this).setTips("加载中...").show();
```
- 数据为空
```java
	Scorpio.empty(this).setTips("主页面空空的~~").show();
```
- 加载出错
```java
    Scorpio.error(this)
        .setRetryText("重新加载")
        .setOnRetryListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Scorpio.loading(MainActivity.this).show();
            }
        }).show();
```
- 自定义状态布局

```java
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Scorpio.with(this).get(CustomState.class).show();
    }

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

        static class ViewHolder extends StateViewHolder {

            ViewHolder(View view) {
                super(view);
            }
        }
    }
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

