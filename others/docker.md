  
```
安装   
yum install docker   
安装完成后，使用下面的命令来启动 docker 服务，并将其设置为开机启动：

service docker start
chkconfig docker on

#LCTT 译注：此处采用了旧式的 sysv 语法，如采用CentOS 7中支持的新式 systemd 语法，如下：
systemctl  start docker.service
systemctl  enable docker.service

使用Docker 中国加速器

vi  /etc/docker/daemon.json

#添加后：
{
    "registry-mirrors": ["https://registry.docker-cn.com"],
    "live-restore": true
}
重新启动docker

systemctl restart docker
输入docker version 返回版本信息则安装正常。
```

制作docker centos镜像   
https://blog.csdn.net/JXYZH11/article/details/79112655   
