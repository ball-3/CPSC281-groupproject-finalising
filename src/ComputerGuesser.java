import java.util.ArrayList;
import java.util.Random;

public class ComputerGuesser extends Player{
    BigramGraph bigramGraph;
    Letter currentLetter;
    Letter letterToGuess;
    Letter nextLetterToGuess;
    Random rand;
    ArrayList<Letter> listOfGuessedLetters;

    public ComputerGuesser(boolean isComputerPicker){
        listOfGuessedLetters = new ArrayList<>();
        bigramGraph = new BigramGraph(isComputerPicker);
        currentLetter = null;
        letterToGuess = null;
        nextLetterToGuess = null;
        rand = new Random();
    }

    public void guessLetter(){
        if(bigramGraph.isCompPicker){
            guessLetterSmart();
        }else{
            guessLetterNormal();
        }

    }

    public void guessLetterNormal(){
        if(nextLetterToGuess != null){
            // there is a good next letter to guess
            guessedLetter = nextLetterToGuess.label;
            currentLetter = nextLetterToGuess;
        }else{
            // no good letter to guess so it generates a random one
            getNewLetterToGuess();
        }

        System.out.println("\nGuessed letter: " + guessedLetter);
        correctGuessIndexes = checkLetter(guessedLetter);

        listOfGuessedLetters.add(currentLetter);

        if(correctGuessIndexes.isEmpty()){
            // letter was not in word so guess was false
            guessedLetterCorrect = false;
            System.out.println("Letter was incorrect.");
            bigramGraph.listOfLetters.remove(currentLetter);
            currentLetter = null;
            nextLetterToGuess = null;
        }else{
            // guess was correct so we can look at the next best letter to guess based on this one
            guessedLetterCorrect = true;
            letterToGuess = null;
            System.out.println("Letter was correct.");
            bigramGraph.listOfLetters.remove(currentLetter);

            getNextLetterToGuess();

        }
    }

    public void guessLetterSmart(){
        if(nextLetterToGuess != null){
            // there is a good next letter to guess
            guessedLetter = nextLetterToGuess.label;
            currentLetter = nextLetterToGuess;
        }else{
            // no good letter to guess, so it generates a random one
            getNewLetterToGuess();
        }

        System.out.println("\nGuessed letter: " + guessedLetter);
        correctGuessIndexes = checkLetter(guessedLetter);

        listOfGuessedLetters.add(currentLetter);

        if(correctGuessIndexes.isEmpty()){
            // letter was not in word so guess was false
            guessedLetterCorrect = false;
            System.out.println("Letter was incorrect.");
            bigramGraph.listOfLetters.remove(currentLetter);

            // sets all freqencies of this letter to 0 (basically removing it from the graph)
            for(char letter = 'a'; letter <= 'z'; letter++){
                bigramGraph.totalFrequencies.get(letter).replace(currentLetter.label, 0);
            }
            currentLetter = null;
            nextLetterToGuess = null;
        }else{
            // guess was correct so we can look at the next best letter to guess based on this one
            guessedLetterCorrect = true;
            letterToGuess = null;
            System.out.println("Letter was correct.");
            bigramGraph.listOfLetters.remove(currentLetter);

            // sets all freqencies of this letter to 0 (basically removing it from the graph)
            for(char letter = 'a'; letter <= 'z'; letter++){
                bigramGraph.totalFrequencies.get(letter).replace(currentLetter.label, 0);
            }

            getNextLetterToGuessSmart();

        }
    }

    public void getNextLetterToGuessSmart(){
        char letterWithHighestFrequency  = '\0';
        int highestFrequency = 0;
        for(char letter = 'a'; letter <= 'z'; letter++){
           if(bigramGraph.totalFrequencies.get(currentLetter).get(letter) > highestFrequency){
               letterWithHighestFrequency = letter;
               highestFrequency = bigramGraph.totalFrequencies.get(currentLetter).get(letter);
           }
        }

        if(highestFrequency != 0){
            nextLetterToGuess = new Letter(letterWithHighestFrequency);
        }else{
            nextLetterToGuess = null;
        }
    }

    public ArrayList<Integer> checkLetter(char x){
        return word.indexesOfCharacter(x);
    }


    public void getNewLetterToGuess(){
        int index = rand.nextInt(bigramGraph.listOfLetters.size());

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
