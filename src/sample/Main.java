package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Main Class
 * The main class for running the application. Loads relevant fonts and runs the main application from the view class, that uses
 * the EnigmaController as the main controller.
 *
 * @author Jonathan Brett
 * @version 13
 * @since 2020-01-26
 */
public class Main extends Application {

    public static Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception{

        Main.primaryStage = primaryStage;
        //Importing fonts to be used around the simulator.
        Font.loadFont(getClass().getResourceAsStream("resources/fonts/Timothy-Regular.otf"), 12);
        Font.loadFont(getClass().getResourceAsStream("resources/fonts/Molot.otf"), 12);
        Font.loadFont(getClass().getResourceAsStream("resources/fonts/SKETCHY.otf"), 12);

        Parent root = FXMLLoader.load(getClass().getResource("enigmaWindow.fxml"));
        primaryStage.setTitle("Enigma Simulator");
        //Set the desktop icon
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("resources/images/desktopicon.png")));
        Scene scene = new Scene(root, 1200, 700);
        //Set the stylesheet to be used.
        scene.getStylesheets().add("sample/stylesheet.css");
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();

        //Don't allow users to change the size of the screen.
        Main.primaryStage.setResizable(false);
    }

    /**
     * Main method for launching the simulator.
     * @param args arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }



}
