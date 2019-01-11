# scorpio

#### 项目介绍
状态切换框架，内置数据为空，错误，加载中三种默认布局。


#### 安装教程

```groovy
implementation 'com.lnysky.tech:scorpio:0.0.1'
```

#### 使用说明

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Scorpio.content(this).show();
        Scorpio.loading(this).setTips("加载中...").show();
        //Scorpio.empty(this).setTips("主页面空空的~~").show();
        //Scorpio.error(this).setRetryText("重新加载")
        //                .setOnRetryListener(new View.OnClickListener() {
        //                    @Override
        //                    public void onClick(View v) {
        //                        Scorpio.loading(MainActivity.this).show();
        //                    }
        //                }).show();
        //Scorpio.with(this).get(CustomState.class).show();
    }
}
```

```java
public static class CustomState extends State<CustomState.ViewHolder> {

        public CustomState(StateSwitcher switcher) {
            super(switcher);
        }

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
