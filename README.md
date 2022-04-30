# RmgX_Assignment

Prjoect : Asset Management Application

i created this application using springboot,jpa and h2 database. to create folder structure i went to spring initializr site then i selected build tool maven,language java, spring boot version 2.7.0 
after this i included some dependencies from depency section like spring web,spring data JPA and H2 Database. i gave artifact name as rmgx with base package as com.thinking.machines

this application conatins sperate folders for
services,exception,repositories,dto and dao to increase readability and cleanness.

in package com.thinking.machines.rmgx.respositories ,I created different Jpa repositories for different tables/classes

in package com.thinking.machines.machines.rmgx.dto ,I created different Classes(As mentioned in assignment) and Annotated with @Entity annoation. i applied annoation on fields according to need of tables  

in package com.thinking.machines.rmgx.services , i created controller classes or we can say services or functions that are mapped with a particular web request.
i did not access repsitories directly in services,instead of doing this i created  DAO Classes(in package com.thinking.machines.rmgx.dao) which will act as intermediary between services and repositories.

Controller Classes/Services -----> DAO Classes ------> respsitories

i idea was simple services will communicate with DAO classes and further methods of DAO classes will communicate with respositories.
Key point: methods of DAO classes have been declared with an unchcked Exception named as "DAOException". this exception class is in package com.thinking.machines.rmgx.exception , so while dealing with methods of DAO classes in services you will have to take care of checked exception.

lets talk about servies folder that contains servies.each method/service of controller class has return type of Type ActionRespose(it is class in package com.thinking.machines.rmgx.dto package).Object of ActionResponse will be sent in response for every services and i will increase readability of code also. 

public class ActionResponse implements java.io.Serializable
{
public boolean success=false;
public String exception="";
public Object result=null;
} 

suppose we are adding an asset if our operation gets successfull then i set the propertes of ActionResponse as success=true , exception="", and if there is a output then assigned it to result otherwise result will be null. but i case of addition of asset i will be like result=Asset Object.
and if operation gets fail then i set success=false, excetion=exception coming form dao class  and result=null;

when ever program will run there will be four table in database Category,Asset,Emloyee,AssetEmployee

i wrote a file data.sql in resources folder to save the time of data entry manually, data.sql will fill dummy data in category table and employee table. this thing will help in testing of asset Services.
since category and asset are related to each other with relation one to many (like one category can have many asset or wise versa many asset can have one category) here asset table contains a foreign key(category_id) associated with primary key of category class so i someone add a record of asset and enter a wrong category_id which is not present in category table then i will generate error and dao class will throw an exception and ActionResponse Object will be sent in response with Sucess=false;

in case of asset and employee i assumed that relationship would be many to one (asset to employee). in this case to achive this i created a extra table AssetEmployee and  maintained things programatically.i also took care of the thing that an Asset should not get deleted if is assigned to an employee.

i perfomed Testing of DAO using JUnit5 and for intergration testing or 
full project testing i used Rest clinet/RestMan . below i have explained how you can test full application.

lets test the AssetController Services.

@RestController
@RequestMapping(value="/asset")
public class AssetController
{
@PostMapping("/add")
public ActionResponse add(@RequestBody Asset asset)

@PostMapping("/update")
public ActionResponse update(@RequestBody Asset asset)

@GetMapping("/getAll")
public ActionResponse getAll()

@PostMapping("/delete/{name}")
public ActionResponse delete(@PathVariable("name") String assetName)

@GetMapping("/getByName")
public ActionResponse getByName(@RequestParam("name") String name)

@PostMapping("/assign/{name}/{id}")
public ActionResponse assign(@PathVariable("name") String assetName,@PathVariable("id") int employeeId)

@PostMapping("/revoke/{name}/{id}")
public ActionResponse revoke(@PathVariable("name") String assetName,@PathVariable("id") int employeeId)


}


Add Method testing : to test add method we will send Post type request
                     with URL : http://localhost:8080/asset/add 
                     and in request body we will send JSON Data like 
                       {
			  "name": "chair",
			  "purchaseDate": "2021-05-14",
 			  "conditionNote": "some note on condition",
 			  "assignmentStatus": "Available",
    			  "category": {
				       "id": 2 
    				      }
			} 
// no need to specify all fields of category class here i will only use id
--------------------------------
update method testing :  URL: http://localhost:8080/asset/update
                         Json data : {
			  "name": "chair",
			  "purchaseDate": "2021-05-14",
 			  "conditionNote": "some note 123",
 			  "assignmentStatus": "Available",
    			  "category": {
				       "id": 13 
    				      }
			  } 
--------------------------------
getAll method testing : URL : http://localhost:8080/asset/getAll with 
                        GET type request
--------------------------------
delete method testing : URL : http://localhost:8080/asset/delete/mouse                         with post type request  
--------------------------------
getByName method testing : URL                     : http://localhost:8080/asset/getByName?name=mouse
                    with Get type request
--------------------------------
assign method testing : URL : http://localhost:8080/asset/assign/mouse/1 with post post type request
here mouse is assetName and 1 is employee id
---------------------------------
--------------------------------
revoke method testing : URL : http://localhost:8080/asset/revoke/mouse/1 with post post type request
here mouse is as setName and 1 is employee id

similary we can do testing for methods of CategoryController class and Employee Controller Class.
