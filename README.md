# TaskMangementAPI


PreRequisites

        Install JDK 1.8 or above
        
        Install Maven or Gradle and H2 as follows below 


Execute using Gradel:

          ./gradlew clean bootRun

Notes:

DB Design :

	CREATE TABLE IF NOT EXISTS Task
	(
	Task_Id char(50),
	Description char(100),
	Created_By char(50),
	Created_Time datetime,
	Due_Date datetime,
	Reminder_Time int,
	Reminder_Type char(100),
	Place char(50),
	RecurrenceType  char(50),
	Task_Status char(50),
	Action_Type char(50),
	);


API Design :
Rest APIs:  CRUD      
Retrieve -> GET  
Create -> POST
Update -> PUT
Delete -> Delete


Notification Job Design:

	Create Job :

	   Due Date:
	     Query
	       Current Time > "2021-10-05 13:11:35.422" -> 20 Tasks

	     Make 20 calls
	      https://api.kaleyra.io/v1/%s/voice/outbound?to=%s&bridge=%s&api-key=%s&target=[{"message":{"text":"%s"}}]

Different Layers:
API Layer
Service Layer
DAO Layer
Connectors Layer

Full Technical Stack:

	Java 8
	Gradle - Build Tool
	SprintBoot - API
	Embedded   - Tomcat
	Rest - Spring
	JPA  -
	Making external API call -> Okhttp Framework
	DBConnection Pool
