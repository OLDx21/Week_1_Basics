package Week_1;

import java.util.TreeMap;

public class KnightsTourProblem {
  //список, который хранит последовательность ходов
  TreeMap<Integer, Coordinates> coordinatesTreeMap = new TreeMap<>();

  public static void main(String[] args) {
    KnightsTourProblem knightsTourProblem = new KnightsTourProblem();
    Coordinates coordinates = knightsTourProblem.getRandomCoord();
    knightsTourProblem.fillData(coordinates.getxCoord(), coordinates.getyCoord());

  }

  //метод для создание матрицы, и старта коня
  void fillData(int x, int y) {
    System.out.println(y + " " + x);
    //Создание матрицы
    Integer[][] board = new Integer[8][8];
    //Установка стартовой позиции коня
    board[y][x] = 1;
    coordinatesTreeMap.put(1, new Coordinates(x, y));
    //все возможные ходы коня
    int[][] all = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};

    recFindPos(x, y, 2, all, board);
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

  private boolean recFindPos(int x, int y, int movei, int[][] all, Integer[][] board) {
    int next_x, next_y;
    if (movei == 65) {
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
          board[next_x][next_y] = null;
      }
    }
    return false;
  }

  /**
   * метод, определяющий, можно ли ставить коня на координаты x,y
   */

  private boolean isSafe(int x, int y, Integer[][] board) {
    return x >= 0 && x < 8 && y >= 0 && y < 8 && board[x][y] == null;
  }

  private Coordinates getRandomCoord() {
    return new Coordinates((int) (Math.random() * 7), (int) (Math.random() * 7));
  }
}