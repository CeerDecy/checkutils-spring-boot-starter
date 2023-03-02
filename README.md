# CheckUtils-Spring-Boot-Starter

![](https://img.shields.io/badge/SpringBoot-v3.0.3-green)![](https://img.shields.io/badge/JDK-17-red) 

一个用于在SpringBoot项目中判断参数对象中属性是否为空的工具箱，简化你的代码！

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

### 1. 直接使用

CheckUtils类提供多种静态方法用以判断，既可以返回布尔类型，也可选择直接抛出异常。

### 2. 注解

#### @CheckBean

##### 方式一：全属性判断

将 `@CheckBean`写在方法上方，若不带任何参数则会直接判断该方法中第一个参数（此处为 `userBean`）中所有属性值的空值与否。

```java
@CheckBean
public void test(UserBean userBean){...}
```

##### 方式二：自定义属性判断

将 `@CheckBean`写在方法上方，`fields`是一个String数组，它记录的是要判断的属性的名字。若带有`fields`则会判断该方法第一个参数中属性名为`fields`中记录过的属性名称的值。

```java
@CheckBean(fields = {"username","email"})
public void test(UserBean userBean){...}
```

##### 方式三：指定对象全属性判断

将`test`方法中`userBean`的参数名称给到 `@CheckBean`注解的`bean`参数，会将判断`userBean`这个对象中所有属性的空值与否，但凡有一个属性值为空，则抛出异常。

```java
@GetMapping("/t1")
@CheckBean(bean = "userBean")
public ModelAndView test(@RequestBody UserBean userBean){...}
```

##### 方式四：指定对象自定义属性判断

在方式二的基础上给到第二个参数`fields`。`fields`是一个String数组，它记录的是要判断的属性的名字。以下例子实现的是判断`userBean`对象中属性名为`username`和`email`的空值与否，但凡有一个属性值为空，则抛出异常。

```java
@GetMapping("/t1")
@CheckBean(bean = "userBean",fields = {"username","email"})
public ModelAndView test(AccountBean accountBean, @RequestBody UserBean userBean){...}
```

##### 方式五：多对象自定义属性判断

若`@CheckBean`注解不给予`bean`属性，则注解从上至下第几个注解对应要判断的第几个参数对象。

```java
@CheckBean
@CheckBean
public void test1(UserBean userBean,AccountBean accountBean){...}
```

此处`test2()`和上面的`test1()`效果一致

```java
@CheckBean(bean = "userBean")
@CheckBean(bean = "accountBean")
public void test2(UserBean userBean,AccountBean accountBean){...}
```

甚至你可以倒过来写

```java
@CheckBean(bean = "accountBean")
@CheckBean(bean = "userBean")
public void test(UserBean userBean,AccountBean accountBean){...}
```

但需要注意的是若其中一个不给`bean`值，则第几个注解就会对应地判断第几个参数对象，如下两个注解其实只是在判断同一个参数对象`accountBean`

```java
@CheckBean(bean = "accountBean")
@CheckBean
public void test(UserBean userBean,AccountBean accountBean){...}
```

其他用法与上述方法一致，同样支持自定义属性名称的判断

```java
@CheckBean(fields = {"username","age"})
@CheckBean(fields = {"accountId"})
public void test(UserBean userBean,AccountBean accountBean){...}
```

## 三、合作？

Blog：https://ceerdecy.com.cn/

Email：CeerDecy@163.com