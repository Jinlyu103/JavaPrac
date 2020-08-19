package nowcode.huaWeiPrac.randomNumberOfMing;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int n = sc.nextInt();
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            List<Integer> nums = new ArrayList<>();
            for (int i = 0; i<n; i++){
                int tmp = sc.nextInt();
                if (map.containsKey(tmp)){
                    continue;
                }
                map.put(tmp, 1);
                nums.add(tmp);
            }
            Collections.sort(nums);
            //通过List stream函数可以将任意数据类型都转换成String，
            //然后再通过Collectors.joining()方法来将元素通过任意形式拼接，真是一种方便又简单的方式。
            System.out.println(nums.stream().map(Objects::toString).collect(Collectors.joining("\n")));
        }
    }
}
