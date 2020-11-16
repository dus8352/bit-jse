package day08;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import day07.Board;
import util.ArrayUtil;
import util.ScannerUtil;

// 글을 작성할 거나 수정할때
// 날짜가 자동으로
// 입력되는 버젼

public class BoardViewer03 {
    private Scanner scanner;
    private Board[] boardArray;
    // 글 번호를 보관할 전역변수 id
    private int id = 1;

    public BoardViewer03() {
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
        
        //글 작성일과 수정일을 저장한다.
        b.setWrittenDate(Calendar.getInstance());
        b.setUpdatedDate(Calendar.getInstance());

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
        SimpleDateFormat sdf = new SimpleDateFormat("yy년 M월 d일 H시m분s초");
        System.out.println("제목: " + b.getTitle());
        System.out.println("글번호: " + b.getId());
        System.out.println("작성자: " + b.getWriter());
        System.out.println("작성일: " + sdf.format(b.getWrittenDate().getTime()));
        System.out.println("수정일: " + sdf.format(b.getUpdatedDate().getTime()));
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
        
        boardArray[index].setUpdatedDate(Calendar.getInstance());
    }

    private void deleteBoard(int index) {
        System.out.print("정말로 삭제하시겠습니까? y/n");
        String agree = ScannerUtil.nextLine(scanner);
        if (agree.equals("y")) {
            boardArray = ArrayUtil.removeByIndex(boardArray, index);
        }
    }
}
