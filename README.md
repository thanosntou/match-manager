# Match Manager

### This project runs 2 docker containers:
1. The match-manager app
2. Postgres DB (make sure to stop your local instance, if wanna use this flawlessly)

### How to run:
1. cd in project folder  
2. `mvn install`
3. `docker-compose up`

### Swagger documentation:  
> http://localhost:8080/swagger-ui/index.html

FYI
In the request bodies, the matchTime can be written as "matchTime": "21:45:00". 

For the create api, all body fields are required.  

For the search api, all are optional. Available search criteria:   
[description, teamA, teamB, sport, matchDate, matchTime] 

### Example request for create:
```json
{
"matchDate": "2022-10-26",
"matchTime": "21:45:00",
"teamA": "AEK",
"teamB": "PAO",
"sport": "Football",
"specifier": "X",
"odd": 1.5
}
```

### Example request for search:
```json
{
  "teamB": "PAO",
  "matchDate": "2022-10-26",
  "sport": "Football"
}
```