package com.member.main;

import java.util.Scanner;
import com.member.controller.MemberController;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MemberController control = new MemberController();

        
        int loginAttempts = 0;
        final int MAX_LOGIN_ATTEMPTS = 3;

        while (loginAttempts < MAX_LOGIN_ATTEMPTS) {       	
            System.out.println("***********************************************");
            System.out.println("   		    로그인");
            System.out.println("***********************************************");
            System.out.print("아이디를 입력하세요: ");
            String adminId = scanner.nextLine();

            if (!adminId.equals("admin")) {
                System.out.println("일치하는 아이디가 없습니다.");
                continue;
            }

            System.out.print("비밀번호를 입력하세요: ");
            String adminPassword = scanner.nextLine();

            if (adminPassword.equals("admin")) {
                System.out.println("로그인 성공");
                break;
            } else {
                System.out.println("비밀번호가 틀렸습니다.");
                loginAttempts++;
            }
        }

        if (loginAttempts >= MAX_LOGIN_ATTEMPTS) {
            System.out.println("로그인 횟수 초과");
        } else {

            boolean exit = false;
            while (!exit) {
            	System.out.println("***********************************************");
            	System.out.println("	      회원 관리 프로그램");
            	System.out.println("***********************************************");
                System.out.print("1. 고객 정보 등록하기     ");
                System.out.println("2. 고객 정보 조회하기");
                System.out.print("3. 고객 정보 수정하기     ");
                System.out.println("4. 고객 정보 삭제하기");
                System.out.print("5. 고객 정보 목록보기     ");
                System.out.println("6. 고객 정보 파일출력");
                System.out.println("7. 종료");
                System.out.println("***********************************************");
                System.out.print("메뉴 번호를 선택해주세요: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                    	control.registerMember(scanner);
                        continue;
                    case 2:
                    	control.findMemberAndDisplayInfo(scanner);
                    	continue;
                    case 3:
                    	control.updateMemberInfo(scanner);
                		continue;
                    case 4:
	                    control.deleteMember(scanner);
	            		continue;
                    case 5:
	                    control.displayBasicMemberInfo();
	            		continue;
                    case 6:
	                    control.writeMemberInfoToFile();
	            		continue;
                    case 7:
                        exit = true;
                        break;
                    default:
                        System.out.println("잘못된 메뉴 번호입니다. 다시 선택해주세요.");
                }
            }
        }

        scanner.close();
    }
}



