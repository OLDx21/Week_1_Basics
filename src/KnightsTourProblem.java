
import java.util.TreeMap;

public class KnightsTourProblem {

  //список, который хранит последовательность ходов
  TreeMap<Integer, Coordinates> coordinatesTreeMap = new TreeMap<>();

  public static void main(String[] args) {
    KnightsTourProblem knightsTourProblem = new KnightsTourProblem();
    knightsTourProblem.filldata();
  }

  //метод для создание матрицы, и старта коня
  void filldata() {
    //Создание матрицы
    int[][] board = new int[8][8];
    //Установка стартовой позиции коня
    int xCoord = 0, yCoord = 0;
    board[yCoord][xCoord] = 1;
    coordinatesTreeMap.put(0, new Coordinates(0, 0));
    //все возможные ходы коня
    int[][] all = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};

    recFindPos(xCoord, yCoord, 1, all, board);
    //Вывод результата
    char letter;
    for (Coordinates coord : coordinatesTreeMap.values()) {
      letter = (char) (65 + coord.getxCoord());
      System.out.print("<" + letter + ">" + "<" + (coord.getyCoord() + 1) + ">; ");
    }
    //Вывод доски
    System.out.println("\n" + "------------------------------------------------------");
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        letter = (char) (65 + j);
        System.out.print("<" + letter + ">" + "<" + (i + 1) + ">; ");
      }
      System.out.println();
    }

  }

  /**
   * Рекурсивный метод, который ставит коня на next_x next_y координаты, и сразу после этого ставит следующего коня и подбирает ему ход,
   * если для следующего коня не находится ход, то ход действующего коня обнуляется, и ему подбирается другой ход
   */

  private boolean recFindPos(int x, int y, int movei, int[][] all, int[][] board) {
    int next_x, next_y;
    if (movei == 64) {
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
          coordinatesTreeMap.put(board[i][j], new Coordinates(j, i));
        }
      }
      return true;
    }
    for (int k = 0; k < 8; k++) {
      next_x = x + all[k][1];
      next_y = y + all[k][0];
      if (isSafe(next_x, next_y, board)) {
        board[next_x][next_y] = movei;
        if (recFindPos(next_x, next_y, movei + 1, all, board))
          return true;
        else
          board[next_x][next_y] = 0;
      }
    }
    return false;
  }

  /**
   * метод, определяющий, можно ли ставить коня на координаты x,y
   */

  private boolean isSafe(int x, int y, int[][] board) {
    return x >= 0 && x < 8 && y >= 0 && y < 8 && board[x][y] == 0;
  }
}