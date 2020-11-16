package viewer;

import java.util.Scanner;

import controller.UserController;
import dto.UserDTO;
import util.ScannerUtil;

public class UserViewer {
    private Scanner scanner;
    private UserController userController;
    
    public UserViewer() {
        scanner = new Scanner(System.in);
        userController = new UserController();
    }
    
    //1. 메뉴 출력 메소드
    
    public UserDTO showMenu() {
        
        while(true) {
            UserDTO logIn = new UserDTO();
            
            String message = "1. 로그인 2. 회원가입 3. 종료";
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);
            if(userChoice == 1) {
                logIn = logIn();
                if(logIn == null) {
                    break;
                }
                System.out.println("===========================");
                System.out.println(logIn.getNickname()+"님 환영합니다.");
                System.out.println("===========================");
                return logIn;
            }else if(userChoice == 2) {
                register();
            }else if(userChoice == 3) {
                break;
            }
        }
        
        
        return null;
    }
    
    private UserDTO logIn() {
        UserDTO logIn = new UserDTO();
        
        System.out.print("username (종료를 원하시면 x를 입력하세요): ");
        logIn.setUsername(ScannerUtil.nextLine(scanner));
        if(logIn.getUsername().toLowerCase().equals("x")) {
            return null;
        }
        
        System.out.print("pw: ");
        logIn.setPassword(ScannerUtil.nextLine(scanner));
        
        while(userController.logIn(logIn) == null) {
            System.out.println("잘못된 정보입니다.");
            
            System.out.print("username (종료를 원하시면 x를 입력하세요): ");
            logIn.setUsername(ScannerUtil.nextLine(scanner));
            if(logIn.getUsername().toLowerCase().equals("x")) {
                return null;
            }
            
            System.out.print("pw: ");
            logIn.setPassword(ScannerUtil.nextLine(scanner));
        }
        
        
        
        return userController.logIn(logIn);
    }
    
    private void register() {
        UserDTO u = new UserDTO();
        
        System.out.print("username: ");
        u.setUsername(ScannerUtil.nextLine(scanner));
        
        while(userController.validateUsername(u.getUsername())) {
            System.out.println("중복된 username입니다. 다시 입력해주세요");
            System.out.print("username: ");
            u.setUsername(ScannerUtil.nextLine(scanner));
        }
        
        System.out.print("pw: ");
        u.setPassword(ScannerUtil.nextLine(scanner));
        
        System.out.print("nickname: ");
        u.setNickname(ScannerUtil.nextLine(scanner));
        
        userController.register(u);
        
    }
    
    public String selectNicknameById(int id) {
        return userController.selectNickname(id);
    }
}












