package cellstates;

// har context-rolle i state pattern. delegerer til state

public class SudokuCell {
    private int value; // den aktuelle værdi af cellen
    private final CellState state; // håndterer om cellen er original eller brugerinput

    public SudokuCell(int value, boolean isOriginal){
        this.value = value;
        if (isOriginal){
            this.state = new OriginalState();
        } else {
            this.state = new UserState();
        }
    }

    // deleger setValue til den gældende state
    public void setValue(int newValue){
        state.setValue(this, newValue);
    }
    
    // package-private: ingen public/private, men alligevel kun tilgængelig for de relevante klasser (da alle state-relevante klasser er i samme pakke). 
    void setValueInternal(int newValue){
        this.value = newValue;
    }
    
    public boolean isEditable() {
        return state.isEditable(); // delegerer til den aktuelle state
    }
    

    public void getSetValueInternal(int newValue){
        setValueInternal(newValue);
    }
    public void getSetValue(int newValue){
        setValue(newValue);
    }
    public int getValue(){
        return value;
    }
    public CellState getState(){
        return state;
    }
}
