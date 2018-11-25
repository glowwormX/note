* ps -ef|grep tomcat、ps aux|grep nginx  查看tomcat进程   
* netstat -tunlp|grep 3306 查看端口号对应的进程   
* lsof -i:3306  查看端口号对应的进程(yum install lsof)
* kill -9 2254  杀死进程   
* tail -f ../logs/catalina.out 跟踪日志   
* cat /etc/centos-release 查看centos版本
* uname -r Linux 内核版本
* getconf LONG_BIT 位数
* uanme -a 内核+位数
* 内存 free
* 硬盘 fdisk -l 或 df
* cpu cat /proc/cpuinfo | grep name | cut -f2 -d: | uniq -c

管理员身份打开    
sudo gedit /etc/rc.local   
sudo gedit /etc/profile    
 调整亮度   
echo 500 > /sys/class/backlight/intel_backlight/brightness    
移动/home/xqw/下载/xxx到usr/xxx：     mv /home/xqw/下载/xxx /usr/xxx     
    
创建链接：    
    ln -s /usr/eclipse/eclipse /home/xqw/桌面/eclipse   
    
复制文件：cp xxx x/xx/xxx2  
复制文件夹：cp -r xxx x/xx/xxx2   
删除文件：rm -rf xxx (r：递归，f直接删除不提示,rm -rf 的时候一定要小心，Linux没有回收站。)   
修复安装    
apt-get -f install   
修复读取不了其他盘    
sudo apt-get install ntfs-3g 安装    
    
sudo ntfsfix /dev/sda5 修复   
    
安装    
一：deb安装：   
sudo dpkg -i wine-qqintl_0.1.3-2_i386.deb   
二：configure文件：   
1：先解压  tar xvJf ***.tar.xz   
2：./configure   
3：make   
4：sudo make install   
三：sh文件直接运行   
sh 路径/pycharm.sh    
   
    
卸载    
方法一、如果你知道要删除软件的具体名称，可以使用           
sudo apt-get remove --purge 软件名称     
sudo apt-get autoremove --purge 软件名称    
方法二、如果不知道要删除软件的具体名称，可以使用   
dpkg --get-selections | grep ‘软件相关名称’   
sudo apt-get purge 一个带core的package，如果没有带core的package，则是情况而定。   
重启服务    
sudo /etc/init.d/apache2 restart   
   
    
添加快捷图标：    
终端输入：sudo gedit /usr/share/applications/Pycharm.desktop   
粘贴模板：   
[Desktop Entry]   
Type=Application   
Name=Pycharm   
GenericName=Pycharm3   
Comment=Pycharm3:The Python IDE   
Exec=sh 路径/pycharm.sh    
Icon= 路径/pycharm.png   
Terminal=pycharm   
Categories=Pycharm;   
    
启动tomcat查看日志    
bin/startup.sh   
    
tail -f logs/catalina.out    
    
清除当前目录下所有：   
    
rm -rf *   
    
   
    
查看与nginx有关的进程   
    
ps aux|grep nginx   
    
   
    
vim：命令模式下   
    
a 输入   
    
:q!强制退出   
    
ZZ保存退出   
 
u撤销
ctrl r 回复
V行选模式   
y复制   
    
p粘贴   
    
   
    
关闭防火墙：   
    
service iptables stop   
    
设置默认关闭     
chkconfig iptables off   
详细：  https://blog.csdn.net/u011846257/article/details/54707864    

cengtos安装ubuntu deb软件
sudo yum install alien -y   
sudo yum install qtwebkit -y   
alien -r sogoupinyin_2.2.0.0102_amd64.deb   
rpm -ivh sogoupinyin-2.2.0.0102-2.x86_64.rpm   

centos 7   
firewall-cmd --zone=public --add-port=27017/tcp --permanent   
systemctl restart firewalld.service   

ubuntu   
sudo ufw status   
sudo ufw allow 80   
sudo ufw enable   
sudo ufw reload   
