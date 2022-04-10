# Configure DB
- Require to be installed MySQL 5+, create new DB
- Modify DB JDBC URL with new DB in the file *src/main/resources/META-INF/persistence.xml*
- In folder <code>sql</code>, execute 2 SQL scripts to initialize DB schema. Note: Update file *2.init_data.sql* for the trading rate code/cost per trade
    - *sql\1.create_schema.sql*
    - *sql\2.init_data.sql*

# Compile & Build
- Require to be installed JDK 8, Maven 3
- Run command to build: <code>mvn clean package</code>

# Run
- After finishing build process, run this command to execute the application

<code>
java -cp target\assignment-0.0.1-SNAPSHOT-jar-with-dependencies.jar au.com.livewirelabs.assignment.Assignment −exchange ASX

java -cp target\assignment-0.0.1-SNAPSHOT-jar-with-dependencies.jar au.com.livewirelabs.assignment.Assignment −exchange CXA
</code>