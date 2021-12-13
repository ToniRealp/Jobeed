## How to install
### Option 1
Deploy the project as we have done with all the other labs
### Option 2
Run the attached docker-compose file, which has an image for both mysql database and tomcat server.
When the images are up and running execute the init.sql file to configure the database.
Then just make sure that the binded directory in the docker-compose tomcat configuration is the one containing the jobeed.war file.
The .war will be automatically deployed to the tomcat server running in docker.