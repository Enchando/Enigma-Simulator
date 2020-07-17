package sample;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class DataCentre {

    /** Extras used around the code **/
    public static char[] alphabet = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    /** Rotor Values **/
    public static ArrayList<String> rotorChoices = new ArrayList<String>(Arrays.asList("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "I-K", "II-K", "III-K", "IC", "IIC", "IIIC", "Beta", "Gamma"));
    public static ArrayList<String> ringSettings = new ArrayList<>(Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
            "20", "21", "22", "23", "24", "25", "26"));

    /** Plugboard Values and Details **/
    public static ArrayList<Plug> switchesArray = new ArrayList<>();
    public static ArrayList<ArrayList<Button>> switchButtonsArray = new ArrayList<>();
    public static ArrayList<String> chosenSwitchesColors = new ArrayList<>();
    public static ArrayList<String> colorChoices = new ArrayList<>(Arrays.asList("#910000", "#858585", "#cfc800", "#39b800", "#286352", "#001f85", "#190042",
            "#aa00cc", "#c47676", "#808a43", "#324723", "#6fde92", "#aecfe8"));

    /**Enigma machine set up **/
    public static Reflector reflector;
    public static int currentReflectorChoice;
    public static boolean enable4Rotor;
    public static Plugboard plugboard = new Plugboard(switchesArray);
    public static Rotor[] rotors = new Rotor[4];


    /** Sound details - loading, mute etc. **/
    public static ArrayList<String> sounds = new ArrayList<>(Arrays.asList("resources/sound/click1.mp3", "resources/sound/click2.mp3",
            "resources/sound/click3.mp3", "resources/sound/click4.mp3"));

    public static Image imageOn = new Image(String.valueOf(EnigmaController.class.getResource("resources/images/soundon.png")));
    public static BackgroundSize backgroundSize = new BackgroundSize(38,37,true,true,true, false);
    public static BackgroundImage backgroundOnImage = new BackgroundImage(imageOn, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,  backgroundSize);
    public static Background soundOn = new Background(backgroundOnImage);

    public static Image imageOff = new Image(String.valueOf(EnigmaController.class.getResource("resources/images/soundoff.png")));
    public static BackgroundImage backgroundOffImage = new BackgroundImage(imageOff, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,  backgroundSize);
    public static Background soundOff = new Background(backgroundOffImage);

    /** Information for the Guidebook and Operator classes. Was originally going to be written in text files and then loaded in, but requires significantly more time and complexity
     * defining what parts of the text file are headers, how they should be displayed etc.
     * */
    public final static Text mainGuidebookHeader = new Text("Enigma Simulator by Jonathan Brett \n\n");
    public final  static Text[] guidebookHeaders = new Text[]{new Text("How to operate the simulator\n\n"), new Text("Encoding a message\n\n"),
            new Text("Rotor Management\n\n"), new Text("Ring Settings\n\n"), new Text("Plugboard and Reflector Management\n\n"), new Text("Encryption Display\n\n")};

    public final static Text bodyGuidebook0 = new Text("The enigma machine is an encryption device that was famously used by the German military and high command during World War Two. " +
            "The encryption technique patented by its inventor, Arthur Scherbius, was thought to be unbreakable; if anyone got an encrypted message encoded by an enigma machine, " +
            "it would be “impossible” to convert back into understandable plaintext. This was because of the pseudo-random substitution dictated by the electrical pathways that linked the " +
            "various components inside of the enigma. This simulator aims to replicate this complexity in a way that is understandable and realistic, to create the most historically accurate " +
            "experience possible. \n\n");
    public final static Text bodyGuidebook1 = new Text("When opening the simulator, you can see two sections to the left and the right of the screen. The left-hand side is the enigma machine, which you can interact with. " +
            "The right-hand side shows a piece of paper, that will show what you have encoded when interacting with the enigma machine. The various parts of the machine are shown on the diagram below. \n\n");
    public final  static Text bodyGuidebook2 = new Text("To start encoding a message, simply start pressing the keyboard buttons located at the bottom half of the enigma machine. When a key is pressed, a few things will change. Firstly, you can see that a letter located on the lamp panel will glow for a few seconds. " +
            "This same letter will be displayed on the piece of paper to the right-hand side of the screen. When you pressed the letter on the keyboard, the enigma machine took the letter, sent it through all of the components, and returned the encrypted letter. \n\n" +
            "To demonstrate this, you can type on the keyboard “HELLO”, one letter after the other, will be encrypted to the ciphertext “MFNCZ”. This can be seen on the paper, with each letter glowing on the lamp board in turn. \n\n" +
            "There are many ways to change the form of encryption from the enigma. These are – the rotor options; the rotor positions; the ring settings; the plugboard and the reflector. Changing these will change how the enigma will encrypt a message. " +
            "To see how an actual enigma operator would have set up his machine before encoding, see the guidebook titled ‘Operating in the Field’.\n\n");
    public final static Text bodyGuidebook3 = new Text("The rotors are one of the main sources of encryption for the enigma machine and are changeable in many ways. The simulator aims to show how the rotors would have been shown on an actual machine. As shown in Figure 1, " +
            "they are found at the top of the simulator, side by side and displaying the letter that they are currently on. To the right of them on the table, you can see the rotor options choice boxes and the ring settings choice boxes, shown in Figure 2. Each of these relate to the rotors and how they encrypt a letter.\n\n" +
            "The individual rotors (Figure 1) have two arrows to the right of them. You can press them to rotate the rotors around, going through each letter of the alphabet. When you rotate the rotors, you are changing the positions of the mappings in the rotor. The mappings show which letter to encrypt an input letter to. \n\n" +
            "When you encrypt a letter, you will notice that the rightmost rotor changes from the current letter, for example ‘A’, to the next letter in the alphabet, ‘B’ in this case. The other rotors to the left of this will also rotate when you encrypt a message – however, they only do so when the rotor to its right " +
            "passes its “notch position” on the rotor, which moves the rotor to its left up a position. To change which rotors to use, simply click on any of the choice boxes underneath “Rotor Options” (seen in Figure 2) and select the rotor you wish to use. You will see many options to choose from – including all the standard rotors (rotors ‘I’ through ‘VIII’) and " +
            "other rotors used by specialist users, such as the rotors denoted with a ‘K’ such as ‘I-K’ (used in the Swiss K machine) and earlier rotors such as the ‘C’ rotors, such as ‘IC’, used in the commercial enigma.\n\n");
    public final static Text bodyGuidebook4 = new Text("The last way to change the rotors is to modify the “Ring Settings”, shown in Figure 2. The ring settings are used to change the internal wiring of the rotor, and like when the rotors moves up or down a position, changes the mappings of the encryption. The standard ring setting " +
            "for each rotor is ‘01’, where the wirings have not moved in the rotors. At ring setting ‘01’, the wiring for the letter at ‘01’ (so ‘A’) is the same. If it was ‘02’, ‘A’ would use ‘B’’s wiring and so on. If you change the ring settings to another number, you will get a different wiring organisation in the rotor.\n\n");
    public final static Text bodyGuidebook5 = new Text("To access the plugboard and reflector settings, press the button located at the bottom of the enigma machine labelled “Plugboard and Reflector Settings”. This will bring up a window, similar to Figure 3.\n\n" +
            "At the top of the window, you will see a choice box that can be used to switch between reflectors; simply click on the choice box and you will see some options to  choose from. Click on any of the options to switch between reflectors.\n\n" +
            "The plugboard operates in the same way that a real enigma machine does. The plugboard is a simple mapping from one letter to another letter – if there is a plug between ‘A’ and ‘B’, then any time ‘A’ is pressed on the keyboard, it will instead be encrypted as ‘B’, and visa versa. To create a plug, click on one of the letters, " +
            "which will glow a certain colour, and then another. A plug has now been created between these two letters. If you want to remove the plug, click on either of the two letters, to which the glow will disappear from both. An example is shown below of two successful plugs, one between ‘R’ and ‘G’ and ‘I’ and ‘K’.\n\n" +
            "Only one plug can be placed per letter – for example, you cannot place a plug between ‘A’ and ‘B’, and from ‘A’ to ‘C’. As there are 26 letters in the alphabet, you can place 13 plugs to create 13 pairs of letters. To exit back to the enigma machine, press the red ‘X’ found in the top right corner. Your choices of plugboard " +
            "and reflector will be saved and used in further encryptions. To come back to the plugboard and reflector, press the same button you clicked at the start, and you will see all your previous choices as you left them.\n\n");
    public final static Text bodyGuidebook6 = new Text("Now you have familiarised yourself with how the simulator works, you can go and change around the settings to produce different encryption methods. When an enigma machine operator was encrypting or decrypting a message in the field, they would have had a piece of paper next to the machine that would have been used to " +
            "write down what they encrypted so they didn’t forget. To replicate this, you will see to the right of the enigma machine a piece of paper.\n\n" +
            "When you start typing, you will see your message being printed out here. If at any point of creating a message you decide to change the settings, the next letter you type after the change will be separated from the previous ciphertext. If you want to erase the ciphertext completely, click the “Clear” button located at the bottom right of the" +
            " paper. The button next to this, “Show Encryption Process”, will print on the paper how the letter that you input is encrypted through the enigma machine.");


    public static final Text mainOperatorHeader = new Text("Operating in the Field \n\n");
    public static final Text[] operatorHeaders = new Text[]{new Text("How would an enigma operator encrypt a message?\n\n"), new Text("Decrypting a message\n\n")};

    public static final Text bodyOperator0 = new Text("If you have not already familiarised yourself with the set-up of the enigma simulator, it is recommended you read the guidebook titled “Enigma Guidebook” on the main simulator page. Now, you can try and encrypt a message the same way an enigma operator would have done." +
            "When the operator wanted to send a message, they would look up their key sheet to find out how to encrypt a message. Here is a simplified example:  \n\n");
    public static final Text bodyOperator1 = new Text("Suppose we were on day 9, located near the bottom of the table under 'Datum'. This example is using a three-rotor enigma, so don't worry about using the fourth rotor. We have various settings now – the rotor settings (Walzenlage), the ring settings (Ringstellung), " +
            "the plugboard settings (Steckerverbindungen) and daily key possibilities (Kenngruppen). The operator would have the message to encrypt – for example, “Everything is ok”. " +
            "Spaces in the message would be substituted by ‘X’, meaning the message would be “everythingxisxokx”. They would set up their enigma with the settings on the sheet – so looking at " +
            "the simulator, you would set rotor slot 1 with rotor ‘IV’,  slot 2 to ‘III’ and slot 3 to ‘I’. The ring settings can now be set too – in this case, 17, 10 and 18. The plugs can also be " +
            "set up – so ‘E’ would connect to ‘H’, ‘I’ to ‘R’ etc. We'll use reflector B for this example too.  With all this done, the operator would choose one of the four daily key possibilities – for this we’ll use “KNQ”. They would also randomly " +
            "pick three initial rotor starting positions and a three-letter message key. To make it easy, they decide to use “ABC” for the rotor starting positions and “DEF” for the message key.  With " +
            "the rotors set to “ABC”, they would encrypt “DEF”. This comes out to “VWL”. \n\n");
    public static final Text bodyOperator2 = new Text(" The rotors would then be set to the message key letters – “DEF” – and the message would be encrypted.  This gives “IAQORYLFTNJMGIHQK”.  The final step to send it to someone else " +
            "would be to add to the start of the message the daily key possibility padded with two random letters. So, for this message, the key we chose is “KNQ”, and the two letters we’ll use are ‘H’ and ‘F’. We " +
            "could use “HKNQF”, “FKNQH”, “HFKNQ” etc. We’ll use “HKNQF”. Through Morse code, a message can be then sent to the receiver – with additional information such as the initial rotor starting positions (“ABC”), " +
            "message length, and the encrypted message key (“RRK”). To simplify things, we'll just add the encrypted message key, “VWL”, and the rotor starting positions, “ABC”, at the front. We can now send this combined " +
            "with the message in five letter chunks – “HKNQF ABCVW LIAQO RYLFT NJMGI HQK”.\n\n");
    public static final Text bodyOperator3 = new Text("The receiver of the message would get this encrypted message and get the three-letter daily key from the first five letters, confirming they used the correct sheet and day. This was just done through trial and error – " +
            "as there are five letters, it is easy to find the three-letter key from it.  They would configure the enigma machine to the daily settings. They would use the initial rotor starting positions as “ABC”, and decrypt “VWL” to get “DEF”. They can " +
            "finally set the rotor positions to DEF and decrypt the message.\n\n");

}
