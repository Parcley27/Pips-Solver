import java.util.ArrayList;
import java.util.List;

public class Cell {
    public final int row;
    public final int col;
    public boolean isAllowed;  // false = disallowed
    public Integer value;      // null if unassigned
    public final List<Group> groups;

    public Cell(int row, int col, boolean isAllowed) {
        this.row = row;
        this.col = col;
        this.isAllowed = isAllowed;
        this.value = null;
        this.groups = new ArrayList<>();

    }

    public void setValue(int value) {
        this.value = value;

    }

    public void clearValue() {
        this.value = null;

    }

    public boolean isEmpty() {
        return value == null;

    }

    public void addGroup(Group group) {
        groups.add(group);

    }

    @Override
    public String toString() {
        return isAllowed ? (value != null ? "[" + value + "]" : "[ ]") : "[X]";
        
    }
}
