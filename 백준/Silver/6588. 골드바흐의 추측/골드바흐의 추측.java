import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static boolean[] isPrime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        isPrime = new boolean[1000001];
        Arrays.fill(isPrime, true);

        for(int i = 2; i <= 1000000; i++){
            if(isPrime[i])
                for(int j = i + i; j <= 1000000; j += i)
                    isPrime[j] = false;
        }

        int input = Integer.parseInt(st.nextToken());
        while(input != 0){
            int min = 2;
            int max = input;

            while(true){
                while(!isPrime[min])
                    min++;
                while(!isPrime[max])
                    max--;
                if(min > max) {
                    System.out.println("Goldbach's conjecture is wrong.");
                    break;
                }
                if(input == min + max){
                    System.out.println(input + " = " + min + " + " + max);
                    break;
                }
                else if(input < min + max) max--;
                else min++;
            }
            st = new StringTokenizer(br.readLine());
            input = Integer.parseInt(st.nextToken());
        }
    }
}
