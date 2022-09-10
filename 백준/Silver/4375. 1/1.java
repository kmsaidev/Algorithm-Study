import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";

        while((input = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(input);
            N = Long.parseLong(st.nextToken());

            long num = 0;
            for(int i = 1; ; i++){
                num = num * 10 + 1;
                num %= N;
                if(num == 0) {
                    System.out.println(i);
                    break;
                }
            }
        }
    }
}
