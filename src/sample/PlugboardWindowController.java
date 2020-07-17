package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static sample.EnigmaController.*;
import static sample.DataCentre.*;

/**
 * A separate controller to the main controller for initialising and setting up the plugboard and reflector window. Does the various computations relevant for changing the plugs around
 * and updates the DataCentre with the choices made.
 */
public class PlugboardWindowController {

    public BorderPane stagePane;
    private double xOffset = 0;
    private double yOffset = 0;

    public Button plugA = new Button("A");
    public Button plugB = new Button("B");
    public Button plugC = new Button("C");
    public Button plugD = new Button("D");
    public Button plugE = new Button("E");
    public Button plugF = new Button("F");
    public Button plugG = new Button("G");
    public Button plugH = new Button("H");
    public Button plugI = new Button("I");
    public Button plugJ = new Button("J");
    public Button plugK = new Button("K");
    public Button plugL = new Button("L");
    public Button plugM = new Button("M");
    public Button plugN = new Button("N");
    public Button plugO = new Button("O");
    public Button plugP = new Button("P");
    public Button plugQ = new Button("Q");
    public Button plugR = new Button("R");
    public Button plugS = new Button("S");
    public Button plugT = new Button("T");
    public Button plugU = new Button("U");
    public Button plugV = new Button("V");
    public Button plugW = new Button("W");
    public Button plugX = new Button("X");
    public Button plugY = new Button("Y");
    public Button plugZ = new Button("Z");

    public Button closePlugboard;
    public Button minusPlugboard;

    public ChoiceBox<String> reflectorChoice;

    private ArrayList<Character> currentPair = new ArrayList<>();

    private ArrayList<Button> currentButtonPair = new ArrayList<>();

    private ArrayList<String> reflectorChoices = new ArrayList<>(Arrays.asList("Reflector A", "Reflector B", "Reflector C", "Reflector B Thin", "Reflector C Thin"));

    private String currentColor;

    /**
     * Initialising the window. Defining all of the actions for buttons and user interactions, setting up what to put in the choicebox etc.
     */
    public void initialize(){

        System.out.println(switchesArray);
        System.out.println(switchButtonsArray);

        reflectorChoice.setItems(FXCollections.observableArrayList(reflectorChoices));
        reflectorChoice.setValue(reflectorChoices.get(currentReflectorChoice));

        switchReflector(reflectorChoice);

        plugA.setOnAction(e -> pairCreation('A', plugA));
        plugB.setOnAction(e -> pairCreation('B', plugB));
        plugC.setOnAction(e -> pairCreation('C', plugC));
        plugD.setOnAction(e -> pairCreation('D', plugD));
        plugE.setOnAction(e -> pairCreation('E', plugE));
        plugF.setOnAction(e -> pairCreation('F', plugF));
        plugG.setOnAction(e -> pairCreation('G', plugG));
        plugH.setOnAction(e -> pairCreation('H', plugH));
        plugI.setOnAction(e -> pairCreation('I', plugI));
        plugJ.setOnAction(e -> pairCreation('J', plugJ));
        plugK.setOnAction(e -> pairCreation('K', plugK));
        plugL.setOnAction(e -> pairCreation('L', plugL));
        plugM.setOnAction(e -> pairCreation('M', plugM));
        plugN.setOnAction(e -> pairCreation('N', plugN));
        plugO.setOnAction(e -> pairCreation('O', plugO));
        plugP.setOnAction(e -> pairCreation('P', plugP));
        plugQ.setOnAction(e -> pairCreation('Q', plugQ));
        plugR.setOnAction(e -> pairCreation('R', plugR));
        plugS.setOnAction(e -> pairCreation('S', plugS));
        plugT.setOnAction(e -> pairCreation('T', plugT));
        plugU.setOnAction(e -> pairCreation('U', plugU));
        plugV.setOnAction(e -> pairCreation('V', plugV));
        plugW.setOnAction(e -> pairCreation('W', plugW));
        plugX.setOnAction(e -> pairCreation('X', plugX));
        plugY.setOnAction(e -> pairCreation('Y', plugY));
        plugZ.setOnAction(e -> pairCreation('Z', plugZ));

        closePlugboard.setOnAction(this::closeStage);
        minusPlugboard.setOnAction(this::minimizeStage);

        //Initialising the plugs if some have already been chosen when the window was opened before.
        for (int i = 0; i < switchesArray.size(); i++) {
            char[] pair = new char[]{switchesArray.get(i).input, switchesArray.get(i).output};
            for(char c:pair){
                changeColour(c, "-fx-background-color: " + chosenSwitchesColors.get(i));
            }
        }

        //Moving the stage around.
        stagePane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = plugboardStageTemp.getX() - event.getScreenX();
                yOffset = plugboardStageTemp.getY() - event.getScreenY();
            }
        });

        stagePane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                plugboardStageTemp.setX(event.getScreenX() + xOffset);
                plugboardStageTemp.setY(event.getScreenY() + yOffset);
            }
        });
    }

    /**
     * Close the stage method.
     * @param event Event for user clicking the exit button.
     */
    public void closeStage(ActionEvent event){
        Stage stage = (Stage) closePlugboard.getScene().getWindow();
        pStageOpen = false;
        stage.close();
    }

    /**
     * Minimize the stage method.
     * @param event Event for user clicking the minimize button.
     */
    private void minimizeStage(ActionEvent event){
        Stage stage = (Stage) minusPlugboard.getScene().getWindow();
        stage.setIconified(true);
    }

    /**
     * Method for creating a new plug to be used in the enigma encryption system. Updates the DataCentre class with the relevant
     * plugs that have been created.
     * @param c The selected plug char.
     * @param button The button that was pressed by the user.
     */
    public void pairCreation(char c, Button button){
        boolean checkIfExists = false;

        /**Check to see if a plug exists using the selected plug **/
        for(int i = 0; i < switchesArray.size(); i++){
            if(switchesArray.get(i).output == c || switchesArray.get(i).input == c){
                addSpace = true;
                changeColour(switchesArray.get(i).input, "-fx-background-color: #000000");
                changeColour(switchesArray.get(i).output, "-fx-background-color: #000000");
                colorChoices.add(chosenSwitchesColors.get(i));
                chosenSwitchesColors.remove(i);
                switchesArray.remove(i);
                switchButtonsArray.remove(i);
                checkIfExists = true;
                break;
            }
        }

        /** If the plug does not exist already, then we can check if it can be created. **/
        if(!checkIfExists){

            /** If the first plug of a pair has been selected **/
            if(currentPair.isEmpty()){
                currentPair.add(c);
                currentButtonPair.add(button);
                Random randomGenerator = new Random();
                currentColor = colorChoices.get(randomGenerator.nextInt(colorChoices.size()));
                button.setStyle("-fx-background-color: " + currentColor);

             /** If the selected plug is the same as the one already selected **/
            }else if(c == currentPair.get(0)){
                addSpace = true;
                currentPair.remove(0);
                currentButtonPair.remove(0);
                button.setStyle("-fx-background-color: #000000");

            /** If the current selected plug isn't already chosen, there is one selected currently and it isn't the same as this plug **/
            }else if(currentPair.size() == 1 && c != currentPair.get(0)){
                addSpace = true;
                currentPair.add(c);
                currentButtonPair.add(button);
                button.setStyle("-fx-background-color: " + currentColor);
                chosenSwitchesColors.add(currentColor);
                colorChoices.remove(currentColor);

                switchesArray.add(new Plug(currentPair.get(0), currentPair.get(1)));

                ArrayList<Button> buttonArrayList = new ArrayList<>();
                buttonArrayList.add(currentButtonPair.get(0));
                buttonArrayList.add(currentButtonPair.get(1));

                switchButtonsArray.add(buttonArrayList);

                currentPair.remove(1);
                currentPair.remove(0);
                currentButtonPair.remove(1);
                currentButtonPair.remove(0);

            }
        }
    }

    /**
     * Method for changing the reflector choice and updating the DataCentre class when done.
     * @param reflectorCB The reflector choicebox containing the options for reflectors.
     */
    public void switchReflector(ChoiceBox<String> reflectorCB){
        addSpace = true;
        reflectorCB.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number currentValue, Number newValue) {
                int newReflectorChoice = newValue.intValue();
                switch(newReflectorChoice){
                    case 0:
                        reflector = new Reflector(Components.ReflectorA);
                        currentReflectorChoice = 0;
                        break;
                    case 1:
                        reflector = new Reflector(Components.ReflectorB);
                        currentReflectorChoice = 1;
                        break;
                    case 2:
                        reflector = new Reflector(Components.ReflectorC);
                        currentReflectorChoice = 2;
                        break;
                    case 3:
                        reflector = new Reflector(Components.ReflectorBThin);
                        currentReflectorChoice = 3;
                        break;
                    case 4:
                        reflector = new Reflector(Components.ReflectorCThin);
                        currentReflectorChoice = 4;
                        break;
                }
            }
        });
    }

    /**
     * Method for setting the style of the button to the color that was selected. As usual with JavaFX, could not find a more efficient way
     * to do this due to naming of buttons, labels etc. not being accessible other than directly using their names.
     * @param c The input character that shows which button to use.
     * @param backColor The color to change the button to.
     */
    public void changeColour(char c, String backColor){
        switch(c) {
            case 'A': plugA.setStyle(backColor); break;
            case 'B': plugB.setStyle(backColor); break;
            case 'C': plugC.setStyle(backColor); break;
            case 'D': plugD.setStyle(backColor); break;
            case 'E': plugE.setStyle(backColor); break;
            case 'F': plugF.setStyle(backColor); break;
            case 'G': plugG.setStyle(backColor); break;
            case 'H': plugH.setStyle(backColor); break;
            case 'I': plugI.setStyle(backColor); break;
            case 'J': plugJ.setStyle(backColor); break;
            case 'K': plugK.setStyle(backColor); break;
            case 'L': plugL.setStyle(backColor); break;
            case 'M': plugM.setStyle(backColor); break;
            case 'N': plugN.setStyle(backColor); break;
            case 'O': plugO.setStyle(backColor); break;
            case 'P': plugP.setStyle(backColor); break;
            case 'Q': plugQ.setStyle(backColor); break;
            case 'R': plugR.setStyle(backColor); break;
            case 'S': plugS.setStyle(backColor); break;
            case 'T': plugT.setStyle(backColor); break;
            case 'U': plugU.setStyle(backColor); break;
            case 'V': plugV.setStyle(backColor); break;
            case 'W': plugW.setStyle(backColor); break;
            case 'X': plugX.setStyle(backColor); break;
            case 'Y': plugY.setStyle(backColor); break;
            case 'Z': plugZ.setStyle(backColor); break;
        }
    }


}
