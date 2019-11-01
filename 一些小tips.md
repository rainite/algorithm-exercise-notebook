# 刷题小tip
## List -> Array
```java
    List<int[]> aa = new ArrayList<>();
    int[][] bb = aa.toArray(new int[][]{});
```
- 这里是调用了`<T> T[] toArray(T[] a);` 这个方法, 如果toArray里不传东西, 则默认返回Object[]
- Java 11 之后, 可以传入构造方法
```java
    int[][] bb = aa.toArray(int[][]::new);
```

## Array -> List
注意list里不能有primitive type. 这里转换需要boxing
```java
int[] num = {1,2,3};
List<Integer> b = 
    IntStream.of(num)
        .boxed()
        .collect(Collectors.toCollectio(LinkedList::new));
```
Object array 可以正常用Arrays.asList()转换

## new 多个线程
```java
    Thread[] threads = new Thread[100];
    for (int i = 0; i < 100; i++) {
        int finalI = i;
        threads[i] = new Thread(() -> System.out.println(finalI));
    }
    Arrays.asList(threads).forEach(a -> a.start());
```