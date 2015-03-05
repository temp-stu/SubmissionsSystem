

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                        http://maven.apache.org/maven-v4_0_0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>ajia.helloworld</groupId>
	<artifactId>application-woven</artifactId>
	<packaging>jar</packaging>
	<version>1.0-SNAPSHOT</version>
	<name>application-woven</name>
	<dependencies>
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjrt</artifactId>
			<version>1.6.5</version>
		</dependency>
		<dependency>
			<groupId>ajia.helloworld</groupId>
			<artifactId>profiling</artifactId>
			<version>1.0-SNAPSHOT</version>
		</dependency>
		<dependency>
			<groupId>ajia.helloworld</groupId>
			<artifactId>application-unwoven</artifactId>
			<version>1.0-SNAPSHOT</version>
		</dependency>
	</dependencies>
	
	<build>
		<plugins>
			<plugin>
				<groupId>org.codehaus.mojo</groupId>
				<artifactId>aspectj-maven-plugin</artifactId>
				<executions>
					<execution>
						<goals>
							<goal>compile</goal>
						</goals>
					</execution>
				</executions>
				<configuration>
					<weaveDependencies>
						<weaveDependency>
							<groupId>ajia.helloworld</groupId>
							<artifactId>application-unwoven</artifactId>
						</weaveDependency>
					</weaveDependencies>
					<aspectLibraries>
						<aspectLibrary>
							<groupId>ajia.helloworld</groupId>
							<artifactId>profiling</artifactId>
						</aspectLibrary>
					</aspectLibraries>
				</configuration>
			</plugin>
			<plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>exec-maven-plugin</artifactId>
                <executions>
                    <execution>
                        <goals>
                            <goal>java</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <mainClass>ajia.main.Main</mainClass>
                </configuration>
            </plugin>
		</plugins>
	</build>
</project>

<!-- Listing C.4 Maven pom.xml to weave application-unwoven artifacts with profiling artifact -->


/////


<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                        http://maven.apache.org/maven-v4_0_0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>ajia.helloworld</groupId>
	<artifactId>application-woven</artifactId>
	<packaging>jar</packaging>
	<version>1.0-SNAPSHOT</version>
	<name>application-woven</name>
	<dependencies>
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjrt</artifactId>
			<version>1.6.5</version>
		</dependency>
		<dependency>
			<groupId>ajia.helloworld</groupId>
			<artifactId>profiling</artifactId>
			<version>1.0-SNAPSHOT</version>
		</dependency>
		<dependency>
			<groupId>ajia.helloworld</groupId>
			<artifactId>application-unwoven</artifactId>
			<version>1.0-SNAPSHOT</version>
		</dependency>
	</dependencies>
	
	<build>
		<plugins>
			<plugin>
				<groupId>org.codehaus.mojo</groupId>
				<artifactId>aspectj-maven-plugin</artifactId>
				<executions>
					<execution>
						<goals>
							<goal>compile</goal>
						</goals>
					</execution>
				</executions>
				<configuration>
					<weaveDependencies>
						<weaveDependency>
							<groupId>ajia.helloworld</groupId>
							<artifactId>application-unwoven</artifactId>
						</weaveDependency>
					</weaveDependencies>
					<aspectLibraries>
						<aspectLibrary>
							<groupId>ajia.helloworld</groupId>
							<artifactId>profiling</artifactId>
						</aspectLibrary>
					</aspectLibraries>
				</configuration>
			</plugin>
			<plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>exec-maven-plugin</artifactId>
                <executions>
                    <execution>
                        <goals>
                            <goal>java</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <mainClass>ajia.main.Main</mainClass>
                </configuration>
            </plugin>
		</plugins>
	</build>
</project>

<!-- Listing C.4 Maven pom.xml to weave application-unwoven artifacts with profiling artifact -->


//////


<?xml version="1.0" encoding="UTF-8"?>
<project name="TestingAspectJ" default="compile" basedir=".">
    <description>Builds, tests, and runs the project TestingAspectJ.</description>
    <!--<import file="nbproject/build-impl.xml"/>-->

    <property name="src.dir" value="src/main/java" />
    <property name="class.dir" value="classes" /> 
    <property name="aspectj.home" value="C:/dev/aspectj8.4" />	  
    
    <path id="project.class.path">
        <pathelement path="${class.dir}" />        
        <pathelement location="${src.dir}" />
        <pathelement location="${aspectj.home}/lib/aspectjtools.jar"/>
        <pathelement location="${aspectj.home}/lib/aspectjrt.jar"/>
    </path>
    
    <taskdef classpathref="project.class.path" resource="org/aspectj/tools/ant/taskdefs/aspectjTaskdefs.properties" />

    <target name="clean" description="Removes all directories related to this build.">
        <delete dir="${class.dir}" />
    </target>
    
    <target name="_validate-properties">
        <fail unless="aspectj.home" message="aspectj.home must be set, check it"/>
        <available property="_aspectj.home.valid" file="${aspectj.home}/lib/aspectjtools.jar"/>
        <fail unless="_aspectj.home.valid" message="Invalid aspectj.home. Must point to the parent of the lib directory" />
    </target>
	
    <target name="compile" depends="_validate-properties">
        <mkdir dir="${class.dir}"/>
        <iajc destdir="${class.dir}" source="1.7" srcdir="${src.dir}" classpathref="project.class.path" />
    </target>

    <target name="run" depends="compile">
        <java classname="net.hamendi.main.Main" classpath="classes" classpathref="project.class.path" fork="true">
        	<sysproperty key="ant.run" value="true"/>
	</java>
    </target>
	
	<!--
	<target name="compile">
		<mkdir dir="dist" />
		<iajc source="1.5" classpathref="project.class.path" outjar="dist/profiling.jar" xlintfile="xlint.properties">
			<sourceroots>
				<pathelement location="src/main/java" />
			</sourceroots>
		</iajc>
	</target>
	
	<target name="compile">
        <mkdir dir="dist" />
        <mkdir dir="classes" />
        <javac source="1.5" destdir="classes" srcdir="src/main/java" />
        <jar destfile="dist/application.jar" basedir="classes" />
    </target>

   weave
	<target name="initialize">
		<mkdir dir="dist" />
		<copy todir="dist">
			<fileset dir="../SectionB3andC3_1JavaLibrary/dist">
				<include name="application.jar" />
			</fileset>
			<fileset dir="../SectionB2andC2AspectLibrary/dist">
				<include name="profiling.jar" />
			</fileset>
		</copy>
	</target>

	<target name="weave" depends="initialize">
		<mkdir dir="dist" />
		<iajc injars="dist/application.jar" aspectpath="dist/profiling.jar" outjar="dist/profiled-application.jar" classpathref="project.class.path">
		</iajc>
	</target>

	<target name="run-normal" depends="weave">
		<java classname="ajia.main.Main" classpathref="project.class.path">
			<classpath>
				<pathelement location="dist/application.jar" />
			</classpath>
		</java>
	</target>
	
	<target name="run-woven" depends="weave">
		<java classname="ajia.main.Main" classpathref="project.class.path">
			<classpath>
				<pathelement location="dist/profiled-application.jar" />
				<pathelement location="dist/profiling.jar" />
			</classpath>
		</java>
	</target>

	-->


</project>
