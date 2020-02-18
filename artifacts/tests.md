# Tests

| Tested Class | Test Method Name | Testing | Pass Criteria|
|--------------|------------------|---------|----------------|
| LoginActivity | setEmail | Soft keyboard inputs string into email field | Text displayed is the same as the text input |
| LoginActivity | setPassword | Soft keyboard inputs string into password field | Text displayed is the same as the text input |
| LoginActivity | performLogin | When correct email/password combo is entered and login is pressed, user is taken to MapActivity | After button is clicked, MapActivity is the newly displayed screen |
| LoginActivity | performLoginSpam | When correct email/password combo is entered and login is pressed many times, user is taken to MapActivity | After button is clicked several times, MapActivity is the newly displayed screen |
| AccountModel | logInSuccess | When correct email/password combo is entered to login, a user is logged in | login task returns success | 
| AccountModel | logInFailure | When incorrect email/password combo is entered to login, a user is not logged in | login task returns failure | 
| AccountModel | signUpSuccess | When new email/password combo is entered to signup a user is signed up | signUp task returns success | 
| AccountModel | signUpFailure | When invalid email/password combo is entered, a user is not signed up | signUp task returns failure | 
| MapController | getLocation | Location of the device is accessed correctly | location matches actual location |
| MapController | getMap | Map is present | Map is displayed |
| MapController | getLocationPermission | When Locations Permissions are set to true, location permissions are stored as true | Location permissions are true |
| MapActivity | addButton | When add button is clicked, user is taken to CreateReportActivity | After button is clicked, CreateReportActivity is the newly displayed screen | 
| CreateReportController | writeReportSuccess | When report fields are entered, a report is made | createResult task returns success | 
| CreateReportActivity | firebaseMap_report | When fields are input with dummy text, the text is stored in a report | Report fields match input |
| CreateReportActivity | getCollectionPath | Report is matched with current location | Report matches location |
| CreateReportActivity | customGetCollectionPath | Report is matched with a custom location | Report matches custom location |
| SharedPreferencesController | email | Email data is stored | Stored data matches input | 
| SharedPreferencesController | password | Password data is stored | Stored data matches input | 
| SharedPreferencesController | notSet | With no input, login credentials are absent | No login credentials present |
| SharedPreferencesController | emailSet | Email data is stored | The email that is stored and received matches what was input | 
| SharedPreferencesController | passwordSet | Password data is stored | The password that is stored and received matches what was input | 
| SharedPreferencesController | allSet | Email and password data is stored | The email and password that are stored and received match what was input | 
| SharedPreferencesController | clear | After email and password are stored, clear method removes credentials from storage | No login credentials present | 