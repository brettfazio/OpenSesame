# Program Organization

**TODO** You should have a diagram of your high level architecture in this section, along with a description of each component and a table that relates each component to one or more user stories.

# Major Classes

**TODO** You should have an UML class diagram in this section, along with a description of each class and a table that relates each component to one or more user stories. At a minimum, you need 1 diagram of your major classes. You are encouraged to also include more detailed diagrams that include all of your classes. 

# Data Design

**TODO** If you are using a database, you should have a basic Entity Relationship Diagram (ERD) in this section. This diagram should describe the tables in your database and their relationship to one another (especially primary/foreign keys), including the columns within each table. 

# Business Rules

**TODO** You should list the assumptions, rules, and guidelines from external sources that are impacting your program design. 

# User Interface Design

**TODO** You should have one or more user interface screens in this section. Each screen should be accompanied by an explanation of the screens purpose and how the user will interact with it. You should relate each screen to one another as the user transitions through the states of your application. You should also have a table that relates each window or component to the support using stories. 

# Resource Management

Resources used on the application side will all be lightweight. All querying for data will happen on the backend, the inclusion of a Google Maps API will only have a nominal impact on graphics performance as it is a commonly used interface on phones. Local memory resources will all be managed by the JVM and the AndroidX toolkit.

# Security

We are storing all user generated content in Firebase as well as any login information which has a high level of built in security. In addition, when we are storing data locally on the system we are using private system preferences provided by AndroidX to ensure that only our application can access user data.

# Performance



# Scalability

With the Google Maps API, this app can place markers anywhere on the globe. The user will start on the UCF campus and the app will focus its attentions to the local area, but this app can scale easily to greater areas. Work orders for facilities would have to be disabled for markers set beyond a specific boundary to prevent work orders for places not at UCF. Additionally our Firebase backend will automatically scale to meet the needs of the userbase in regards to both queries per second and storage.

# Interoperability

The only data sharing that will be taking place will be between the local application and the Firebase backend. It will be accomplished via the Firebase API.

# Internationalization/Localization

Internationalization/localization would have to be handled on the back end to set a different location boundary perimeter and the presence of work orders and/or where the work orders would go to. Multiple language options for future development is feasible. 

# Input/Output

# Error Processing

(Does this mean how it intends to handle errors or what kinds of errors it may generate or both or what?) 

Errors will be, when possible, translated to user-friendly language in a pop-up window. 

Errors will be generated for: trying to set a marker outside of UCF, trying to set a marker on top of another marker, trying to send a work order for an existing work order. 

# Fault Tolerance

Low fault tolerance for anything on Google's side. High tolerance for incorrect user input. 

# Architectural Feasibility

# Overengineering

Adding pictures along with the markers. 

# Build-vs-Buy Decisions

We are using Firebase becuase it is the main backend used for mobile apps and since its developed by Google it has good integration with Android. We are using this to process/store login information and store all data that users will be served.
We are using Mockito to generate mocks for unit testing.

# Reuse

This app will be able to be the backbone of other UCF apps that want to use the Google Map functionalities. 

# Change Strategy
