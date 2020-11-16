package day08;
// 우리가 게시판에 글을 쓸때는

// 글 번호가 자동으로 부여된다.
// 또한 우리가 글을 10개를 쓰고 
// 도중에 하나를 지우더라도
// 새글을 쓰면 글 번호는 11부터 다시 부여된다!

import java.util.Scanner;

import day07.Board;
import util.ArrayUtil;
import util.ScannerUtil;

// 데이터베이스가 있을 경우에는 이렇게 글번호/학생번호
// 즉 고유 식별 번호(=id) 를 자동으로 관리해주지만
// 우리는 데이터베이스가 없으므로
// 전역변수를 사용하여
// id를 클래스가 알아서 부여하도록 만들어주자

public class BoardViewer02 {
    private Scanner scanner;
    private Board[] boardArray;
    // 글 번호를 보관할 전역변수 id
    private int id = 1;

    public BoardViewer02() {
        scanner = new Scanner(System.in);
        boardArray = new Board[0];
    }

    public void showMenu() {
        while (true) {
            System.out.println("게시판 관리 프로그램");
            String message = " 1. 새글 쓰기 2. 목록 보기 3. 종료 \n > ";
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);
            if (userChoice == 1) {
                addBoard();
            } else if (userChoice == 2) {
                selectAll();
            } else if (userChoice == 3) {
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }
        }
    }

    private void addBoard() {
        Board b = new Board();

        System.out.print("제목: ");
        b.setTitle(ScannerUtil.nextLine(scanner));

        b.setId(id++);

        System.out.print("작성자: ");
        b.setWriter(ScannerUtil.nextLine(scanner));

        System.out.print("내용");
        b.setContent(ScannerUtil.nextLine(scanner));

        boardArray = ArrayUtil.add(boardArray, b);
    }

    private void selectAll() {
        if (boardArray.length == 0) {
            System.out.println("아직 입력된 게시글이 없습니다.");
        } else {
            for (int i = boardArray.length - 1; i >= 0; i--) {
                System.out.printf("%d. %s\n", i + 1, boardArray[i].getTitle());
            }
            String message = "상세보기할 글 번호를 선택해주세요(0은 뒤로가기) \n >";
            int userChoice = ScannerUtil.nextInt(scanner, message, 0, boardArray.length) - 1;
            if (userChoice != -1) {
                selectOne(userChoice);
            }
        }
    }

    private void selectOne(int index) {
        Board b = boardArray[index];
        System.out.println("제목: " + b.getTitle());
        System.out.println("글번호: " + b.getId());
        System.out.println("작성자: " + b.getWriter());
        System.out.println("----------------------");
        System.out.println(b.getContent());

        String message = "1. 수정 2. 삭제 3. 뒤로가기 \n>";
        int userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);
        if (userChoice == 1) {
            updateBoard(index);
            selectOne(index);
        } else if (userChoice == 2) {
            deleteBoard(index);
            selectAll();
        } else if (userChoice == 3) {
            selectAll();
        }
    }

    private void updateBoard(int index) {
        System.out.print("제목: ");
        boardArray[index].setTitle(ScannerUtil.nextLine(scanner));

        System.out.print("내용: ");
        boardArray[index].setContent(ScannerUtil.nextLine(scanner));
    }

    private void deleteBoard(int index) {
        System.out.print("정말로 삭제하시겠습니까? y/n");
        String agree = ScannerUtil.nextLine(scanner);
        if (agree.equals("y")) {
            boardArray = ArrayUtil.removeByIndex(boardArray, index);
        }
    }
}
