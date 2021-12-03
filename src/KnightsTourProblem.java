import java.util.ArrayList;

public class KnightsTourProblem {

  private static final String DOWN = "down";

  private static final String RISE = "rise";

  //список, который хранит последовательность ходов
  ArrayList<Coordinates> listOfMoves = new ArrayList<>();

  int column = 0;

  public static void main(String[] args) {
    KnightsTourProblem knightsTourProblem = new KnightsTourProblem();
    knightsTourProblem.filldata();
  }

  //метод для создание матрицы, стартового хода и вывода результата
  void filldata() {
    //Создание матрицы
    int[][] board = new int[8][8];

    //Стартовый ход
    goDown(board);

    char letter;

    //Вывод результата
    for (Coordinates coord : listOfMoves) {
      letter = (char) (65 + coord.getxCoord());
      System.out.print("<" + letter + ">" + "<" + coord.getyCoord() + ">; ");
    }

  }

  //ход вниз
  void goDown(int[][] board) {
    for (int yCoord = 0; yCoord < 8; yCoord++) {
      listOfMoves.add(new Coordinates(column, yCoord + 1));
    }
    turnRight(RISE, board);
  }

  //ход вверх
  void riseUp(int[][] board) {
    for (int yCoord = 7; yCoord >= 0; yCoord--) {
      listOfMoves.add(new Coordinates(column, yCoord + 1));
    }
    turnRight(DOWN, board);
  }

  //ход в сторону, для определения в какую именно, создана переменная direction, и константы RISE и DOWN
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
