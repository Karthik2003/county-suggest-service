# County-Search-Service
County suggest/search service

Outputs Required:

1. Service to run in port 3000.
   Search results to be limited to 5.

Assumptions -

A> For 2 letter search query, will search exact match in STATE column of County table.

B> For more than 3 letters and less than 2 letters, will search containing matching words in Name column of County table.

### Application Execution
List of any third party/ open sources used.
1. To run the application:
- Download the source code from Git Hub
- Run the application from Intellij/Eclipse
- Health checkup -> http://localhost:3000/health
- Metrics -> http://localhost:3000/prometheus
- In postman do a GET call on http://localhost:3000/suggest?q=cowl
  gives the expected output