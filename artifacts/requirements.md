# Requirements

| Requirement ID | User Story ID | Requirement | Acceptance Criteria | Effort | Priority | Status |
|----------------|---------------|-------------|-------------|--------|--------|--------|
|000|000| A user is able to enter their e-mail address for account creation|User's entered email is accessible by the application|1|Necessary|Satisfied|
|001|000| A user is able to enter their password for account creation|User's entered password is accessible by the application|1|Necessary|Satisfied|
|002|000| A user is unable to create an account with a void username or password|An account is created when the user presses signup with a valid username/password combination|1|Necessary|Satisfied|
|003|000| When a user creates an account their account is created and saved|Backend has a collection of user information|2|Necessary|Satisfied|
|004|001| The application is successfully able to launch|When the application is launched the process will successfully start to run without crashing|1|Necessary|Satisfied|
|005|002| The application maintains a users information while closed|If the application is closed, login information is maintained|1|Necessary|Satisfied|
|006|003| Without logging in, the application successfully displays a map of issues|Can navigate to the Map in the application without signing in|2|Important|Working|
|007|003| After logging in, the application successfully displays a map of issues|After logging in, a map is displayed|3|Necessary|Satisfied|
|008|004| A user's information is able to be stored locally|Application will have a cache of user login data|1|Necessary|Satisfied|
|009|004| A user's information is able to be retrieved locally|Cache of user login information is accessible across launches|1|Necessary|Working|
|010|004| A user is automatically signed in if local sign in information is present|Upon application open, if the user has stored login data, the app will automatically log in|1|Necessary|Satisfied|
|011|005| Tapping an add report button brings up a form to create a report|A form shows up when the add report button is tapped|3|Necessary|Satisfied|
|012|005| Submitting a report form creates a report|A report object is created on Firebase when the submit button is pressed|3|Necessary|Satisfied|
|013|021| A user is able to access settings for the application|A settings menu is accessible from within the application|3|Necessary|Working|
|014|022| A user is able to sign out of their account|A signout button is available and when pressed the user session will end|3|Necessary|Done|
|015|023| A user can see their location on a map|When using the app a pin will appear on the map showing the user's current location|3|Necessary|Working|
|016|024| A user can associate their new report with a location|When creating a report, you can select a location on the map to place the report|3|Necessary|Satisfied|
|017|031| A user can tap on a user story to bring up additional information about a report|When a report is tapped, a screen with additional information comes up|3|Necessary|Working|
|018|016| A user can add more information to a report on the map|When viewing the additional information screen for a report, the user can add more information|3|Necessary|Planned|
|019|019| A user can filter reports by their category|When viewing reports as a list, the user can filer them by their category|3|Important|Planned|
|020|023| A user can accept or decline location permissions|When using the app, a dialog will pop up asking the user to accept or deny location permissoins|3|Necessary|Working|
