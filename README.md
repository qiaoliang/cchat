## 前言

今天，我们就一起使用 netty 实现一个 Android IM，包括Android客户端和后台服务端。

## 构思

作为一个 IM 应用，我们需要识别用户，客户端建立长连接后需要汇报自己的信息，服务器验证通过后将其缓存起来，表明该用户在线。

客户端是一个一个的个体，服务器作为中转，比如，A 给 B 发送消息，A 先把消息发送到服务器，并告诉服务器这条消息要发给谁，然后服务器把消息发送给 B。

服务器在收到消息后可以对消息进行存储，如果 B 不在线，就等 B 上线后再将消息发送过去。

![](https://raw.githubusercontent.com/wangchenyan/cchat/master/art/network.png)

## 后台服务

### 1. 在本地启动一个后台服务，接收Android客户端请求

``` cd server
    gradle run
```

### 2. 生成一个可以独立运行的jar包

* 使用下面命令

```cd server
   gradle shadowjar
```

* 它会在目录`build/libs`下生成一个名为`nettyServer-all.jar`。

* 使用下面命令运行

```java -jar nettyServer-all.jar```


## Android 客户端

### 1. 修改文件，指定后台服务所用的IP和端口号

* 修改PushService.java原文件中，如下两个类属性

```    private static final String HOST = "10.240.78.82";
       private static final int PORT = 8300;
```

### 2. 在Android Studio中启动模拟器

### 3. 使用 用户名 test1 和 密码 123456 登录
