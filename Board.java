import java.util.*;

public class Board {
    public final int rows;
    public final int cols;
    private final Cell[][] grid;
    private final List<Group> groups;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Cell[rows][cols];
        this.groups = new ArrayList<>();

    }

    public void setCell(int row, int col, boolean isAllowed) {
        grid[row][col] = new Cell(row, col, isAllowed);

    }

    public void addGroup(Group group) {
        groups.add(group);
        for (Group.Coordinate coord : group.getCells()) {
            Cell cell = getCell(coord.row, coord.col);
            if (cell != null) {
                cell.addGroup(group);

            }
        }
    }

    public Cell getCell(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) return null;
        return grid[row][col];

    }

    public boolean canPlace(Domino domino, int row, int col, boolean horizontal) {
        Cell c1 = getCell(row, col);
        Cell c2 = horizontal ? getCell(row, col + 1) : getCell(row + 1, col);

        if (c1 == null || c2 == null || !c1.isAllowed || !c2.isAllowed || !c1.isEmpty() || !c2.isEmpty()) {
            return false;

        }

        // Temporarily place values
        c1.setValue(domino.getLeft());
        c2.setValue(domino.getRight());

        boolean valid = groupsSatisfied();

        // Undo
        c1.clearValue();
        c2.clearValue();

        return valid;

    }

    public boolean canPlaceValues(int v1, int v2, int row, int col, boolean horizontal) {
        Cell c1 = getCell(row, col);
        Cell c2 = horizontal ? getCell(row, col + 1) : getCell(row + 1, col);
    
        if (c1 == null || c2 == null || !c1.isAllowed || !c2.isAllowed || !c1.isEmpty() || !c2.isEmpty()) {
            return false;
        }
    
        c1.setValue(v1);
        c2.setValue(v2);
        boolean valid = groupsSatisfied();
        c1.clearValue();
        c2.clearValue();
    
        return valid;
    }
    

    public void placeDomino(Domino domino, int row, int col, boolean horizontal, boolean reversed) {
        int val1 = reversed ? domino.getReversedLeft() : domino.getLeft();
        int val2 = reversed ? domino.getReversedRight() : domino.getRight();

        Cell c1 = getCell(row, col);
        Cell c2 = horizontal ? getCell(row, col + 1) : getCell(row + 1, col);

        c1.setValue(val1);
        c2.setValue(val2);
        domino.setUsed(true);

    }

    public void placeDominoWithValues(Domino domino, int v1, int v2, int row, int col, boolean horizontal) {
        Cell c1 = getCell(row, col);
        Cell c2 = horizontal ? getCell(row, col + 1) : getCell(row + 1, col);
    
        c1.setValue(v1);
        c2.setValue(v2);
        domino.setUsed(true);
    }
    

    public void removeDomino(Domino domino, int row, int col, boolean horizontal) {
        Cell c1 = getCell(row, col);
        Cell c2 = horizontal ? getCell(row, col + 1) : getCell(row + 1, col);

        c1.clearValue();
        c2.clearValue();
        domino.setUsed(false);

    }

    private boolean groupsSatisfied() {
        for (Group group : groups) {
            List<Integer> values = new ArrayList<>();

            for (Group.Coordinate coord : group.getCells()) {
                Cell cell = getCell(coord.row, coord.col);

                if (cell == null || cell.isEmpty()) continue;

                values.add(cell.value);

            }

            // Only check if all group cells are filled
            if (values.size() != group.getCells().size()) continue;

            int target = group.getTargetValue() != null ? group.getTargetValue() : -1;

            switch (group.getType()) {   
                case SUM_EQUAL_TO:
                    int sumET = values.stream().mapToInt(Integer::intValue).sum();

                    if (sumET != target) return false;
                
                    break;
                
                case SUM_NOT_EQUAL_TO:
                    int sumNET = values.stream().mapToInt(Integer::intValue).sum();

                    if (sumNET == target) return false;

                    break;

                case EQUAL_TO:
                    for (int v : values) {
                        if (v != target) return false;

                    }

                    break;

                case EQUAL:
                    int first = values.get(0);

                    for (int v : values) {
                        if (v != first) return false;

                    }

                    break;

                case NOT_EQUAL_TO:
                    for (int v : values) {
                        if (v == target) return false;

                    }

                    break;

                case LESS_THAN:
                    int sumLT = values.stream().mapToInt(Integer::intValue).sum();

                    if (sumLT >= target) return false;

                    break;

                case GREATER_THAN:
                    int sumGT = values.stream().mapToInt(Integer::intValue).sum();

                    if (sumGT <= target) return false;

                    break;

            }
        }

        return true;
    }

    public void print() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                System.out.print(grid[r][c] + " ");

            }
            System.out.println();

        }
    }
}
