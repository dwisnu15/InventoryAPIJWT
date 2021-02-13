# Simple Inventory API With JSON Web Token

## What you need
1. IntelliJ or VSCode -> VSCode will make running the app a little bit complex
2. Postman application for sending http request and JSON
3. Database Management System (DBMS), preferably MySQL or MariaDB
4. If you want to test the Swagger UI, a browser better BESIDES Internet Explorer (they're not supported)

## Setup
1. Open and read the **application.yml** file. It should give you the necessary information on what  Edit the _application.data.dir_ to wherever you place this project. Also note on which server.port is going to be used when the app is run.
	1. Do remember to also change the _spring.datasource_ to your own configuration (database driver, the url for the database, etc.)

2. Start your database service. Remember to create the database schema based on the spring.datasource.url in application.yml file.

3. The **WebSecurityConfig.java** file on /configs package should contains which pages are authorized to be accessed without JWT. If you do want to create the necessary username and password as to get the feel of using the JWT, go to step 4; if not, change the **configure()** method on .antMatchers() parameters to access whatever request mappings are there in controllers and continue to step 4.

4. To get JWT token, you need to create an instance of UserInfo. Run the Postman app, input `localhost:{your_port}/customers/add` on the url bar, click the button beside the url and change it to **POST**. Then click the button of `Body`, change the right-most `text` to JSON and fill it like image below. 

![postman](/home/dio/Documents/inventory/inventory-api/postman.png)

5. Now that you have the JWT Token, you can use it as `Authorization` header by using it with "Bearer" like : "Bearer {your token}" . You need to use this header if you want to gain access to other url (unless you did change the configure() on step 3)

6. Enjoy the ~~broken~~ app