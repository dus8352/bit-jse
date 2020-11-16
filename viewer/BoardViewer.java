package viewer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Scanner;

import controller.BoardController;
import dto.BoardDTO;
import dto.UserDTO;
import util.ScannerUtil;

public class BoardViewer {
    private Scanner scanner;
    private BoardController boardController;
    private UserViewer userViewer;
    private UserDTO logInUser;
    private ReplyViewer replyViewer;
    public BoardViewer() {
        scanner = new Scanner(System.in);
        boardController = new BoardController();
        userViewer = new UserViewer();
        replyViewer = new ReplyViewer();
        
    }
    
    //1. 게시글 기능 메뉴를 보여주는 
    //   showBoardMenu()
    public void showBoardMenu() {
        logInUser = userViewer.showMenu();

        while(logInUser != null) {
            String message = "1. 새글 작성 2. 글 목록 보기 3. 처음 화면으로";
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);
            if(userChoice == 1) {
                writeBoard();
            }else if(userChoice == 2) {
                printList();
            }else if(userChoice == 3) {
                logInUser = userViewer.showMenu();
                
            }
        }
        
        System.out.println("사용해주셔서 감사합니다.");
    }

    private void writeBoard() {
        BoardDTO b = new BoardDTO();
        b.setWriterId(logInUser.getId());
        b.setWrittenDate(Calendar.getInstance());
        b.setUpdatedDate(Calendar.getInstance());
        
        System.out.print("제목: ");
        b.setTitle(ScannerUtil.nextLine(scanner));
        
        System.out.print("내용: ");
        b.setContent(ScannerUtil.nextLine(scanner));
        
        boardController.insert(b);
    }

    private void printList() {
        ArrayList<BoardDTO> boardList = boardController.selectAll();
        if(boardList.isEmpty()) {
            System.out.println();
            System.out.println("아직 입력된 글이 없습니다.");
            System.out.println();
        }else {
            //아래의 코드를 실행하면,
            //boardList의 순서가 역순이 되게
            //바뀐다.
            Collections.reverse(boardList);
            for(BoardDTO b : boardList) {
                System.out.printf("%d. %s\t%s\n", b.getId(), b.getTitle(),
                        userViewer.selectNicknameById(b.getWriterId()));
            }
            System.out.print("상세보기할 글 번호를 적어주세요 (0은 뒤로가기): ");
            int id = ScannerUtil.nextInt(scanner);
            if(id != 0) {
                printOne(id);
            }
        }
    }
    
    private void printOne(int id) {
        BoardDTO b= boardController.selectOne(id);
        if(b == null) {
            System.out.println();
            System.out.println("존재하지 않는 글 번호입니다. 메뉴로 돌아갑니다.");
            System.out.println();
            
        }else {
            SimpleDateFormat sdf = new SimpleDateFormat("y-M-d H:m:s");
            System.out.println();
            System.out.println("---------------------------");
            System.out.println("제목: "+b.getTitle());
            System.out.println("작성자: "+userViewer.selectNicknameById(b.getWriterId()));
            System.out.println("작성일: "+sdf.format(b.getWrittenDate().getTime()));
            System.out.println("수정일: "+sdf.format(b.getUpdatedDate().getTime()));
            System.out.println("---------------------------");
            System.out.println(b.getContent());
            System.out.println("---------------------------");
            System.out.println();
            replyViewer.printAll(b.getId(), userViewer);
            String message = new String();
            if(b.getWriterId() == logInUser.getId()) {
            message = "1. 수정 2. 삭제 3. 댓글달기 4. 목록으로";
            
            
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 4);
            if(userChoice == 1) {
                updateBoard(id);
            }else if(userChoice == 2) {
                deleteBoard(id);
            }else if(userChoice == 3) {
                replyViewer.writeReply(b.getId(), logInUser.getId());
            }else if(userChoice == 4) {
                printList();
            }
        }else {
            message = "1. 댓글달기 2. 뒤로가기";
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 2);
            if(userChoice == 1) {
                replyViewer.writeReply(b.getId(), logInUser.getId());
            }else if(userChoice == 2) {
                printList();
                
            }
            }
            }
}
    
    
    private void updateBoard(int id) {
        BoardDTO b= new BoardDTO();
        b.setId(id);
        b.setUpdatedDate(Calendar.getInstance());
        
        System.out.print("제목: ");
        b.setTitle(ScannerUtil.nextLine(scanner));
        
        System.out.print("내용: ");
        b.setContent(ScannerUtil.nextLine(scanner));
        
        boardController.update(b);
        printOne(id);
    }
    
    private void deleteBoard(int id) {
        System.out.println("해당 글을 정말로 삭제하시겠습니까? y/n ");
        System.out.print("> ");
        String agree = ScannerUtil.nextLine(scanner);
        if(agree.toLowerCase().equals("y")) {
            boardController.delete(id);
            printList();
        }else {
            printOne(id);
        }
    }
}












