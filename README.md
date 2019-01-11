# scorpio

#### 项目介绍
状态切换框架，内置数据为空，错误，加载中三种默认布局。


#### 安装教程

```groovy
implementation 'com.lnysky.tech:scorpio:0.0.1'
```

#### 使用说明

```java
private static final int CUSTOM = 4;
private Bar scorpio;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    scorpio = Scorpio.with(this);
    scorpio.register(new Provider(CUSTOM) {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup parent) {
            return inflater.inflate(R.layout.custom, parent, false);
        }
    });
}
```

```java
scorpio.content();

scorpio.builtIn().loading();

scorpio.builtIn().empty().setTips("主页面空空的~~");

scorpio.builtIn().error()
                 .setRetryText("重新加载")
                 .setOnRetryListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           scorpio.builtIn().loading();
                       }
                 });
```