# Intro
Google Guava core library Example and Demos<br>
Google 核心 Java 库的用法即用例整理<br>

| Ⅰ  | Ⅱ | Ⅲ |
| :--------: | :----------: | :-----------: |
| [String](#String)  |  [Preconditions](#Preconditions)|[Ordering](#Ordering) | 

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
可以作为排序比较器的实现
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
