import java.util.Comparator;

/**
 * 访客排序比较器：实现Comparator接口
 * 排序规则：先按年龄升序，年龄相同按票类型字母顺序
 */
public class VisitorAgeTicketComparator implements Comparator<Visitor> {
    @Override
    public int compare(Visitor v1, Visitor v2) {
        // 1. 按年龄升序（核心排序条件）
        int ageCompare = Integer.compare(v1.getAge(), v2.getAge());
        if (ageCompare != 0) {
            return ageCompare;
        }

        // 2. 年龄相同：按票类型字母顺序（次要排序条件）
        return v1.getTicketType().compareToIgnoreCase(v2.getTicketType());
    }
}
