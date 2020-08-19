package nowcode.extractUniqNum;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); //规则数
        int M = sc.nextInt(); //需要过滤的IP数量
        List<String> rules = new ArrayList<>(); //过滤规则
        List<String> ips = new ArrayList<>(); //需要过滤的IP

        for (int i = 0; i< N; i++){
            String r = sc.next();
            rules.add(r);
        }

        for (int i = 0; i<M; i++){
            String ip = sc.next();
            ips.add(ip);
        }

        for (String ip: ips){
            if (canBeFilter(rules, ip)){
                System.out.print(1 + " ");
            } else {
                System.out.print(0 + " ");
            }
        }
    }

    private static boolean canBeFilter(List<String> rules, String ip){
        boolean ans = false;

        for (String r:rules){
            String[] arrR = r.split("\\.");
            String[] arrIp = ip.split("\\.");
            //IP直接与某条规则一样
            if (r.equals(ip)){
                ans = true;
                break;
            }
            if (arrIp.length == arrR.length && ! arrR[0].equals("*") && !arrR[arrR.length-1].equals("*")){
                continue; //切分后长度相等，但是规则中没有*号，不匹配
            }
            if (match(arrR,arrIp)){
                ans = true;
                break;
            }
        }

        return ans;
    }

    private static boolean match(String[] p, String[] t){
        int n = t.length;
        int pIdx = 0;
        int tIdx = 0;
        boolean flag = true;
        while (tIdx < n){
            if (pIdx == 0){
                if (p[pIdx].equals(t[tIdx])){
                    tIdx++;
                    pIdx++;
                    continue;
                }
                if (p[pIdx].equals("*")){
                    if (n - tIdx-1==p.length-1){
                        pIdx++;
                    }
                    tIdx ++;
                    continue;
                }
                if (!p[pIdx].equals(t[tIdx])){
                    flag = false;
                    break;
                }
            } else if (pIdx < p.length - 1){
                if (!p[pIdx].equals(t[tIdx])){
                    flag = false;
                    break;
                }
                pIdx++;
                tIdx++;
            } else {
                //pIdx = p.length-1, 若不为*，且与tIdx不相等，false，退出循环
                if ((tIdx == n-1) && !p[pIdx].equals(t[tIdx]) && !p[pIdx].equals("*")){
                    flag = false;
                    break;
                }
                if (p[pIdx].equals("*") || (tIdx == n-1 && p[pIdx].equals(t[tIdx]))){
                    flag = true;
                    break;
                }
                tIdx ++;
            }
        }

        return flag;
    }
}
