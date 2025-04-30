import java.util.List;

public class Solver {
    private final Board board;
    private final List<Domino> dominoes;

    public Solver(Board board, List<Domino> dominoes) {
        this.board = board;
        this.dominoes = dominoes;

    }

    public boolean solve() {
        return placeNextDomino(0);

    }

    private boolean placeNextDomino(int index) {
        if (index >= dominoes.size()) {
            return true;
        }
    
        Domino domino = dominoes.get(index);
        if (domino.isUsed()) {
            return placeNextDomino(index + 1);
        }
    
        for (int row = 0; row < board.rows; row++) {
            for (int col = 0; col < board.cols; col++) {
                for (boolean horizontal : new boolean[]{true, false}) {
                    // Try both directions: normal and flipped
                    int left = domino.getLeft();
                    int right = domino.getRight();
    
                    // Normal order
                    if (board.canPlaceValues(left, right, row, col, horizontal)) {
                        board.placeDominoWithValues(domino, left, right, row, col, horizontal);
                        if (placeNextDomino(index + 1)) return true;
                        board.removeDomino(domino, row, col, horizontal);
                    }
    
                    // Flipped order (180-degree flip)
                    if (board.canPlaceValues(right, left, row, col, horizontal)) {
                        board.placeDominoWithValues(domino, right, left, row, col, horizontal);
                        if (placeNextDomino(index + 1)) return true;
                        board.removeDomino(domino, row, col, horizontal);
                    }
                }
            }
        }
    
        return false;
    }
}
