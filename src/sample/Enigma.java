package sample;

import javafx.scene.control.Alert;

import java.util.Arrays;

import static sample.DataCentre.*;
import static sample.EnigmaController.*;

/**
 * Main class for doing the entire process of encrypting a letter using the individual objects created defining each component.
 */
public class Enigma {

    /**
     * Method for encrypting one letter into the correct ciphertext using the enigma encryption procedure.
     * @param rotors The rotor objects that have been defined beforehand.
     * @param encrypted The letter to be encrypted.
     * @param plugboard The plugboard object
     * @param reflector The reflector object
     * @return The encrypted letter.
     */
    public static char encryptLetter(Rotor[] rotors, char encrypted, Plugboard plugboard, Reflector reflector){

        //Code to be used for testing speed of encryption to meet the functional specification.
//        long startTime = System.nanoTime();

        if(new String(alphabet).indexOf(encrypted) == -1){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error in Input for Encryption");
            alert.setHeaderText("Error");
            alert.setContentText("Input to the enigma formatted incorrectly!");
            alert.showAndWait();
        }

        if(rotors.length < 3){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error in Rotors for Encryption");
            alert.setHeaderText("Error");
            alert.setContentText("Not enough rotors selected!");
            alert.showAndWait();
        }

        System.out.println("Input " + encrypted);
        if(displayEncryptionProcess){
            outputDisplayContents += "\n" + "Input letter: " + encrypted + "\n";
        }

        //Checking to see if the fourth rotor is enabled, and if so, to add on an extra iteration so that it is used when encrypting.
        int iterations = rotors.length - 1;
        if(!enable4Rotor){
            iterations -= 1;
        }

        //Before the encryption starts, the fast rotor is always moved forward one position.{
        rotors[0].rotateRotorOnceForward();

        // Checking if adjacent rotor needs to be pushed forward one if the notch is passed, plus additional check if notch position is 25, to loop.
        for(int i = 2; i >= 0; i --) {
            if (rotors[i].rotateAdjRotor() && rotors[i].canRotateAdjRotor) {
                if(rotors[i].notchPositions[0] == 25) {
                    if(rotors[i].charCount > 0) {
                        rotors[i + 1].rotateRotorOnceForward();
                    }
                }else{
                    rotors[i + 1].rotateRotorOnceForward();
                }

                // Accounting for the "double step" of rotor 2 and 3.**/
                if(i == 1 && rotors[1].notchPositions[0] == rotors[1].rotorPosition) {
                    rotors[1].rotateRotorOnceForward();
                    rotors[1].canRotateAdjRotor = false;
                }if(i == 0){
                    rotors[1].canRotateAdjRotor = true;
                }
            }

        }

        for(Rotor rotor:rotors){
            rotor.charCount += 1;
        }
        char beforePlug = encrypted;

        /** First step is putting the letter through the plugboard. **/
        encrypted = plugboard.checkPlugboard(encrypted);

        System.out.println("Swapped " + beforePlug + " to " + encrypted);
        if(displayEncryptionProcess) {
            outputDisplayContents += "Plugboard encryption: " + encrypted + "\n";
        }

        /** Then, going forward through rotors **/

        for(int i = 0; i <= iterations; i++) {
            if(i == 0){
                encrypted = rotors[i].translate(encrypted, false, 0);
            } else {
                encrypted = rotors[i].translate(encrypted, false, rotors[i - 1].rotorPosition);
            }
            if(displayEncryptionProcess) {
                outputDisplayContents += "Rotor " + (i + 1) + " encryption: " + encrypted + "\n";
            }
            System.out.println("Rotor " + (i + 1) + " encryption: " + encrypted + " with rotor mappings "+ Arrays.toString(rotors[i].rotorMappings));
        }

        System.out.println("Pass through rotors " + encrypted);


        /** On to the reflector encryption (dependant on the fourth rotor) **/
        if(enable4Rotor) {
            encrypted = reflector.getReflection(encrypted, rotors[3].rotorPosition);
        }else{
            encrypted = reflector.getReflection(encrypted, rotors[2].rotorPosition);
        }

        System.out.println("Reflector " + encrypted);
        if(displayEncryptionProcess) {
            outputDisplayContents += "Reflector encryption: " + encrypted + "\n";
        }

        /** Back through the rotors from the reflector **/

        for(int j = iterations; j >= 0; j--) {
            if(j == iterations){
                encrypted = rotors[j].translate(encrypted, true, 0);
            } else {
                encrypted = rotors[j].translate(encrypted, true, rotors[j + 1].rotorPosition);
            }
            if(displayEncryptionProcess) {
                outputDisplayContents += "Rotor " + (j + 1) + " encryption: " + encrypted + "\n";
            }
            System.out.println("Rotor " + (j + 1) + " encryption: " + encrypted + " with rotor mappings "+ Arrays.toString(rotors[j].rotorMappings));

        }
        System.out.println("Back through rotors " + encrypted);


        /** Final plugboard encryption **/
        beforePlug = encrypted;
        encrypted = plugboard.checkPlugboard(encrypted);
        System.out.println("Final swap: " + beforePlug + " to get " + encrypted + "\n");
        if(displayEncryptionProcess) {
            outputDisplayContents += "Final plugboard encryption: " + encrypted + "\n";
        }

//        long endTime = System.nanoTime();
//        long timeElapsed = endTime - startTime;
//        System.out.println("Time to Execute in Milliseconds: " + timeElapsed / 1000000);

        return encrypted;
    }

}
