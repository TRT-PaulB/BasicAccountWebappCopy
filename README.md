"# BasicAccountWebapp"

*******************************************
NOTES:
No account modelling included in the implementation as this was not necessary

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
********* OPTIONS FOR RUNNING APP *********

- Direct from application: 
  - download the app from github:
    https://github.com/MiniProjs123/BasicAccountWebapp
    
  - Navigate to within the top level BasicAccountWebapp directory and open a terminal/console window
  
  - Run using maven:
    - Unix based systems:   ./mvnw spring-boot:run
    - Windows:              mvn spring-boot:run     
  
 *******************************************   


	
	