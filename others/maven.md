    下载源码
    <classifier>sources</classifier>
    <type>java-source</type>
    
    可选依赖
    <optional>true</optional>
    假设以上配置是项目A的配置，即：Project-A --> Project-B。在编译项目A时，是可以正常通过的。
    如果有一个新的项目X依赖A，即：Project-X -> Project-A。此时项目X就不会依赖项目B了。如果项目X用到了涉及项目B的功能，那么就需要在pom.xml中重新配置对项目B的依赖。
    
    排除依赖
    排除B里的E
    <dependency>
      <groupId>sample.ProjectB</groupId>
      <artifactId>Project-B</artifactId>
      <version>1.0-SNAPSHOT</version>
      <exclusions>
        <exclusion>
          <groupId>sample.ProjectE</groupId> <!-- Exclude Project-E from Project-B -->
          <artifactId>Project-E</artifactId>
        </exclusion>
      </exclusions>
    </dependency>
