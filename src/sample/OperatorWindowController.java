package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import static sample.DataCentre.*;
import static sample.DataCentre.bodyOperator3;

/**
 * Main class for controlling the Operator window. Includes loading the relevant data and displaying them in the correct way.
 */
public class OperatorWindowController {

    public BorderPane stagePane;
    private double xOffset = 0;
    private double yOffset = 0;

    public TextFlow displayText;
    public ScrollPane scrollText;
    public HBox displayBox;
    public ImageView enigmaImage;
    public Button close;
    public Button minus;

    /**
     * Initialising the window with the correct data, user interaction etc.
     */
    public void initialize(){
        //Initialise the text to be used.
        mainOperatorHeader.setFont(Font.font("Molot", FontWeight.BOLD, 20));
        mainOperatorHeader.setFill(Color.BLACK);
        for (Text header : operatorHeaders) {
            header.setFont(Font.font("Molot", FontWeight.BOLD, 15));
            header.setFill(Color.BLACK);
        }

        displayText.getChildren().add(mainOperatorHeader);
        displayText.getChildren().add(bodyOperator0);
        displayText.getChildren().add(operatorHeaders[0]);
        displayText.getChildren().add(bodyOperator1);
        displayText.getChildren().add(bodyOperator2);
        displayText.getChildren().add(operatorHeaders[1]);
        displayText.getChildren().add(bodyOperator3);

        scrollText.setFitToWidth(true);
        scrollText.setPrefWidth(displayBox.getPrefWidth()/2);

        //Moving the window around with user interaction.
        stagePane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = EnigmaController.operatorStageTemp.getX() - event.getScreenX();
                yOffset = EnigmaController.operatorStageTemp.getY() - event.getScreenY();
            }
        });

        stagePane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                EnigmaController.operatorStageTemp.setX(event.getScreenX() + xOffset);
                EnigmaController.operatorStageTemp.setY(event.getScreenY() + yOffset);
            }
        });

        close.setOnAction(this::closeStage);
        minus.setOnAction(this::minimizeStage);
    }

    /** Close the stage **/
    public void closeStage(ActionEvent event){
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    /** Minimize Stage **/
    public void minimizeStage(ActionEvent event){
        Stage stage = (Stage) minus.getScene().getWindow();
        stage.setIconified(true);
    }
}

