# 安装 Java、sdkman、maven和gradle

1. 将JDK文件包放到files目录下
2. 将yml文件中的变量 file_location的值修改为JDK文件包的文件名，如下所示：

``` bash
   vars:
     file_location: 'jdk8.tar.gz'
```

3. 修改hosts文件中的机器地址为目标机器
4. 运行命令

```bash
ansible-playbook -i hosts deploy_jar.yml
```

