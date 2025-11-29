/**
 * 员工类：继承Person，表示游乐设施操作员
 * 新增2个属性：员工编号、职位
 */
public class Employee extends Person {
    private String employeeId;  // 员工编号（唯一标识）
    private String position;    // 职位（如：Ride Operator）

    // 默认构造器
    public Employee() {}

    // 带参数构造器（先初始化父类属性，再初始化子类属性）
    public Employee(String name, int age, String id, String employeeId, String position) {
        super(name, age, id);
        this.employeeId = employeeId;
        this.position = position;
    }

    // Getter和Setter
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    // 重写toString：方便打印员工信息
    @Override
    public String toString() {
        return "Employee{name='" + getName() + "', age=" + getAge() + ", id='" + getId() +
                "', employeeId='" + employeeId + "', position='" + position + "'}";
    }
}
