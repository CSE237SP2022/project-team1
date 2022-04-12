# project-team1
project-team1 created by GitHub Classroom

A calorie counter application that can store and provide information
about a user's food intake. 

User Stories
- Users who wish to have a working record of their food intake can track each food they eat
- Users who wish to view total stats for a particular day can see their cumulative calories,
  carbs, fat, and protein for a session
- Users can view a .csv file with the food data of every food item entered across multiple sessions, called 'dailyFoodStatistics.csv'
- Users can view a .csv file of every food item they have entered in a given session in the file called 'database.csv' (in the counterClass folder).  
  
Current Issues
- Counter currently tracks total cumulative calories, but does not reset yet (like reset after a day)
- Counter saves total cumulative food data in a 'dailyFoodStatistics.csv' file (which you can search up after you enter food data into the counter), but counter does not internally save the total cumulative calorie data session to session yet, so it only tracks total statistics per session
- Temporarily fixed error messages that come up when you enter say a word instead of a number for the food data prompts, but need to do a better fix with a helper method
- database.csv is overwritten every time a user ends and then starts a new session. 
 
Future Implementations
- Recognize when a food has already been entered and remember its statistics so the user
  will not have to repeat/enter anything other than its name
- Further condense code by placing more of it in separate methods
- Store food information based on the current day and reset food statistics after the day
- Add more common foods to database.csv and make it a file that comes in the repo so all calorie tracker users have access to the large database
  
Run Instructions
  1. In a terminal window, clone the repository using the git clone command
  2. To execute, enter command './run.sh'
