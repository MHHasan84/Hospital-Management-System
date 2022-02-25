
# Hospital Management System


#### Database Setup:

* install oracle 19c [installation process](https://docs.oracle.com/en/industries/food-beverage/simphony/19.3/simig/t_preinstall_oracle_db_configuration.htm)
* create schema c##hospital_management_db

```
create user c##hospital_management_db identified by password;
grant dba to c##hospital_management_db;
```
* import data from `C##HOSPITAL_MANAGEMENT_DB.sql` file.
#### Spring Boot project Setup:

* download jdk-15.0.1 from [here](https://www.oracle.com/java/technologies/javase/jdk15-archive-downloads.html)
* download spring initializr from [here](https://start.spring.io/)
* add the following dependencies
    * Spring Web
    * Thymleaf
    * Spring Data Jdbc
    * Oracle Driver
* download ojdbc8-21.4.0.0.1 from [here](https://mvnrepository.com/artifact/com.oracle.database.jdbc/ojdbc8-production/21.4.0.0.1) and import the .jar file
* clone the github repository or download the zip and extract.
    ```
    https://github.com/MHHasan84/Hospital-Management-System.git
    ```
#### Run The Project
* to Run the project execute `main` method in the `com.example.HospitalManagementSystemApplication` class from your IDE.

  Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins.html#build-tool-plugins.maven) like so  

    ```
    mvn spring-boot:run
    ```
* open a browser tab at http://localhost:80/login
* to login the system as admin use
    `userId:` admin and `password:` admin

### Contributors
* Md Mahmudul Hasan - 1805084
* Najmus Sakib Rashid - 1805066
### Supervisor
* [Dr. Abu Sayed Md. Latiful Hoque](https://cse.buet.ac.bd/faculty/facdetail.php?id=asmlatifulhoque)
