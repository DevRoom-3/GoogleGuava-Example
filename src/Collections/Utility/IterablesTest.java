package Collections.Utility;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;

/**
 * create by chenjiayang on 2018/9/17
 */
public class IterablesTest {

    public static void main(String[] args) {
        /**
         * filter elements by type
         * 按照类型筛选元素
         */
        List<Object> randomObjects = Lists.newArrayList(15, 12d, "hello", Lists.newArrayList(),
                Maps.newConcurrentMap(), "world");
        Iterable<String> result = Iterables.filter(randomObjects, String.class);
        System.out.println(result);

        /**
         * combine two iterables
         * 合并两个list，不去重
         */
        List<String> list1 = Lists.newArrayList("one", "two");
        List<String> list2 = Lists.newArrayList("two");
        Iterable<String> oneAndTwo = Iterables.concat(list1, list2);
        System.out.println(oneAndTwo);

        /**
         * find first specific number
         * 寻找满足条件的第一个值
         */
        List <Integer> numbers = Lists.newArrayList(1, 2, 3);
        Integer value = Iterables.find(numbers, new Predicate<Integer> () {
            public boolean apply(Integer number) {
                return number == 3 ;
            }
        });
        System.out.println(value);

        /**
         * find first not null
         * 寻找第一个非 null 值
         */
        List<String> strings = Lists.newArrayList("java", null, "python");
        String firstNonNull = Iterables.find(strings, Predicates.notNull());
        System.out.println(firstNonNull);


        /**
         * get first element
         * 拿出第一个值
         */
        List<String> strings2 = Lists.newArrayList(null, "one", "two", "three");
        String firstElement = Iterables.getFirst(strings, null);
        String lastElement = Iterables.getLast(strings, null);
        System.out.println(firstElement);
        System.out.println(lastElement);

        /**
         * transform elements
         */
        List<String> numbersAsStrings = Lists.newArrayList("1", "2", "3");

        Iterable<Double> doubles = Iterables.transform(numbersAsStrings, new Function<String, Double>() {
            @Override
            public Double apply(String input) {
                return new Double(input);
            }
        });
        System.out.println(doubles);
    }
}
