<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

# Database Migration <img src="https://github.com/ADA-GWU/2024-a1-db-migration-FaridM5/assets/67589966/8509678a-db28-4984-938e-8aad64d00953" width="45">

<i class="fa fa-bookmark" style="font-size:13px"></i> This repository contains programs written in:

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white) 

<h3><i class="fa fa-bookmark" style="font-size:13px"></i> References:</h3>
<li><a href="https://www.prisma.io/dataguide/types/relational/expand-and-contract-pattern#:~:text=The%20expand%20and%20contract%20pattern%20is%20a%20process%20that%20database,data%20structure%20without%20affecting%20uptime." target=_blank>Prisma's Data Guide</a></li>
<li><a href="https://www.tim-wellhausen.de/papers/ExpandAndContract/ExpandAndContract.html" target=_blank>Tim Wellhausen</a></li>

<h4><i class="fa fa-bookmark" style="font-size:13px"></i> What applications required from the system:</h3>
<ol>
<i class="fa fa-check" style="font-size:13px"> Latest version Java (21)</i>

<i class="fa fa-check" style="font-size:13px"> PostgreSql <i>(recommended to have latest)</i></i>
</ol>

<h4><i class="fa fa-bookmark" style="font-size:13px"></i> You don't need to:</h4>
<ol>
<i class="fa fa-times-circle" style="font-size:13px"> Download Gradle</i> <br>
<i class="fa fa-times-circle" style="font-size:13px"> Download an IDEA</i>
<i class="fa fa-times-circle" style="font-size:13px"> Download ay package/JAR file or any other things to insert into IDEA</i>
<br><i class="fa fa-times-circle" style="font-size:13px"> Go through several steps to run the app</i><br>
<i class="fa fa-times-circle" style="font-size:13px"> Insert your postgres credentials every time</i>
<i class="fa fa-times-circle" style="font-size:13px"> Write a command to run different scripts/programs</i>

</ol>
<p style=font-size:13px;>Just you, and your terminal are required simply to run the app. <i class="fa fa-smile-o" style="font-size:13px;color:black"></i></p>
<i style=font-size:13px;>Everything will be handled automatically by:</i>

![Static Badge](https://img.shields.io/badge/build-Java-orange?style=flat&logo=Gradle&label=gradle)

<h3><i class="fa fa-bookmark" style="font-size:13px"></i> Strategy used:</h3>

**Neme:** Expand and Contract Pattern <i>(also known as parallel change OR Blue-Green deployment)</i>

<i class="fa fa-book" style="font-size:13px"></i>
Reference:
[Prisma's Data Guide](https://www.prisma.io/dataguide/types/relational/expand-and-contract-pattern#:~:text=The%20expand%20and%20contract%20pattern%20is%20a%20process%20that%20database,data%20structure%20without%20affecting%20uptime.)
- **Description:** The expand and contract pattern is a process that database administrators and software developers can use to transition data from an old data structure to a new data structure without affecting uptime. It works by applying changes through a series of discrete steps designed to introduce the new structure in the background, prepare the data for live usage, and then switch over to the new structure seamlessly. Beyond just migrating data and clients to a new data structure, the expand and contract pattern is also helpful in that it allows you to rollback changes easily at most points in the process if something doesn't go as planned or if your requirements change.

<i class="fa fa-book" style="font-size:13px"></i>
Reference:
[Tim Wellhausen](https://www.tim-wellhausen.de/papers/ExpandAndContract/ExpandAndContract.html)
- **Description:** Implement breaking changes in multiple steps so that each individual step does not break the system and can be reverted. First, expand the system by adding the new structure to the database. Then migrate the existing data into the new structure while the system redundantly writes into both the old and the new structure. After the migration is done, contract the system to remove the old data structure and the old code.
<i>Note that we can keep the old versions as backup to revert the changes for preventing any system breaks</i>

![TimWellReferencePicture](https://www.tim-wellhausen.de/papers/ExpandAndContract/Schritte-geschnitten.svg)

<i class="fa fa-picture-o" style="font-size:13px"></i> The process described as a model. If you want to access the picture: <a href="https://github.com/ADA-GWU/2024-a1-db-migration-FaridM5/assets/67589966/0ea8b09b-6118-4153-b3ef-49db977b8752" target="_blank">Click!</a><br>![ModelForStrategy](https://github.com/ADA-GWU/2024-a1-db-migration-FaridM5/assets/67589966/0ea8b09b-6118-4153-b3ef-49db977b8752)

<h4><i class="fa fa-bookmark" style="font-size:13px"></i> How the program works in logic:</h4>
<ol><i class="fa fa-wrench" style="font-size:13px"> Program asks the user if he/she wants to edit the configuration file which is used to connect to the database</i><ol><i class="fa fa-toggle-on" style="font-size:13px"> If the user presses "+", then the program will ask for the username and password, first to save into config file to retrieve it from there automatically for further runs </i>
<i class="fa fa-toggle-off" style="font-size:13px">If the user presses "-", then the program will directly refer to the config file to retrieve the value from there (<i>null</i> by default)</i>
<i class="fa fa-warning" style="font-size:13px"> User is repeatedly asked to put either "+" or "-" untill there is no invalid parameter provided </i></ol>
<i class="fa fa-wrench" style="font-size:13px"> Program asks the user to provide database name for the connection. <i>This will be asked every time in case you would like to have the operation in another database.</i></i>
<i class="fa fa-wrench" style="font-size:13px"> Program connectst to the database using the credentials provided inside config file and database name provided to the terminal.</i>
<ol><i class="fa fa-warning" style="font-size:13px"> User is warned about the connection failure <i>(if credentials are incorrect or there is a problem with connection)</i></i></ol>
<i class="fa fa-wrench" style="font-size:13px"> Program looks for the tables named as <i>"STUDENTS"</i> and <i>"INTERESTS"</i>:</i>
<ol><i class="fa fa-toggle-on" style="font-size:13px"> If the program detects the tables named as <i>"STUDENTS"</i> and <i>"INTERESTS"</i>, then it aks to drop and recreate or just keep the tables. <i>"Y"</i> to recreate, <i>"N"</i> to skip.</i>
<i class="fa fa-toggle-off" style="font-size:13px"> If the program do not detects the tables named as <i>"STUDENTS"</i> and <i>"INTERESTS"</i>, then it just creates the tables.</i>
<i class="fa fa-warning" style="font-size:13px"> User is repeatedly asked to put either "Y" or "N" untill there is no invalid parameter provided </i>
</ol>
<i class="fa fa-wrench" style="font-size:13px"> Program prints the tables named as  <i>"STUDENTS"</i> and <i>"INTERESTS"</i>. <i>The program uses a seperate class which dynamically prints the tables. It is not staticly programmed to print the specific tables.</i></i>
<ol><i class="fa fa-warning" style="font-size:13px"> User is warned about the error if there is any <i>(with the specific error message)</i></i></ol>
<i class="fa fa-wrench" style="font-size:13px"> Program asks for the specific action to take:</i>
<ol><i class="fa fa-database" style="font-size:13px"><i> end</i> to stop the running app and leave</i>
<i class="fa fa-database" style="font-size:13px"> <i> migrate</i> to call for the migration class and perform changes on the database the</i>
<ol><i class="fa fa-sticky-note" style="font-size:13px"> If migration has been performed before and you haven't performed a rollback function before you do the migrate again, then you will get the error which also handles itself. The program clears the action performed after migrate is called second time to avoid unneccessary tables. <i>Just simply perform rollback operation to overcome.</i></i>
<i class="fa fa-warning" style="font-size:13px"> User is warned about error happenend during the migration process if there is any <i>(with the specific error message)</i>.</i></ol>
<i class="fa fa-database" style="font-size:13px"><i> rollback</i> to call for the rollback class to revert the changes made</i>
<ol><i class="fa fa-warning" style="font-size:13px"> User is warned about error happenend during the rollback process if there is any <i>(with the specific error message)</i>.</i></ol>
<i class="fa fa-warning" style="font-size:13px"> User is repeatedly asked to put one of these: <i>"end"</i>, <i>"migrate"</i>, <i>"rollback"</i> untill there is no invalid parameter provided </i>
</ol></ol>
<h4><i class="fa fa-bookmark" style="font-size:13px"></i> How to run the program?:</h4><ol>
<i>Each picture will be provided by the drive if there is any issue</i>

<i class="fa fa-terminal" style="font-size:15px"> We just simply clone the project and go into the directory</i>

![CloneRepo](https://github.com/ADA-GWU/2024-a1-db-migration-FaridM5/assets/67589966/70825602-b01a-468d-98b8-aba505f03a45)

```bash
  git clone https://github.com/ADA-GWU/2024-a1-db-migration-FaridM5.git
  
  cd 2024-a1-db-migration-FaridM5
```
<i class="fa fa-terminal" style="font-size:15px"> Before this command executed, the gradlew file should look unexecutable. By doing so, we define a permission to a file to be executable.</i>

![Chmod](https://github.com/ADA-GWU/2024-a1-db-migration-FaridM5/assets/67589966/9219916d-7f17-427c-8d89-60e0a5d189c9)

```bash
  chmod +x gradlew
```

<i class="fa fa-terminal" style="font-size:15px">  
We just use <i>run</i> task that will handle both building and running part itself. </br>
We just finished running part of the app which is as easy as it is and do not require you to put more effort :)
<i></i></i>

![RunTask](https://github.com/ADA-GWU/2024-a1-db-migration-FaridM5/assets/67589966/7310e44f-c395-4a7b-b1ff-02205188e796)

```bash
  ./gradlew run
```
</ol><br>
<i class="fa fa-mortar-board" style="font-size:13px">As I stated before, the program will handle everything itself. Whether you want to change the credentials in the config file, you want to end the app, do migrate or rollback. You just read what the program tells you and puts the required input to wait for it to handle the process. No other script execution things for different processes are needed</i>

<h4><i class="fa fa-bookmark" style="font-size:13px"></i> Some pictures from the process:</h4>
<ol>
<i class="fa fa-terminal" style="font-size:15px">  
When the user inserts invalid credentials to connect to the database (<i>Note that if the user puts invalid credentials it will be saved into the config file. So, you need to put + as an input when the program asks for the editing config file. By doing so, you can provide the correct credentials</i>):
<i></i></i>

![DBConnectionProcessPicture](https://github.com/ADA-GWU/2024-a1-db-migration-FaridM5/assets/67589966/5ee1bbef-19f9-43a2-927e-62d559374c2c)

<i class="fa fa-terminal" style="font-size:15px">  
The case where the tables already exists and recreates them <i>(if not exists just creates)</i>
</i>

![TableCreationProcess](https://github.com/ADA-GWU/2024-a1-db-migration-FaridM5/assets/67589966/3f29bae0-55f7-43b0-8ad9-cd08d1a1c60b)

<i class="fa fa-terminal" style="font-size:15px">  
<i> Migration</i> process
</i>

![MigrationProcessPicture](https://github.com/ADA-GWU/2024-a1-db-migration-FaridM5/assets/67589966/97be5c1c-8f17-46f8-a53d-8573eb530a19)

<i class="fa fa-terminal" style="font-size:15px">  
<i> Rollback </i> process
</i>

![RollbackProcessPicture](https://github.com/ADA-GWU/2024-a1-db-migration-FaridM5/assets/67589966/c7469b70-bf1c-42b3-b9f7-7369623c3b80)

<i class="fa fa-terminal" style="font-size:15px">  
<i> End </i> process
</i>

![EndProcessPicture](https://github.com/ADA-GWU/2024-a1-db-migration-FaridM5/assets/67589966/edbddf01-30a1-4fbc-bc6a-ac064b18fe60)

</ol>




















