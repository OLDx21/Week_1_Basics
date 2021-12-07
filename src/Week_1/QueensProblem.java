package Week_1;

public class QueensProblem {

  public static void main(String[] args) {
    int[][] board = new int[8][8];
    solveQueens(board, 0, 3);
    printRes(board);

  }

  /**
   *
   *
   * Вывод конечного результата
   */

  public static void printRes(int[][] board) {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++)
        System.out.print(" " + board[i][j] + " ");
      System.out.println();
    }
  }

  /**
   *
   *
   * Проверка, возможно ли поставить королеву на заданные координаты
   */

  public static boolean isSafe(int[][] board, int row, int column) {
    int i, j;
    for (i = 0; i < column; i++) {
      if (board[row][i] == 1) return false;
    }
    for (i = row, j = column; i >= 0 && j >= 0; i--, j--) {
      if (board[i][j] == 1) return false;
    }
    for (i = row, j = column; i < 8 && j >= 0; i++, j--) {
      if (board[i][j] == 1) return false;
    }
    return true;
  }

  /**
   * Рекурсивный метод, который ставит королеву в n колонку, и сразу после этого ставит в колонку n+1,
   * если в колонку n+1 никак нельзя поставить королеву, то в колонке n будет перестановка королевы в следующий ряд
   *
   *
   */

  public static boolean solveQueens(int[][] board, int column) {
    if (column >= 8) return true;
    for (int i = 0; i < 8; i++) {
      if (isSafe(board, i, column)) {
        board[i][column] = 1;
        if (solveQueens(board, column + 1)) return true;
        // выполняется только в том случае, если в колонку n+1 никак нельзя поставить королеву
        board[i][column] = 0;
      }
    }
    return false;
  }

  public static void solveQueens(int[][] board, int column, int firstPosition) {
    board[firstPosition][0]=1;
    solveQueens(board, column+1);
  }
}
