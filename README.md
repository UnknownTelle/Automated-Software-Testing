# Automated-Software-Testing

Using an API that serves informaion about restaurants in New York, the task was to make a pice of software that would fulfil specified business requirments.

#### Business requirements
* Return all restaurants within a specific neighbourhood that has a certin cuisine
* Return all resturants that are open on a specific day, time
* Return restaurants within a neighbourhood that has a rating score equal to or higher then what the user asks 
* Return restaurants within a neighbourhood that has a DOHMH inspection score equal to or higher then what the user asks
* Return a list of restaurants in order to there location from a hotel within each neighbourhood

#### Testing used
* Unit tests
* Integration tests
* Functional tests

## API informaion
The API would return a JSON message containing a list of all restaurants and there properties that would then be used within this application. An example JSON message returned is:

```
{
   "restaurants":[
      {
         "id":1,
         "name":"Mission Chinese Food",
         "DOHMH_inspection_score":"13",
         "neighborhood":"Manhattan",
         "photograph":"1.jpg",
         "address":"171 E Broadway, New York, NY 10002",
         "latlng":{
            "lat":40.713829,
            "lng":-73.989667
         },
         "cuisine_type":"Asian",
         "operating_hours":{
            "Monday":"5:30 pm - 11:00 pm",
            "Tuesday":"5:30 pm - 12:00 am",
            "Wednesday":"5:30 pm - 12:00 am",
            "Thursday":"5:30 pm - 12:00 am",
            "Friday":"5:30 pm - 12:00 am",
            "Saturday":"12:00 pm - 4:00 pm, 5:30 pm - 12:00 am",
            "Sunday":"12:00 pm - 4:00 pm, 5:30 pm - 11:00 pm"
         },
         "reviews":[
            {
               "name":"Steve",
               "date":"October 26, 2016",
               "rating":4,
               "comments":"..."
            },
            {
               "name":"Morgan",
               "date":"October 26, 2016",
               "rating":4,
               "comments":"..."
            },
            {
               "name":"Jason",
               "date":"October 26, 2016",
               "rating":3,
               "comments":"..."
            }
         ]
      }
 ```

## Disclaimer 
This was created for a stage two univerisity assessment
