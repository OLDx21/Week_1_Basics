import java.util.ArrayList;

public class KnightsTourProblem {

  private static final String DOWN = "down";

  private static final String RISE = "rise";

  ArrayList<Coordinates> listOfMoves = new ArrayList<>();

  int column = 0;

  public static void main(String[] args) {
    KnightsTourProblem knightsTourProblem = new KnightsTourProblem();
    knightsTourProblem.filldata();
  }

  void filldata() {
    int[][] board = new int[8][8];
    board[0][0] = 1;

    for (int yCoord = 0; yCoord < 8; yCoord++) {
      for (int xCoord = 0; xCoord < 8; xCoord++) {
        board[yCoord][xCoord] = (7 * yCoord) + xCoord + yCoord;
      }
    }
    goDown(board);
    char letter;

    for (Coordinates coord : listOfMoves) {
      letter = (char) (65 + coord.getxCoord());
      System.out.print("<" + letter + ">" + "<" + coord.getyCoord() + ">; ");
    }

  }

  void goDown(int[][] board) {
    for (int yCoord = 0; yCoord < 8; yCoord++) {
      listOfMoves.add(new Coordinates(column, yCoord + 1));
    }
    turnRight(RISE, board);
  }

  void riseUp(int[][] board) {
    for (int yCoord = 7; yCoord >= 0; yCoord--) {
      listOfMoves.add(new Coordinates(column, yCoord + 1));
    }
    turnRight(DOWN, board);
  }

  void turnRight(String direction, int[][] board) {
    if (column == 7) {
      return;
    }
    column += 1;
    if (direction.equals(RISE)) {
      riseUp(board);
    } else {
      goDown(board);
    }
  }
}
