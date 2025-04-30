import java.util.*;

public class Pips {
    public static void main(String[] args) {
        // Example setup from the example pips game
        Board board = new Board(3, 3);

        /*
        (0, 0) (0, 1) (0, 2)
        (1, 0) (1, 1) (1, 2)
        (2, 0) (2, 1) (2, 2)
        */

        // Set cells as allowed or disallowed
        board.setCell(0, 0, false);
        board.setCell(0, 1, true);
        board.setCell(0, 2, true);

        board.setCell(1, 0, false);
        board.setCell(1, 1, true);
        board.setCell(1, 2, true);

        board.setCell(2, 0, true);
        board.setCell(2, 1, true);
        board.setCell(2, 2, false);

        // Add Groups
        Group purple = new Group(Group.Type.EQUAL_TO, 0);
        purple.addCell(2, 0);
        board.addGroup(purple);

        Group pink = new Group(Group.Type.EQUAL, null);
        pink.addCell(1, 1);
        pink.addCell(2, 1);
        board.addGroup(pink);

        Group teal = new Group(Group.Type.SUM_EQUAL_TO, 10);
        teal.addCell(0, 2);
        teal.addCell(1, 2);
        board.addGroup(teal);

       // Dominoes as provided in game
       List<Domino> dominoes = List.of(
            new Domino(5, 5),
            new Domino(0, 2),
            new Domino(2, 3)

        );

        // Solve
        Solver solver = new Solver(board, dominoes);
        boolean solved = solver.solve();

        if (solved) {
            System.out.println("Solution found:");
            board.print();

        } else {
            System.out.println("No solution.");
            board.print();

        }
    }
}
