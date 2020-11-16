package viewer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import controller.ReplyController;
import dto.ReplyDTO;
import util.ScannerUtil;

public class ReplyViewer {
    private ReplyController controller;
    private Scanner scanner;
    private int id = 1;
    
    public ReplyViewer() {
        controller = new ReplyController();
        scanner = new Scanner(System.in);
    }
    
    //해당 글의 댓글을출력하는 메소드
    public void printAll(int boardId, UserViewer userViewer) {
        ArrayList<ReplyDTO> list = controller.selectByBoardId(boardId);
        for(ReplyDTO r : list) {
            SimpleDateFormat sdf = new SimpleDateFormat("y-M-d H:m:s");
            
            System.out.println("-----------------------------");
            System.out.printf("%s\t%s\n",
                    userViewer.selectNicknameById(r.getWriterId()),
                    sdf.format(r.getWrittenDate().getTime()));
            System.out.println("-----------------------------");
            System.out.println(r.getContent());
            System.out.println("-----------------------------");
        }
    }
    
    //해당 글의 댓글을 작성하는 메소드
    public void writeReply(int boardId, int writerId) {
        ReplyDTO r = new ReplyDTO();
        r.setId(id++);
        r.setWrittenDate(Calendar.getInstance());
        r.setBoardId(boardId);
        r.setWriterId(writerId);
        
        System.out.print("댓글 내용: ");
        r.setContent(ScannerUtil.nextLine(scanner));
        
        controller.insert(r);
        
    }
}
