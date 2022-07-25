# AdditionRepository:

This Service provides two Api to send a question to user to add 3 numbers and in 2nd Api
it will verify question and result If both are correct than send result with 200 status code.
If fails send 400 status code with bad request.

Software Used:
Java (1.8)
PostMan
Rest Api
Spring Boot
Default port is 8080 we can change the port If we want by providing
in application.properties file server.port=####

In the First api Get Request will call
URL: http://localhost:8080/question
the request will come to AdditionServiceController sendQuestion method will invoke.
UserName collected from Incoming request than it will call getRandomNumber method of service
It will generate 3 random number maxlimit upto 50 and all 3 randomNumber store as a key
of linked hashmap and sum of these number as Value.
We will send the response to the user question as Sting please sum the numbers .
example: "Hello Prabhanshu; Please sum the numbers  [34,49,22]"

In 2nd API: http://localhost:8080/answer
User will send the answer of above question as a Resul String
example below:
"result": "Great. The original question was “Please sum the numbers 34,49,22 and the answer is 105."
Than AdditionServiceController class verifyQuestionAndResult method will invoke.
After that will collect the incoming request Result String.
Service class extract number will invoke it will extract all number present in incoming String.
it will converted into StringArray and after that integer both opreans ans some are segerated on index basis and put an
map. Finally we will comare both the map if both are same than 200 Ok or else return 400 bad request.



