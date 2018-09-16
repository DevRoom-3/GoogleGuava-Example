package Collections.Utility;

import com.google.common.base.Joiner;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.List;

public class ListsTest {

    public static void main(String[] args) throws Exception {

        /**
         * create a new list
         * 创建一个新列表
         */
        List<String> names = Lists.newArrayList("Java", "Python", "Golang");
        System.out.println(Joiner.on(",").join(names));

        /**
         * reverse a list
         * 反转一个列表
         */
        List<String> reversed = Lists.reverse(names);
        System.out.println(Joiner.on(",").join(reversed));

        /**
         * split a list into several lists
         * 将一个 list 分隔为多个 list
         */
        List<List<String>> partition = Lists.partition(names, 2);
        //output=[Java, Python],[Golang]
        System.out.println(Joiner.on(",").join(partition));

        /**
         * remove null item in the list
         * 去除 null 值
         */
        List<String> removeNullNames = Lists.newArrayList("John", null, "Adam", null, "Jane");
        Iterables.removeIf(removeNullNames, Predicates.isNull());
        System.out.println(removeNullNames);
    }
}
