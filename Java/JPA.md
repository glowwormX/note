JPA @Entity(name="xx")JPA中创建name为xx的实体类，并映射表,在JPQL语句中要使用xx作为表名，默认为实体类名   
JPA @Table(name="xx")映射该实体类为xx的表   
统一 @Entity+@Table(name="表名")   
```
jpa复杂查询
返回新实体类:SampleIndexView 此处不加限定名报错，Sample和Type对应注解为 @Entity的实体类，如果有相同的需要加限定名
@Query(value="SELECT new net.nbjp.dao.pojo.vo.SampleIndexView(s.id, s.seriecode, s.serienamecn, s.createuser, s.createtime, t.typenamecn) "
		+ "FROM Sample s, Type t  "
		+ "WHERE s.producttype = t.id "
		+ "AND (s.seriecode like %:seriecode% "
		+ "OR s.serienamecn like %:serienamecn% "
		+ "OR t.typenamecn like %:typenamecn% )")
List<SampleIndexView> listSampleIndexView(@Param("seriecode") String seriecode,
		@Param("serienamecn") String serienamecn,@Param("typenamecn") String typenamecn);
```
