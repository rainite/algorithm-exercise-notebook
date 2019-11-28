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
## Arrays.sort()
这里面如果要写comparator, 不能传入primitive 的 array! 比如char[], 因为Comparator有<>的, <>里必须放Object!
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
## 常用Regex表达
所有非字母:
```
[^a-zA-Z]
```
`[^` 表示 Match a single character not present in the list below  
`-` 表示一个range  
这个regex表达意思为只要有一个非字母,就返回true, 配合replaceAll function, 可以把所有非字母的元素给替换了. 比如
```
paragraph.replaceAll("[^a-zA-Z]", " ")
```
去除所有空格:
```
\s+
```
Java里`\`要转义表示, 于是就变成了:
```
s.replaceAll("\\s+", "");
```

## Something in bash
怎么在sh环境写一个forloop：
```
List=$($CHILLI_QUERY "$CHILLI_SOCK" list ip "$CLIARG_ip" 2>/dev/null)
# 把list元素放到一个bucket里，可以用$1, $2获取
set -- $List
i=1;
while [ $i -le $# ]; do
    eval echo "$"$i
    i=$((i+1));
done
```

第二种做法：
```
i=0
for var in $List
do
    i=$((i+1))
    if [ $i = 16 ] ; then
        i=$((i-15))
    fi
    case $i in
    1)
        mac=$(runt get network.hotspot."$instance".mac)
        mac="${mac} $var"
        runt set network.hotspot."$instance".mac "$mac"
        ;;
    2)
        ;;
    *)
        ;;
    esac
done
```