/**
 * 访客类：继承Person，表示主题公园访客
 * 新增2个属性：访客编号、票类型
 */
public class Visitor extends Person {
    private String visitorId;  // 访客编号（唯一标识）
    private String ticketType; // 票类型（如：Adult/Child/Young Adult）

    // 默认构造器
    public Visitor() {}

    // 带参数构造器（先初始化父类属性，再初始化子类属性）
    public Visitor(String name, int age, String id, String visitorId, String ticketType) {
        super(name, age, id);
        this.visitorId = visitorId;
        this.ticketType = ticketType;
    }

    // Getter和Setter
    public String getVisitorId() { return visitorId; }
    public void setVisitorId(String visitorId) { this.visitorId = visitorId; }
    public String getTicketType() { return ticketType; }
    public void setTicketType(String ticketType) { this.ticketType = ticketType; }

    // 重写toString：方便打印访客信息
    @Override
    public String toString() {
        return "Visitor{name='" + getName() + "', age=" + getAge() + ", id='" + getId() +
                "', visitorId='" + visitorId + "', ticketType='" + ticketType + "'}";
    }
}
