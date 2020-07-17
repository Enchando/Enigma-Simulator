package sample;

import java.util.Arrays;

public class Rotor {

    public int rotorSlot;
    public int rotorPosition;
    public char[] rotorMappings;
    private char[] originalRotorMappings;
    public int[] notchPositions;
    public boolean canRotateAdjRotor;
    public char[] alphabet = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    public boolean notchPosition25;
    public int charCount;

    /**
     * Definition of the Rotor object. Contains all the variables of the rotor needed for adjustments, positioning, mappings
     * etc. Made so it can be uniquely identified and work independently from other rotors.
     * @param rotorSlot The slot that the rotor is placed in - used for things such as checking notch positions.
     * @param notchPositions The notch positions for the rotor.
     * @param rotorPosition The current position of the rotor. Cycles from 0 to 25, where 0 is start position and 25 is the 'Z' position.
     * @param rotorMappings The mappings to be used for that rotor. Can be changed dependant on other methods, e.g. ring settings, so must be identifiable for the individual rotors rather
     *                      than referring back to the same reference in Components class.
     */
    public Rotor(int rotorSlot, int[] notchPositions, int rotorPosition, char[] rotorMappings){

        this.rotorSlot = rotorSlot;
        this.rotorPosition = 0;
        this.rotorMappings = rotorMappings;
        this.notchPositions = notchPositions;
        this.originalRotorMappings = rotorMappings.clone();
        this.canRotateAdjRotor = true;
        this.notchPosition25 = true;
        this.charCount = 0;

        /** Make sure rotor is in correct position when initialising  - more reassurance than anything. **/

        for(int i = 0; i < rotorPosition; i++){
            this.rotateRotorOnceForward();
        }
    }

    /**
     * Method for putting the letter through the rotor and outputting the correct ciphertext.
     * @param c The input character.
     * @param reverse Variable used to show whether going forwards or backwards through the rotor
     * @param previousRotorPosition A variable added to show the position of the previous rotor so that it is input into the correct
     *                              letter on this rotor.
     * @return
     */
    public char translate(char c, boolean reverse, int previousRotorPosition){

        if(reverse){

            // Find input char "position" into the rotor
            int newRotorInput = new String(alphabet).indexOf(c) + this.rotorPosition;

            // If it exceeds the length of alphabet, wrap back around.
            if(newRotorInput > 25){
                newRotorInput = newRotorInput - 26;
            }

            // Now find the position of the char in the rotor mappings at that position.
            int position = new String(this.rotorMappings).indexOf(alphabet[newRotorInput]);

            // As it's in reverse, instead of going from alphabet -> mapping, need mapping -> alphabet.
            //System.out.println("Rotor Pos" + this.rotorPosition + "Translation " + alphabet[position]);
            return alphabet[position];

        }

        else {
            if(c - 'A' - previousRotorPosition < 0){
                return this.rotorMappings[26 + (c - 'A' - previousRotorPosition)];
            }
            else{
                return this.rotorMappings[new String(alphabet).indexOf(c) - previousRotorPosition];
            }
        }
    }


    /**
     * Method to rotate the rotor once forward. Needed for both user adjustment and when the enigma machine moves the rotor forward itself.
     */
    public void rotateRotorOnceForward(){

        char newLastChar = this.rotorMappings[0];

        for(int i = 0; i <= this.rotorMappings.length - 2; i ++){
            this.rotorMappings[i] = this.rotorMappings[i + 1];
        }
        this.rotorMappings[this.rotorMappings.length - 1] = newLastChar;

        if(this.rotorPosition + 1 <= 25){
            this.rotorPosition += 1;
        }else{
            this.rotorPosition = 0;
        }

        //System.out.println("Rotate forwards rotor at pos" + this.rotorPosition);

    }


    /**
     * Method to rotate the rotor once backwards. Not used by the enigma machine itself, only for when the user wants to adjust it themselves - technically
     * this functionality would not exist in the original enigma machine but added for user experience.
     */
    public void rotateRotorOnceBackward(){

        char newFirstChar = this.rotorMappings[this.rotorMappings.length - 1];

        for(int i = this.rotorMappings.length - 2; i >= 0; i --){
            this.rotorMappings[i + 1] = this.rotorMappings[i];
        }

        this.rotorMappings[0] = newFirstChar;

        if(this.rotorPosition - 1 < 0){
            this.rotorPosition = 25;
        }else{
            this.rotorPosition -= 1;
        }

        //System.out.println("Rotate Backwards rotor at pos" + this.rotorPosition);
    }


    /**
     * Method for deciding whether or not to rotate the rotor next to the current one. This is used for the notch position
     * located on the rotors.
     * @return rotate, whether or not the adjacent rotor can be rotated.
     */
    public boolean rotateAdjRotor(){

        boolean rotate = false;

        for (int notchPosition : this.notchPositions) {
            /*Slightly different methodology for when the rotor is in slot 1 compared to slot 2 - technically slot 3 and 4 rotors will never rotate an adjacent
            rotor themselves so they do not need to be considered */
            if (this.rotorSlot == 1) {
                if (notchPosition + 1 == this.rotorPosition) {
                    rotate = true;
                    break;
                    //Accounting for if the notch position is 25 and so loops back around.
                }else if(notchPosition + 1 == 26 && this.rotorPosition == 0) {
                    rotate = true;
                    break;
                }
            }
            else {
                if (notchPosition == this.rotorPosition) {
                    rotate = true;
                    break;
                }
            }
        }

        return rotate;
    }

    /**
     * Method for changing the ring settings of the rotor. Had some difficulty with this method as there are many interpretations on what the ring setting does or outputs
     * so chose the method that most sources agree with.
     * @param newRingPosition The new ring setting to be used for the rotor.
     */
    public void changeRingSetting(int newRingPosition) {

        /** Use the original mappings and not the edited one **/
        char[] ringMappings = new char[26];

        //The 'dot' position is usually 'A' on the rotor, and will be altered with the adjustment to the new setting.
        int dotPosition = Arrays.binarySearch(this.originalRotorMappings, 'A') + newRingPosition;

        for (int i = 0; i <= 25; i++) {
            int position = Arrays.binarySearch(alphabet, this.originalRotorMappings[i]) + newRingPosition;
            if (position > 25) {
                ringMappings[i] = alphabet[position - 26];
            } else {
                ringMappings[i] = alphabet[position];
            }
        }


        this.rotorMappings = ringMappings;

        for(int j = 0; j <= dotPosition; j++){
            char newFirstChar = this.rotorMappings[this.rotorMappings.length - 1];

            for (int i = this.rotorMappings.length - 2; i >= 0; i--) {
                this.rotorMappings[i + 1] = this.rotorMappings[i];
            }

            this.rotorMappings[0] = newFirstChar;
        }

        //Readjusting the rotor back to the position used last.
        for (int i = 0; i < this.rotorPosition; i++) {

            char newLastChar = this.rotorMappings[0];
            for (int j = 0; j <= this.rotorMappings.length - 2; j++) {
                this.rotorMappings[j] = this.rotorMappings[j + 1];
            }
            this.rotorMappings[this.rotorMappings.length - 1] = newLastChar;
        }

    }

}
