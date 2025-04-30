public class Domino {
    private final int left;
    private final int right;
    private boolean used;

    public Domino(int left, int right) {
        this.left = left;
        this.right = right;
        this.used = false;

    }

    // Get original orientation
    public int getLeft() {
        return left;

    }

    public int getRight() {
        return right;

    }

    // Get reversed orientation
    public int getReversedLeft() {
        return right;

    }

    public int getReversedRight() {
        return left;

    }

    public boolean isUsed() {
        return used;

    }

    public void setUsed(boolean used) {
        this.used = used;

    }

    // Useful for comparing dominos regardless of order
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Domino)) return false;

        Domino other = (Domino) obj;

        return (this.left == other.left && this.right == other.right) ||
               (this.left == other.right && this.right == other.left);

    }

    @Override
    public int hashCode() {
        return Integer.hashCode(left) + Integer.hashCode(right);

    }

    @Override
    public String toString() {
        return "[" + left + "|" + right + "]";
        
    }
}
