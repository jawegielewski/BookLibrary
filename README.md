**Once I have sime time I will modify BookDaoImpl:**
- **to be compliant with tryWithResources principle,**
- **to use "select" and "update" operations with binding parameters not concatenation**
<br />
<br />
As the IDE I used IntelliJ Idea and I highly recomend this environment. There is output in both Main.java and as a tests.
<br />
<br />
To run Main.java open src/main/java/Main.java in main package and click the inverted, green triangle.
Similarly if you need to run tests, navigate to src/test/java and choose accordingly BookDaoImplTests and BookTests.
<br />
<br />
Program uses singleton pattern for database connection to limit this number as a good practice.
Please note that there can be only one database connection established.

