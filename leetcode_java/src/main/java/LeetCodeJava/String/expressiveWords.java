package LeetCodeJava.String;

// https://leetcode.com/problems/expressive-words/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class expressiveWords {

    // V0
    // TODO : fix
//    public int expressiveWords(String s, String[] words) {
//
//        int ans = 0;
//
//        if (words.length == 0){
//            return 0;
//        }
//
//        Map<String, Integer> map = getCountMap(s);
//
//        List<Map<String, Integer>> list = new ArrayList<>();
//        for (String w: words){
//            Map<String, Integer> tmpMap = getCountMap(w);
//            System.out.println("tmpMap = " + tmpMap);
//            boolean canExpress = true;
//            int keyCnt = 0;
//
//            // s = "aaa", words = ["aaaa"]
//
//            for (String k : tmpMap.keySet()){
//                keyCnt += 1;
//                if (!map.containsKey(k)){
//                    canExpress = false;
//                    break;
//                }
//                if (tmpMap.get(k) < map.get(k) && map.get(k) < 3){
//                    canExpress = false;
//                    break;
//                }
//                if (tmpMap.get(k) > map.get(k)){
//                    canExpress = false;
//                    break;
//                }
//
//            }
//            if (canExpress && keyCnt == map.keySet().size()){
//                ans += 1;
//            }
//        }
//
//        System.out.println("map = " + map);
//
//        return ans;
//    }
//
//    private Map<String, Integer> getCountMap(String s){
//        Map<String, Integer> map = new HashMap<>();
//        for (String x : s.split("")){
//            map.put(x, map.getOrDefault(x, 0)+1);
//        }
//        return map;
//    }

    // V1
    // https://leetcode.com/problems/expressive-words/solutions/4729707/java-easy-100-solution-easy-to-understand/
    public int expressiveWords_1(final String s, final String[] words) {
        int count = 0;

        for(final String word : words)
            if(helper(s, word))
                count++;

        return count;
    }

    private boolean helper(final String s, final String word) {
        if(s.length() < word.length())
            return false;

        int i = 0, j = 0;

        while(i < s.length() && j < word.length()) {
            if(s.charAt(i) != word.charAt(j))
                return false;

            final char curr = word.charAt(j);
            int sCount = 0;

            while(i < s.length() && s.charAt(i) == curr) {
                sCount++;
                i++;
            }

            int wordCount = 0;

            while(j < word.length() && word.charAt(j) == curr) {
                wordCount++;
                j++;
            }

            if(sCount - wordCount < 0 || (sCount - wordCount != 0 && sCount < 3))
                return false;
        }

        return i >= s.length() && j >= word.length();
    }

    // V2
    // https://leetcode.com/problems/expressive-words/solutions/1850024/java-solution-using-frequency-lists/
    public int expressiveWords_2(String s, String[] words) {

        int expressive = 0;

        List<List<Integer>> sFreq = countOrder(s);

        for(String w: words) {
            List<List<Integer>> wFreq = countOrder(w);
            if(sFreq.size() != wFreq.size())
                continue;
            else {
                boolean flag = true;
                for(int i = 0 ; i < sFreq.size(); i++) {
                    if(sFreq.get(i).get(0) != wFreq.get(i).get(0)) {
                        flag = false;
                        break;
                    }
                    else if(sFreq.get(i).get(0) == wFreq.get(i).get(0) &&
                            sFreq.get(i).get(1) != wFreq.get(i).get(1)) {
                        if(sFreq.get(i).get(1) > wFreq.get(i).get(1) &&
                                sFreq.get(i).get(1) >= 3)
                            flag = true;
                        else {
                            flag = false;
                            break;
                        }
                    }
                }
                if(flag)
                    expressive++;
            }
        }

        return expressive;
    }

    public List<List<Integer>> countOrder(String str) {

        List<List<Integer>> countList = new ArrayList<>();
        char prev = str.charAt(0);
        int cnt = 1;
        for(int i = 1; i <= str.length(); i++) {
            if(i < str.length() && str.charAt(i) == str.charAt(i - 1))
                cnt++;
            else {
                List<Integer> temp = new ArrayList<>();
                temp.add((int)(prev - 'a'));
                temp.add(cnt);
                countList.add(temp);
                cnt = 1;
                if(i < str.length())
                    prev = str.charAt(i);
                else
                    break;
            }
        }
        return countList;
    }

}
