package day08;
// 게시판 관리프로그램
// 단, 메인 메소드는 여기 없고
// BoardEx라는곳에 만들고
// 여기에는 게시판 관리 프로그램에서
// 필요한 메소드들을 모두 모아놓고
// showMenu()가 호출되면 메소들도 호출되서
// 우리가 BoardEx의 메인에선
// BoardViewer viewer = new BoardViewer();
// viewer.showMenu();
// 만 실행시켜도 되게 만들어봅시다

import java.util.Scanner;

import day07.Board;
import util.ArrayUtil;
import util.ScannerUtil;

public class BoardViewer {
    private Scanner scanner;
    private Board[] boardArray;
    
    public BoardViewer() {
        scanner = new Scanner(System.in);
        boardArray = new Board[0];
    }
    
    //메뉴를 보여주는 showMenu() 메소드
    public void showMenu() {
        while(true) {
            System.out.println("게시판 관리 프로그램");
            String message = " 1. 새글 쓰기 2. 목록 보기 3. 종료 \n > ";
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);
            if(userChoice == 1) {
                // 새글을 쓰는 메소드 호출
                addBoard();
            }else if(userChoice == 2) {
                // 글 목록 보는 메소드 호출
                selectAll();
            }else if(userChoice == 3) {
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }
        }
    }
    
    //사용자가 게시글을 입력하고 해당 글을
    //배열에 추가해주는
    //addBoard 메소드
    //이제는 우리가 어떤 메소드를 사용할때
    //반드시 해당 클래스의 객체를 만들어서 사용하기 때문에
    //더이상 static 키워드는 붙이지 않는다!!!
    
    
    
    private void addBoard() {
        //정보를 담을 Board 객체를 하나 만들어준다.
        Board b = new Board();
        
        System.out.print("제목: ");
        b.setTitle(ScannerUtil.nextLine(scanner));
        
        System.out.print("번호: ");
        b.setId(scanner.nextInt());
        
        System.out.print("작성자: ");
        b.setWriter(ScannerUtil.nextLine(scanner));
        
        System.out.print("내용");
        b.setContent(ScannerUtil.nextLine(scanner));
        
        boardArray = ArrayUtil.add(boardArray, b);
    }
    
    // 글의 목록을 출력하는 
    // selectAll() 메소드
    private void selectAll() {
        //만약 작성된 게시글이 없을 경우에는
        //메시지 출력하고
        //있을 때에만 목록을 출력해준다.
        if(boardArray.length == 0) {
            System.out.println("아직 입력된 게시글이 없습니다.");
        }else {
            for(int i = boardArray.length-1; i >= 0; i--) {
                System.out.printf("%d. %s\n", i+1, boardArray[i].getTitle());
            }
            //사용자로부터 뒤로가기를 할지 아니면 
            //글을 선택해서 상세보기 할지를 입력 받는다.
            String message = "상세보기할 글 번호를 선택해주세요(0은 뒤로가기) \n >";
            int userChoice = ScannerUtil.nextInt(scanner, message, 0, boardArray.length) - 1;
            if(userChoice != -1) {
                //사용자가 선택한 글을 보는 메소드 호출
                selectOne(userChoice);
            }
        }
    }
    
    // 사용자가 선택한 번호의 글을 출력하고
    // 수정, 삭제, 뒤로가기 등의 메시지를 호출할 
    // selectOne() 메소드
    private void selectOne(int index) {
        //출력을 할 수 있게 임시로
        //Board 객체를 만들어 boardArray[index] 로 초기화해주자
        Board b= boardArray[index];
        System.out.println("제목: "+b.getTitle());
        System.out.println("글번호: "+b.getId());
        System.out.println("작성자: "+b.getWriter());
        System.out.println("----------------------");
        System.out.println(b.getContent());
        
        //사용자로부터 선택을 받아
        //수정, 삭제, 뒤로가기 중 하나를 실행한다.
        String message = "1. 수정 2. 삭제 3. 뒤로가기 \n>";
        int userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);
        if(userChoice == 1) {
            updateBoard(index);
            selectOne(index);
        }else if(userChoice == 2) {
            deleteBoard(index);
            selectAll();
        }else if(userChoice == 3) {
            selectAll();
        }
    }
    
    //파라미터로 넘어온 index에 해당하는 글을
    //수정하는 메소드
    private void updateBoard(int index) {
        System.out.print("제목: ");
        boardArray[index].setTitle(ScannerUtil.nextLine(scanner));
        
        System.out.print("내용: ");
        boardArray[index].setContent(ScannerUtil.nextLine(scanner));
    }
    
    //파라미터로 넘어온 index에 해당하는 글을
    //삭제하는 메소드
    private void deleteBoard(int index) {
        System.out.print("정말로 삭제하시겠습니까? y/n");
        String agree = ScannerUtil.nextLine(scanner);
        if(agree.equals("y")) {
            boardArray = ArrayUtil.removeByIndex(boardArray, index);
        }
    }
}


















