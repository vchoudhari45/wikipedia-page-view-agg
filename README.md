# Wikipedia pageview data pipeline

## Description

Simple Akka HTTP application that computes the top 25 pages on Wikipedia for each of the Wikipedia sub-domains.

## Usage 

### Health check
Format 
```
curl http://host:port/ruok
```

Example 
```
http://localhost:9000/ruok
```

### Run for Current Day and Hour 
Format 
```
curl http://host:port/page-view
```

Example 
```
http://localhost:9000/page-view
```

### Run for Given Day & Hour 
Format 
```
curl http://host:port/page-view?from=YYYYMMDDHH
```

Example 
```
http://localhost:9000/page-view?from=2019010100
```
 
### Run for Day & Hour Range 
```
curl http://host:port/page-view?from=YYYYMMDDHH&to=YYYYMMDDHH
```

Example 
```
http://localhost:9000/page-view?from=2019010100&to=2019010102
```
