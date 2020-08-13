## 华为研发工程师编程题（2019）

https://www.nowcoder.com/question/next?pid=1088888&qid=36827&tid=35855958

### 第一题：汽水瓶
有这样一道智力题：“某商店规定：三个空汽水瓶可以换一瓶汽水。小张手上有十个空汽水瓶，她最多可以换多少瓶汽水喝？”答案是5瓶，方法如下：先用9个空瓶子换3瓶汽水，喝掉3瓶满的，喝完以后4个空瓶子，用3个再换一瓶，喝掉这瓶满的，这时候剩2个空瓶子。然后你让老板先借给你一瓶汽水，喝掉这瓶满的，喝完以后用3个空瓶子换一瓶满的还给老板。如果小张手上有n个空汽水瓶，最多可以换多少瓶汽水喝？

输入描述:
输入文件最多包含10组测试数据，每个数据占一行，仅包含一个正整数n（1<=n<=100），表示小张手上的空汽水瓶数。n=0表示输入结束，你的程序不应当处理这一行。


输出描述:
对于每组测试数据，输出一行，表示最多可以喝的汽水瓶数。如果一瓶也喝不到，输出0。


输入例子1:
3
10
81
0

输出例子1:
1
5
40

### 第二题：明明的随机数

明明想在学校中请一些同学一起做一项问卷调查，为了实验的客观性，他先用计算机生成了N个1到1000之间的随机整数（N≤1000），对于其中重复的数字，只保留一个，把其余相同的数去掉，不同的数对应着不同的学生的学号。然后再把这些数从小到大排序，按照排好的顺序去找同学做调查。请你协助明明完成“去重”与“排序”的工作(同一个测试用例里可能会有多组数据，希望大家能正确处理)。



Input Param

n               输入随机数的个数

inputArray      n个随机整数组成的数组


Return Value

OutputArray    输出处理后的随机整数



注：测试用例保证输入参数的正确性，答题者无需验证。测试用例不止一组。

样例输入解释：
样例有两组测试
第一组是3个数字，分别是：2，2，1。
第二组是11个数字，分别是：10，20，40，32，67，40，20，89，300，400，15。


输入描述:
输入多行，先输入随机整数的个数，再输入相应个数的整数


输出描述:
返回多行，处理后的结果


输入例子1:
3
2
2
1
11
10
20
40
32
67
40
20
89
300
400
15

输出例子1:
1
2
10
15
20
32
40
67
89
300
400

### 第三题：进制转换

写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。（多组同时输入 ）


输入描述:
输入一个十六进制的数值字符串。


输出描述:
输出该数值的十进制字符串。


输入例子1:
0xA

输出例子1:
10