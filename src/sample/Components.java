package sample;

public class Components {

    /** Identifying all the rotor's wiring tables, which are unique for each rotor **/
    public static char[] RotorI = new char[]{'E', 'K', 'M', 'F', 'L', 'G', 'D', 'Q', 'V', 'Z', 'N', 'T', 'O', 'W', 'Y', 'H', 'X', 'U', 'S', 'P', 'A', 'I', 'B', 'R', 'C', 'J'};
    public static char[] RotorII = new char[]{'A', 'J', 'D', 'K', 'S', 'I', 'R', 'U', 'X', 'B', 'L', 'H', 'W', 'T', 'M', 'C', 'Q', 'G', 'Z', 'N', 'P', 'Y', 'F', 'V', 'O', 'E'};
    public static char[] RotorIII = new char[]{'B', 'D', 'F', 'H', 'J', 'L', 'C', 'P', 'R', 'T', 'X', 'V', 'Z', 'N', 'Y', 'E', 'I', 'W', 'G', 'A', 'K', 'M', 'U', 'S', 'Q', 'O'};
    public static char[] RotorIV = new char[]{'E', 'S', 'O', 'V', 'P', 'Z', 'J', 'A', 'Y', 'Q', 'U', 'I', 'R', 'H', 'X', 'L', 'N', 'F', 'T', 'G', 'K', 'D', 'C', 'M', 'W', 'B'};
    public static char[] RotorV = new char[]{'V', 'Z', 'B', 'R', 'G', 'I', 'T', 'Y', 'U', 'P', 'S', 'D', 'N', 'H', 'L', 'X', 'A', 'W', 'M', 'J', 'Q', 'O', 'F', 'E', 'C', 'K'};
    public static char[] RotorVI = new char[]{'J', 'P', 'G', 'V', 'O', 'U', 'M', 'F', 'Y', 'Q', 'B', 'E', 'N', 'H', 'Z', 'R', 'D', 'K', 'A', 'S', 'X', 'L', 'I', 'C', 'T', 'W'};
    public static char[] RotorVII = new char[]{'N', 'Z', 'J', 'H', 'G', 'R', 'C', 'X', 'M', 'Y', 'S', 'W', 'B', 'O', 'U', 'F', 'A', 'I', 'V', 'L', 'P', 'E', 'K', 'Q', 'D', 'T'};
    public static char[] RotorVIII = new char[]{'F', 'K', 'Q', 'H', 'T', 'L', 'X', 'O', 'C', 'B', 'J', 'S', 'P', 'D', 'Z', 'R', 'A', 'M', 'E', 'W', 'N', 'I', 'U', 'Y', 'G', 'V'};
    public static char[] RotorIK = new char[]{'P', 'E', 'Z', 'U', 'O', 'H', 'X', 'S', 'C', 'V', 'F', 'M', 'T', 'B', 'G', 'L', 'R', 'I', 'N', 'Q', 'J', 'W', 'A', 'Y', 'D', 'K'};
    public static char[] RotorIIK = new char[]{'Z', 'O', 'U', 'E', 'S', 'Y', 'D', 'K', 'F', 'W', 'P', 'C', 'I', 'Q', 'X', 'H', 'M', 'V', 'B', 'L', 'G', 'N', 'J', 'R', 'A', 'T'};
    public static char[] RotorIIIK = new char[]{'E', 'H', 'R', 'V', 'X', 'G', 'A', 'O', 'B', 'Q', 'U', 'S', 'I', 'M', 'Z', 'F', 'L', 'Y', 'N', 'W', 'K', 'T', 'P', 'D', 'J', 'C'};
    public static char[] RotorIC = new char[]{'D', 'M', 'T', 'W', 'S', 'I', 'L', 'R', 'U', 'Y', 'Q', 'N', 'K', 'F', 'E', 'J', 'C', 'A', 'Z', 'B', 'P', 'G', 'X', 'O', 'H', 'V'};
    public static char[] RotorIIC = new char[]{'H', 'Q', 'Z', 'G', 'P', 'J', 'T', 'M', 'O', 'B', 'L', 'N', 'C', 'I', 'F', 'D', 'Y', 'A', 'W', 'V', 'E', 'U', 'S', 'R', 'K', 'X'};
    public static char[] RotorIIIC = new char[]{'U', 'Q', 'N', 'T', 'L', 'S', 'Z', 'F', 'M', 'R', 'E', 'H', 'D', 'P', 'X', 'K', 'I', 'B', 'V', 'Y', 'G', 'J', 'C', 'W', 'O', 'A'};
    public static char[] Beta = new char[]{'L', 'E', 'Y', 'J', 'V', 'C', 'N', 'I', 'X', 'W', 'P', 'B', 'Q', 'M', 'D', 'R', 'T', 'A', 'K', 'Z', 'G', 'F', 'U', 'H', 'O', 'S'};
    public static char[] Gamma = new char[]{'F', 'S', 'O', 'K', 'A', 'N', 'U', 'E', 'R', 'H', 'M', 'B', 'T', 'I', 'Y', 'C', 'W', 'L', 'Q', 'P', 'Z', 'X', 'V', 'G', 'J', 'D'};

    /** The notch positions of the rotors define when to turn the adjacent rotor to the rotor. For Beta and Gamma, there is no turnover point so the value has been set to higher than 25 **/
    public static int[] RotorINotches = new int[]{16};
    public static int[] RotorIINotches = new int[]{4};
    public static int[] RotorIIINotches = new int[]{21};
    public static int[] RotorIVNotches = new int[]{9};
    public static int[] RotorVNotches = new int[]{25};
    public static int[] RotorVINotches = new int[]{25, 12};
    public static int[] RotorVIINotches = new int[]{25, 12};
    public static int[] RotorVIIINotches = new int[]{25, 12};
    public static int[] RotorIKNotches = new int[]{24};
    public static int[] RotorIIKNotches = new int[]{4};
    public static int[] RotorIIIKNotches = new int[]{13};
    public static int[] RotorICNotches = new int[]{24};
    public static int[] RotorIICNotches = new int[]{4};
    public static int[] RotorIIICNotches = new int[]{13};
    public static int[] BetaNotches = new int[]{50};
    public static int[] GammaNotches = new int[]{50};

    /** The reflector arrangements are organised into two lists, as to do a simple mapping from one letter to another **/
    public static char[][] ReflectorA = new char[][]{{'A', 'B', 'C', 'D', 'F', 'G', 'H', 'I', 'K', 'N', 'O', 'P', 'S'}, {'E', 'J', 'M', 'Z', 'L', 'Y', 'X', 'V', 'W', 'R', 'Q', 'U', 'T'}};
    public static char[][] ReflectorB = new char[][]{{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'I', 'J', 'K', 'M', 'T', 'W'}, {'Y', 'R', 'U', 'H', 'Q', 'S', 'L', 'P', 'X', 'N', 'O', 'Z', 'V'}};
    public static char[][] ReflectorC = new char[][]{{'A', 'B', 'C', 'D', 'E', 'G', 'H', 'K', 'L', 'M', 'N', 'T', 'S'}, {'F', 'V', 'P', 'J', 'I', 'O', 'Y', 'R', 'Z', 'X', 'W', 'Q', 'U'}};
    public static char[][] ReflectorBThin = new char[][]{{'A', 'B', 'C', 'D', 'F', 'G', 'H', 'I', 'L', 'M', 'R', 'S', 'T'}, {'E', 'N', 'K', 'Q', 'U', 'Y', 'W', 'J', 'O', 'P', 'X', 'Z', 'V'}};
    public static char[][] ReflectorCThin = new char[][]{{'A', 'B', 'C', 'E', 'F', 'G', 'H', 'I', 'L', 'P', 'Q', 'S', 'U'}, {'R', 'D', 'O', 'J', 'N', 'T', 'K', 'V', 'M', 'W', 'Z', 'X', 'Y'}};
}
