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

/**
 * Main class for controlling the guidebook window. Includes interaction with the window and shows what things are to be displayed.
 */
public class GuidebookWindowController {

    public BorderPane stagePane;
    private double xOffset = 0;
    private double yOffset = 0;

    //Fetching the texts for the DataCentre class.
    private Text[] bodies = new Text[]{bodyGuidebook0, bodyGuidebook1, bodyGuidebook2, bodyGuidebook3, bodyGuidebook4, bodyGuidebook5, bodyGuidebook6};
    public TextFlow displayText;
    public ScrollPane scrollText;
    public HBox displayBox;
    public ImageView enigmaImage;
    public Button close;
    public Button minus;

    /**
     * Initialise the window with the relevant items that need to be displayed and how they should be displayed.
     */
    public void initialize(){

        //Set up the text to be displayed.
        mainGuidebookHeader.setFont(Font.font("Molot", FontWeight.BOLD, 20));
        mainGuidebookHeader.setFill(Color.BLACK);
        for (Text header : guidebookHeaders) {
            header.setFont(Font.font("Molot", FontWeight.BOLD, 15));
            header.setFill(Color.BLACK);
        }

        displayText.getChildren().add(mainGuidebookHeader);
        for(int i = 0; i < guidebookHeaders.length; i++){
            displayText.getChildren().add(guidebookHeaders[i]);
            displayText.getChildren().add(bodies[i]);
        }

        scrollText.setFitToWidth(true);
        scrollText.setPrefWidth(displayBox.getPrefWidth()/2);

        //User interaction with moving the screen.
        stagePane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = EnigmaController.guidebookStageTemp.getX() - event.getScreenX();
                yOffset = EnigmaController.guidebookStageTemp.getY() - event.getScreenY();
            }
        });

        stagePane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                EnigmaController.guidebookStageTemp.setX(event.getScreenX() + xOffset);
                EnigmaController.guidebookStageTemp.setY(event.getScreenY() + yOffset);
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
