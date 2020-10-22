package kth.threads;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        var rand = new Random(System.currentTimeMillis());

        var pitimes = new long[10];
        var petimes = new long[10];
        var utimes = new long[10];
        for (int j = 0; j < 10; j++) {
            // Parallel including creation
            {
                var start = System.currentTimeMillis();

                var sum = 0;
                var arr = new int[100_000_000];
                for (int i = 0; i < 100_000_000; i++) {
                    arr[i] = rand.nextInt();
                }

                SumTask t1 = new SumTask(arr, 0, 24_999_999);
                SumTask t2 = new SumTask(arr, 25_000_000, 49_999_999);
                SumTask t3 = new SumTask(arr, 50_000_000, 74_999_999);
                SumTask t4 = new SumTask(arr, 75_000_000, 99_999_999);

                t1.start();
                t2.start();
                t3.start();
                t4.start();

                t1.join();
                sum += t1.getSum();

                t2.join();
                sum += t2.getSum();

                t3.join();
                sum += t3.getSum();

                t4.join();
                sum += t4.getSum();

                var time = System.currentTimeMillis() - start;
                System.out.println("Parallel timing (including creation): " + time + " sum: " + sum);
                pitimes[j] = time;
            }

            // Parallel exclusive creation
            {
                var sum = 0;
                var arr = new int[100_000_000];
                for (int i = 0; i < 100_000_000; i++) {
                    arr[i] = rand.nextInt();
                }

                SumTask t1 = new SumTask(arr, 0, 24_999_999);
                SumTask t2 = new SumTask(arr, 25_000_000, 49_999_999);
                SumTask t3 = new SumTask(arr, 50_000_000, 74_999_999);
                SumTask t4 = new SumTask(arr, 75_000_000, 99_999_999);

                var start = System.currentTimeMillis();
                t1.start();
                t2.start();
                t3.start();
                t4.start();

                t1.join();
                sum += t1.getSum();

                t2.join();
                sum += t2.getSum();

                t3.join();
                sum += t3.getSum();

                t4.join();
                sum += t4.getSum();

                var time = System.currentTimeMillis() - start;
                System.out.println("Parallel timing (excluding creation): " + time + " sum: " + sum);
                petimes[j] = time;
            }

            // Unparallel
            {
                var start = System.currentTimeMillis();
                var sum = 0;
                for (int i = 0; i < 100_000_000; i++) {
                    sum += rand.nextInt();
                }
                var time = System.currentTimeMillis() - start;
                System.out.println("Nonparallel timing: " + time + " sum: " + sum);
                utimes[j] = time;
            }
        }

        long piavg = 0;
        long peavg = 0;
        long uavg = 0;
        for (int i = 0; i < 10; i++){
            piavg += pitimes[i];
            peavg += petimes[i];
            uavg += utimes[i];
        }
        piavg /= 10;
        peavg /= 10;
        uavg /= 10;
        System.out.println("Parallel timing avg (including creation): " + piavg);
        System.out.println("Parallel timing avg (excluding creation): " + peavg);
        System.out.println("Nonparallel timing avg: " + uavg);
    }

    static class SumTask extends Thread {
        int[] arr;
        int low, high;
        int sum = 0;

        SumTask(int[] arr, int low, int high) {
            this.arr = arr;
            this.low = low;
            this.high = high;
        }

        @Override
        public void run() {
            super.run();

            for (int i = low; i <= high; i++) {
                sum += arr[i];
            }
        }

        public int getSum() {
            return sum;
        }
    }
}
