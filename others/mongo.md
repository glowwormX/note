## Mongo

### 服务器操作
``` 
      ./mongod <-port 27017> 服务启动
      备份
      mongodump -h IP --port 端口 -u 用户名 -p 密码 -d 数据库 -o 文件存在路径 
      （./mongodump -d mbxt）
      （./mongodump -d mbxt -o /home/hlkj/Documents/mongobak/shellbak/test）
      还原
        整库
        mongorestore -h IP --port 端口 -u 用户名 -p 密码 -d 数据库 (--drop) 文件夹路径
        单表
        mongorestore -h IP --port 端口 -u 用户名 -p 密码 -d 数据库 -c 表名 文件路径
      （./mongorestore -d mbxt dump/mbxt/）
      （./mongorestore -d mbxt /home/hlkj/Documents/mongobak/shellbak/test）
      导出表，或者表中部分字段
      mongoexport -h IP --port 端口 -u 用户名 -p 密码 -d 数据库 -c 表名 -f 字段
```

#### 

### 副本集搭建
``` 
    启动三台 replSet设置同一个名字
    ./mongod --port 2001 --bind_ip 0.0.0.0 --dbpath /data/db/ --replSet rs0
    ./mongod --port 2002 --bind_ip 0.0.0.0 --dbpath /data/db/ --replSet rs0
    ./mongod --port 2003 --bind_ip 0.0.0.0 --dbpath /data/db/ --replSet rs0

    主服务连接进去设置：
    mongo --port 2001
    rs.initiate()
    rs.add("<hostname>:2002")
    rs.add("<hostname>:2003")
    rs.conf()
    
    2.1.0 spring-mongo-data 注入

    @Bean
    MongoTransactionManager transactionManager(MongoDbFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

    @Transactional
``` 
####

### 对查询的结果的字段进行过滤	
``` 
db.expressSigned.aggregate([
    {
        $project: {
            "signDetails": {
                $filter: {
                    input: "$signDetails",
                    as: "item",
                    cond: { 
                        $eq: [ '$$item.delete', 0 ]
                    }
                }
            }
        }
    }
])
``` 
``` java 	
       Aggregation project = newAggregation(
                project().and(filter("signDetails")
                        .as("item")
                        .by(valueOf( "item.delete").equalToValue(0)))
                        .as("signDetails")
        );
      ExpressSigned expressSigned2 = mongoTemplate.aggregate(project, "expressSigned",                                                    ExpressSigned.class).getUniqueMappedResult();

```

### 1.更新List中符合条件的内容
      以下代码为更新人员id为staffId, 图片list(imgs)中type为'type'的人员图片信息

#### 
``` java
        Update update = new Update();
        update.set("imgs.$.photo", img.getPhoto());
        update.set("imgs.$.type", img.getType());
        
        Query query = Query.query(new Criteria().andOperator(Criteria.where("id").is(staffId), Criteria.where("imgs").elemMatch(Criteria.where("type").is(type))));
        
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Staff.class);
```

### 2.向List中新增数据
``` java
        Query query = Query.query(Criteria.where("id").is(staffId));
        Update update = new Update();
        update.addToSet("imgs, img);
        mongoTemplate.upsert(query, update, Staff.class);

```

### 3.查询中条件为or
``` java
        Query query = new Query();
 
        query.addCriteria(new Criteria()
                          .orOperator(
                                  Criteria.where("name").regex(pattern),
                                  Criteria.where("mobilePhone").regex(pattern),
                                  Criteria.where("policeNumber").regex(pattern)
                          ));
```
假如有一个Document如下：
``` 
{
    "_id" : "69bca85a-5a61-4b04-81fb-ff6a71c3802a",
    "_class" : "cn.com.chinacloud.paas.mir2.application.model.bo.ApplicationInstanceBO",
    "slug" : "shawn-shawn-mysql-1",
    "version" : "1",
    "name" : "shawn-mysql-1",
    "status" : "UNHEALTHY",
    "resourceTask" : {
        "task" : "DEPLOY",
        "taskResult" : "DEPLOY_TIMEOUT"
    },
    "totalElementNum" : 1,
    "totalCellNum" : 1,
    "subElementNum" : 1,
    "elementInstanceBOs" : [ 
        {
            "_id" : "1347580a-02a1-41a6-9d29-c78850f948b5",
            "slug" : "shawn-shawn-mysql-1-mysql",
            "name" : "mysql",
            "namespace" : "shawn",
            "status" : "STARTING",
            "totalCellNum" : 1,
            "runningCellNum" : 0,
            "imageName" : "172.16.71.199/common/mysql",
            "imageVersion" : "5.6",
            "intranetAccessURL" : [ 
                {
                    "_id" : null,
                    "address" : "shawn-mysql-1-mysql.shawn",
                    "proxyPort" : 3306,
                    "targetPort" : 3306
                }
            ],
            "internetAccessURL" : [ 
                {
                    "_id" : null,
                    "address" : "172.16.71.200",
                    "targetPort" : 3306
                }
            ]
        }
    ],
    "applicationId" : "c27dbb31-20e9-40a2-94d9-e6a2df661604",
    "dependencyInstances" : [],
    "canStart" : false,
    "canStopAndReBuild" : false
}
``` 
如果想要更新上面的紫色的status，由于elementInstanceBOs是数组结构，想要更新具体哪一个，可以用$表示数组的下标。
``` java
    Query query = new Query(Criteria.where("elementInstanceBOs.slug").is(pSlug));
    Update update = new Update().set("elementInstanceBOs.$.status", pElementInstanceStatusEnum)
          .set("elementInstanceBOs.$.runningCellNum", runningCell);
    mongoTemplate.updateFirst(query, update, ApplicationInstanceBO.class);
java
在上面的语句当中，Criteria.where("elementInstanceBOs.slug").is(pSlug)选择条件返回的是整个document，但是具体更新数组里面的哪个一还是得用下标$来表示。

mongoDB中数组的其他操作：

查看一个文档的一个键值comments为一个数组[“test1”,”test2”]：
``` 
> db.post.findOne({"id":1})   
{    
    "_id" : ObjectId("54a530c3ff0df3732bac1680"),    
    "id" : 1,    
    "name" : "joe",    
    "age" : 21,    
    "comments" : [    
        "test1",    
        "test2"    
    ]    
}    
>
 ```

一、$push向数组末尾添加元素

``` 
> db.post.update({"id":1},{$push:{"comments": "test3"}})   
WriteResult({ "nMatched" : 1, "nUpserted" : 0, "nModified" : 1 })    
> db.post.findOne({"id":1})    
{    
    "_id" : ObjectId("54a530c3ff0df3732bac1680"),    
    "id" : 1,    
    "name" : "joe",    
    "age" : 21,    
    "comments" : [    
        "test1",    
        "test2",    
        "test3"    
    ]    
}    
>
``` 
``` java
        Query query = new Query( Criteria.where("id").is(id);
        Update update = new Update().push("comments", 'test3');
 
``` 
    

使用$each一次性添加多个值：

``` 
> db.post.update({"id":1},{$push:{"comments":{$each:["test4","test5","test6"]}}})   
WriteResult({ "nMatched" : 1, "nUpserted" : 0, "nModified" : 1 })    
> db.post.findOne({"id":1})    
{    
    "_id" : ObjectId("54a530c3ff0df3732bac1680"),    
    "id" : 1,    
    "name" : "joe",    
    "age" : 21,    
    "comments" : [    
        "test1",    
        "test2",    
        "test3",    
        "test4",    
        "test5",    
        "test6"    
    ]    
}    
>
``` 
``` java
        Query query = new Query( Criteria.where("id").is(id);
        List<String> list=new ArrayList<String>();
        list.add("test3");
        list.add("test4");
        list.add("test4");
        Update update = new Update().pushAll("comments", list);
 
``` 
二、用$pop删除数组中的元素

从数组末尾删除一个值：

``` 
> db.post.update({"id":1},{$pop:{"comments":1}})   
WriteResult({ "nMatched" : 1, "nUpserted" : 0, "nModified" : 1 })    
> db.post.findOne({"id":1})    
{    
    "_id" : ObjectId("54a530c3ff0df3732bac1680"),    
    "id" : 1,    
    "name" : "joe",    
    "age" : 21,    
    "comments" : [    
        "test1",    
        "test2",    
        "test3",    
        "test4",    
        "test5"    
    ]    
}
```
从数组开头删除一个值：   


> db.post.update({"id":1},{$pop:{"comments":-1}})    
WriteResult({ "nMatched" : 1, "nUpserted" : 0, "nModified" : 1 })    
> db.post.findOne({"id":1})    
{    
    "_id" : ObjectId("54a530c3ff0df3732bac1680"),    
    "id" : 1,    
    "name" : "joe",    
    "age" : 21,    
    "comments" : [    
        "test2",    
        "test3",    
        "test4",    
        "test5"    
    ]    
}    
>
三、删除数组中一个指定的值：

> db.post.update({"id":1},{$pull:{"comments":"test3"}})   
WriteResult({ "nMatched" : 1, "nUpserted" : 0, "nModified" : 1 })    
> db.post.findOne({"id":1})    
{    
    "_id" : ObjectId("54a530c3ff0df3732bac1680"),    
    "id" : 1,    
    "name" : "joe",    
    "age" : 21,    
    "comments" : [    
        "test2",    
        "test4",    
        "test5"    
    ]    
}    
>
java程序：

        Query query = new Query( Criteria.where("_id").is(id);

        Update update = new BasicUpdate("{'$pull':{'comments':'test4'}}");

        mongoTemplate.updateFirst(query, update, Post.class);
 

 

四、基于数组下标位置修改：

> db.post.update({"id":1},{$set:{"comments.1":"test9"}})   
WriteResult({ "nMatched" : 1, "nUpserted" : 0, "nModified" : 1 })    
> db.post.findOne({"id":1})    
{    
    "_id" : ObjectId("54a530c3ff0df3732bac1680"),    
    "id" : 1,    
    "name" : "joe",    
    "age" : 21,    
    "comments" : [    
        "test2",    
        "test9",    
        "test5"    
    ]    
}    


spring-mongodb-data  返回指定字段

    DBObject dbObject = new BasicDBObject();
    //dbObject.put("name", "zhangsan");  
    //查询条件 BasicDBObject fieldsObject=new BasicDBObject();
    //指定返回的字段fieldsObject.put("name", true); 
     fieldsObject.put("age", true);  
     fieldsObject.put("sex", true);   
      query = new BasicQuery(dbObject,fieldsObject);
      List<Person> user = mongoTemplate.find(query, Person.class);

  第二种方式
       
       Query query = Query.query(Criteria.where("staffId").is(staffId).and("delete").is(MbxtConstant.IS_NO_DELETE));
       //elemMatch 只能返回一个
        query.fields().include("staffId").elemMatch("signDetails",Criteria.where("delete").is(MbxtConstant.IS_NO_DELETE));
        return mongoTemplate.findOne(query, ExpressSigned.class);
>

关联查询 问题：_id为ObjectId类型 projectId为String 不能装换

        LookupOperation lookupOperation = LookupOperation.newLookup().
                from("project").
                localField("projectId").
                foreignField("_id").
                as("project");
        Pattern pattern = Pattern.compile("^.*" + searchValue + ".*$", Pattern.CASE_INSENSITIVE);

        AggregationOperation match = Aggregation.match(Criteria.where("project.name").regex(pattern));


        Aggregation aggregation = Aggregation.newAggregation(lookupOperation, match);

        List<ProjectStorage> results = mongoTemplate.aggregate(aggregation, "projectStorage", ProjectStorage.class).getMappedResults();

 
