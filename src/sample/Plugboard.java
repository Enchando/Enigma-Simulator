package sample;

import java.util.ArrayList;

/**
 * Class for defining the plugboard and how it operates.
 */
class Plugboard {

    public ArrayList<Plug> plugboardSwitches;

    public Plugboard(ArrayList<Plug> plugboardSwitches){
        this.plugboardSwitches = plugboardSwitches;
    }

    /**
     * Checks if the input character has a plug linked to it, and then return the output.
     * @param input The input character.
     * @return The correct output character at the end of the plug.
     */
    public char checkPlugboard(char input){
        for(Plug plugboardSwitch : plugboardSwitches){
            if(plugboardSwitch.checkIfExists(input)) {
                return plugboardSwitch.swapChars(input);
            }
        }
        return input;
    }

}
