package dev;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://javaguide.cn/java/concurrent/java-concurrent-questions-01.html#%E4%BD%95%E4%B8%BA%E7%BA%BF%E7%A8%8B

/** Progress, Thread test */

public class workspace3 {
    public static void main(String[] args) {

//        // 获取 Java 线程管理 MXBean
//        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
//        // 不需要获取同步的 monitor 和 synchronizer 信息，仅获取线程和线程堆栈信息
//        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
//        // 遍历线程信息，仅打印线程 ID 和线程名称信息
//        for (ThreadInfo threadInfo : threadInfos) {
//            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
//        }

        int n1 = 1;
        int n2 = 2;
        int n3 = 0;

        String binary1 = Integer.toBinaryString(n1);
        String binary2 = Integer.toBinaryString(2);
        String binary3 = Integer.toBinaryString(3);

        System.out.println(n1 + " in binary is: " + binary1);
        System.out.println(n2 + " in binary is: " + binary2);
        System.out.println(n3 + " in binary is: " + binary3);

    }

    // LC 125
    public boolean isPalindrome(String s) {

        if (s == null || s.length() == 0){
            return true;
        }
        String s_updated = "";
        StringBuilder sb = new StringBuilder();
        //String[] s_split = s.split(",");
        char[] array = s.toCharArray();
        for (char x : array){
            String x_ = Character.toString(x);
            if (x_ != null && x_ != "" && Character.isAlphabetic(x)){
                sb.append(x_.toLowerCase());
            }
        }

        System.out.println(sb.toString());
        return sb.toString() == sb.reverse().toString();
    }

    // LC 121
    public int maxProfit(int[] prices) {

        if (prices.length == 0){
            return 0;
        }

        int res = 0;
        int min = -1;
        int max = -1;

        for (int i : prices){
            int cur = i; //prices[i];
            System.out.println("cur = " + cur);
            if (min == -1){
                min = cur;
                continue;
            }
            if (min > cur){
                min = cur;
                continue;
            }
            if (max == -1){
                max = cur;
                max = cur;
            }
            if (cur > max){
                max = cur;
            }
            int tmp = max - min;
            System.out.println("max = " + max + " min = " + min + " tmp = " + tmp);
            max = -1;
            res = Math.max(tmp, res);
        }

        return res;
    }

    // LC 371
    public int getSum(int a, int b) {

        String a_1 = Integer.toBinaryString(a);
        String b_1 = Integer.toBinaryString(b);

        if (a == 0 && b == 0){
            return 0;
        }

        if(a == 0 || b == 0){
            if (a == 0){
                return b;
            }
            return a;
        }

        int len = Math.max(a_1.length(), b_1.length());

        if (len > a_1.length()){
            a_1 = multiplyStr(a_1, len - a_1.length());
        }else{
            b_1 = multiplyStr(b_1, len - b_1.length());
        }

        char[] a_str = a_1.toCharArray();
        char[] b_str = b_1.toCharArray();

        int plus = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = a_str.length-1; i >= 0; i--){

            int a_val = Integer.parseInt(String.valueOf(a_str[i]));
            int b_val = Integer.parseInt(String.valueOf(b_str[i]));

            int cur = a_val + b_val + plus;
            plus = 0;
            if (cur >= 2){
                plus = 1;
                cur -= 2;
            }
           sb.append(cur);
        }

        if (plus != 0){
            if (plus == 1){
                sb.append("1");
            }else{
                int gap = plus - 2;
                sb.append(gap);
                sb.append("1");
            }
        }

        String collected = sb.reverse().toString();
        System.out.println("collected = " + collected);
        int res = Integer.parseInt(collected, 2);
        System.out.println("res = " + res);
        return res;
    }

    // https://www.studytonight.com/java-examples/how-to-multiply-string-in-java
    public String multiplyStr(String str, int multiplier){

        if (str == null){
            return str;
        }

        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < multiplier; x++){
            sb.append(0);
        }

        sb.append(str);
        return sb.toString();
    }


}
