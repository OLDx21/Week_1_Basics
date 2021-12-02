
//Метод Москопула
public class MagicSquareV2 {
  public static void main(String[] args) {
    MagicSquareV2 magicSquareV2 = new MagicSquareV2();
    magicSquareV2.createMagicSquare();

  }

  void createMagicSquare() {
    int[][] square = new int[5][5];
    int xCoord = 2, yCoord = 0;
    Coordinates coordinates;
    square[yCoord][xCoord] = 1;

    for (int i = 2; i <= 25; i++) {
      if (isSafe(square, xCoord + 1, yCoord + 2)) {
        coordinates = determinationCoordinates(xCoord + 1, yCoord + 2);
      } else {
        coordinates = determinationCoordinates(xCoord, yCoord + 4);
      }
      square[coordinates.getyCoord()][coordinates.getxCoord()] = i;
      xCoord = coordinates.getxCoord();
      yCoord = coordinates.getyCoord();
    }
    printMagicSquare(square);
  }

  private boolean isSafe(int[][] square, int xCoord, int yCoord) {
    Coordinates coordinates = determinationCoordinates(xCoord, yCoord);
    return square[coordinates.getyCoord()][coordinates.getxCoord()] == 0;
  }

  private Coordinates determinationCoordinates(int xCoord, int yCoord) {
    Coordinates coordinates = new Coordinates(xCoord, yCoord);
    if (xCoord > 4) {
      coordinates.setxCoord(xCoord - 5);
    }
    if (yCoord > 4) {
      coordinates.setyCoord(yCoord - 5);
    }
    return coordinates;
  }

  void printMagicSquare(int[][] square) {
    for (int y = 0; y < 5; y++) {
      System.out.println();
      for (int x = 0; x < 5; x++) {
        System.out.print(square[y][x] + " ");
      }
    }
  }
}
