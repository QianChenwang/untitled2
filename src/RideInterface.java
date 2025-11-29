/**
 * 游乐设施接口：定义游乐设施的核心行为
 * 所有方法需在Ride类中实现
 */
public interface RideInterface {
    // 队列相关方法（Part 3）
    void addVisitorToQueue(Visitor visitor);    // 添加访客到等待队列
    void removeVisitorFromQueue();              // 从队列移除首个访客
    void printQueue();                          // 打印等待队列

    // 骑行历史相关方法（Part 4A）
    void addVisitorToHistory(Visitor visitor);  // 添加访客到骑行历史
    boolean checkVisitorFromHistory(Visitor visitor); // 检查访客是否在历史中
    int numberOfVisitors();                     // 返回历史访客总数
    void printRideHistory();                    // 打印骑行历史（需用Iterator）

    // 运行周期方法（Part 5）
    void runOneCycle();                         // 运行一个游乐设施周期
}
