import java.io.File;
import java.util.Scanner;

class VideoStoreLauncher {

    private static final Scanner scanner = new Scanner(System.in);
    private static final VideoStore vs = new VideoStore();
//    static {
//        File file=new File(vs.getPATH());
//        if(!file.exists()){
//            System.out.println("空仓");
//        }
//
//    }
    public static void main(String[] args) {

        vs.load();

        while (true) {
            System.out.println("1. 添加电影");
            System.out.println("2. 出租电影");
            System.out.println("3. 归还电影");
            System.out.println("4. 查询电影");
            System.out.println("0. 退出");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    addMovie();
                    break;
                case "2":
                    checkOut();
                    break;
                case "3":
                    returnVideo();
                    break;
                case "4":
                    printInfo();
                    break;
                case "0":
                    System.out.println("谢谢使用！");
                    return;
            }
//vs.load();

        }
    }

    private static void printInfo() {
        vs.listInventory();
    }

    private static void addMovie() {
        System.out.println("请输入电影名字：");
        String name = scanner.nextLine();
        // Video video = new Video("name");
        Video video = vs.addVideo(name);
        System.out.println(video);
        vs.store();
    }

    private static void checkOut() {
        System.out.println("请输入电影名字：");
        String name = scanner.nextLine();
        // Video video = new Video("name");
        vs.checkOut(name);
        vs.store();
//      System.out.println(vs.checkOut(name) ? name + "出租成功" : name + "出租失败");
    }

    private static void returnVideo() {
        System.out.println("请输入电影名字：");
        String name = scanner.nextLine();
        vs.returnVideo(name);
        vs.store();
//        System.out.println(vs.checkOut(name) ? name + "归还成功" : name + "归还失败");
    }

    private static void findVideo() {
        System.out.println("请输入电影名字：");
        String name = scanner.nextLine();
        if (vs.findVideo(name) != null) {
            System.out.println(vs.findVideo(name));
        } else {
            System.out.println("未找到该电影");
        }
        vs.store();
    }

}