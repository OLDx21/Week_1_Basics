package Week_2;

import Week_1.Coordinates;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Метод Москопула
public class MagicSquareW2V1 {
    public static void main(String[] args) {
        int threadCount = 2;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        for(int i =0; i<threadCount; i++) {
            executorService.submit(() -> {
                MagicSquareW2V1 magicSquareW2V1 = new MagicSquareW2V1();
                Coordinates coordinates = magicSquareW2V1.getRandomCoord();
                magicSquareW2V1.createMagicSquare(coordinates.getxCoord(), coordinates.getyCoord());
            });
        }
        executorService.shutdown();


    }

    /**
     * Главный метод, создающий матрицу и сам магический квадрат
     */

    void createMagicSquare(int xCoord, int yCoord) {
        //создание матрицы
        int[][] square = new int[5][5];
        //первоначальные координаты
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

   synchronized static void printMagicSquare(int[][] square) {
       System.out.println();
        for (int y = 0; y < 5; y++) {
            System.out.println();
            for (int x = 0; x < 5; x++) {
                System.out.print(square[y][x] + " ");
            }
        }
    }
    private Coordinates getRandomCoord() {
        return new Coordinates((int) (Math.random() * 4), (int) (Math.random() * 4));
    }
}
