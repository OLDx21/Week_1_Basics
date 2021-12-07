package Week_1;

//Метод Москопула
public class MagicSquareV2 {
  public static void main(String[] args) {
    MagicSquareV2 magicSquareV2 = new MagicSquareV2();
    magicSquareV2.createMagicSquare();

  }

  /**
   * Главный метод, создающий матрицу и сам магический квадрат
   */

  void createMagicSquare() {
    //создание матрицы
    int[][] square = new int[5][5];
    //первоначальные координаты
    int xCoord = 2, yCoord = 0;
    Coordinates coordinates;
    //установка единицы на первоначальные координаты
    square[yCoord][xCoord] = 1;

    for (int i = 2; i <= 25; i++) {

      /* изначально, идет проверка, занята ли координата xCoord + 1, yCoord + 2, по методу Москопула,
      если она занята, то число занимает координату xCoord, yCoord + 4, так же, в обоих случаях вызывается метод determinationCoordinates,
      для чего этот медот, можно посмотреть ниже
       */
      if (isSafe(square, xCoord + 1, yCoord + 2)) {
        coordinates = determinationCoordinates(xCoord + 1, yCoord + 2);
      } else {
        coordinates = determinationCoordinates(xCoord, yCoord + 4);
      }
      // на определенную координату устанавливается число
      square[coordinates.getyCoord()][coordinates.getxCoord()] = i;

      //xCoord и yCoord становятся на последнюю обработанную координату
      xCoord = coordinates.getxCoord();
      yCoord = coordinates.getyCoord();
    }
    //вывод результата
    printMagicSquare(square);
  }

  /**
   * Метод, проверяющий занята ли заданная координата числом
   */

  private boolean isSafe(int[][] square, int xCoord, int yCoord) {
    Coordinates coordinates = determinationCoordinates(xCoord, yCoord);
    return square[coordinates.getyCoord()][coordinates.getxCoord()] == 0;
  }

  /**
   * Метод, определяющий реальные координаты, т.к по методу Москопула возможен выход за пределы матрицы, и в таком случае,
   * реальная координата будет равна прямо эквивалентной заданной координате в сущетсвующей матрице
   */

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

  /**
   * Вывод конечного результата
   */

  void printMagicSquare(int[][] square) {
    for (int y = 0; y < 5; y++) {
      System.out.println();
      for (int x = 0; x < 5; x++) {
        System.out.print(square[y][x] + " ");
      }
    }
  }
}
