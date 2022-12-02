# Maxar Code Challenge
_____

This repo contains the source code for the completed Maxar code challenge.

## Solution
Repo contains two Java/Spring Boot applications:  
`challenge_api`: REST API with a `GET` endpoint to return a random UUID that represents a `jobId`  
`challenge_runner`: command line application used to issue requests to REST API and collect job IDs

## Usage
**NOTE**: the code here was compiled with `Java 11`, so `Java 11` or newer is needed to run these applications

`challenge_runner` expects the `challenge_api` REST API to be running in order to execute requests against it. The
jar file for the API application is at `/challenge_api/build/challenge_api-0.0.1-SNAPSHOT.jar`. You can execute
this jar file to start the API application running on `localhost:8080`:  
```
$JAVA_HOME/bin/java -jar challenge_api/build/challenge_api-0.0.1-SNAPSHOT.jar
```

Once the API application is running, you can execute the Runner application and specify how many `GET` requests to make
to the API using this command:
```
$JAVA_HOME/bin/java -jar challenge_runner/build/challenge_runner-0.0.1-SNAPSHOT.jar <number_of_requests_to_make>
```

The Runner application will execute the specified number of `GET` requests against the running API application,
collect the response job UUIDs and pretty print a JSON representation of the collected job UUIDs:

```json
{
  "jobs": ["Job-UUID", "Job-UUID", "Job-UUID"]
}
```

## Challenge Requirements:
A program is needed to issue between 1000 and 2000 REST requests to a single endpoint to collect a set of unique 
identifiers. The identifiers returned from the REST call are JOB Id's of another system that can be tracked later. 
The program must issue all the requests and return at exit a JSON output that is the list of JOB identifiers as 
returned from the REST call. It is expected that the REST calls will respond within a random distribution of 1 - 
10 seconds.

1. Must make 1000 - 2000 REST calls to a single REST endpoint as efficiently as possible to collect the JOB Id's.
2. Format of the REST call will be `GET`
3. REST call will include a URL parameter called `id`
4. Each REST call will be issued with a different value assigned to the `id` parameter
5. Expected Responses from the REST call will take between 1 and 10 seconds and will be random in nature.
6. The REST response format will be well formatted JSON.
7. The REST response format will have one key value pair with the key called `jobId`
and the value being a generated UUID.
8. All response must be collected and stored for a final response at program exit.
9. At program exit a well formatted JSON object will be print for analysis
10. The JSON package on exit must have a key value pair with the key being `jobs` and the value being a JSON List 
of the response job id values from the REST calls made.