# Intro
Google Guava core library Example and Demos<br>
Google 核心 Java 库的用法即用例整理

## String
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
