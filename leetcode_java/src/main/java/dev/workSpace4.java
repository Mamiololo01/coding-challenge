package dev;

import java.util.*;
import java.util.stream.Collectors;

public class workSpace4 {
    int abc = 100;
    public static void main(String[] args) {


//        String x = "leetcode";
//        String y = "code";
//
//        System.out.println(x.contains(y));
//        System.out.println(y.contains(x));

        /**
         *
         * 1) Array -> List
         *
         */
        Integer [] arr1 = new Integer[]{1,2,3};
        List<Integer> list1 = Arrays.asList(arr1);

        System.out.println("arr1 = " + arr1);
        System.out.println("list1 = " + list1);


        /**
         *
         * 2) List -> Array
         *
         */
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        Integer [] arr2 = list2.toArray(new Integer[list2.size()]);

        System.out.println("arr2 = " + arr2);
        System.out.println("list2 = " + list2); // list2 = [1, 2, 3]


        /** Reverse List */
        Collections.reverse(list2);
        System.out.println("list2 = " + list2); // list2 = [3, 2, 1]

        /** Init M x N matrix */
        Boolean[][] matrix = new Boolean[3][4];
        System.out.println(matrix);
        System.out.println(matrix[0][0]);

        // fill
        Boolean[] x = new Boolean[3];
        Arrays.fill(x, false);
        System.out.println(x[0]);

        /** Sort array */
        int[][] intervals = new int[][]{ {15,20}, {0,30}, {5,10} };
        System.out.println("---> intervals");
        for (int[] interval : intervals){
            System.out.println("1st = " + interval[0] + ", 2nd = " + interval[1]);
        }

        Arrays.stream(intervals).sorted();

        // sort (Arrays.sort)
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        System.out.println("---> sorted intervals");
        for (int[] interval : intervals){
            System.out.println("1st = " + interval[0] + ", 2nd = " + interval[1]);
            /**
             * 1st = 0, 2nd = 30
             * 1st = 5, 2nd = 10
             * 1st = 15, 2nd = 20
             *
             */
        }

        // sort (Arrays.stream(intervals).sorted)
//        int[][] sortedIntervals = Arrays.stream(intervals)
//                .sorted((a, b) -> Integer.compare(a[0], b[0]))
//                .toArray(int[][]::new);
//
//        System.out.println("---> sorted intervals V2");
//        for (int[] interval : sortedIntervals){
//            System.out.println("1st = " + sortedIntervals[0] + ", 2nd = " + sortedIntervals[1]);
//        }


        /** Array sort example 1 */
        System.out.println("---> array sort example 1 ");
        Integer[] _array = {5, 5, 7, 8, 9, 0};
        for (int a : _array){
            System.out.println("1st = " + a);
        }

        //Arrays.sort(_array, (a,b) -> Integer.compare(a, b));
        Arrays.sort(_array, (a,b) -> Integer.compare(-a, -b));

        System.out.println("---> array sort example 1 -- after sorted ");
        //Arrays.sort(_array, Collections.reverseOrder());
        for (int a : _array){
            System.out.println("1st = " + a);
        }

        /** List sort example 1 */
        System.out.println("----> List sort example 1");
        List<Integer> _list = new ArrayList();
        _list.add(5);
        _list.add(1);
        _list.add(3);
        _list.add(0);
        for (int a : _list){
            System.out.println("1st = " + a);
        }

        System.out.println("----> List sort example 1 -- after sorted ");
        // sort
        //Collections.sort(_list, (a,b) -> Integer.compare(a, b));
        Collections.sort(_list, (a,b) -> Integer.compare(-a, -b));
        for (int a : _list){
            System.out.println("1st = " + a);
        }


        /** List sort example 2 */

        System.out.println("----> List sort example 2");

        List<Integer> _list2 = new ArrayList<>(Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5));
//        for (int a : _list2){
//            System.out.println("1st = " + a);
//        }
        System.out.println(_list2);

        // Sort using Stream API
        _list2 = _list2.stream()
                .sorted((a, b) -> b - a)  // Using Comparator to sort in reverse order
                .collect(Collectors.toList());

        System.out.println("----> List sort example 2 -- after sorted ");
        System.out.println(_list2);



        System.out.println("----------------");

        String x_ = "leetcode";
        String[] x_arr = x_.split("");
        System.out.println(x_arr[0]);
        System.out.println(x_arr[1]);
        System.out.println(x_arr[0].equals("l"));

        System.out.println("----------------");

        String s = "www.runoob.com";
        char result = s.charAt(6);
        System.out.println(result);
        System.out.println(String.valueOf(result));



        System.out.println(" ---------------- SORT ON HASH MAP ----------------");


        // Create a HashMap
        HashMap<String, Integer> map = new HashMap<>();
        map.put("apple", 5);
        map.put("banana", 2);
        map.put("cherry", 8);
        map.put("date", 1);

        // Print the original map
        System.out.println("Original map: " + map);

        // Sort the map by values
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        // Create a new LinkedHashMap to preserve the order of the sorted entries
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();

//        // V1 : via Entry
//        for (Map.Entry<String, Integer> entry : list) {
//            sortedMap.put(entry.getKey(), entry.getValue());
//        }


        // V2 : via key
        // Get the list of keys
        List<String> keys = new ArrayList<>(map.keySet());

        // Sort the keys based on their corresponding values
        keys.sort((k1, k2) -> map.get(k1).compareTo(map.get(k2)));

        /**
         * You can modify the code to avoid using Map.Entry by converting the
         * HashMap to a list of keys and then sorting the keys based on
         * their corresponding values. Here is the modified version:
         */
        for (String key : keys) {
            sortedMap.put(key, map.get(key));
        }

        // Print the sorted map
        System.out.println("Sorted map: " + sortedMap);


        //--------------

//        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(map.entrySet());
//        sortedEntries.sort(Map.Entry.comparingByValue());
//
//        // Print the sorted entries
//        for (Map.Entry<String, Integer> entry : sortedEntries) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }

        System.out.println("sub string --------------");
        String str1 = "abcd";
        System.out.println(str1);
        System.out.println(str1.substring(0,2));

//        for (int z : new int[]{1,2,3}){
//            System.out.println(z);
//        }


        System.out.println("in string --------------");

        String token1 = "+-*/";
        System.out.println(token1.contains("+"));
        System.out.println(token1.contains("7"));


        System.out.println("array init val --------------");
        Integer[] arr_10 = new Integer[5];
        Arrays.fill(arr_10, 0);
        System.out.println("arr_10 = " + arr_10.toString());
        for (Integer y : arr_10){
            System.out.println(y);
        }


        workSpace4 wp = new workSpace4();
        wp.myFunc();


        System.out.println("isValidP test --------------");
        String mystr = "(())"; //"()))";
        System.out.println(wp.isValidP(mystr));


        System.out.println("matrix for loop testing --------------");
        int l = 4;
        int w = 4;
        for (int i = 0; i < l; i++){
            for (int j = i+1; j < w; j++){
                System.out.println("i = " + i + ", j = " + j);
            }
        }

        System.out.println("mypow --------------");

        System.out.println(wp.myPow(2.0, 10));
        System.out.println(wp.myPow(2.1, 3));
        System.out.println(wp.myPow(2.0, -2));
        System.out.println(wp.myPow(0, 10));
        System.out.println(wp.myPow(1, 10));
        System.out.println(wp.myPow(2.0, -2147483648));

        System.out.println("long test --------------");
        Long l1 = 101L;
        System.out.println("l1 = " + l1);
        System.out.println("l1 - 1L = " + (l1 - 1L));


        // https://github.com/yennanliu/CS_basics/blob/master/doc/faq/java/java_basic.md
        System.out.println("basic, reference type test --------------");
        String a = "aa";
        String a1 = "aa";
        String b = new String("bb");
        String b1 = new String("bb");
        System.out.println(a == a1); // true
        System.out.println(b == b1); // false


        System.out.println("generic test --------------");

        // 创建不同类型数组：Integer, Double 和 Character
        Integer[] intArray = { 1, 2, 3 };
        String[] stringArray = { "Hello", "World" };
        printArray( intArray  );
        printArray( stringArray  );

        System.out.println("compare test --------------");
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(1);
        arrayList.add(3);
        arrayList.add(2);

        System.out.println("arrayList before sort = " + arrayList);
        Collections.sort(arrayList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                //return 0;
                //return o2.compareTo(o1);
                return o1.compareTo(o2);
            }
        });

        System.out.println("arrayList after sort = " + arrayList);


        System.out.println("compareTo test --------------");


        System.out.println("Arrays.asList test --------------");

//        int[] myArray = {1, 2, 3};
//        List myList = Arrays.asList(myArray);
//        System.out.println(myList.size());//1
//        System.out.println(myList.get(0));//数组地址值
//        System.out.println(myList.get(1));//报错：ArrayIndexOutOfBoundsException
//        int[] array = (int[]) myList.get(0);
//        System.out.println(array[0]);//1


//        Integer[] myArray = {1, 2, 3};
//        List myList = Arrays.asList(myArray);
//        System.out.println(myList.size());//1
//        System.out.println(myList.get(0));//数组地址值
//        System.out.println(myList.get(1));//报错：ArrayIndexOutOfBoundsException
//        Integer [] array = (Integer[]) myList.get(0);
//        System.out.println(array[0]);//1
    }

    public class Person implements Comparable<Person>{

        private String name;
        private int age;

        public int getAge(){
            return age;
        }

        @Override
        public int compareTo(Person o) {
            if (this.age > o.getAge()){
                return 1;
            }
            if (this.age < o.getAge()){
                return -1;
            }
            return 0;
        }
    }

    public boolean isValidP(String input){
        System.out.println("---> input = " + input);
        if (input.length() == 0){
            return true;
        }
        // leftCnt : "(" count
        // rightCnt : ") count
        int leftCnt = 0;
        int rightCnt = 0;
        for (String x : input.split("")){
            if (x.equals("(")){
                leftCnt += 1;
            }else{
                rightCnt += 1;
            }
            if (rightCnt > leftCnt){
                return false;
            }
        }
        return true;
    }

    private void myFunc(){

        System.out.println("abc = " + abc);
    }

    public double myPow(double x, int n) {

        if (x == 1.0 || x == 0.0){
            return x;
        }

        if (n == 0){
            return 1;
        }

        boolean negative = false;
        if (n < 0){
            negative = true;
            n = n * -1;
        }
        Long n_ = new Long(n);
        double res = 1.0;
        while (n_ > 0){
            res = res * x;
            n_ -= 1;
        }

        System.out.println("res = " + res);
        if (negative){
            return 1 / res;
        }

        return res;
    }

    // https://javaguide.cn/java/basis/java-basic-questions-03.html#%E6%B3%9B%E5%9E%8B%E7%9A%84%E4%BD%BF%E7%94%A8%E6%96%B9%E5%BC%8F%E6%9C%89%E5%93%AA%E5%87%A0%E7%A7%8D

    public interface Generator<T> {
        public T method();
    }


    public static < E > void printArray( E[] inputArray )
    {
        for ( E element : inputArray ){
            System.out.printf( "%s ", element );
        }
        System.out.println();
    }


}
