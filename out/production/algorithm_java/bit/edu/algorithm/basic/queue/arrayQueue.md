## 数组模拟队列

队列的特点是先进先出，即先进入队列的元素，先弹出队列。可以通过数组模拟队列的操作。

**队列的基本操作：**
* 添加数据
* 弹出数据
* 判断是否为空
* 判断队列是否满
* 打印队列头的数据
* 打印队列中的所有数据

### 简单队列
实现一个简单的队列，可以在队列的尾部添加数据，可以取出队列头部的数据。但是取出数据之后，头部的空余空间不能被重新放入数据。当rear到达maxSize之后，不能继续添加数据。

**主要思路:**
设计一个类表示模拟的队列。通过一个数组模拟队列的操作，主要由几下几个域。
* 数组：存放队列数据 `array``
* maxSize：队列的长度
* front：队列的头部
    front会随着数据的输出而改变。这里认为front是头部元素的前一个位置，初始化为 `front = -1`。
* rear：队列的尾部
    rear随着数据的输入而改变。这里认为最后一个数据对应的索引就是rear，初始化为 `rear = -1`。
 
#### 添加数据
1. 判断队列是否已满
2. 如果没满，`rear` 索引 `+1`
3. 将数据加到数组尾部， `array[rear] = n`

#### 取出数据
1. 判断队列是否为空
2. 如果不为空，`front` 索引 `+1`
3. 取出头部数据，`return array[front]`

#### 判断是否为空
`front` 和 `rear` 初始化为-1，rear表示最后一个数据的索引，front表示第一个数据的索引减1，所以只要`rear == fornt`就表示队列中没有数据。

#### 判断队列是否满
队列最后一个数据的索引为 `maxSize - 1`，所以只要 `maxSize - 1 == rear` 就表示最后一个数据已经到达最大队列尾部。

#### 打印队列头的数据
1. 判断队列是否为空
2. 如果不为空，打印 `array[front]` 数据，不需要对front进行操作。

#### 打印队列中的全部数据
1. 判断队列是否为空
2. 从 0 到 rear 遍历数组，依次打印数据

### 环形队列

### Scanner的使用

Scanner可以扫描输入的字符。
```java
Scanner scanner = new Scanner(System.in);
```

可以读取输入的字符
```java
char input = scanner.next().charAt(0);
```

可以将输入的数据解析为整数：
```java
int value = scanner.nextInt();
```