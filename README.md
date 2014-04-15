smartsantander
==============

Java library for accessing SmartSantander's sensors data, both real time and historical, as described in the Service Documentation: http://smartsantander.eu/wiki/index.php/Data/SEN2SOCSmartSantanderIntegration


Check the API for more details:

http://predictia.github.io/smartsantander/index.html

Import as maven dependency
--------------------------

You will need to add to your project's pom.xml file the repository:

    <repositories>
       <repository>
          <id>predictia-public-releases</id>
          <url>https://raw.github.com/Predictia/maven-repo/master/releases</url>
       </repository>
    </repositories>


And the dependency itself

    <dependencies>
      <dependency>
        <groupId>es.predictia</groupId>
        <artifactId>smartsantander</artifactId>
        <version>0.0.1</version>
      </dependency>
    </dependencies>
