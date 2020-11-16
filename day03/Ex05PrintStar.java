package day03;
// 별찍기 1 번
import java.util.Scanner;
public class Ex05PrintStar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------");
        System.out.println("별찍기 1번");
        System.out.println("--------");
        System.out.println("출력할 줄 수: ");
        int userNumber = scanner.nextInt();
        for(int i = 1; i <= userNumber; i++) {
            for(int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        scanner.close();
    }
}