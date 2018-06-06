git init //把这个目录变成Git可以管理的仓库

git add README.md //文件添加到仓库

git add . //不但可以跟单一文件，还可以跟通配符，更可以跟目录。一个点就把当前目录下所有未追踪的文件全部add了    
git commit -m "first commit" //把文件提交到仓库

git remote add origin （git@github.com:wangjiax9/practice.git） //关联远程仓库

git push -u origin master //把本地库的所有内容推送到远程库上


https://www.cnblogs.com/xiaosongbiog/p/7003601.html   
 
…or create a new repository on the command line   

echo "# self-work" >> README.md   
git init   
git add README.md   
git commit -m "first commit"   
git remote add origin https://github.com/glowwormX/self-work.git   
git push -u origin master   

…or push an existing repository from the command line   

git remote add origin https://github.com/glowwormX/self-work.git   
git push -u origin master   

…or import code from another repository   

You can initialize this repository with code from a Subversion, Mercurial, or TFS project.   
