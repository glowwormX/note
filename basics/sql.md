Provider=SQLOLEDB;Data Source=192.168.0.64,1433;Initial Catalog=EXiang;User ID=sa;Password=a_123456   
   
data source = "注解1";database = 注解2;uid = "注解3";pwd ="注解4"   
   
log4j定义了8个级别的log（除去OFF和ALL，可以说分为6个级别），优先级从高到低依次为：OFF、FATAL、ERROR、WARN、INFO、DEBUG、TRACE、 ALL。   
配置：引用dll、配置xml文件、AssemblyInfo加入 [assembly: log4net.Config.XmlConfigurator(ConfigFile = "log4net.xml", Watch = true)]   
   
语法：   
http://www.w3school.com.cn/sql/index.asp   
1.创建数据库   
create database _name   
2.删除数据库   
drop database _name   
3.创建表   
   
if object_id('_name',N'U') is not null drop table touch_Reconsideration;   
   
sqlserver：   
create table _name(   
id int primary key not null identity(1,1),   
sex nvarchar(2) not null check(sex in("男","女")),   
idCard varchar(18) not null unique,   
delete_flag int not null default 0,   
dept_id int foreign key references dept(id)   
)   
mysql：   
create table _name(   
id int primary key not null INCREMENT,   
sex enum('男','女') not null,   
idCard varchar(18) not null unique,   
delete_flag int not null default 0,   
)   
https://www.cnblogs.com/geaozhang/p/6786105.html   
   
根据已经有的表创建表   
sqlserver ： select col1 into new_table form old_table [where 1=2;//只复制结构]   
mysql :  create table new_table select col1,* form old_table [where 1=2;//只复制结构]   
4.创建后添加约束    
alter table _name add constraint constraint_name check(id in("男","女"))   
alter  table _name add constraint constraint_name unique(idcardNum)   
alter table _name add constraint constraint_name foreign key(dept_id) references dept(id)   
删除约束 alter table _name drop constraint _name   
   
5.查询   
执行顺序：http://blog.csdn.net/abauch_d/article/details/31361615   
聚集函数必须在having中,select 列必须在group by中   
将多步骤分解成子查询   
查询每个部门大于平均工资的人   
select e.last_name,e.salary,d.department_id from employees e,(select avg(salary) avg,department_id from employees group by department_id) d    
where e.salary > d.avg and e.department_id=d.department_id order by d.department_id   
   
row_number() OVER (PARTITION BY COL1 ORDER BY COL2) 表示根据COL1分组，在分组内部根据 COL2排序，而此函数计算的值就表示每组内部排序后的顺序编号（组内连续的唯一的)   
   
   
查询每门成绩前两名的学生   
   
1、SELECT autoId,studentId,courseName,score FROM (   
    SELECT *,row_number() over(PARTITION  by studentId order by autoid) num from studentScore   
) as a   
where a.num <= 2   
   
2、SELECT * FROM studentScore a   
 WHERE 2 > (SELECT COUNT(*)   
              FROM studentScore b   
             WHERE b.courseName = a.courseName   
               AND a.score < b.score)   
 Order by a.courseName   
   
  studentScore表根据courseName自连接，取出满足a.score < b.score条件的行数小于2的就是“每门成绩前两名学生”   
 3、select * from studentScore a where a.autoId in (   
    select top 2 autoId from studentScore   
    where a.courseName=courseName   
    order by score desc   
)   
   
   
6.存储过程   
create procedure   _name   
    @a int ,   
    @b int,   
    @sum int output   
as   
begin   
    set @sum = @a + @b   
end   
   
7.触发器   
create trigger _name   
    on _table   
    for update   
as   
    declare @   
   
CREATE TRIGGER trigger_name trigger_time trigger_event   
   
      ON tbl_name FOR EACH ROW trigger_stmt   
   
触发程序是与表有关的命名数据库对象，当表上出现特定事件时，将激活该对象。   
   
触发程序与命名为tbl_name的表相关。tbl_name必须引用永久性表。不能将触发程序与TEMPORARY表或视图关联起来。   
   
trigger_time是触发程序的动作时间。它可以是BEFORE或AFTER，以指明触发程序是在激活它的语句之前或之后触发。   
   
trigger_event指明了激活触发程序的语句的类型。trigger_event可以是下述值之一：   
   
·         INSERT：将新行插入表时激活触发程序，例如，通过INSERT、LOAD DATA和REPLACE语句。   
   
·         UPDATE：更改某一行时激活触发程序，例如，通过UPDATE语句。   
   
·         DELETE：从表中删除某一行时激活触发程序，例如，通过DELETE和REPLACE语句。   
   
请注意，trigger_event与以表操作方式激活触发程序的SQL语句并不很类似，这点很重要。例如，关于INSERT的BEFORE触发程序不仅能被INSERT语句激活，也能被LOAD DATA语句激活。   
   
可能会造成混淆的例子之一是INSERT INTO .. ON DUPLICATE UPDATE ...语法：BEFORE INSERT触发程序对于每一行将激活，后跟AFTER INSERT触发程序，或BEFORE UPDATE和AFTER UPDATE触发程序，具体情况取决于行上是否有重复键。   
   
对于具有相同触发程序动作时间和事件的给定表，不能有两个触发程序。例如，对于某一表，不能有两个BEFORE UPDATE触发程序。但可以有1个BEFORE UPDATE触发程序和1个BEFORE INSERT触发程序，或1个BEFORE UPDATE触发程序和1个AFTER UPDATE触发程序。   
   
trigger_stmt是当触发程序激活时执行的语句。   
   
