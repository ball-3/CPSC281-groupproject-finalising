import java.util.ArrayList;

public class Letter{
    char label;
    ArrayList<Letter> pointers = new ArrayList<>();
    public Letter(char label){
        this.label = label;
    }

    public void addPointer(Letter l){
        pointers.add(l);
    }

    public void removePointer(int i){
        pointers.remove(i);
    }

    public void setPointers(ArrayList<Letter> letterArrayList){
        pointers = letterArrayList;
    }
}
