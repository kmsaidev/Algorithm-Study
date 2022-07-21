import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, B;
    static String[] words;
    static Node trie;
    static char[][] board;
    static boolean[][] visited;
    static String longWord;
    static StringBuilder sb = new StringBuilder();
    static int cnt, score;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        words = new String[N];
        trie = new Node(false);
        for(int i = 0; i < N; i++) {
            words[i] = br.readLine();
            addWord(trie, words[i]);
        }
        br.readLine();
        st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken());
        for(int i = 0; i < B; i++){
            clearHit(trie);
            init();
            for(int j = 0; j < 4; j++){
                String str = br.readLine();
                for(int k = 0; k < 4; k++)
                    board[j][k] = str.charAt(k);
            }
            br.readLine();
            for(int j = 0; j < 4; j++)
                for(int k = 0; k < 4; k++)
                    if(trie.child[board[j][k] - 'A'] != null)
                        dfs(j, k, trie.child[board[j][k] - 'A']);
            System.out.println(score + " " + longWord + " " + cnt);
        }
    }

    static void addWord(Node move, String word){
        for(int i = 0; i < word.length(); i++){
            int idx = word.charAt(i) - 'A';
            if(move.child[idx] == null) {
                if (i != word.length() - 1)
                    move.child[idx] = new Node(false);
                else
                    move.child[idx] = new Node(true);
            } else{
                if (i == word.length() - 1)
                    move.child[idx].isWord = true;
            }
            move = move.child[idx];
        }
    }

    static void init(){
        board = new char[4][4];
        visited = new boolean[4][4];
        cnt = 0;
        score = 0;
        longWord = "";
        sb.append("");
    }

    static void dfs(int x, int y, Node n){
        int[] move_x = {0, 1, 1, 1, 0, -1, -1, -1};
        int[] move_y = {1, 1, 0, -1, -1, -1, 0, 1};

        visited[x][y] = true;
        sb.append(board[x][y]);
        if(n.isWord && !n.isHit) {
            int len = sb.length();
            if(longWord.length() < len)
                longWord = sb.toString();
            else if(longWord.length() == len){
                longWord = longWord.compareTo(sb.toString()) < 0 ? longWord : sb.toString();
            }
            cnt++;
            switch(len){
                case 3:
                case 4:
                    score += 1;
                    break;
                case 5:
                    score += 2;
                    break;
                case 6:
                    score += 3;
                    break;
                case 7:
                    score += 5;
                    break;
                case 8:
                    score += 11;
                    break;
            }
            n.isHit = true;
        }
        for(int i = 0; i < 8; i++){
            int new_x = x + move_x[i];
            int new_y = y + move_y[i];
            if(0 <= new_x && new_x < 4 && 0 <= new_y && new_y < 4 &&
                    !visited[new_x][new_y] && n.child[board[new_x][new_y] - 'A'] != null){
                dfs(new_x, new_y, n.child[board[new_x][new_y] - 'A']);
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        visited[x][y] = false;
    }

    static void clearHit(Node move){
        if(move.isHit)
            move.isHit = false;
        for(int i = 0; i < move.child.length; i++){
            if(move.child[i] != null)
                clearHit(move.child[i]);
        }
    }
}

class Node{
    Node child[];
    boolean isWord, isHit;

    public Node(boolean isWord){
        child = new Node[26];
        this.isWord = isWord;
        isHit = false;
    }
}