package com.fish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;





public class Fish {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Scanner sc = new Scanner(System.in);
	FishDAO dao = new FishDAO();
	FishPrint print = new FishPrint();
	FishException fe = new FishException();
	
	String user;
	
	//회원가입
	public void insert(){
		
		String str=null,pwd1=null,pwd2=null,birth;
		int ch;
		try {

			FishDTO dto = new FishDTO();
			do{
				try {
					System.out.print("ID 입력?");
					str = sc.next();
					if(!str.equals(fe.engNum(str))){
						throw new Exception();
					}
					
					if(str.length()>10)
						System.out.println("10자이내로 입력하세요");
					else
						dto.setId(str);
					
				} catch (Exception e) {
					System.out.println("영문자,숫자 혼용만 가능합니다.");
				}
					
				
			}while(str.length()>10 || !str.equals(fe.engNum(str)));
			
			do{
				try {
					System.out.print("비밀번호?");
					pwd1 = sc.next();
					if(!pwd1.equals(fe.engNum(pwd1))){
						throw new Exception();
					}
					
					System.out.print("비밀번호확인?");
					pwd2 = sc.next();
					if(!pwd1.equals(fe.engNum(pwd1))){
						throw new Exception();
					}
					
					if(!pwd1.equals(pwd2))
						System.out.println("비밀번호를 다시 확인하세요");
					else
						dto.setPwd(pwd1);
					
				} catch (Exception e) {
					System.out.println("영문자,숫자 혼용만 가능합니다.");
				}
			}while(!pwd1.equals(pwd2)||!pwd1.equals(fe.engNum(pwd1)));
			
			System.out.println("이름?");
			dto.setName(sc.next());
			do{
				System.out.println("1.남/2.여");
				ch = sc.nextInt();
				if(ch==1||ch==2)
					dto.setGender(ch);
				else
					System.out.println("1,2만 입력하세요");
			}while(ch!=1&&ch!=2);
			
			do{
				System.out.println("생년월일[yyyy-mm-dd]?");
				birth = sc.next();
				dto.setBirth(birth);
				
			}while(fe.frame(birth));
			
			System.out.println("전화번호?");
			dto.setTel(sc.next());
			
			System.out.println("XX도 XX시 XX구?(ex:경기도 용인시 기흥구)");
			dto.setAdd1(br.readLine());
			System.out.println("XX동 번지?(ex:신갈동 29-5)");
			dto.setAdd2(br.readLine());

			int result = dao.insertData(dto);

			if(result!=0){
				System.out.println("회원가입 성공!");
			}else {
				System.out.println("회원가입 실패ㅠㅠ");			
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	//회원탈퇴
	public void delete(){
				
		System.out.println("비밀번호?");
		String pw = sc.next();
		
		if(dao.getLists(user).equals(pw)){
			
			int result = dao.deleteData(user);
			
			if(result != 0){
				
				System.out.println("ID가 삭제되었습니다!");
				
			}else {
				
				System.out.println("ID가 삭제되지 않았습니다!");
			}
			
		}
		
		
	}
	
	//로그인
	public boolean login(){

		boolean flag=false;

		try {

			System.out.println("ID 입력하세요");
			String id = sc.next();

			System.out.println("비밀번호를 입력하세요");
			String pwd = sc.next();
			
				
				if(dao.getLists(id).equals(pwd)){
				
					System.out.println("로그인성공!");
					user = id;
					flag = true;
	
				}else {
					System.out.println("아이디&비밀번호를 확인하세요");			
				}
			

		} catch (NullPointerException e) {System.out.println("아이디가 없습니다");
		} catch (Exception e) {System.out.println(e.toString());}
		return flag;
	}

	//배송날짜유무확인
	public void CheckDate(){
		
		if(dao.getSetDate(user)==null){
			System.out.println("배송을 원하는 날짜를 설정하세요");
			SetDelidate(); //메소드를 보냄
		}
		
	}
	
	//배송날짜 설정
	public void SetDelidate(){
		
		int result=0;
		
		FishDTO dto = new FishDTO();
		System.out.println("받을 날짜를 정하세요");
		
			dto.setDate(sc.next());
			dto.setId(user);
			
	
			result = dao.getDelidate(dto);
		
			
		if(result!=1)
			System.out.println("날짜를 다시 확인해 주세요");
		else
			System.out.println("배송날짜 설정 완료");
	}

	//개인정보 수정
	public void memberUpdate() throws Exception{
		
			
		int su;
		String add1 = null;
		String add2 = null;
		String tel = null;
		
		System.out.println("비밀번호?");
		String pw = sc.next();

		if (dao.getLists(user).equals(pw)) {
			
			//비밀번호 변경
			do{
				System.out.println("비밀번호를 변경 하시겠습니까?");
				System.out.println("1.네 2.아니요");
				su = sc.nextInt();	
			}while(su<0||su>2);
			
			if(su==1){	
				
				do{
					System.out.println("변경할 비밀번호?");
					pw = sc.next();
				}while(!pw.equals(fe.engNum(pw)));
			}
			
			//전화번호 변경
			do{	
				su=0;
				System.out.println("전화번호를 변경 하시겠습니까?");
				System.out.println("1.네 2.아니요");
				su = sc.nextInt();	
				
			}while(su<0||su>2);
			
			
			if(su==1){	
				System.out.println("변경할 전화번호?");
				tel = sc.next();
			}
			
			//주소변경
			do{	
				su=0;
				System.out.println("주소를 변경 하시겠습니까?");
				System.out.println("1.네 2.아니요");
				su = sc.nextInt();	
			}while(su<0||su>2);
			
			if(su==1){	
				System.out.println("변경할 주소?");
				System.out.println("XX도 XX시 XX구?(ex:경기도 용인시 기흥구)");
				add1 = br.readLine();
				System.out.println("XX동 번지?(ex:신갈동 29-5)");
				add2 = br.readLine();
			}
			
			int result = dao.updateData(add1, add2, tel, pw, user);
			
			if (result==1) {
				memberCheck();
				System.out.println("회원정보 수정 성공");
			} else {
				System.out.println("회원정보 수정에 실패했습니다");
			}
			
		}
		
	}
	
	//개인정보 확인
	public void memberCheck(){
		
		FishDTO dto = dao.memeberCheckdata(user);
		
		System.out.println(print.basicPrint(dto));
		
		
	}
	
	//주문
	public void order() throws Exception{
		
		int result;
		FishDTO dto = new FishDTO();
		
		int pat,shu,pizza,fish,choco;
		String pat2,shu2,pizza2,fish2,choco2;
		
		System.out.println("주문[팥,슈크림,피자,붕어,초코]?");
		fe.onlyNum(pat2 = sc.next()); fe.onlyNum(shu2 = sc.next()); fe.onlyNum(pizza2 = sc.next());
		fe.onlyNum(fish2 = sc.next()); fe.onlyNum(choco2 = sc.next());
		
		pat = Integer.parseInt(pat2);shu = Integer.parseInt(shu2);pizza = Integer.parseInt(pizza2);
		fish = Integer.parseInt(fish2);choco = Integer.parseInt(choco2);
		
		dto.setPat(pat);dto.setShu(shu);dto.setPizza(pizza);dto.setFish(fish);dto.setChoco(choco);
		dto.setId(user);
		
		result = dao.orderData(dto);
		
		if(result!=0)
			System.out.println("성공!!");
		else
			System.out.println("실패");

	}
	
	//주문확인
	public void orderCheck(){

		FishDTO dto = dao.orderCheckData(user);

		if (dto.getPat()==0&&dto.getShu()==0&&dto.getPizza()==0&&dto.getFish()==0&&dto.getChoco()==0){
			System.out.println("주문내역이 없습니다");
		} else{
			dto.setId(user);			
			System.out.println(print.OrderPrint(dto));
		}


	}

	//로그아웃
	public void logout(){
		user = null;
	}

	//배송목록체크
	public void todayDelivaryCheck(){
		int result=0;
		List<FishDTO> lists = dao.getTodayDelivaryList();
		
		if(lists!=null){
			
			Iterator<FishDTO> it = lists.iterator();
			System.out.println("오늘 배송 내역");
			System.out.println("--------------------------");
			while(it.hasNext()){
				FishDTO dto = it.next();
				System.out.println(print.OrderPrint(dto));
				System.out.println("--------------------------");
			}
			result = dao.finishDelivary(lists);
		
			
			
		}
		
	}

	//매출 조회
		public void searchCash() throws Exception{
			
			String start,end;
			FishDTO sum = new FishDTO();
			
			int sum1=0,sum2=0,sum3=0,sum4=0,sum5=0;
			
			
			do{
				System.out.print("조회 시작날짜 ? ");
				start = sc.next();
			}while(fe.frame(start));
			
			do{
				System.out.print("조회 마지막날짜 ? ");
				end = sc.next();	
			}while(fe.frame(end));
			
			int a=Integer.parseInt(start.replace("-", "")),b=Integer.parseInt(end.replace("-", ""));
			if((a-b)>0){
				String temp;
				temp = start;start = end;end = temp;
			}
			
			List<FishDTO> lists = dao.getCashList(start, end);
			Iterator<FishDTO> it = lists.iterator();
			
			while(it.hasNext()){
				FishDTO dto = it.next();
				sum1+= dto.getPat();sum2+= dto.getShu();sum3+= dto.getPizza();sum4+= dto.getFish();sum5+= dto.getChoco();
			}
			
			sum.setPat(sum1);sum.setShu(sum2);sum.setPizza(sum3);sum.setFish(sum4);sum.setChoco(sum5);
			
			System.out.println(print.totPrint(sum,start,end));
		}


}
