package com.lordbao.llbt;


/**
 * @Author Lord_Bao
 * @Date 2024/11/1 15:05
 * @Version 1.0
 */
public class MyTreeMapTest {
    public static void test2(){
        MyTreeMap<Integer, String> treeMap = new MyTreeMap<>();

        // 插入一些键值对
        treeMap.add(5, "Value5");
        treeMap.add(3, "Value3");
        treeMap.add(7, "Value7");
        treeMap.add(1, "Value1");
        treeMap.add(9, "Value9");
        treeMap.add(2, "Value2");
        treeMap.add(8, "Value8");

        // 测试是否包含某个键
        System.out.println("Contains 3: " + treeMap.contains(3)); // 预期输出：true
        System.out.println("Contains 10: " + treeMap.contains(10)); // 预期输出：false

        // 测试获取值
        System.out.println("Get value of key 5: " + treeMap.get(5)); // 预期输出：Value5
        System.out.println("Get value of key 8: " + treeMap.get(8)); // 预期输出：Value8
        System.out.println("Get value of key 10: " + treeMap.get(10)); // 预期输出：null

        // 遍历树，检查是否正确插入并按顺序输出
        System.out.println("Tree traversal:");
        treeMap.traverse();

        // 添加更多数据并验证红黑树的性质
        System.out.println("\nAdding more keys to test balancing:");
        treeMap.add(4, "Value4");
        treeMap.add(6, "Value6");

        // 再次遍历树以验证平衡
        System.out.println("Tree traversal after adding more keys:");
        treeMap.traverse();
        System.out.println(treeMap.getKeys());
        System.out.println(treeMap.getVals());
    }

    public static void  test1(){
        MyTreeMap<Integer,Character> map = new MyTreeMap<>();
        for(int i=0;i<26;i++){
            map.add(i, (char) ('a'+i));
        }
        map.traverse();
        System.out.println(map.contains(-1));
        System.out.println(map.contains(0));
        System.out.println(map.contains(26));
        if(map.contains(10)){
            System.out.println(map.get(10));
        }

        System.out.println(map.getKeys());
        System.out.println(map.getVals());
    }
    public static void main(String[] args) {
       test1();
//       test2();
    }


}
