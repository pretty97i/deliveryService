package com.fish;

import java.io.IOException;
import java.util.Scanner;

import com.db.DBConn;

public class FishMain extends Thread {

	public static void main(String[] args) throws Exception {
		
		Fish ob = new Fish();
		FishException fe = new FishException();
		FishImage fi = new FishImage();
		Scanner sc  = new Scanner(System.in);
		
		
		int ch;
		String temp;
		
//-----------------------------------------------------------------------------------------------------------
		boolean flag = true;
		
		for(int i=0;i<10;i++){
			try {sleep(300);} catch (Exception e) {}

			for(int x=0;x<40;x++){System.out.println();}

			if(flag){
				for(int j=0;j<fi.Fish1.length;j++){System.out.print(fi.Fish1[j]);}
				flag = false;
			}else {
				for(int k=0;k<fi.Fish2.length;k++){System.out.print(fi.Fish2[k]);}
				flag = true;
			}
		}
		
		for (int i=0;i<fi.name.length;i++) {
			System.out.print(fi.name[i]);
			try {sleep(100);} catch (Exception e) {}
		}
		System.out.println();
		System.out.println();
		System.out.println();
		
		while(true){
			do{
				System.out.println("1.로그인 2.회원가입 3.종료");
				temp = sc.next();
				fe.onlyNum(temp);
				ch = Integer.parseInt(temp);			
			}while((ch<0||ch>4)&&ch!=100);
			
			switch (ch){
			case 100:
				ob.todayDelivaryCheck();break;
			
			case 0:
				//매출조회
				ob.searchCash();break;
			case 2:
				//회원가입
				ob.insert();break;
			case 3:
				//종료
				DBConn.close();System.exit(0);
			case 1:
				//로그인
				if(!ob.login()){break;
				}else{
					//로그인후
					ob.CheckDate();
					while(ch!=6&&ch!=5){
						do{
							System.out.println("1.주문 2.주문확인 3.배송날짜설정 4.개인정보수정 5.회원탈퇴 6.로그아웃");
							ch = sc.nextInt();
						}while(ch<1||ch>6);
						
						switch (ch){
						case 1:
							//주문
							ob.order();break;
						case 2:
							//주문확인
							ob.orderCheck();break;
						case 3:
							//배송날짜설정
							ob.SetDelidate();break;
						case 4:
							//개인정보수정
							ob.memberUpdate();break;
						case 5:
							ob.delete();break;
						case 6:
							//로그아웃
							ob.logout();break;
						}//switch~end
					}//while(ch!=5)~end
				}//else~end
				break;
			}//switch~end
		}//while(true)~end
	}
}
