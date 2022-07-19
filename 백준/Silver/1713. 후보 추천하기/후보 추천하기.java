import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<Student> picture;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        picture = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            int stNum = sc.nextInt();
            boolean isFinish = false;
            for(int j = 0; j < picture.size(); j++){
                Student s = picture.get(j);
                if(s.stNum == stNum) {
                    s.likeCount++;
                    isFinish = true;
                    break;
                }
            }
            if(!isFinish){
                if(picture.size() == N) {
                    Collections.sort(picture);
                    picture.remove(0);
                }
                picture.add(new Student(stNum, 1, i));
            }
        }

        Collections.sort(picture, Comparator.comparingInt(Student::getStNum));
        for(int i = 0; i < picture.size(); i++)
            System.out.print(picture.get(i).stNum + " ");
    }
}

class Student implements Comparable<Student>{
    int stNum;
    int likeCount;
    int time;
    public Student(int stNum, int likeCount, int time){
        this.stNum = stNum;
        this.likeCount = likeCount;
        this.time = time;
    }

    @Override
    public int compareTo(Student o) {
        if(this.likeCount < o.likeCount)
            return -1;
        else if(this.likeCount == o.likeCount)
            return this.time - o.time;
        else return 1;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stNum=" + stNum +
                ", likeCount=" + likeCount +
                ", time=" + time +
                '}';
    }

    public int getStNum() {
        return stNum;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public int getTime() {
        return time;
    }
}