package findLadders;

import java.util.*;

public class Solution {
    private List<List<String>> ans = new ArrayList<>();
    private static final int INF = 1<<20;
    int minLen = INF;

    public List<List<String>> findLadders1(String beginWord, String endWord, List<String> wordList){
        //回溯法：超时
        List<String> mid = new ArrayList<String>(); //存放中间转换过程
        if (!wordList.contains(endWord)){ //wordList不包含endWord，直接返回空list
            return ans;
        }
        mid.add(beginWord);
        backtrack(mid, beginWord, endWord, wordList);
        return ans;
    }

    private void backtrack(List<String> mid, String beginWord, String endWord, List<String> wordList){
        if (isValid(beginWord,endWord)){//当beginWord==endWord时，表示已经完成了转换
            mid.add(endWord);
            if (mid.size() < minLen){
                ans.clear();
            }
            if(mid.size() > minLen){
                mid.remove(endWord);
                return;
            }
            List<String> tmp = new ArrayList<String>();
            for (String s: mid) {
                tmp.add(s);
            }
            ans.add(tmp);
            minLen = Math.min(minLen, mid.size());
            mid.remove(endWord);
            return;
        }

        for (int i = 0; i < wordList.size() ; i++) {
            if (mid.contains(wordList.get(i)) || !isValid(beginWord, wordList.get(i)) || endWord.equals(wordList.get(i))){
                continue;
            }
            String tmp = beginWord;
            beginWord = wordList.get(i);
            mid.add(beginWord);
            backtrack(mid, beginWord, endWord, wordList);
            mid.remove(beginWord);
            beginWord = tmp;
        }
    }

    private boolean isValid(String w1, String w2){
        //判断w1和w2是否只相差一个字母
        int n = w1.length();
        int dif = 0;
        for (int i = 0; i < n ; i++) {
            if (w1.charAt(i) != w2.charAt(i)){
                dif ++;
            }
            if (dif > 1){
                break;
            }
        }
        if (dif == 1){
            return true;
        }
        return false;
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList){
        /**
         *最短转换序列，优先考虑BFS，需要先构建图
         * 1、构建图：
         *      将每一个单词抽象为一个点，如果两个单词之间可以通过改变一个字母进行转换，
         *      那么二者之间有一条无向边
         *      因此，需要把满足转换条件的点相连，就可以形成图，
         *      图的顶点为：WordList ∪ {beginWord}
         *      这个过程时间复杂度O(n^2)
         * 2、以beginWord为图的起点，以endWord为终点进行BFS
         */
        //建立单词到Id的映射关系
        Map<String, Integer> wordId = new HashMap<String, Integer>();
        //建立Id到单词的映射
        List<String> idWord = new ArrayList<String>();
        int id = 0;
        //将wordlist中所有单词加入wordId中
        for (String word: wordList) {
            if (!wordId.containsKey(word)){
                wordId.put(word, id++);
                idWord.add(word);
            }
        }
        if (!wordId.containsKey(endWord)){
            return new ArrayList<>();
        }
        //将beginword也加入wordId中,并为每一个单词分配一个Id
        if (!wordId.containsKey(beginWord)){
            wordId.put(beginWord,id++);
            idWord.add(beginWord);
        }
        //创建一个存图的边用的数组，并初始化
        ArrayList<Integer>[] edges = new ArrayList[idWord.size()];
        for (int i = 0; i < idWord.size() ; i++) {
            edges[i] = new ArrayList<>();
        }
        //添加边
        for (int i = 0; i < idWord.size() ; i++) {
            for (int j = i+1; j < idWord.size() ; j++) {
                //若两者只相差一个字母，则有边
                if (isValid(idWord.get(i), idWord.get(j))){
                    edges[i].add(j);
                    edges[j].add(i);
                }
            }
        }
        int dest = wordId.get(endWord); //获取endWord的Id,目的Id
        int[] cost = new int[id]; //beginWord到每个点的代价
        for (int i = 0; i < id ; i++) {
            cost[i] = INF; //每个点的代价初始化为INF
        }

        //将起点beginWord加入队列，并将其cost设为0
        Queue<ArrayList<Integer>> q = new LinkedList<>();
        ArrayList<Integer> tmpBegin = new ArrayList<>();
        tmpBegin.add(wordId.get(beginWord));
        q.add(tmpBegin);
        cost[wordId.get(beginWord)] = 0;

        //开始广度优先搜索
        while (!q.isEmpty()){
            ArrayList<Integer> now = q.poll();
            int last = now.get(now.size()-1);
            if (last == dest){//若该点为终点，将其存入ans中
                ArrayList<String> tmp = new ArrayList<>();
                for (int index:now){
                    tmp.add(idWord.get(index)); //转换为对应的Word
                }
                ans.add(tmp);
            } else { //该点不为终点，继续搜索
                for (int i = 0; i < edges[last].size(); i++){
                    int to = edges[last].get(i);
                    if (cost[last] + 1 <= cost[to]){
                        cost[to] = cost[last] + 1;
                        //把to加入路径中
                        ArrayList<Integer> tmp = new ArrayList<>(now);
                        tmp.add(to);
                        q.add(tmp);
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<String>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");
        System.out.println(sol.findLadders(beginWord,endWord,wordList));
    }
}
