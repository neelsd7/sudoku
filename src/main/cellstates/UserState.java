package cellstates;

// konkret state-rolle i state pattern
public class UserState extends CellState {
    @Override
    public void setValue(SudokuCell cell, int value) {
        // brugeren må gerne ændre denne celle:
        cell.setValueInternal(value);
    }
    
    @Override
    public boolean isEditable(){
        return true;
    }
}
