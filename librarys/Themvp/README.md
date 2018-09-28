
## 在工程中应用submodule

### project/settings.gradle 声明子模块
```groovy
include ':librarys:Themvp'
project(':Themvp').projectDir = new File('librarys/Themvp')
```

### 主工程app/build.gradle 注册子模块
```groovy
implementation project(':librarys:Themvp')
```