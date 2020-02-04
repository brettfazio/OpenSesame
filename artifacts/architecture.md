# Program Organization

You should have a diagram of your high level architecture in this section, along with a description of each component and a table that relates each component to one or more user stories.

# Major Classes

You should have an UML class diagram in this section, along with a description of each class and a table that relates each component to one or more user stories. At a minimum, you need 1 diagram of your major classes. You are encouraged to also include more detailed diagrams that include all of your classes. 

# Data Design

If you are using a database, you should have a basic Entity Relationship Diagram (ERD) in this section. This diagram should describe the tables in your database and their relationship to one another (especially primary/foreign keys), including the columns within each table. 

# Business Rules

You should list the assumptions, rules, and guidelines from external sources that are impacting your program design. 

# User Interface Design

You should have one or more user interface screens in this section. Each screen should be accompanied by an explaination of the screens purpose and how the user will interact with it. You should relate each screen to one another as the user transitions through the states of your application. You should also have a table that relates each window or component to the support using stories. 

# Resource Management

# Security

We are storing all user generated content in Firebase as well as any login information which has a high level of built in security. In addition, when we are storing data locally on the system we are using private system prefrences provided by AndroidX to ensure that only our application can access user data.

# Performance

# Scalability

# Interoperability

# Internationalization/Localization

# Input/Output

# Error Processing

# Fault Tolerance

# Architectural Feasibility

# Overengineering

# Build-vs-Buy Decisions

We are using Firebase becuase it is the main backend used for mobile apps and since its developed by Google it has good integration with Android. We are using this to process/store login information and store all data that users will be served.
We are using Mockito to generate mocks for unit testing.

# Reuse

# Change Strategy
