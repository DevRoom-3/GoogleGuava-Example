import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import java.util.List;

/**
 * create by chenjiayang on 2018/9/14
 */
public class OrderingTest {

    private static final List<Integer> list = Lists.newArrayList(7, 4, 9, 10, 2);

    public static void main(String[] args) throws Exception {

        /**
         * create natural order comparator
         * 创建一个比较器，自然顺序
         */
        Ordering<Integer> naturalOrder = Ordering.natural();
        System.out.println(naturalOrder.sortedCopy(list));

        /**
         * create arbitrary order comparator
         * 创建一个比较器，随机顺序
         */
        Ordering<Object> arbitraryOrder = Ordering.arbitrary();
        System.out.println(arbitraryOrder.sortedCopy(list));

        /**
         * create arbitrary order comparator
         * 创建一个比较器，自定义
         */
        Ordering<Integer> byGreaterOrdering = new Ordering<Integer>() {
            @Override
            public int compare(Integer left, Integer right) {
                return right - left;
            }
        };
        System.out.println(byGreaterOrdering.sortedCopy(list));
    }
}
