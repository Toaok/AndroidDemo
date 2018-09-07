
## 在工程中应用submodule

### project/settings.gradle 声明子模块
```groovy
include ':librarys:Leaks'
project(':Leaks').projectDir = new File('librarys/Leaks')
```

### 主工程app/build.gradle 注册子模块
```groovy
debugImplementation project(':librarys:Leaks')
```

## debug build type

### 主工程app/build.gradle 声明buildtype的工程入口
```groovy
sourceSets {
        main {
            jniLibs.srcDirs = ['libs'] 
        }

        // 声明debug的变种
        debug.setRoot('src/debug')
    }
```

### 重写debug buildtype的相关代码

#### 重写debug buildtype的AndroidManifest
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="com.visionet.cx_ckd">

    <application
        android:name="com.visionet.dzcx. DApplication_BT"
        android:label="@string/app_name"
        tools:replace="android:name,android:label">
    </application>
</manifest>
```


#### 重写debug buildtype的Application
```java
public class DApplication_BT extends DApplication {
 
    @Override
    public void onCreate() {
        super.onCreate(); 
        init();
    } 

    private void init() {
        Leaks.init(getAppContext());
    }
}
```

## 示例

```java

Leaks.init(getAppContext());

```

# chrome 中调起

1.运行app

2.浏览器输入
chrome://inspect/#devices

3.inspect