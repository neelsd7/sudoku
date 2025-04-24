package cellstates;

// konkret state-rolle i state pattern

// repræsenterer celler, der har en original værdi. kan ikke ændres
public class OriginalState extends CellState {
    @Override
    public void setValue(SudokuCell cell, int value) { 
        System.out.println("Du kan ikke ændre en original celle."); // fejlbesked
        // cell.getSetValue(value); 
    }
    
    @Override
    public boolean isEditable(){
        return false;
    }
}
