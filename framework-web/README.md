## 说明

简单的将spring boot、mybatis、druid集成

## mybatis-generator.xml使用

### 第一步

```
mvn package mybatis-generator:generate
```

### 第二步

将`${basedir}/generate`目录下的dao 、 model、mapper xml分别拷贝到项目中指定路径。

