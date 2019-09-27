# Java8 Lambda和Stream的使用小技巧
## 对lambda的理解
1. 首先，在应对面试写题的立场上，我们只从high level 层面去了解lambda，很多细节以及具体的底层的实现就不去深究了。
2. lambda就是匿名函数的简写形式，但在使用的时候我觉得应该抽象的理解成一个被包装起来的处理过程，我们只需要注意输入什么和输出什么就行。与通常的传入变量相对应，lamnda是传入一个处理的方法, 强调输入与输出的过程
3. Java对lambda的实现是通过 `@FunctionalInterface`, 即我们需要在支持lambda表达式的方法里传入一个 FunctionalInterface 类型。
4. 最常见的类型类型有：
   - `Function<T, R>` 要求传入类型T，返回类型为R (T 可以和 R 同类型)
   - `Predicate<T>` 要求返回 boolean
   - `Consumer<T>` 要求返回 void
   - `Comparator<T>` 要求传入 a,b 两个变量，返回 int
5. 对于满足特定传入和传出的函数，可直接使用 :: 来简化操作，比如
   `System.out::println`就满足是一个Consumer类型的条件，注意这里不能在方法体后面加`()`，我们是要传入整个函数，加了括号等于是call了这个函数，call完整句话就变成println返回的void类型了
6. 有了这些基本的理解后，就可以运用Java8里stream接口直接起飞了

## 哪些数据结构可以使用stream()？
1. stream() 被放到了Collection 中，所以常用的数据类型比如什么List，Set都可以使用
2. 一些常用的primitive type 比如 int，long，double 也可以使用对应的stream方法转成stream类型:IntStream, LongStream, DoubleStream
3. 甚至String提供chars()方法来转换成IntStream

## stream()的处理流程
1. 创建Stream
   - 一个数据源（如集合、数组），获取一个流
2. 中间操作
   - 一个中间操作链，对数据源的数据进行处理
3. 终止操作（终端操作）
   - 一个终止操作，执行中间操作链，并产生结果
  
注意:
1. Stream自己不会存储元素
2. Stream不会改变源对象，相反，它们会返回一个持有结果的新Stream
3. Stream 操作是延迟执行的，这意味着他们会等到需要结果的时候才执行

常见的中间操作:
1. 筛选
   - filter(Predicate) 用于排除元素
   - distinct() 去重(使用equals)
2. 映射
   - map(Function) 传入T，传出R，用于类型转换 比如Employee::getName
3. 排序
   - sorted(Comparator)

常见的终止操作:
1. 查找
   - allMatch(Predicate) stream中所有元素匹配Predicate条件，则返回 true 否则 false
   - anyMatch(Predicate) 有一个满足就 true
   - count() 计数
   - max(Comparator),min(Comparator) 返回流中最大、小值
   - forEach(Consumer) 内部迭代一次
2. 收集，因为stream不改变原始数据，所以最好我们还是希望输出的
   - collect(Collector) 这个collector下面详解
## 起飞
### Primitive -> Object (boxing)
```java
int[] num = {1,2,3};
List<Integer> b = 
    IntStream.of(num)
        .boxed()
        .collect(Collectors.toCollectio(LinkedList::new));
```
1. 创建
   - `IntStream.of()`
2. 中间过程
   - `.boxed()`
3. 终止操作
   - `.collect()`
   - 这里传入`Collectors.toCollection(LinkedList::new)`是提供一个构造方法，lambda表达式LinkedList::new提供容器

### List信息筛选
比如读入一串csv数据，想要获得每个brand的总数
```java
String[] text = new String[4];
text[0] = "ID1,Minneapolis,shoes,2,Air";
text[1] = "ID2,Chicago,shoes,1,Air";
text[2] = "ID3,Central Department Store,shoes,5,BonPied";
text[3] = "ID4,Quail Hollow,forks,3,Pfitzcraft";
List<String> list = Arrays.asList(text);
Map<String,Integer> brand = list.stream()
        .collect(Collectors.groupingBy(word -> word.split(",")[2],Collectors.summingInt(word -> Integer.parseInt(word.split(",")[3])))
        );
```
最后得到map里{"shoes" : 8, "forks" : "forks" : 3}
注意groupingBy这里signature第一项为Function<>，构造出Key，第二项是另一个Collector，供你做任何骚操作，构造出value

### 文件读写
注意文件读写一定要在try catch(IOException e)里
```java
try {
    List<String> file = Files.lines(Paths.get("/example.txt"))
            .collect(Collectors.toCollection(ArrayList::new));
    Files.write(Paths.get("/example2.txt"),file);
} catch (IOException e) {
    
}
```
Files.lines,Files.write 都是接受一个Path interface，返回stream()， 这里Paths实现了Path，可用get方法取得文件
