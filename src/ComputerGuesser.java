import java.util.ArrayList;
import java.util.Random;

public class ComputerGuesser extends Player{
    BigramGraph bigramGraph;
    Letter currentLetter;
    Letter letterToGuess;
    Letter nextLetterToGuess;
    Random rand;
    ArrayList<Letter> listOfGuessedLetters;

    public ComputerGuesser(){
        listOfGuessedLetters = new ArrayList<>();
        bigramGraph = new BigramGraph();
        currentLetter = null;
        letterToGuess = null;
        nextLetterToGuess = null;
        rand = new Random();
    }

    public void guessLetter(){
        if(nextLetterToGuess != null){
            guessedLetter = nextLetterToGuess.label;
            currentLetter = nextLetterToGuess;
        }else{
            getNewLetterToGuess();
        }

        System.out.println("\nGuessed letter: " + guessedLetter);
        correctGuessIndexes = checkLetter(guessedLetter);

        listOfGuessedLetters.add(currentLetter);

        if(correctGuessIndexes.isEmpty()){
            guessedLetterCorrect = false;
            System.out.println("Letter was incorrect.");
            bigramGraph.listOfLetters.remove(currentLetter);
            currentLetter = null;
            nextLetterToGuess = null;
        }else{
            guessedLetterCorrect = true;
            letterToGuess = null;
            System.out.println("Letter was correct.");
            bigramGraph.listOfLetters.remove(currentLetter);
            getNextLetterToGuess();
        }



//        if(word.lettersLeft == 0){
//            wordIsGuessed = true;
//            System.out.println("\nI got the correct word!");
//        }
    }

    public ArrayList<Integer> checkLetter(char x){
        return word.indexesOfCharacter(x);
    }


    public void getNewLetterToGuess(){
        int index = rand.nextInt(bigramGraph.listOfLetters.size() - 1);

        Letter letter = bigramGraph.listOfLetters.get(index);
        currentLetter = letter;
        letterToGuess = letter;
        guessedLetter = letterToGuess.label;
    }

    public void getNextLetterToGuess(){
        if(!currentLetter.pointers.isEmpty()){
            for(int j = 0; j < bigramGraph.listOfLetters.size(); j++) {
                Letter l = bigramGraph.listOfLetters.get(j);
                for (int i = 0; i < listOfGuessedLetters.size(); i++) {
                    if (listOfGuessedLetters.get(i) == l) {
                        currentLetter.pointers.remove(l);
                    }
                }
            }
        }

        if(currentLetter.pointers.isEmpty()){
            nextLetterToGuess = null;
        }else{
            nextLetterToGuess = currentLetter.pointers.get(0);
            currentLetter = currentLetter.pointers.get(0);
            System.out.println("Next Letter to guess: " + nextLetterToGuess.label);
        }
    }
}
