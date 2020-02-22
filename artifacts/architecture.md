# Program Organization

### Context Diagram:

<img src = "/images/ContextDiagram.png" width="200" >

Above context diagram shows the basic interaction that is the user interacting with the OpenSesame app.

### Container Diagram:

<img src = "/images/SystemArchitecture.png" width="1000" >

The above container diagram shows the interaction between the user, the OpenSesame android app, and our two backend services: Firebase (for login and data storage) and Google Maps (for displaying the map).

### Component Diagram:

<img src = "/images/ComponentDiagram.png" width="1000" >

The component diagram shows the interactions between the user and the various controllers of the application.  These controllers manipulate the application, and process inputs & outputs.

# Major Classes

<img src = "/images/ClassDiagram.png" width="1000" >

Class | Description | User Stories
------|-------------|-------------
LogInActivity|Prompts a user to enter their sign in credentials, or sign up for an account|010
LaunchActivity|Automatically signs the user in if they have saved information, otherwise sends them to LogInActivity|001<br>002<br>004
SignUpActivity|Allows to the user to create an account with a Username, Email, and Password|000
MapActivity|Displays a map of marked locations and the location of the user|003<br>005<br>009<br>011<br>012<br>015<br>017<br>020<br>023
SettingsActivity|Displays a list of account settings & preferences|006<br>008<br>009<br>014<br>021<br>022
CreateReportActivity|Allows a user to enter information for a report|007<br>016<br>018
ReportListActivity|Allows a user to see a list of all nearby reports|013<br>019

# Data Design

<img src = "/images/ERD.png" width="1000" >

# Business Rules

Business Rule ID | Rule
----------------- | -----
000 | Only faculty and staff of UCF can submit work orders to UCF Facilities. 
001 | Only UCF Facilities employees can change the status of work orders. 

# User Interface Design

Upon login, the page transitions to the main page where the user finds locations on the map and reports issues. The yellow button is for additional work orders and the pin indicates the user's current location. Drop-down text is easily visible, options organized into sections, and the text changes from white to another color for verification.

<img src = "/images/login.PNG" width="160" > <img src = "/images/UI.png" width="200" > 

# Resource Management

Resources used on the application side will all be lightweight. All querying for data will happen on the backend, the inclusion of a Google Maps API will only have a nominal impact on graphics performance as it is a commonly used interface on phones. Local memory resources will all be managed by the JVM and the AndroidX toolkit.

# Security

We are storing all user generated content in Firebase as well as any login information which has a high level of built in security. In addition, when we are storing data locally on the system we are using private system preferences provided by AndroidX to ensure that only our application can access user data. 

Local user permissions will be gathered and enforced. Location information will not be accessed without explicit user permission. 

# Performance

As a lightweight application, performance will be fast and reliable. Lag and loading times will be less than three seconds. Relying on Google's Firebase and Map APIs, the application should be able to reliably send and receive information in a timely manner. 

# Scalability

With the Google Maps API, this app can place markers anywhere on the globe. The user will start on the UCF campus and the app will focus its attentions to the local area, but this app can scale easily to greater areas. Work orders for facilities would have to be disabled for markers set beyond a specific boundary to prevent work orders for places not at UCF. Additionally our Firebase backend will automatically scale to meet the needs of the userbase in regards to both queries per second and storage.

# Interoperability

The only data sharing that will be taking place will be between the local application and the Firebase backend. It will be accomplished via the Firebase API.

# Internationalization/Localization

Internationalization/localization would have to be handled on the back end to set a different location boundary perimeter and the presence of work orders and/or where the work orders would go to. Multiple language options for future development is feasible. 

# Input/Output

Besides local metadata that is read, the main I/O operation will be the user inputting touches or text to interact with the application. The main output will be queries fetched from the backend. There will be no file reading operations for which we have to specify handling errors.

# Error Processing

Error processing when dealing with errors from the backend (like querying or logging in) will be detective and thus just notify the user when an error occurred. With user errors, if there is an error currently being created (invalid text) the application will attempt to correct but if it is posthumous, then the error will simply be detected and logged.

Errors will be, when possible, translated to user-friendly language in a pop-up window. 

Errors will be generated for: trying to set a marker outside of UCF, trying to set a new duplicate marker on top of another, trying to send a work order for one already in progress, logging in with invalid credentials, among other things. 

# Fault Tolerance

Low fault tolerance for anything on Google's or Firebase's end. High tolerance for incorrect user input. 

# Architectural Feasibility

This app is economically feasible with unpaid versions of developmental tools like Trello and GitHub and access to free API and frameworks. 

The more technically feasible aspects of the app are the ability to mark places on the map, assign each marker to a user, to display different markers, to add/remove different markers, and to fill out and send work order forms. 

The less feasible aspects of the app are related to gathering work order material that already exists in the UCF Facilities backlog. 

# Overengineering

Overengineering possibilities to current design plans include: 
- being able to add pictures to markers 
- multiple language options 

# Build-vs-Buy Decisions

We are using Firebase because it is the main backend used for mobile apps and because it is developed by Google it has good integration with Android. We are using this to process/store login information and store all data that users will be served.

We are using Mockito to generate mocks for unit testing. 

We are using Google Maps because of the aforementioned good integration with Android, its ease of access, and its readily-available documentation. 

# Reuse

This app will be able to be the backbone of other UCF apps that want to use the Google Map functionalities. 

# Change Strategy

1. Identify the root cause of the issue. 
2. check for common workarounds that exist. 
3. Discuss with group members on the same day that the issue is discovered. 
4. Add to, edit, or remove the user story involved with the issue. 
5. Reevaluate current design documentation to determine high-level changes. 
6. Add to the backlog and document in next available Git commit. 
