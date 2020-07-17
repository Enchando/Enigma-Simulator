package sample;

import javafx.scene.control.Alert;

/**
 * Class for defining the reflector and how it should return a letter.
 */
public class Reflector {

    public char[][] reflector;
    public char[] alphabet = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public Reflector(char[][] reflector) {
        this.reflector = reflector;
    }

    /**
     * Method for finding the reflection of an input character depending on the rotor that is next to it.
     * @param c The input character to be reflected.
     * @param rotorPosition The rotor next to the reflector's position.
     * @return The correct output from reflecting the input character to the output character defined by the reflector.
     */
    public char getReflection(char c, int rotorPosition) {

        //Checking to see the position of the rotor and then using this as the "input" to the reflector rather than just the input character.
        int position = new String(alphabet).indexOf(c) - rotorPosition;

        if(position < 0){
            position = 26 + position;
        }

        char newChar = alphabet[position];
        char reflectorReturn = newChar;

        //Find the correct output for the reflector.
        for (int j = 0; j < reflector[0].length; j++) {
            if (newChar == reflector[0][j]) {
                reflectorReturn  = reflector[1][j];
                break;
            } else if (newChar == reflector[1][j]) {
                reflectorReturn = reflector[0][j];
                break;
            }
        }

        //Warning in case no reflection is found. Returns what was input if not avaliable.
        if(newChar == reflectorReturn) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error in Reflector");
            alert.setHeaderText("Error");
            alert.setContentText("No reflection found in the reflector! Please check the code to make sure formatted correctly. Using input value.");
            alert.showAndWait();
        }
        return reflectorReturn;
    }

    public char testGetReflection(char c, int rotorPosition) {

        //Checking to see the position of the rotor and then using this as the "input" to the reflector rather than just the input character.
        int position = new String(alphabet).indexOf(c) - rotorPosition;

        if(position < 0){
            position = 26 + position;
        }

        final char newChar = alphabet[position];

        //Find the correct output for the reflector.
        for (int j = 0; j < reflector[0].length; j++) {
            if (newChar == reflector[0][j]) {
                return reflector[1][j];
            } else if (newChar == reflector[1][j]) {
                return reflector[0][j];
            }
        }

        return newChar;
    }

}
