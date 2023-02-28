# CheckUtils-Spring-Boot-Starter

![](https://img.shields.io/badge/SpringBoot-v3.0.3-green)![](https://img.shields.io/badge/JDK-17-red) 

一个用于在SpringBoot项目中判断参数对象中属性是否为空的工具箱，极大简化你的代码！

## 一、安装与配置

还没上传到Maven呢，先下个jar包玩玩吧

本项目基于SpringAOP开发，所以开始之前请在你的Maven依赖中添加此依赖

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

SpringBoot出现以下日志信息则说明成功启动

```bash
INFO 77084 --- [main] c.c.checkutils.service.CheckAopService   : Check Service Starting
```

## 二、如何使用

### 1. @CheckBean注解

方式一：将test方法中userBean的参数名称给到CheckBean注解的param参数，会将判断userBean这个对象中所有属性的空值与否，但凡有一个属性值为空，则抛出异常。

```java
@GetMapping("/t1")
@CheckBean(param = "userBean")
public ModelAndView test(@RequestBody UserBean userBean){
	...
}
```

方式二：在方式一的基础上给到第二个参数fields。fields是一个String数组，它记录的是要判断的属性的名字。以下例子实现的是判断userBean对象中属性名为username和email的空值与否，效果同上

```java
@GetMapping("/t1")
@CheckBean(param = "userBean",fields = {"username","email"})
public ModelAndView test(@RequestBody UserBean userBean){
	...
}
```

### 2.其他待完善

## 三、合作？

Blog：https://ceerdecy.com.cn/

Email：CeerDecy@163.com