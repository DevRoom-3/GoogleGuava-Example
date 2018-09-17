![](https://img.shields.io/badge/Java-1.8.0-green.svg)
![](https://img.shields.io/badge/Guava-22.0-brightgreen.svg)<br>

# Intro
Google Guava core library Example and Demos<br>
Google 核心 Java 库的用法即用例整理，只介绍日常编码中最常用的方式方法，对于完整的教程请参见 [Guava Wiki](https://github.com/google/guava/wiki) <br>

| I | II | III | IV | V | VI| VII |
| :--------: | :----------: | :-----------: | :-----------: | :-----------: | :-----------: | :-----------: | 
| [String](##String)  |  [Preconditions](##Preconditions)|[Ordering](##Ordering) | [Lists](##Lists) | [Iterables](##Iterables) | [ListenableFuture](##ListenableFuture) | [Cache](##Cache) |

## String
String 相关的用法有 Joiner 将元素拼接成字符串，Splitter 按照某样规则分割字符串
```java
public class StringTest {


    private static final String str = "Java,Golang,Python,Scala";
    private static final String str1 = "Java Golang Python Scala";

    public static void main(String[] args) throws Exception {

        /**
         * Join item together to String
         * 将元素合并成一个 String
         */
        Joiner joiner = Joiner.on(",").skipNulls();
        String formate = joiner.join(null, "Java", "Golang", "Python");
        // output=Java,Golang,Python
        System.out.println(formate);

        /**
         * trans List to Str
         * 同样可以将 List 拼成一个 String
         */
        List<Integer> numbers = Lists.newArrayList(1,2,3);
        formate = Joiner.on(",").join(numbers);
        // output=1,2,3
        System.out.println(formate);


        /**
         * trans String to List
         * 将 String 转换为 List，逗号分隔
         */
        List<String> str2List = Splitter.on(",").trimResults().splitToList(str);
        System.out.println(str2List.toString());

        /**
         * trans String to List
         * 将 String 转换为 List，空格分隔
         */
        List<String> str2List1 = Splitter.on(CharMatcher.whitespace()).trimResults().splitToList(str1);
        System.out.println(str2List1.toString());
    }
}
```

## Preconditions
一种前置断言，用来检测参数
```java
public class PreconditionsTest {

    private static final Logger logger = Logger.getLogger(Preconditions.class.getName());

    public static void main(String[] args) throws Exception {
        int i = 10;
        try {
            Preconditions.checkArgument(i < 10, "i is greater than 10");
            Preconditions.checkArgument(i < 10, "i is greater than 10, i is %s", i);
        } catch (IllegalArgumentException e) {
            logger.info("IllegalArgumentException e=" + e.getMessage());
        }

        String str = null;
        try {
            Preconditions.checkNotNull(str);
        } catch (NullPointerException e) {
            logger.info("NullPointerException e=" + e.getMessage());
        }

        try {
            Preconditions.checkState(str == null);
        } catch (IllegalStateException e) {
            logger.info("IllegalStateException e=" + e.getMessage());
        }
    }
}
```

## Ordering

Ordering 是 Guava 提供的一个比较器工具，内置的静态方法有：
1. `natural()`：例如：整数从小到大，字符串是按字典顺序;
2. `usingToString() `：使用 toString() 返回的字符串按字典顺序进行排序；
3. `arbitrary()`：返回一个所有对象的任意顺序

```java
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
```

## Lists
Guava 为常见的集合类提供了静态创建方法
```java
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
```

## Iterables
```java
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
```
