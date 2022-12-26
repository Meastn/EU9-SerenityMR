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
6. Create a package EU9 under Java
7. Create Spartan package under EU9 package
8. Create admin package under Spartan package
9. Create SpartanAdminGetTEst java class under admin package
10. This is just a regular test. In order to serenity recognize the test
    * add annotation @SerenityTest at the class level
    * it is coming from import net.serenitybdd.junit5.SerenityTest;
11. Add a properties file with the exect name "serenity.properties" under the main project folder
12. add following lines to the new property file
    * serenity.project.name =EU9 API Report
    * serenity.test.root =EU9
13. The sis for serenity to pickup log and eliminate warnings
    <dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-simple</artifactId>
    <version>2.0.5</version>
    </dependency>
14. SerenityRest provides its own assertions so that they can be directly used in reporting
15. Ensure.that() is the assertion method of SerenityRest
Like:
```
  Ensure.that("Status code is 200", validatableResponse -> validatableResponse.statusCode(200));
  
  Ensure.that("Content Type is JSON", vLRes -> vLRes.contentType(ContentType.JSON));
  
  Ensure.that("ID is 15", vLRes -> vLRes.body("id", is (15)));
```  

16. We do not need jackson or gson dependencies to serialize as long as we use SerenityRest


