# Tests

| Tested Class | Test Method Name | Testing | Pass Criteria|
|--------------|------------------|---------|----------------|
| AccountModel | logInSuccess | When correct email/password combo is entered to login, a user is logged in | login task returns success | 
| AccountModel | logInFailure | When incorrect email/password combo is entered to login, a user is not logged in | login task returns failure | 
| AccountModel | signUpSuccess | When new email/password combo is entered to signup a user is signed up | signUp task returns success | 
| AccountModel | signUpFailure | When invalid email/password combo is entered, a user is not signed up | signUp task returns failure | 
| AccountModel | signOutTest  | When a user signs out, the auth state changes | no exceptions occur upon sign out | 
| CreateReportActivity | setTitle | Report has a title field | A report has a title |
| CreateReportActivity | setInfo | Report has an info field | A report has info text |
| CreateReportController | writeReportSuccess | When report fields are entered, a report is made | createResult task returns success | 
| CreateReportController | reportCreateSuccess | When a report is submitted, a report is made | A report result returns success | 
| CreateReportController | reportCreateFailure | When a report fails to submit, the result is a failure | When a report fails to submit, the result is a failure |
| LoginActivity | setEmail | Soft keyboard inputs string into email field | Text displayed is the same as the text input |
| LoginActivity | setPassword | Soft keyboard inputs string into password field | Text displayed is the same as the text input |
| LoginActivity | performLogin | When correct email/password combo is entered and login is pressed, user is taken to MapActivity | After button is clicked, MapActivity is the newly displayed screen |
| LoginActivity | performLoginSpam | When correct email/password combo is entered and login is pressed many times, user is taken to MapActivity | After button is clicked several times, MapActivity is the newly displayed screen |
| LoginActivity | signUp | That pressing sign up takes you to the sign up page | After pressing the sign up button an Intent to SignUpActivity is triggered. |
| MapActivity | addButton | When add button is clicked, user is taken to CreateReportActivity | After button is clicked, CreateReportActivity is the newly displayed screen | 
| MapActivity | assertAddButtonStartState | An add button appears | The add button is displayed with the text ADD and the + symbol |
| MapActivity | assertProcessingState | When the add button is pressed, the add button changes state and the map can now be clicked to place a marker | The add button is transformed into a Cancel button |
| MapActivity | assertCancelling | When the cancel button is pressed, the attempt to make a marker is cancelled | The cancel button returns to an add button and regular map interactivity resumes |
| MapActivity | assertBackToProcessing | The user can click add and cancel multiple times and it will be in the right state | When the add button is pressed, cancelled, and then add button is clicked again, the add state is the current state |
| MapActivity | uselessMap | When toggling between add and cancel states, the map is still there | The map is present | 
| MapActivity | createAndCancel | A user can cancel the act of creating a report at any point in the process before submit | Clicking add to add a marker and then clicking the back button returns the state back to the original state |
| MapController | getLocation | Location of the device is accessed correctly | location matches actual location |
| MapController | getMap | Map is present | Map is displayed |
| MapController | getLocationPermission | When Locations Permissions are set to true, location permissions are stored as true | Location permissions are true |
| Report | firebaseMap_report | When fields are input with dummy text, the text is stored in a report | Report fields match input |
| Report | getCollectionPath | Report is matched with current location | Report matches location |
| Report | customGetCollectionPath | Report is matched with a custom location | Report matches custom location |
| SharedPreferencesController | email | Email data is stored | Stored data matches input | 
| SharedPreferencesController | password | Password data is stored | Stored data matches input | 
| SharedPreferencesController | notSet | With no input, login credentials are absent | No login credentials present |
| SharedPreferencesController | emailSet | Email data is stored | The email that is stored and received matches what was input | 
| SharedPreferencesController | passwordSet | Password data is stored | The password that is stored and received matches what was input | 
| SharedPreferencesController | allSet | Email and password data is stored | The email and password that are stored and received match what was input | 
| SharedPreferencesController | clear | After email and password are stored, clear method removes credentials from storage | No login credentials present | 
| SharedPreferencesController | theme | Getter for theme from the user defined preferences | Returns the same theme that was written to the Android context | 
| SharedPreferencesController | color | Getter for color from the user defined preferences | Returns the same color that was written to the Android context | 
| SettingsTest | colorSet | Color is stored | Returns a color | 
| SettingsTest | themeSet | Theme is stored | Returns a theme |
| SettingsTest | defaultTheme | Default theme is run when no theme is specified | Theme is set to default amber theme |
| SettingsTest | colorDialog | Color chooser dialog is displayed when button is clicked | Dialog is opened |
| SettingsTest | checkbox | Checkboxes are checked when clicked | Checkbox is selected after click | 
| ReportDetailController |  reportName | Getter for the underlying ReportReference name | Returns the proper name of the underlying ReportReference object |
| ReportDetailController |  reportInformation | Getter for the underlying ReportReference info | Returns the proper info of the underlying ReportReference object |
| ReportDetailController |  reportLocationInfo | Getter for the underlying ReportReference locationInfo | Returns the proper location information of the underlying ReportReference object |
| ReportReference | firebaseMap_report | When fields are input with dummy text, the text is stored in a report | Report fields match input |
| ReportReference | getCollectionPath | Report is matched with current location | Report matches location |
| ReportReference | customGetCollectionPath | Report is matched with a custom location | Report matches custom location |
| ReportDetailActivity | addMoreInfo | Pressing "moreInformation" button transition to the proper ReportAddInfo activity | After pressing the moreInfo button the activity shown is the ReportAddInfo activity
| ReportDetailActivity | setFields | Same information is displayed on the screen as is put in the intent extra |Report put into the intent extra is displayed on the screen |
| ReportDetailActivity | goToCreateAndBack | A user can go to the Create message page and then cancel that action. |A user can go to the Create message page and then cancel that action. |
| ReportAddInfoActivity | setMessage | Can enter a message into the field. |A message can be successfully entered. |
| ReportEditInfoActivity | setTitle | Title edit text is set to report title. |Title matches report. |
| ReportEditInfoActivity | setInfo | Info edit text is set to report info text. |Info matches report.|
| ReportEditInfoActivity | setLocInfo | Location info edit text is set to report loc info. |Location info matches report.|
| ReportEditInfoActivity | editButtonTest | Save button appears. |Edit button is completely visible.|
