import java.util.ArrayList;
import java.util.Objects;

public class Letter implements Comparable {
    char label;
    ArrayList<Letter> pointers = new ArrayList<>();
    public Letter(char label){
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Letter letter = (Letter) o;
        return label == letter.label;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
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

    @Override
    public int compareTo(Object o) {
        if(this.equals(o)){
            return 1;
        }else{
            return 0;
        }
    }
}
