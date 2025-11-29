import java.util.Comparator;

/**
 * 主类：包含所有Part的演示逻辑，main方法执行完整流程
 */
public class AssignmentTwo {
    public static void main(String[] args) {
        AssignmentTwo demo = new AssignmentTwo();

        // 按Part顺序演示所有功能
        System.out.println("==================================== Part 3：等待队列演示 ====================================");
        demo.partThree();

        System.out.println("\n==================================== Part 4A：骑行历史演示 ====================================");
        demo.partFourA();

        System.out.println("\n==================================== Part 4B：排序演示 ====================================");
        demo.partFourB();

        System.out.println("\n==================================== Part 5：运行周期演示 ====================================");
        demo.partFive();

        System.out.println("\n==================================== Part 6：导出CSV演示 ====================================");
        demo.partSix();

        System.out.println("\n==================================== Part 7：导入CSV演示 ====================================");
        demo.partSeven();
    }

    // Part 3：等待队列演示（添加5个访客、移除1个、打印队列）
    public void partThree() {
        // 创建操作员
        Employee operator = new Employee("John Doe", 30, "123456789", "EMP001", "Ride Operator");
        // 创建游乐设施（过山车，每周期最多2人）
        Ride rollerCoaster = new Ride("Roller Coaster", "Thrill", operator, 2);

        // 添加5个访客到队列
        rollerCoaster.addVisitorToQueue(new Visitor("Jack", 25, "987654321", "VIS001", "Adult"));
        rollerCoaster.addVisitorToQueue(new Visitor("Sharon", 22, "876543210", "VIS002", "Adult"));
        rollerCoaster.addVisitorToQueue(new Visitor("Benny", 18, "765432109", "VIS003", "Young Adult"));
        rollerCoaster.addVisitorToQueue(new Visitor("Leo", 30, "654321098", "VIS004", "Adult"));
        rollerCoaster.addVisitorToQueue(new Visitor("Emma", 28, "543210987", "VIS005", "Adult"));

        // 打印初始队列
        rollerCoaster.printQueue();

        // 移除1个访客
        rollerCoaster.removeVisitorFromQueue();

        // 打印移除后的队列
        System.out.println("\n[AFTER REMOVE]");
        rollerCoaster.printQueue();
    }

    // Part 4A：骑行历史演示（添加5个访客、检查存在性、打印历史）
    public void partFourA() {
        // 创建游乐设施（雷雨水上 ride）
        Ride thunderstorm = new Ride("Thunderstorm", "Water Ride", null, 4);

        // 添加5个访客到历史
        Visitor v1 = new Visitor("Tom", 24, "111222333", "VIS006", "Adult");
        Visitor v2 = new Visitor("Sherly", 26, "222333444", "VIS007", "Adult");
        Visitor v3 = new Visitor("Ben", 20, "333444555", "VIS008", "Young Adult");
        Visitor v4 = new Visitor("David", 35, "444555666", "VIS009", "Adult");
        Visitor v5 = new Visitor("Lily", 29, "555666777", "VIS010", "Adult");

        thunderstorm.addVisitorToHistory(v1);
        thunderstorm.addVisitorToHistory(v2);
        thunderstorm.addVisitorToHistory(v3);
        thunderstorm.addVisitorToHistory(v4);
        thunderstorm.addVisitorToHistory(v5);

        // 检查访客是否在历史中
        thunderstorm.checkVisitorFromHistory(v3); // 存在
        thunderstorm.checkVisitorFromHistory(new Visitor("Mike", 30, "666777888", "VIS011", "Adult")); // 不存在

        // 打印历史访客总数
        thunderstorm.numberOfVisitors();

        // 打印骑行历史（Iterator实现）
        thunderstorm.printRideHistory();
    }

    // Part 4B：排序演示（添加5个访客、排序、对比前后结果）
    public void partFourB() {
        // 创建游乐设施（原木漂流）
        Ride logFlume = new Ride("Log Flume", "Family Ride", null, 6);

        // 添加5个不同年龄和票类型的访客
        logFlume.addVisitorToHistory(new Visitor("Alice", 12, "777888999", "VIS012", "Child"));
        logFlume.addVisitorToHistory(new Visitor("Bob", 30, "888999000", "VIS013", "Adult"));
        logFlume.addVisitorToHistory(new Visitor("Charlie", 12, "999000111", "VIS014", "Child"));
        logFlume.addVisitorToHistory(new Visitor("Diana", 25, "000111222", "VIS015", "Adult"));
        logFlume.addVisitorToHistory(new Visitor("Ethan", 18, "111333555", "VIS016", "Young Adult"));

        // 排序前打印
        System.out.println("[BEFORE SORT]");
        logFlume.printRideHistory();

        // 用自定义Comparator排序
        Comparator<Visitor> comparator = new VisitorAgeTicketComparator();
        logFlume.sortRideHistory(comparator);

        // 排序后打印
        System.out.println("[AFTER SORT]（按年龄→票类型）");
        logFlume.printRideHistory();
    }

    // Part 5：运行周期演示（添加10个访客、运行1个周期、对比队列和历史）
    public void partFive() {
        // 创建操作员
        Employee operator = new Employee("Sarah Smith", 28, "234567890", "EMP002", "Ride Operator");
        // 创建游乐设施（摩天轮，每周期最多3人）
        Ride ferrisWheel = new Ride("Ferris Wheel", "Family Ride", operator, 3);

        // 添加10个访客到队列
        for (int i = 0; i < 10; i++) {
            String visitorId = "VIS" + String.format("%03d", 20 + i);
            String ticketType = (i < 3) ? "Child" : "Adult";
            ferrisWheel.addVisitorToQueue(new Visitor("Visitor " + (i+1), 15 + i, "ID" + (100 + i), visitorId, ticketType));
        }

        // 运行前打印队列
        System.out.println("[BEFORE CYCLE]");
        ferrisWheel.printQueue();

        // 运行1个周期
        ferrisWheel.runOneCycle();

        // 运行后打印队列和历史
        System.out.println("[AFTER CYCLE] 队列剩余访客：");
        ferrisWheel.printQueue();

        System.out.println("[AFTER CYCLE] 骑行历史：");
        ferrisWheel.printRideHistory();

        // 打印运行周期数
        System.out.println("[INFO] " + ferrisWheel.getName() + " 已运行 " + ferrisWheel.getNumOfCycles() + " 个周期");
    }

    // Part 6：导出CSV演示（添加5个访客、导出到文件）
    public void partSix() {
        // 创建游乐设施（碰碰车）
        Ride bumperCars = new Ride("Bumper Cars", "Family Ride", null, 4);

        // 添加5个访客到历史
        bumperCars.addVisitorToHistory(new Visitor("Frank", 22, "345678901", "VIS030", "Young Adult"));
        bumperCars.addVisitorToHistory(new Visitor("Grace", 27, "456789012", "VIS031", "Adult"));
        bumperCars.addVisitorToHistory(new Visitor("Henry", 19, "567890123", "VIS032", "Young Adult"));
        bumperCars.addVisitorToHistory(new Visitor("Ivy", 23, "678901234", "VIS033", "Adult"));
        bumperCars.addVisitorToHistory(new Visitor("Jackie", 21, "789012345", "VIS034", "Young Adult"));

        // 导出到CSV文件（相对路径，可根据系统调整）
        String filePath = "bumperCars_rideHistory.csv";
        bumperCars.exportRideHistory(filePath);
    }

    // Part 7：导入CSV演示（从Part 6的文件导入、验证结果）
    public void partSeven() {
        // 创建新的游乐设施（用于导入）
        Ride importedRide = new Ride("Imported Bumper Cars", "Family Ride", null, 4);

        // 导入Part 6导出的文件
        String filePath = "bumperCars_rideHistory.csv";
        importedRide.importRideHistory(filePath);

        // 验证导入结果：打印总数和详情
        importedRide.numberOfVisitors();
        importedRide.printRideHistory();
    }
}
