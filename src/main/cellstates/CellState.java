package cellstates;

// definerer en fælles struktur for cellernes tilstande

// tilføj evt. notestate senere

public abstract class CellState{
    // abstrakte metoder. tvinger OriginalState og UserState til at implementere dem
    public abstract void setValue(SudokuCell cell, int value);
    public abstract boolean isEditable();
}
    