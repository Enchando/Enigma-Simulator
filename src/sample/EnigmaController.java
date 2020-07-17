package sample;

import javafx.animation.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


import java.io.IOException;
import java.util.Random;

import static sample.DataCentre.*;

/**
 * Enigma Controller
 * The Enigma controller is designed to manage and display the contents on the main screen of the simulator. This is where
 * the user can use the enigma to encrypt a message, change various components or load different windows with help and information.
 *
 * @author Jonathan Brett
 * @version 13
 * @since 2020-01-26
 */

public class EnigmaController {

    //Border for the main window
    public BorderPane stagePane;

    //Writing area to display ciphertext
    public TextArea encryptionDisplay;

    //Set up the labels to be used for the enigma lamp board
    public Label A = new Label("A");
    public Label B = new Label("B");
    public Label C = new Label("C");
    public Label D = new Label("D");
    public Label E = new Label("E");
    public Label F = new Label("F");
    public Label G = new Label("G");
    public Label H = new Label("H");
    public Label I = new Label("I");
    public Label J = new Label("J");
    public Label K = new Label("K");
    public Label L = new Label("L");
    public Label M = new Label("M");
    public Label N = new Label("N");
    public Label O = new Label("O");
    public Label P = new Label("P");
    public Label Q = new Label("Q");
    public Label R = new Label("R");
    public Label S = new Label("S");
    public Label T = new Label("T");
    public Label U = new Label("U");
    public Label V = new Label("V");
    public Label W = new Label("W");
    public Label X = new Label("X");
    public Label Y = new Label("Y");
    public Label Z = new Label("Z");

    //Set up labels for the individual rotor's displays
    public Label rotorSlot1 = new Label("A");
    public Label rotorSlot2 = new Label("A");
    public Label rotorSlot3 = new Label("A");
    public Label rotorSlot4 = new Label("A");

    //Navigation and sound buttons
    public Button close;
    public Button minus;
    public Button soundbutton;

    //Keyboard buttons
    public Button buttonA;
    public Button buttonB;
    public Button buttonC;
    public Button buttonD;
    public Button buttonE;
    public Button buttonF;
    public Button buttonG;
    public Button buttonH;
    public Button buttonI;
    public Button buttonJ;
    public Button buttonK;
    public Button buttonL;
    public Button buttonM;
    public Button buttonN;
    public Button buttonO;
    public Button buttonP;
    public Button buttonQ;
    public Button buttonR;
    public Button buttonS;
    public Button buttonT;
    public Button buttonU;
    public Button buttonV;
    public Button buttonW;
    public Button buttonX;
    public Button buttonY;
    public Button buttonZ;

    //Rotor movement buttons.
    public Button rotor1Up = new Button();
    public Button rotor1Down = new Button();
    public Button rotor2Up = new Button();
    public Button rotor2Down = new Button();
    public Button rotor3Up = new Button();
    public Button rotor3Down = new Button();
    public Button rotor4Up = new Button();
    public Button rotor4Down = new Button();

    //Open new stage buttons.
    public Button enigmaGuidebookButton = new Button();
    public Button operatorGuidebookButton = new Button();
    public Button plugboardButton = new Button();

    //Encryption display modification buttons.
    public Button encryptionProcessButton;
    public Button clearButton;

    //Items to do with rotor slot 4 relating to turning it on/off.
    public CheckBox enableRotor4CBox = new CheckBox();
    public HBox rotorSlot4Image = new HBox();
    public VBox rotor4Background = new VBox();

    //Choice-boxes for deciding which rotor to use in each slot.
    public ChoiceBox<String> rotor1Choice;
    public ChoiceBox<String> rotor2Choice;
    public ChoiceBox<String> rotor3Choice;
    public ChoiceBox<String> rotor4Choice;

    //Choice-boxes for ring settings for each rotor.
    public ChoiceBox<String> ringSetRotor1;
    public ChoiceBox<String> ringSetRotor2;
    public ChoiceBox<String> ringSetRotor3;
    public ChoiceBox<String> ringSetRotor4;

    //Minor variables that are changed/modified when interacting with the simulator.
    public static String outputDisplayContents;
    public static boolean displayEncryptionProcess;

    private static double xOffset = 0;
    private static double yOffset = 0;

    public static boolean addSpace;
    private boolean mute;
    private int currentCount;

    //Stages to be used for the new windows that open, so that they can be identified later on.
    public static Stage plugboardStageTemp;
    public static Stage guidebookStageTemp;
    public static Stage operatorStageTemp;

    //Checks needed to see if the windows are already open.
    public static boolean pStageOpen;
    public static boolean gStageOpen;
    public static boolean oStageOpen;

    /**
     * Initialising everything to do with the main controller; including the default components for the enigma, values to be used for each of the components
     * on screen, handling events from buttons etc.
     */
    public void initialize(){

        soundbutton.setBackground(soundOn);

        currentCount = 0;
        addSpace = false;
        mute = false;
        enable4Rotor = false;
        rotorSlot4Image.setOpacity(0.2);
        rotor4Background.setOpacity(0.2);
        rotor4Up.setDisable(true);
        rotor4Down.setDisable(true);

        pStageOpen = false;
        gStageOpen = false;
        oStageOpen = false;

        //Initialising rotors with basic 1, 2, 3, 4 layout.
        rotors[0] = new Rotor(1, Components.RotorINotches,0, Components.RotorI.clone());
        rotors[1] = new Rotor(2, Components.RotorIINotches,0, Components.RotorII.clone());
        rotors[2] = new Rotor(3, Components.RotorIIINotches,0, Components.RotorIII.clone());
        rotors[3] = new Rotor(4, Components.RotorIVNotches,0, Components.RotorIV.clone());

        rotor1Choice.setItems(FXCollections.observableArrayList(rotorChoices));
        rotor2Choice.setItems(FXCollections.observableArrayList(rotorChoices));
        rotor3Choice.setItems(FXCollections.observableArrayList(rotorChoices));
        rotor4Choice.setItems(FXCollections.observableArrayList(rotorChoices));

        ringSetRotor1.setItems(FXCollections.observableArrayList(ringSettings));
        ringSetRotor2.setItems(FXCollections.observableArrayList(ringSettings));
        ringSetRotor3.setItems(FXCollections.observableArrayList(ringSettings));
        ringSetRotor4.setItems(FXCollections.observableArrayList(ringSettings));

        rotor1Choice.setValue("I");
        rotor2Choice.setValue("II");
        rotor3Choice.setValue("III");
        rotor4Choice.setValue("IV");

        ringSetRotor1.setValue("01");
        ringSetRotor2.setValue("01");
        ringSetRotor3.setValue("01");
        ringSetRotor4.setValue("01");

        switchRingSetting(ringSetRotor1, 0);
        switchRingSetting(ringSetRotor2, 1);
        switchRingSetting(ringSetRotor3, 2);
        switchRingSetting(ringSetRotor4, 3);

        enableRotor4(enableRotor4CBox);

        rotorSlot1.setText("A");
        rotorSlot2.setText("A");
        rotorSlot3.setText("A");
        rotorSlot4.setText("A");

        switchRotor(rotor1Choice, 0, rotorSlot1);
        switchRotor(rotor2Choice, 1, rotorSlot2);
        switchRotor(rotor3Choice, 2, rotorSlot3);
        switchRotor(rotor4Choice, 3, rotorSlot4);

        rotor1Up.setOnAction(e -> rotorUp(1));
        rotor1Down.setOnAction(e -> rotorDown(1));
        rotor2Up.setOnAction(e -> rotorUp(2));
        rotor2Down.setOnAction(e -> rotorDown(2));
        rotor3Up.setOnAction(e -> rotorUp(3));
        rotor3Down.setOnAction(e -> rotorDown(3));
        rotor4Up.setOnAction(e -> rotorUp(4));
        rotor4Down.setOnAction(e -> rotorDown(4));

        buttonA.setOnAction(e -> encrypt('A'));
        buttonB.setOnAction(e -> encrypt('B'));
        buttonC.setOnAction(e -> encrypt('C'));
        buttonD.setOnAction(e -> encrypt('D'));
        buttonE.setOnAction(e -> encrypt('E'));
        buttonF.setOnAction(e -> encrypt('F'));
        buttonG.setOnAction(e -> encrypt('G'));
        buttonH.setOnAction(e -> encrypt('H'));
        buttonI.setOnAction(e -> encrypt('I'));
        buttonJ.setOnAction(e -> encrypt('J'));
        buttonK.setOnAction(e -> encrypt('K'));
        buttonL.setOnAction(e -> encrypt('L'));
        buttonM.setOnAction(e -> encrypt('M'));
        buttonN.setOnAction(e -> encrypt('N'));
        buttonO.setOnAction(e -> encrypt('O'));
        buttonP.setOnAction(e -> encrypt('P'));
        buttonQ.setOnAction(e -> encrypt('Q'));
        buttonR.setOnAction(e -> encrypt('R'));
        buttonS.setOnAction(e -> encrypt('S'));
        buttonT.setOnAction(e -> encrypt('T'));
        buttonU.setOnAction(e -> encrypt('U'));
        buttonV.setOnAction(e -> encrypt('V'));
        buttonW.setOnAction(e -> encrypt('W'));
        buttonX.setOnAction(e -> encrypt('X'));
        buttonY.setOnAction(e -> encrypt('Y'));
        buttonZ.setOnAction(e -> encrypt('Z'));

        reflector = new Reflector(Components.ReflectorB);
        currentReflectorChoice = 1;

        encryptionDisplay.setWrapText(true);

        clearButton.setOnAction(e -> clearDisplay());
        encryptionProcessButton.setOnAction(e -> showOrHideEncryptionProcess());

        outputDisplayContents = "";

        close.setOnAction(this::closeStage);
        minus.setOnAction(this::minimizeStage);
        soundbutton.setOnAction(this::muteSound);

        //Setting up how to handle opening new stages - the plugboard/reflector window, the guidebook window and the operator window.
        plugboardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!pStageOpen) {
                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getClassLoader().getResource("sample/plugboardWindow.fxml"));
                        Stage plugboardStage = new Stage();
                        plugboardStageTemp = plugboardStage;
                        Scene plugboardWindow = new Scene(root, 600, 400);
                        plugboardWindow.getStylesheets().add("sample/stylesheet.css");
                        plugboardStage.setScene(plugboardWindow);
                        plugboardStage.setTitle("Plugboard and Reflector Settings");
                        plugboardStage.getIcons().add(new Image(getClass().getResourceAsStream("resources/images/desktopicon.png")));
                        plugboardStage.initStyle(StageStyle.TRANSPARENT);
                        plugboardStage.show();
                        plugboardStage.setResizable(false);
                        pStageOpen = true;

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        enigmaGuidebookButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!gStageOpen) {
                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getClassLoader().getResource("sample/guidebookWindow.fxml"));
                        Stage guidebookStage = new Stage();
                        guidebookStageTemp = guidebookStage;
                        Scene guidebookWindow = new Scene(root, 1200, 700);
                        guidebookWindow.getStylesheets().add("sample/stylesheet.css");
                        guidebookStage.setScene(guidebookWindow);
                        guidebookStage.setTitle("Enigma Guidebook");
                        guidebookStage.getIcons().add(new Image(getClass().getResourceAsStream("resources/images/desktopicon.png")));
                        guidebookStage.initStyle(StageStyle.TRANSPARENT);
                        guidebookStage.show();
                        guidebookStage.setResizable(false);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        operatorGuidebookButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!oStageOpen) {
                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getClassLoader().getResource("sample/operatorWindow.fxml"));
                        Stage operatorStage = new Stage();
                        operatorStageTemp = operatorStage;
                        Scene guidebookWindow = new Scene(root, 1200, 700);
                        guidebookWindow.getStylesheets().add("sample/stylesheet.css");
                        operatorStage.setScene(guidebookWindow);
                        operatorStage.setTitle("Operating in the Field");
                        operatorStage.getIcons().add(new Image(getClass().getResourceAsStream("resources/images/desktopicon.png")));
                        operatorStage.initStyle(StageStyle.TRANSPARENT);
                        operatorStage.show();
                        operatorStage.setResizable(false);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        /** Method to always scroll to bottom of the text area if changed **/
        encryptionDisplay.textProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
                encryptionDisplay.setScrollTop(Double.MAX_VALUE);
            }
        });

        /** Moving the stage around when the user holds down the mouse on the stage and moves it around. **/
        stagePane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = Main.primaryStage.getX() - event.getScreenX();
                yOffset = Main.primaryStage.getY() - event.getScreenY();
            }
        });

        stagePane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.primaryStage.setX(event.getScreenX() + xOffset);
                Main.primaryStage.setY(event.getScreenY() + yOffset);
            }
        });

    }

    /**
     * Main method for encrypting a letter and displaying it to the user. This involves using the methods found in the Enigma class
     * and then using the output to light up the correct letter on the lamp-board and place the letter on the paper.
     * @param c The input char to be encrypted.
     */
    public void encrypt(char c) {

        playSound();

        //Simply checking if a space needs to be added to the output display contents, due to changes in the enigma settings by the user.
        if(addSpace){
            outputDisplayContents += "\n";
            currentCount = 0;
            addSpace = false;
        }

        //To keep it realistic to how an enigma operator would encode a message, the output ciphertext returns in blocks of five.
        if(currentCount == 5){
            outputDisplayContents += " ";
            currentCount = 0;
        }

        //Encrypting the letter.
        char cipherletter = Enigma.encryptLetter(rotors, c, plugboard, reflector);
        currentCount += 1;

        outputDisplayContents = outputDisplayContents + cipherletter;
        encryptionDisplay.setText(outputDisplayContents);
        encryptionDisplay.appendText("");

        //Updating the rotors with the correct values.
        rotorSlot1.setText(String.valueOf(alphabet[rotors[0].rotorPosition]));
        rotorSlot2.setText(String.valueOf(alphabet[rotors[1].rotorPosition]));
        rotorSlot3.setText(String.valueOf(alphabet[rotors[2].rotorPosition]));

        //Lighting up the correct label. Unfortunately could not find an easier way of doing this except specifying each label in turn.
        switch(cipherletter){
            case 'A': glowLabel(A); break;
            case 'B': glowLabel(B); break;
            case 'C': glowLabel(C); break;
            case 'D': glowLabel(D); break;
            case 'E': glowLabel(E); break;
            case 'F': glowLabel(F); break;
            case 'G': glowLabel(G); break;
            case 'H': glowLabel(H); break;
            case 'I': glowLabel(I); break;
            case 'J': glowLabel(J); break;
            case 'K': glowLabel(K); break;
            case 'L': glowLabel(L); break;
            case 'M': glowLabel(M); break;
            case 'N': glowLabel(N); break;
            case 'O': glowLabel(O); break;
            case 'P': glowLabel(P); break;
            case 'Q': glowLabel(Q); break;
            case 'R': glowLabel(R); break;
            case 'S': glowLabel(S); break;
            case 'T': glowLabel(T); break;
            case 'U': glowLabel(U); break;
            case 'V': glowLabel(V); break;
            case 'W': glowLabel(W); break;
            case 'X': glowLabel(X); break;
            case 'Y': glowLabel(Y); break;
            case 'Z': glowLabel(Z); break;
        }
    }

    /**
     * Method for switching the rotor. User selects an option from the choicebox and the rotor is updated in the enigma, with the rotor being placed
     * at position 0.
     * @param rotorChoice The choicebox with the options to choose the rotor.
     * @param rotorS    The rotor slot that is being changed.
     * @param rotorLabel The label for the relevant rotor slot that needs to be reset.
     */
    private void switchRotor(ChoiceBox<String> rotorChoice, int rotorS, Label rotorLabel){
        addSpace = true;
        rotorChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number value, Number new_value) {
                Integer rotor_choice = new_value.intValue();
                switch(rotor_choice){
                    case 0:
                        rotors[rotorS] = new Rotor(rotorS + 1, Components.RotorINotches,0, Components.RotorI.clone());
                        break;
                    case 1:
                        rotors[rotorS] = new Rotor(rotorS + 1, Components.RotorIINotches,0, Components.RotorII.clone());
                        break;
                    case 2:
                        rotors[rotorS] = new Rotor(rotorS + 1, Components.RotorIIINotches,0, Components.RotorIII.clone());
                        break;
                    case 3:
                        rotors[rotorS] = new Rotor(rotorS + 1, Components.RotorIVNotches,0, Components.RotorIV.clone());
                        break;
                    case 4:
                        rotors[rotorS] = new Rotor(rotorS + 1, Components.RotorVNotches,0, Components.RotorV.clone());
                        break;
                    case 5:
                        rotors[rotorS] = new Rotor(rotorS + 1, Components.RotorVINotches, 0, Components.RotorVI.clone());
                        break;
                    case 6:
                        rotors[rotorS] = new Rotor(rotorS + 1, Components.RotorVIINotches,0, Components.RotorVII.clone());
                        break;
                    case 7:
                        rotors[rotorS] = new Rotor(rotorS + 1, Components.RotorVIIINotches,0, Components.RotorVIII.clone());
                        break;
                    case 8:
                        rotors[rotorS] = new Rotor(rotorS + 1, Components.RotorIKNotches,0, Components.RotorIK.clone());
                        break;
                    case 9:
                        rotors[rotorS] = new Rotor(rotorS + 1, Components.RotorIIKNotches,0, Components.RotorIIK.clone());
                        break;
                    case 10:
                        rotors[rotorS] = new Rotor(rotorS + 1, Components.RotorIIIKNotches,0, Components.RotorIIIK.clone());
                        break;
                    case 11:
                        rotors[rotorS] = new Rotor(rotorS + 1, Components.RotorICNotches,0, Components.RotorIC.clone());
                        break;
                    case 12:
                        rotors[rotorS] = new Rotor(rotorS + 1, Components.RotorIICNotches,0, Components.RotorIIC.clone());
                        break;
                    case 13:
                        rotors[rotorS] = new Rotor(rotorS + 1, Components.RotorIIICNotches,0, Components.RotorIIIC.clone());
                        break;
                    case 14:
                        rotors[rotorS] = new Rotor(rotorS + 1, Components.BetaNotches,0, Components.Beta.clone());
                        break;
                    case 15:
                        rotors[rotorS] = new Rotor(rotorS + 1, Components.GammaNotches,0, Components.Gamma.clone());
                        break;
                }

                //Updating the ring settings and the label of the rotor changed.
                rotorLabel.setText(String.valueOf(alphabet[rotors[rotorS].rotorPosition]));
                if(rotorS == 0) {
                    rotors[rotorS].changeRingSetting(Integer.parseInt(ringSetRotor1.getValue()) - 1);
                }else if(rotorS == 1){
                    rotors[rotorS].changeRingSetting(Integer.parseInt(ringSetRotor2.getValue()) - 1);
                }else if(rotorS == 2){
                    rotors[rotorS].changeRingSetting(Integer.parseInt(ringSetRotor3.getValue()) - 1);
                }else if(rotorS == 3){
                    rotors[rotorS].changeRingSetting(Integer.parseInt(ringSetRotor4.getValue()) - 1);
                }
            }
        });

    }

    /**
     * Method for chaning the ring setting of a specific rotor.
     * @param ringSetting The choicebox for the specific rotor containing the ring settings to choose from.
     * @param rotorS The rotor slot that has the rotor that is going to be changed.
     */
    private void switchRingSetting(ChoiceBox<String> ringSetting, int rotorS){
        addSpace = true;
        ringSetting.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number value, Number new_value) {
                int ringChoice = new_value.intValue();
                rotors[rotorS].changeRingSetting(ringChoice);
            }
        });
    }

    /**
     * Method for simply enabling the fourth rotor through the use of a checkbox.
     * @param checkBox Checkbox that is either enabled or disabled.
     */
    private void enableRotor4(CheckBox checkBox){
        checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                //Update the enigma settings to either using the fourth rotor or not using it.
                enable4Rotor = !enable4Rotor;
                //Removing or adding all functionality of the fourth rotor.
                if(enable4Rotor) {
                    rotor4Background.opacityProperty().setValue(1);
                    rotorSlot4Image.opacityProperty().setValue(1);
                    rotor4Up.setDisable(false);
                    rotor4Down.setDisable(false);
                } else {
                    rotorSlot4Image.opacityProperty().setValue(0.2);
                    rotor4Background.opacityProperty().setValue(0.2);
                    rotor4Up.setDisable(true);
                    rotor4Down.setDisable(true);
                }
            }
        });
    }

    /**
     * Method to be used when a button is pressed by the user for the option of seeing how the letter is encrypted.
     */
    public void showOrHideEncryptionProcess(){
        displayEncryptionProcess = !displayEncryptionProcess;
        if(displayEncryptionProcess) {
            encryptionProcessButton.setText("Hide Encryption Process");
        }else{
            encryptionProcessButton.setText("Show Encryption Process");
        }
    }


    /**
     * Method for closing the stage.
     * @param event Event of the close button being pressed.
     */
    private void closeStage(ActionEvent event){
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    /**
     * Method for minimizing the stage.
     * @param event Event of the minimize button being pressed.
     */
    private void minimizeStage(ActionEvent event){
        Stage stage = (Stage) minus.getScene().getWindow();
        stage.setIconified(true);
    }

    /**
     * Method for wiping the display of contents.
     */
    public void clearDisplay(){
        outputDisplayContents = "";
        encryptionDisplay.setText(outputDisplayContents);
    }

    /**
     * Method for muting or un-muting the sound in the simulator.
     * @param event
     */
    private void muteSound(ActionEvent event){
        mute = !mute;
        if(mute){
            soundbutton.setBackground(soundOff);
        }else{
            soundbutton.setBackground(soundOn);
        }
    }

    /**
     * Method used to play a sound when a key is pressed on the keyboard. Selects a random sound from the avaliable sounds.
     */
    public void playSound(){
        if(!mute) {
            Random randomGenerator = new Random();
            String soundToUse = sounds.get(randomGenerator.nextInt(sounds.size()));
            try {
                AudioClip clip = new AudioClip(this.getClass().getResource(soundToUse).toExternalForm());
                clip.play();
            } catch (Exception mediaException) {
                System.out.println("Media not found");
            }
        }
    }


    /**
     * Method for setting the rotor label.
     * @param rotorSlot The rotor slot's rotor to be changed.
     */
    public void setRotorLabel(int rotorSlot){

        if(rotorSlot == 1){
            rotorSlot1.setText(String.valueOf(alphabet[rotors[rotorSlot - 1].rotorPosition]));
        }
        else if(rotorSlot == 2){
            rotorSlot2.setText(String.valueOf(alphabet[rotors[rotorSlot - 1].rotorPosition]));
        }
        else if(rotorSlot == 3){
            rotorSlot3.setText(String.valueOf(alphabet[rotors[rotorSlot - 1].rotorPosition]));
        }
        else if(rotorSlot == 4){
            rotorSlot4.setText(String.valueOf(alphabet[rotors[rotorSlot - 1].rotorPosition]));
        }

    }

    /**
     * Method for rotating the rotor up one position when a button is pressed.
     * @param rotorSlot The rotor slot containing the rotor to be moved.
     */
    public void rotorUp(int rotorSlot){
        addSpace = true;
        rotors[rotorSlot - 1].rotateRotorOnceForward();

        setRotorLabel(rotorSlot);
//        System.out.println(rotors[rotorSlot - 1].rotorMappings);
        /** Testing purposes only **/
        //setRotorButtons(rotorSlot);
    }

    /**
     * Method for rotating the rotor down one position when a button is pressed.
     * @param rotorSlot The rotor slot containing the rotor to be moved.
     */
    public void rotorDown(int rotorSlot){
        addSpace = true;
        rotors[rotorSlot - 1].rotateRotorOnceBackward();
//        System.out.println(rotors[rotorSlot - 1].rotorMappings);
        setRotorLabel(rotorSlot);
        /** Testing purposes only **/
        //setRotorButtons(rotorSlot);

    }

    /**
     * Method to implement an animation which lights up the correct label when a letter is encrypted.
     * @param l The label to apply the animation to.
     */
    public void glowLabel(Label l){
        //Defining the animation for the label.
        final Animation animation = new Transition() {
            {
                //Setting the fade transition to fade out over 2000 milliseconds.
                setCycleDuration(Duration.millis(2000));
                setInterpolator(Interpolator.EASE_BOTH);
            }

            @Override
            protected void interpolate(double v) {
                Color vColor = new Color(1, 1, 0.2 + (v / 1.3), 1);
                l.setTextFill(vColor);
            }
        };
        animation.play();
    }

    /** Testing purposes only - used to check that the rotations of the rotors are doing so correctly and the mappings are correct. **/
    public void setRotorButtons(int rotorSlot){

        if(rotorSlot == 1){
            if(rotors[rotorSlot - 1].rotorPosition == 0){
                rotor1Up.setText("Z");
                rotor1Down.setText(String.valueOf(alphabet[rotors[rotorSlot - 1].rotorPosition + 1]));
            }
            else if(rotors[rotorSlot - 1].rotorPosition == 25){
                rotor1Down.setText("A");
                rotor1Up.setText(String.valueOf(alphabet[rotors[rotorSlot - 1].rotorPosition - 1]));
            }
            else {
                rotor1Up.setText(String.valueOf(alphabet[rotors[rotorSlot - 1].rotorPosition - 1]));
                rotor1Down.setText(String.valueOf(alphabet[rotors[rotorSlot - 1].rotorPosition + 1]));
            }

        } else if(rotorSlot == 2){
            if(rotors[rotorSlot - 1].rotorPosition == 0){
                rotor2Up.setText("Z");
                rotor2Down.setText(String.valueOf(alphabet[rotors[rotorSlot - 1].rotorPosition + 1]));
            }
            else if(rotors[rotorSlot - 1].rotorPosition == 25){
                rotor2Down.setText("A");
                rotor2Up.setText(String.valueOf(alphabet[rotors[rotorSlot - 1].rotorPosition - 1]));
            }
            else {
                rotor2Up.setText(String.valueOf(alphabet[rotors[rotorSlot - 1].rotorPosition - 1]));
                rotor2Down.setText(String.valueOf(alphabet[rotors[rotorSlot - 1].rotorPosition + 1]));
            }

        } else if(rotorSlot == 3){
            if(rotors[rotorSlot - 1].rotorPosition == 0){
                rotor3Up.setText("Z");
                rotor3Down.setText(String.valueOf(alphabet[rotors[rotorSlot - 1].rotorPosition + 1]));
            }
            else if(rotors[rotorSlot - 1].rotorPosition == 25){
                rotor3Down.setText("A");
                rotor3Up.setText(String.valueOf(alphabet[rotors[rotorSlot - 1].rotorPosition - 1]));
            }
            else {
                rotor3Up.setText(String.valueOf(alphabet[rotors[rotorSlot - 1].rotorPosition - 1]));
                rotor3Down.setText(String.valueOf(alphabet[rotors[rotorSlot - 1].rotorPosition + 1]));
            }
        }
    }

}
