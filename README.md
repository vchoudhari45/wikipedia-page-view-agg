# Wikipedia pageview data pipeline

## Description

Simple Akka HTTP application that computes the top 25 pages on Wikipedia for each of the Wikipedia sub-domains.

### Features

* Accept input parameters for the date and hour of data to analyze (default to the current date/hour if not passed).
* Download the page view counts from wikipedia for the given date/hour in https://wikitech.wikimedia.org/wiki/Analytics/Data/Pagecounts-all-sites format from https://dumps.wikimedia.org/other/pageviews/.
* Eliminate any pages found in this blacklist: https://s3.amazonaws.com/dd-interview-data/data_engineer/wikipedia/blacklist_domains_and_pages Compute the top 25 articles for the given day and hour by total pageviews for each unique domain in the remaining data.
* Save the results to a file, either locally or on S3, sorted by domain and number of pageviews for easy perusal.
* Only run these steps if necessary; that is, not rerun if the work has already been done for the given day and hour.
* Be capable of being run for a range of dates and hours; each hour within the range should have its own result file.

## Additional things needed to operate this application in a production setting

* Changes to application.conf for Akka Http server & host
* Health-check endpoint should be schedule on system monitoring tool 
* Metrics should be collected for latency of API 
* Storage location can be s3 as of now it is on localhost, project directory

## Potential changes to run automatically for each hour of the day

* Cron Entry or Scheduler like Quartz can be used to make call to API call 

## Testing

Please run below command under the project directory to run automated Tests
```
sbt test
```

## Potential design improvements on this application design

* Akka cluster sharding can be implemented to 


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
