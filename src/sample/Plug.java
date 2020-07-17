package sample;

/**
 * Class for the defining the plug of a plugboard and how it should swap a letter.
 */
public class Plug {

    public char input;
    public char output;

    public Plug(char input, char output){
        this.input = input;
        this.output = output;
    }

    /**
     * Swaps the input char with the correct output.
     * @param c The input character.
     * @return The correct output character.
     */
    public char swapChars(char c) {

        /* Check to see if there is a link and swap if so */
        if(c == input) {
            return output;
        }

        /* Applies both ways so map back if needed */
        else if(c == output){
            return input;
        }

        /* If no plug, return the same letter */
        else {
            return c;
        }
    }

    /**
     * Checks if the plug contains the letter.
     * @param c Input character to be checked.
     * @return A boolean value indicating whether the plug contains either letter.
     */
    public boolean checkIfExists(char c){
        boolean exists = false;
        if(c == input || c== output){
            exists = true;
        }
        return exists;
    }

}
