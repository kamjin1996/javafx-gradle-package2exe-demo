### 介绍：
一个将javafx/tornadofx项目通过gradle插件打包为exe的示范demo。

- 项目基于JDK1.8，打包工具为gradle，打包插件使用了：https://github.com/fvarrui/JavaPackager
- 可以通过将extra.properties配置文件放在打包完成的exe程序同目录下，来读取外部的配置文件
- 日志输出到文件采用了logback+slf4j，日志配置：logback-test.xml
- 该项目也可同等转化为: javafx(java)+maven，但本人平时已习惯用kotlin+gradle，所以当前例子中没有maven打包的相关演示
- 如果你需要转化，自己转化有难度或问题时，可以与我交流 QQ：1569558447

### 打包命令：
gradle packageApp
