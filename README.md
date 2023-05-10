# TO DO APP 2.0
 
## How to configure

1) "To Do APP" used SQL database, so required configure an instance and schema in MySQL with the name "tasklist", this will be database saving routes. Remember, these route will be the same route that the field "datasource.url" of the file="applicattionpropierty" too. ( jdbc:mysql://localhost:3306/taskslist?serverTimezone=UTC ). Although this is already configured.

2) Run the program to give permission to the controller routes to make changes.

3) The Controller routes can be used and tested using templates provide by Thymeleaf (normally port 8080 on localhost), although also can be use other HTTP request and test programme. 

## Routes:
- /index : Homepage.
- /tasks :  view all tasks list. In this page you can be able also search, update or delete an existing task.
- /add-task : view the form to add a new task in database.
- /update-form/{id} : view an exist task to modificate it.
- /user-form : view the form to add a new user in database.

## Security: 
- Except the routes "/index" and "/user-form". the rest of routes are protected by Springboot security, so you need Sign In to ADD, MODIFICATE or DELETE a task. And only the tasks of the user authenticated at that moment can be able to modificate.

## Specifications
With the correct use of TO DO APP, you will be able to:
- Create User: "Admins" and "Regular Users". 
- Create Task To Do: where you can assing a determinate task to a specific user. Is necessary user exist previously to will can asignate a new task.
- View all task on "Task list": where you can view determinate data of every task and user, and you can use filters and sort every column, as well as, edit or eliminate the task you want if you are previously authenticated,


## Main technologies used:
- Java / Spring / SpringBoot : to write the API code by using the libraries provided by both frameworks.
- JPA: to map and relate the API with the database.
- MySQL: to review and administrate the database.
- Thymeleaf: to build a modern server-side Java template engine (View-Mode) for web in HTML.
- SpringBoot Security : to provide security to certain routes.


## Information for Devs:
- The API code is written with the naming conventions, among others, we use PascalCase for the name of Models, and their homonyms in SQL with snake_case. It's suggested for future modifications follows the same convention to keep clean the project.
- The root model of the repository, controllers, models and services were correctly organized in different packages, giving the possibility to future updates and allow growth of the project, which will facilitate the development of the API architecture.

I am at your disposal for any questions that you may have about that.
Thanks for reading to the end!

## App done by:
- [Emmanuel Escobar](https://github.com/Emmascobar)
