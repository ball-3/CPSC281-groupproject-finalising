import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

public class BigramGraph {
    public ArrayList<Letter> listOfLetters = new ArrayList<>(); // for normal bigram graph
    public TreeMap<Character, TreeMap<Character, Integer>> totalFrequencies; // for smart bigram graph
    public String[] chosenCategory;
    public boolean isCompPicker;

    public BigramGraph(boolean isCompPicker){// as in "is the computer picking the word?"
        this.isCompPicker = isCompPicker;
        if(isCompPicker){
            // if yes then the computer will be
            chosenCategory = Player.word.getChosenWordCategory();
            generateSmartBigramGraph(chosenCategory);
        }else{
            generateNormalBigramGraph();
        }
    }

    public void generateNormalBigramGraph(){
        Letter a = new Letter('a');
        Letter b = new Letter('b');
        Letter c = new Letter('c');
        Letter d = new Letter('d');
        Letter e = new Letter('e');
        Letter f = new Letter('f');
        Letter g = new Letter('g');
        Letter h = new Letter('h');
        Letter i = new Letter('i');
        Letter j = new Letter('j');
        Letter k = new Letter('k');
        Letter l = new Letter('l');
        Letter m = new Letter('m');
        Letter n = new Letter('n');
        Letter o = new Letter('o');
        Letter p = new Letter('p');
        Letter q = new Letter('q');
        Letter r = new Letter('r');
        Letter s = new Letter('s');
        Letter t = new Letter('t');
        Letter u = new Letter('u');
        Letter v = new Letter('v');
        Letter w = new Letter('w');
        Letter x = new Letter('x');
        Letter y = new Letter('y');
        Letter z = new Letter('z');



        listOfLetters = new ArrayList<>(Arrays.asList(a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z));

        Letter[] aListOfPointers = {l,r,t};
        addPointers(a, aListOfPointers);

        Letter[] bListOfPointers = {e,c};
        addPointers(b, bListOfPointers);

        Letter[] cListOfPointers = {o};
        addPointers(c, cListOfPointers);

        Letter[] dListOfPointers = {e};
        addPointers(d, dListOfPointers);

        Letter[] eListOfPointers = {s,m,d,t,c,r,h,n};
        addPointers(e, eListOfPointers);

        Letter[] fListOfPointers = {t};
        addPointers(f, fListOfPointers);

        Letter[] gListOfPointers = {e};
        addPointers(g, gListOfPointers);

        Letter[] hListOfPointers = {e,a,t};
        addPointers(h, hListOfPointers);

        Letter[] iListOfPointers = {t,n};
        addPointers(i, iListOfPointers);

        Letter[] jListOfPointers = {u};
        addPointers(j, jListOfPointers);

        Letter[] kListOfPointers = {i};
        addPointers(k, kListOfPointers);

        Letter[] lListOfPointers = {a};
        addPointers(l, lListOfPointers);

        Letter[] mListOfPointers = {a,e};
        addPointers(m, mListOfPointers);

        Letter[] nListOfPointers = {d,e,i,t,o};
        addPointers(n, nListOfPointers);

        Letter[] oListOfPointers = {n,r,u};
        addPointers(o, oListOfPointers);

        Letter[] pListOfPointers = {e};
        addPointers(p, pListOfPointers);

        Letter[] qListOfPointers = {u};
        addPointers(q, qListOfPointers);

        Letter[] rListOfPointers = {a,o,e};
        addPointers(r, rListOfPointers);

        Letter[] sListOfPointers = {e,a,t,h};
        addPointers(s, sListOfPointers);

        Letter[] tListOfPointers = {h,i,e,o,n};
        addPointers(t, tListOfPointers);

        Letter[] uListOfPointers = {e};
        addPointers(u, uListOfPointers);

        Letter[] vListOfPointers = {i};
        addPointers(v, vListOfPointers);

        Letter[] wListOfPointers = {a};
        addPointers(w, wListOfPointers);

        Letter[] xListOfPointers = {t};
        addPointers(x, xListOfPointers);

        Letter[] yListOfPointers = {a};
        addPointers(y, yListOfPointers);

        Letter[] zListOfPointers = {e};
        addPointers(z, zListOfPointers);
    }

    public void generateSmartBigramGraph(String[] category){
        totalFrequencies = new TreeMap<>();
        ArrayList<Character> listOfFirstLetters = new ArrayList<>();

        // fills frequency table with letters
        for(char letter = 'a'; letter <= 'z'; letter++){
            TreeMap<Character, Integer> individualFrequencies = new TreeMap<>();
            for(char letter2 = 'a'; letter2 <= 'z'; letter2++){
                individualFrequencies.put(letter2, 0);
            }
            totalFrequencies.put(letter, individualFrequencies);
        }

        // sorts letters and their frequences
        for(int i = 0; i<category.length; i++){
            String word = category[i];
            for(int j = 0; j<word.length() - 1; j++){
                if(word.charAt(j) != ' ' && word.charAt(j+1) != ' '){
                    totalFrequencies.get(word.charAt(j)).put(word.charAt(j+1),
                            totalFrequencies.get(word.charAt(j)).get(word.charAt(j+1)) + 1) ;
                }else{
                    j++;
                }

            }

            char l = word.charAt(0);

            // list of first letters to guess from
            if(!listOfFirstLetters.contains(l)){
                listOfFirstLetters.add(l);
            }
        }

        // print graph:
        System.out.print("  ");
        for(char letter = 'a'; letter <= 'z'; letter++){
            System.out.print(letter + " ");
        }

        System.out.println();

        for(char letter = 'a'; letter <= 'z'; letter++){
            System.out.print(letter + " ");
            for(char letter2 = 'a'; letter2 <= 'z'; letter2++){
                System.out.print(totalFrequencies.get(letter).get(letter2) + " ");
            }
            System.out.println();
        }

        //Put letters in list
        for(char letter = 'a'; letter <= 'z'; letter++){
            Letter myLetter = new Letter(letter);
            for(char letter2 = 'a'; letter2 <= 'z'; letter2++){
                if(totalFrequencies.get(letter).get(letter2) > 0 && !listOfLetters.contains(myLetter)){
                    listOfLetters.add(myLetter);
                }

            }
        }

        System.out.println();
        for(Letter l : listOfLetters){
            System.out.print(l.label +  "  ");
        }


    }


    public void addPointers(Letter letter, Letter[] listOfPointers){
        for(int i = 0; i < listOfPointers.length; i++){
            letter.addPointer(listOfPointers[i]);
        }
    }

    public void removeLetterFromGraph(Letter letter){
        letter = null;
    }

}