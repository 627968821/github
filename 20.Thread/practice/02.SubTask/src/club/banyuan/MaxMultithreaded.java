package club.banyuan;

class Multithreaded extends Thread {
    private int lo, hi;
    private int[] arr;
    private double maxMultithreaded = 0;

    public Multithreaded(int[] arr, int lo, int hi) {
        this.lo = lo;
        this.hi = hi;
        this.arr = arr;
    }

    @Override
    public void run() {
        for (int i = lo; i < hi; i++) {
            if (maxMultithreaded < Math.sin(arr[i])) {
                maxMultithreaded = Math.sin(arr[i]);
            }
        }
    }

    public double getMaxMultithreaded() {
        return maxMultithreaded;
    }
}

public class MaxMultithreaded {

    /**
     * 计算数组元素的sin值之后，返回最大值。
     *
     * @param arr 目标数组
     * @return sin(数组元素)的最大值
     * @throws InterruptedException 不应该出现此异常
     */
    public static double max(int[] arr, int numThreads) throws InterruptedException {
        int len = arr.length;
        double maxMultithreaded = 0;
        Multithreaded[] multithreadeds = new Multithreaded[numThreads];
        for (int i = 0; i < numThreads; i++) {
            multithreadeds[i] = new Multithreaded(arr, (i * len) / numThreads, ((i + 1) * len / numThreads));
            multithreadeds[i].start();
        }
        for (int i = 0; i < numThreads; i++) {
            multithreadeds[i].join();
            if (maxMultithreaded < multithreadeds[i].getMaxMultithreaded()) {
                maxMultithreaded = multithreadeds[i].getMaxMultithreaded();
            }
        }
        return maxMultithreaded;
    }

    public static void main(String[] args) throws InterruptedException {
        MaxMultithreaded maxMultithreaded = new MaxMultithreaded();
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(MaxMultithreaded.max(arr, 2));

    }
}
