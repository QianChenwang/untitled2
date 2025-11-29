import java.util.*;
import java.io.*;

/**
 * 游乐设施类：实现RideInterface，管理等待队列、骑行历史、运行逻辑等
 */
public class Ride implements RideInterface {
    // 核心属性（Part 1：至少3个，含1个Employee类型）
    private String name;         // 设施名称（如：Roller Coaster）
    private String type;         // 设施类型（如：Thrill/Water/Family）
    private Employee operator;   // 操作员（Employee类型，Part 1要求）

    // 集合属性（Parts 3-4）
    private Queue<Visitor> waitingQueue;    // 等待队列（FIFO，Part 3）
    private LinkedList<Visitor> rideHistory; // 骑行历史（Part 4A）

    // 运行周期属性（Part 5）
    private int maxRider;       // 每周期最多乘客数
    private int numOfCycles;    // 已运行周期数（默认0）

    // 默认构造器：初始化集合和默认值
    public Ride() {
        this.waitingQueue = new LinkedList<>(); // Queue用LinkedList实现
        this.rideHistory = new LinkedList<>();
        this.numOfCycles = 0;
    }

    // 带参数构造器：初始化核心属性
    public Ride(String name, String type, Employee operator, int maxRider) {
        this(); // 调用默认构造器初始化集合
        this.name = name;
        this.type = type;
        this.operator = operator;
        this.maxRider = maxRider;
    }

    // Getter和Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Employee getOperator() { return operator; }
    public void setOperator(Employee operator) { this.operator = operator; }
    public int getMaxRider() { return maxRider; }
    public void setMaxRider(int maxRider) { this.maxRider = maxRider; }
    public int getNumOfCycles() { return numOfCycles; }

    // ==================== Part 3：等待队列实现 ====================
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor != null) {
            waitingQueue.offer(visitor); // Queue的FIFO添加方法
            System.out.println("[SUCCESS] 访客 " + visitor.getVisitorId() + " 已加入 " + name + " 等待队列");
        } else {
            System.out.println("[FAIL] 无法添加访客：访客对象为空");
        }
    }

    @Override
    public void removeVisitorFromQueue() {
        Visitor removed = waitingQueue.poll(); // Queue的FIFO移除方法
        if (removed != null) {
            System.out.println("[SUCCESS] 访客 " + removed.getVisitorId() + " 已离开 " + name + " 等待队列");
        } else {
            System.out.println("[FAIL] 无法移除访客：" + name + " 等待队列为空");
        }
    }

    @Override
    public void printQueue() {
        System.out.println("\n[" + name + "] 等待队列（当前人数：" + waitingQueue.size() + "）：");
        if (waitingQueue.isEmpty()) {
            System.out.println("  队列无访客");
            return;
        }
        int index = 1;
        for (Visitor visitor : waitingQueue) {
            System.out.println("  " + index + ". " + visitor);
            index++;
        }
    }

    // ==================== Part 4A：骑行历史实现 ====================
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (visitor != null) {
            rideHistory.add(visitor);
            System.out.println("[SUCCESS] 访客 " + visitor.getVisitorId() + " 已添加到 " + name + " 骑行历史");
        } else {
            System.out.println("[FAIL] 无法添加到历史：访客对象为空");
        }
    }

    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        if (visitor == null || visitor.getVisitorId() == null) {
            System.out.println("[FAIL] 检查失败：访客信息无效");
            return false;
        }
        for (Visitor v : rideHistory) {
            if (v.getVisitorId().equals(visitor.getVisitorId())) {
                System.out.println("[SUCCESS] 访客 " + visitor.getVisitorId() + " 存在于 " + name + " 骑行历史中");
                return true;
            }
        }
        System.out.println("[FAIL] 访客 " + visitor.getVisitorId() + " 不存在于 " + name + " 骑行历史中");
        return false;
    }

    @Override
    public int numberOfVisitors() {
        int count = rideHistory.size();
        System.out.println("[" + name + "] 骑行历史总访客数：" + count);
        return count;
    }

    @Override
    public void printRideHistory() {
        System.out.println("\n[" + name + "] 骑行历史（当前人数：" + rideHistory.size() + "）：");
        if (rideHistory.isEmpty()) {
            System.out.println("  历史无访客");
            return;
        }
        // 必须使用Iterator遍历（Part 4A要求，否则无分）
        Iterator<Visitor> iterator = rideHistory.iterator();
        int index = 1;
        while (iterator.hasNext()) {
            Visitor visitor = iterator.next();
            System.out.println("  " + index + ". " + visitor);
            index++;
        }
    }

    // ==================== Part 4B：排序骑行历史 ====================
    /**
     * 用自定义Comparator排序骑行历史
     * @param comparator 排序规则（实现Comparator接口）
     */
    public void sortRideHistory(Comparator<Visitor> comparator) {
        if (comparator == null) {
            System.out.println("[FAIL] 排序失败：比较器为空");
            return;
        }
        Collections.sort(rideHistory, comparator);
        System.out.println("[SUCCESS] " + name + " 骑行历史已排序");
    }

    // ==================== Part 5：运行一个周期 ====================
    @Override
    public void runOneCycle() {
        System.out.println("\n[RUN CYCLE] 尝试运行 " + name + " 周期 " + (numOfCycles + 1) + "...");

        // 检查1：是否有操作员
        if (operator == null) {
            System.out.println("[FAIL] 运行失败：" + name + " 未分配操作员");
            return;
        }

        // 检查2：是否有等待访客
        if (waitingQueue.isEmpty()) {
            System.out.println("[FAIL] 运行失败：" + name + " 无等待访客");
            return;
        }

        // 计算本次可乘坐人数（不超过maxRider和队列长度）
        int ridersThisCycle = Math.min(maxRider, waitingQueue.size());
        System.out.println("[INFO] 本次周期将搭载 " + ridersThisCycle + " 名访客（最多支持 " + maxRider + " 人）");

        // 从队列移除访客，添加到历史
        for (int i = 0; i < ridersThisCycle; i++) {
            Visitor visitor = waitingQueue.poll();
            if (visitor != null) {
                addVisitorToHistory(visitor);
            }
        }

        // 周期数+1
        numOfCycles++;
        System.out.println("[SUCCESS] " + name + " 周期 " + numOfCycles + " 运行完成");
    }

    // ==================== Part 6：导出骑行历史到CSV ====================
    /**
     * 导出骑行历史到CSV文件（每行存储一个访客信息）
     * @param filePath 文件路径（如：rideHistory.csv）
     */
    public void exportRideHistory(String filePath) {
        System.out.println("\n[EXPORT] 导出 " + name + " 骑行历史到 " + filePath + "...");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // 写入表头（可选，方便阅读）
            writer.write("name,age,id,visitorId,ticketType");
            writer.newLine();

            // 写入访客数据（逗号分隔）
            for (Visitor visitor : rideHistory) {
                String line = String.join(",",
                        visitor.getName(),
                        String.valueOf(visitor.getAge()),
                        visitor.getId(),
                        visitor.getVisitorId(),
                        visitor.getTicketType()
                );
                writer.write(line);
                writer.newLine();
            }
            System.out.println("[SUCCESS] 导出完成：" + filePath);
        } catch (IOException e) {
            System.out.println("[FAIL] 导出失败：" + e.getMessage());
        }
    }

    // ==================== Part 7：从CSV导入骑行历史 ====================
    /**
     * 从CSV文件导入骑行历史（恢复访客数据）
     * @param filePath 文件路径（需与导出路径一致）
     */
    public void importRideHistory(String filePath) {
        System.out.println("\n[IMPORT] 从 " + filePath + " 导入骑行历史到 " + name + "...");
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean skipHeader = true; // 跳过表头行

            while ((line = reader.readLine()) != null) {
                // 跳过表头
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // 分割CSV数据
                String[] parts = line.split(",");
                if (parts.length != 5) {
                    System.out.println("[WARN] 跳过无效行：" + line);
                    continue;
                }

                // 解析数据（处理年龄转换异常）
                String name = parts[0];
                int age;
                try {
                    age = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    System.out.println("[WARN] 跳过年龄无效行：" + line);
                    continue;
                }
                String id = parts[2];
                String visitorId = parts[3];
                String ticketType = parts[4];

                // 创建访客并添加到历史
                Visitor visitor = new Visitor(name, age, id, visitorId, ticketType);
                rideHistory.add(visitor);
                System.out.println("[INFO] 导入访客：" + visitor.getVisitorId());
            }
            System.out.println("[SUCCESS] 导入完成：共导入 " + rideHistory.size() + " 名访客");
        } catch (FileNotFoundException e) {
            System.out.println("[FAIL] 导入失败：文件未找到 - " + e.getMessage());
        } catch (IOException e) {
            System.out.println("[FAIL] 导入失败：" + e.getMessage());
        }
    }
}


