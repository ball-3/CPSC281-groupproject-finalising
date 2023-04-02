import java.util.ArrayList;
import java.util.Random;

public class Word {
    private AVL wrongInput = new AVL();
    private String word = "";
    private ArrayList<String[]> category = new ArrayList<String[]>();
    private String categoryName;
    private int CharNum;
    // use for hangman
    private final int limitMistake;
    private int stepSize;
    private int nextState = 0; // hangman drawing state
    private boolean gameOver = false;
    private static String[] chosenWordCategory;
    private int numMistakes;
    private static ArrayList<Integer> indexes;


    public String getWrongInput() {
        return wrongInput.toString();
    }

    Word(int limitMistake, String word) {
        numMistakes = 0;
        this.word = word;
        if (limitMistake == 0) limitMistake = -1;
        System.out.println("limitmistake" + limitMistake);
        System.out.println("limit is " + limitMistake);
        stepSize = 27 / limitMistake;
        nextState = 27 % limitMistake;
        this.limitMistake = limitMistake;
    }

    Word(int limitMistake, int wordCategory) {

        String[] fruit = { "fruit", "apple", "banana", "blueberry", "orange", "mango", "lychee" };
        String[] popular_artist = { "popular artist", "taylor swift", "billie eilish", "ariana grande", "adele",
                "olivia rodrigo", "dua lipa", "harry styles" };
        String[] computer_science = { "computer science", "linked list", "skip list", "recursion", "queues", "stack",
                "heap", "avl tree", "warshalls algorithm", "floyds algorithm" };
        category.add(fruit);
        category.add(popular_artist);
        category.add(computer_science);
        generateNewRandomWord(wordCategory);
        numMistakes = 0;
        if (limitMistake == 0) limitMistake = -1;
        System.out.println("limitmistake " + limitMistake);
        System.out.println("limit is " + limitMistake);
        stepSize = 27 / limitMistake;
        nextState = 27 % limitMistake;
        this.limitMistake = limitMistake;
    }

    public int hangmanState() {
        if (wrongInput.getSize() == 0)
            return 0;
        nextState = stepSize * wrongInput.getSize();
        System.out.println(nextState + " step is " + stepSize);
        return nextState;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public String getWord() {
        return word;
    }

    public int getWrongNum() {
        return wrongInput.getSize();
    }

    public int getCharNum() {
        return word.length();
    }

    public static String[] getChosenWordCategory(){
        return chosenWordCategory;
    }

    private void generateNewRandomWord(int wordCategory) {
        Random random = new Random();
        String[] picked = category.get(wordCategory);
        chosenWordCategory = picked;
        categoryName = picked[0];
        int rand;
        while (true) {
            rand = random.nextInt(picked.length);
            if (rand != 0)
                break;
        }
        word = picked[rand];
        System.out.println(word);

    }

    public ArrayList<Integer> indexesOfCharacter(char c) {
        ArrayList<Integer> send = new ArrayList<Integer>();
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == c || word.charAt(i) == Character.toUpperCase(c)||word.charAt(i) == Character.toLowerCase(c)) {
                // tell gui to display the character
                send.add(i);

            }
        }

        indexes = send;
        if (send.size() == 0){
            wrongInput.insert(c);
            numMistakes++;

            System.out.println("Number of mistakes: " + numMistakes + "\n Limit of Mistakes: " + limitMistake);
        }

        if (numMistakes>limitMistake){
            gameOver = true;
        }

        return send;
    }

    public static ArrayList<Integer> getIndexes(){
        return indexes;
    }

}