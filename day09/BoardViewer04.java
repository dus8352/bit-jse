package day09;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import day07.Board;
import util.ScannerUtil;

// 배열대신 ArrayList를 사용한
// 게시판 관리 프로그램
public class BoardViewer04 {
    private Scanner scanner;
    private ArrayList<Board> list;
    private int id;

    // 생성자
    public BoardViewer04() {
        scanner = new Scanner(System.in);
        list = new ArrayList<>();
        id = 1;
    }

    // 메뉴 출력을 담당하는 showMenu() 메소드
    public void showMenu() {
        while (true) {
            System.out.println("게시판 관리 프로그램");
            String message = ("1. 새글 작성 2. 글 목록 보기 3. 종료\n>");
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);
            if (userChoice == 1) {
                addBoard();
            } else if (userChoice == 2) {
                printAll();
            } else if (userChoice == 3) {
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }
        }
    }

    // 글 작성에는 addBoard()
    private void addBoard() {
        Board b = new Board();

        b.setId(id++);

        System.out.print("제목: ");
        b.setTitle(ScannerUtil.nextLine(scanner));

        System.out.print("작성자: ");
        b.setTitle(ScannerUtil.nextLine(scanner));

        System.out.print("내용: ");
        b.setTitle(ScannerUtil.nextLine(scanner));

        b.setWrittenDate(Calendar.getInstance());
        b.setUpdatedDate(Calendar.getInstance());

        list.add(b);
    }

    private void printAll() {
        if (list.isEmpty()) {
            System.out.println("아직 작성된 게시글이 없습니다.");
        } else {
            // 향상된 for문, forEach 구조
            // 만약 우리가 배열, 어레이리스트등의 집합의 요소를 하나씩 호출해야하는 경우
            // 굳이 정식적인 for문을 적어주지 않고
            // "너네한테서 하나씩 뽑아서 객체 a라고 부를게!" 라고
            // 해줄수 있다.
            // 이것을 forEach 구조 혹은 향상된 for문 이라고 부른다.
            // 사용방법은
            // for(데이터타입 변수이름 : 리스트)
            // 0 번부터 차례대로 변수이름이란 임시변수로 만들어서 사용하는것과 같다.

            for (int i = list.size() - 1; i >= 0; i--) {
                System.out.printf("%n. %s\n", i + 1, list.get(i).getTitle());
            }

            String message = "상세보기할 글을 선택해주세요 (0은 뒤로가기)\n>";
            int userChoice = ScannerUtil.nextInt(scanner, message, 0, list.size()) - 1;

            if (userChoice != -1) {
                printOne(userChoice);
            }
        }

    }

    private void printOne(int index) {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        Board b = list.get(index);
        System.out.println("------------------------------");
        System.out.println("제목: " + b.getTitle());
        System.out.println("작성자: " + b.getWriter());
        System.out.println("작성날짜: " + sdf.format(b.getWrittenDate().getTime()));
        System.out.println("수정날짜: " + sdf.format(b.getUpdatedDate().getTime()));
        System.out.println("-------------------------------");
        System.out.println(b.getContent());
        System.out.println("---------------------------------");

        String message = "1. 수정 2. 삭제 3. 목록으로\n>";
        int userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);
        if (userChoice == 1) {
            updateBoard(index);
            printOne(index);
        } else if (userChoice == 2) {
            deleteBoard(index);
            printAll();
        } else if (userChoice == 3) {
            printAll();
        }
    }

    private void updateBoard(int index) {
        System.out.print("제목: ");
        list.get(index).setTitle(ScannerUtil.nextLine(scanner));
        System.out.print("내용: ");
        list.get(index).setContent(ScannerUtil.nextLine(scanner));

        list.get(index).setUpdatedDate(Calendar.getInstance());

    }

    private void deleteBoard(int index) {
        System.out.print("정말로 삭제하시겠습니까? y/n ");
        String agree = ScannerUtil.nextLine(scanner);

        if (agree.toLowerCase().equals("y")) {
            list.remove(index);

        }
    }
}