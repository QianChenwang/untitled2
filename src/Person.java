/**
 * 抽象类：表示人（员工和访客的父类，不可实例化）
 * 包含3个核心属性：姓名、年龄、身份证号
 */
public abstract class Person {
    private String name;    // 姓名
    private int age;        // 年龄
    private String id;      // 身份证号

    // 默认构造器
    public Person() {}

    // 带参数构造器（初始化所有父类属性）
    public Person(String name, int age, String id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    // Getter和Setter（封装属性）
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
}
