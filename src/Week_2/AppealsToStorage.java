package Week_2;

public class AppealsToStorage {
    public static void main(String[] args) {
        HashStorage hashStorage = HashStorage.getInstance();

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                hashStorage.writeToMap((finalI * 3) + 200, 20);
                hashStorage.writeToMap((finalI * 3) + 201, 25);
                hashStorage.writeToMap((finalI * 3) + 202, 29);
            }).start();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hashStorage.readMap();
        System.out.println(hashStorage.getSize());
    }
}
