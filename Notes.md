### Serenity Project
Serenity BDD is a *framework and open source*
library for the creation of automated software testing for code in development.

Here is a link [to basic Serenity documentation!](https://serenity-bdd.github.io).

### Steps to Create a Serenity Framework
1. Create a maven project 
2. under pom.xml add relevant dependencies and plugins
```XML 
<properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
```
3. add serenity core dependency
```XML
 <dependency>
    <groupId>net.serenity-bdd</groupId>
    <artifactId>serenity-core</artifactId>
    <version>3.5.0</version>
</dependency>

```
4. add junit dependency
```XML
<dependency>
   <groupId>junit</groupId>
   <artifactId>junit</artifactId>
   <version>3.8.2</version>
</dependency>
```
5. add serenity junit dependency since serenity can not work with junit without this dependency
```XML
<dependency>
    <groupId>net.serenity-bdd</groupId>
    <artifactId>serenity-junit5</artifactId>
    <version>3.5.0</version>
</dependency>
```
6. 