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
        board.setCell(0, 0, true);
        board.setCell(0, 1, true);
        board.setCell(0, 2, true);

        board.setCell(1, 0, true);
        board.setCell(1, 1, false);
        board.setCell(1, 2, true);

        board.setCell(2, 0, true);
        board.setCell(2, 1, true);
        board.setCell(2, 2, true);

        // Add Groups
        Group purple1 = new Group(Group.Type.LESS_THAN, 5);
        purple1.addCell(0, 0);
        purple1.addCell(0, 1);
        board.addGroup(purple1);

        Group pink1 = new Group(Group.Type.SUM_EQUAL_TO, 5);
        pink1.addCell(0, 2);
        pink1.addCell(1, 2);
        pink1.addCell(2, 1);
        pink1.addCell(2, 2);
        board.addGroup(pink1);

        Group teal = new Group(Group.Type.SUM_EQUAL_TO, 6);
        teal.addCell(1, 0);
        teal.addCell(2, 0);
        board.addGroup(teal);

       // Dominoes as provided in game
       List<Domino> dominoes = List.of(
            new Domino(2, 3),
            new Domino(5, 0),
            new Domino(4, 1),
            new Domino(0, 0)    

        );

        System.out.println("Beginning solve on board:");
        board.print();

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
