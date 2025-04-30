import java.util.ArrayList;
import java.util.List;

public class Group {
    public enum Type {
        SUM_EQUAL_TO,      // sum = n
        SUM_NOT_EQUAL_TO,  // sum != n
        EQUAL_TO,          // = n
        EQUAL,             // =
        NOT_EQUAL_TO,      // ≠n
        LESS_THAN,         // <n
        GREATER_THAN       // >n

    }

    private final Type type;
    private final Integer targetValue; // null for '=' group (equal to each other)
    private final List<Coordinate> cells;

    public Group(Type type, Integer targetValue) {
        this.type = type;
        this.targetValue = targetValue;
        this.cells = new ArrayList<>();

    }

    public void addCell(int row, int col) {
        cells.add(new Coordinate(row, col));

    }

    public List<Coordinate> getCells() {
        return cells;

    }

    public Type getType() {
        return type;

    }

    public Integer getTargetValue() {
        return targetValue;

    }

    // Helper record for coordinates
    public static class Coordinate {
        public final int row;
        public final int col;

        public Coordinate(int row, int col) {
            this.row = row;
            this.col = col;

        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Coordinate)) return false;
            Coordinate other = (Coordinate) o;
            return row == other.row && col == other.col;

        }

        @Override
        public int hashCode() {
            return 31 * row + col;

        }
    }
}
