package Collections.Utility;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.Set;

/**
 * create by chenjiayang on 2018/9/16
 */
public class SetsTest {

    public static void main(String[] args) {
        Set<Character> first = ImmutableSet.of('a', 'b', 'c');
        Set<Character> second = ImmutableSet.of('b', 'c', 'e');

        /**
         * a union operation over Sets
         * 集合的交集
         */
        Set<Character> union = Sets.union(first, second);
        System.out.println(Joiner.on(",").join(union));
    }
}
