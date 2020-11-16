package day02;
// 제어문
// 제어문이란 특정 코드가 실행될지 혹은 반복될지를 결정하는 특수한 코드이다.
// 제어문에는 조건문과 반복문이 있다.
// 조건문: 조건을 체크해서 true가 나오면 특정 코드들을 실행
// 반복문: 조건을 체크해서 true가 나오면 특정 코드들을 반복

// 조건문 - if
// if 조건문은 ()안에 우리가 조건식을 적어주고 
// 해당 조건식이 true가 나오면
// 해당 { }가 실행 된다.

// 조건식: 결과값이 true 혹은 false가 나오는 코드

// 변수의 유효범위(scope)
// 변수의 유효범위란, 해당 변수가 호출 가능한 범위를 뜻한다.
// 특정 변수의 유효범위는 해당 변수가 선언되고 나서부터 그 변수가 선언된 중괄호가 끝날때까지이다.
// 해당 유효범위 안에서는 그 변수와 똑같은 이름의 변수는 선언할 수 없다.
// 유효범위가 끝나면 해당 변수는 사라진걸로 취급되서 호출할 수도 없다.


public class Ex10If {
    public static void main(String[] args) {
        int number = 3;
        if(number < 0) {
            String string1 = "abc";
            System.out.println("number: "+number);
            System.out.println("number는 0 이하입니다.");
            
        }
        
        
        System.out.println("프로그램 종료");
    }
}






















