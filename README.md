"# BasicAccountWebapp"

*******************************************
NOTES:

- No account modelling included in the implementation as this was not necessary
- Removed the superfluous word 'and' from scenarios 2 and 3
- Test provided:
  - service
  - controller 
  
*******************************************
ENDPOINT:  

http://localhost:5000/greeting/{account}/{id}/{type}   

EXAMPLES:
1) Get a greeting for a small personal business account with id 123       
   http://localhost:5000/greeting/personal/123/small

2) Attempt to get a small business account (expect a message saying this path is not implemented)
   http://localhost:5000/greeting/business/1/small
	
3) Get a greeting for a big business account:	
   http://localhost:5000/greeting/business/1/big 

*******************************************
********* DIRECTIONS TO RUN APP: *********
*******************************************

- Direct from application: 
  - download the app from github:
    https://github.com/MiniProjs123/BasicAccountWebapp
    
  - Run the following commands at the application level directory:
    - Build the application jar files
      mvn clean install
  
    - Test the app with maven (optional):
      mvn spring-boot:run
      
      --> http://localhost:5000/greeting/personal/123/small
      --> http://localhost:5000/greeting/business/1/small
      --> http://localhost:5000/greeting/business/1/big
  
     - check that docker is installed
       docker --version
       
     -  Do this???
        mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
        
	 - Build the docker image for the app
	   docker build -t accountapp .
	    
	 - verify the image has been created as expected
	   docker image ls
	   
	 - run the app in an instance of the docker container defined in the image
	   docker run -p 5000:5000 -t accountapp
	
	 - verify the container is up and running, and note the container id for accountapp
	   docker ps
	
	 - stop the app:
	   press 'CTRL+C' to stop the command line running
	   docker stop [container id] 
	   
	 - verify that the container has now been stopped
       docker ps -a
	   
 *******************************************   

	
	/*
	Dockerfile
	
	FROM openjdk:8-jdk-alpine
	RUN addgroup -S spring && adduser -S spring -G spring
	USER spring:spring
	ARG DEPENDENCY=target/dependency
	COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
	COPY ${DEPENDENCY}/META-INF /app/META-INF
	COPY ${DEPENDENCY}/BOOT-INF/classes /app
	ENTRYPOINT ["java","-cp","app:app/lib/*","com.bank.BasicAccountWebappApplication"]
	*/
	
	
	
	

	
	