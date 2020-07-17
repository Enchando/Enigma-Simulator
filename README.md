# Enigma Simulator
 Enigma Simulator developed in Java 11 and JavaFX.

** Refined Enigma Simulator Guide **

This file is aimed at those who know how do basic operations on their system of choice - namely opening a terminal, navigating to a file in
the terminal, extracting files etc. The enigma simulator JAR file can be found in the main directory of the repository.

** Step 1 : Download Java **
The project runs on Java 11 (11.0.7). Any version of Java 11 should work with this project, and will need to be downloaded from the Oracle website.

https://www.oracle.com/java/technologies/javase-jdk11-downloads.html

Once the jdk-11.0.7 has been installed, please check that this version of Java is being used when running the terminal by using "java -version". If 
not, you will have to modify the environment variables.


** Step 2 : Download JavaFX 11 **
The project uses the currently most supported and used version of JavaFX, 11.0.2. This can be found and downloaded from:

https://gluonhq.com/products/javafx/

Extract the .zip file that is downloaded to a new folder for JavaFX, such as in My Documents or Downloads. The extracted folder will be named 
something like "javafx-sdk-11.02".

** Step 3: Constructing the Command to run the simulator ** 

The following command is how the project can be run. The only item that needs to be changed is the location of where you installed JavaFX,
as seen in the command "C:/Users/Joe/Documents/"JavaFX Download"/javafx-sdk-11.0.2/lib/". The "lib" folder must be included at the end of this.

You will need to navigate to the location of the downloaded .jar file of the enigma simulator in the terminal, then use the (adjusted as mentioned)
command:

java --module-path C:/Users/Joe/Documents/"JavaFX Download"/javafx-sdk-11.0.2/lib/ --add-modules javafx.controls,javafx.fxml,javafx.media,java.logging,java.desktop -jar "Enigma Simulator".jar

The enigma simulator will now open and can be interacted with.  
