package com.member.controller;

import com.member.domain.Member;
import com.member.exception.DuplicateNameException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MemberController {
    private List<Member> memberList  = new ArrayList<>();;
    private int nextMemberNumber = 1;
    Member member;
    public MemberController() {
    } 
    
    public boolean isNameAlreadyRegistered(String name) {
        for (Member member : memberList) {
            if (member.getName().equalsIgnoreCase(name)) {
                return true; // 중복된 이름이 이미 등록되어 있음
            }
        }
        return false; // 중복된 이름이 없음
    }

    public void registerMember(Scanner scanner) {
        System.out.print("등록하실 회원의 이름을 입력하세요: ");
        String name = scanner.nextLine();
        
        if (isNameAlreadyRegistered(name)) {
            try {
                throw new DuplicateNameException("이미 등록된 이름입니다.");
            } catch (DuplicateNameException e) {
                System.out.println(e.getMessage());
                return; // 중복 이름이면 등록 중단
            }
        }
        
        System.out.print("등록하실 회원의 연락처를 입력하세요: ");
        int phone = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("등록하실 회원의 주소를 입력하세요: ");
        String addr = scanner.nextLine();
        
        System.out.print("등록하실 회원의 비밀번호를 입력하세요: ");
        int password = scanner.nextInt();
        scanner.nextLine(); 

        Member member = new Member();
        member.setNum(nextMemberNumber++);
        member.setName(name);
        member.setPhone(phone);
        member.setAddr(addr);
        member.setPassword(password);

        memberList.add(member);

        System.out.println("등록이 완료되었습니다.");
        
    }
    
    public Member findMemberByName(String name) {
        for (Member member : memberList) {
        	System.out.println(member.getName());
            if (member.getName().equalsIgnoreCase(name)) {
                return member;
            }
        }
        return null; 
    }
    
    public void findMemberAndDisplayInfo(Scanner scanner) {
    	
        System.out.print("조회할 회원 이름을 입력하세요: ");
        String searchName = scanner.nextLine().trim();
        Member foundMember = findMemberByName(searchName);
        if (foundMember != null) {
            System.out.println(foundMember.getName() + "  고객 정보:");
            System.out.print("회원번호: " + foundMember.getNum());
            System.out.print("	이름: " + foundMember.getName());
            System.out.print("		연락처: " + foundMember.getPhone());
            System.out.println("	주소: " + foundMember.getAddr());
        } else {
            System.out.println("일치하는 회원 정보가 없습니다.");
        }
    }

    private boolean checkPassword(int memberId, int enteredPassword) {
        for (Member member : memberList) {
            if (member.getNum() == memberId) {
                return member.getPassword() == enteredPassword;
            }
        }
        return false;
    }
    
    public void updateMemberInfo(Scanner scanner) {
        System.out.print("수정할 회원 이름을 입력해주세요: ");
        String searchName = scanner.nextLine().trim();

        Member foundMember = findMemberByName(searchName);
        if (foundMember != null) {     
            System.out.print(foundMember.getName() + " 회원의 이름을 수정하세요: ");
            String newName = scanner.nextLine().trim();
            System.out.print(foundMember.getName() + " 회원의 연락처를 수정하세요: ");
            int newPhone = scanner.nextInt();
            scanner.nextLine();
            System.out.print(foundMember.getName() + " 회원의 주소를 수정하세요: ");
            String newAddr = scanner.nextLine().trim();

            System.out.print(foundMember.getName() + " 회원의 비밀번호를 입력하세요: ");
            int password = scanner.nextInt();
            scanner.nextLine(); 
           
            if (!checkPassword(foundMember.getNum(),password)) {
                System.out.println("비밀번호가 다릅니다.");
                return;
            }

            foundMember.setName(newName);
            foundMember.setPhone(newPhone);
            foundMember.setAddr(newAddr);

            System.out.println("수정 완료되었습니다.");
        } else {
            System.out.println("일치하는 회원 정보가 없습니다.");
        }
    }
 
    public void deleteMember(Scanner scanner) {
        System.out.print("삭제할 회원 이름을 입력해주세요: ");
        String searchName = scanner.nextLine().trim();
        
        Member foundMember = findMemberByName(searchName);
        if (foundMember != null) {
            System.out.print("비밀번호를 입력하세요: ");
            int password = scanner.nextInt();
            scanner.nextLine();
            
            if (!checkPassword(foundMember.getNum(),password)) {
                System.out.println("비밀번호가 다릅니다.");
            } else {
                memberList.remove(foundMember);
                System.out.println("삭제되었습니다.");
            }
        } else {
            System.out.println("일치하는 회원 정보가 없습니다.");
        }
    }

    public void displayBasicMemberInfo() {
        for (Member member : memberList) {
            System.out.print("회원번호: " + member.getNum());
            System.out.print("	이름: " + member.getName());
            System.out.println("	   연락처: " + member.getPhone());
        }
    }
    
    public void writeMemberInfoToFile() {
        String fileName = "member_info.txt"; 
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Member member : memberList) {
                writer.print("회원번호: " + member.getNum());
                writer.print("      이름: " + member.getName());
                writer.print("	  연락처: " + member.getPhone());
                writer.print("	 주소: " + member.getAddr());
                writer.println(); 
            }
            System.out.println("회원 정보가 파일에 저장되었습니다.");
        } catch (IOException e) {
            System.err.println("파일 출력 오류: " + e.getMessage());
        }
    }

}

