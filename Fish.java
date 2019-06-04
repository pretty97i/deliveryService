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
	
	//ȸ������
	public void insert(){
		
		String str=null,pwd1=null,pwd2=null,birth;
		int ch;
		try {

			FishDTO dto = new FishDTO();
			do{
				try {
					System.out.print("ID �Է�?");
					str = sc.next();
					if(!str.equals(fe.engNum(str))){
						throw new Exception();
					}
					
					if(str.length()>10)
						System.out.println("10���̳��� �Է��ϼ���");
					else
						dto.setId(str);
					
				} catch (Exception e) {
					System.out.println("������,���� ȥ�븸 �����մϴ�.");
				}
					
				
			}while(str.length()>10 || !str.equals(fe.engNum(str)));
			
			do{
				try {
					System.out.print("��й�ȣ?");
					pwd1 = sc.next();
					if(!pwd1.equals(fe.engNum(pwd1))){
						throw new Exception();
					}
					
					System.out.print("��й�ȣȮ��?");
					pwd2 = sc.next();
					if(!pwd1.equals(fe.engNum(pwd1))){
						throw new Exception();
					}
					
					if(!pwd1.equals(pwd2))
						System.out.println("��й�ȣ�� �ٽ� Ȯ���ϼ���");
					else
						dto.setPwd(pwd1);
					
				} catch (Exception e) {
					System.out.println("������,���� ȥ�븸 �����մϴ�.");
				}
			}while(!pwd1.equals(pwd2)||!pwd1.equals(fe.engNum(pwd1)));
			
			System.out.println("�̸�?");
			dto.setName(sc.next());
			do{
				System.out.println("1.��/2.��");
				ch = sc.nextInt();
				if(ch==1||ch==2)
					dto.setGender(ch);
				else
					System.out.println("1,2�� �Է��ϼ���");
			}while(ch!=1&&ch!=2);
			
			do{
				System.out.println("�������[yyyy-mm-dd]?");
				birth = sc.next();
				dto.setBirth(birth);
				
			}while(fe.frame(birth));
			
			System.out.println("��ȭ��ȣ?");
			dto.setTel(sc.next());
			
			System.out.println("XX�� XX�� XX��?(ex:��⵵ ���ν� ���ﱸ)");
			dto.setAdd1(br.readLine());
			System.out.println("XX�� ����?(ex:�Ű��� 29-5)");
			dto.setAdd2(br.readLine());

			int result = dao.insertData(dto);

			if(result!=0){
				System.out.println("ȸ������ ����!");
			}else {
				System.out.println("ȸ������ ���ФФ�");			
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	//ȸ��Ż��
	public void delete(){
				
		System.out.println("��й�ȣ?");
		String pw = sc.next();
		
		if(dao.getLists(user).equals(pw)){
			
			int result = dao.deleteData(user);
			
			if(result != 0){
				
				System.out.println("ID�� �����Ǿ����ϴ�!");
				
			}else {
				
				System.out.println("ID�� �������� �ʾҽ��ϴ�!");
			}
			
		}
		
		
	}
	
	//�α���
	public boolean login(){

		boolean flag=false;

		try {

			System.out.println("ID �Է��ϼ���");
			String id = sc.next();

			System.out.println("��й�ȣ�� �Է��ϼ���");
			String pwd = sc.next();
			
				
				if(dao.getLists(id).equals(pwd)){
				
					System.out.println("�α��μ���!");
					user = id;
					flag = true;
	
				}else {
					System.out.println("���̵�&��й�ȣ�� Ȯ���ϼ���");			
				}
			

		} catch (NullPointerException e) {System.out.println("���̵� �����ϴ�");
		} catch (Exception e) {System.out.println(e.toString());}
		return flag;
	}

	//��۳�¥����Ȯ��
	public void CheckDate(){
		
		if(dao.getSetDate(user)==null){
			System.out.println("����� ���ϴ� ��¥�� �����ϼ���");
			SetDelidate(); //�޼ҵ带 ����
		}
		
	}
	
	//��۳�¥ ����
	public void SetDelidate(){
		
		int result=0;
		
		FishDTO dto = new FishDTO();
		System.out.println("���� ��¥�� ���ϼ���");
		
			dto.setDate(sc.next());
			dto.setId(user);
			
	
			result = dao.getDelidate(dto);
		
			
		if(result!=1)
			System.out.println("��¥�� �ٽ� Ȯ���� �ּ���");
		else
			System.out.println("��۳�¥ ���� �Ϸ�");
	}

	//�������� ����
	public void memberUpdate() throws Exception{
		
			
		int su;
		String add1 = null;
		String add2 = null;
		String tel = null;
		
		System.out.println("��й�ȣ?");
		String pw = sc.next();

		if (dao.getLists(user).equals(pw)) {
			
			//��й�ȣ ����
			do{
				System.out.println("��й�ȣ�� ���� �Ͻðڽ��ϱ�?");
				System.out.println("1.�� 2.�ƴϿ�");
				su = sc.nextInt();	
			}while(su<0||su>2);
			
			if(su==1){	
				
				do{
					System.out.println("������ ��й�ȣ?");
					pw = sc.next();
				}while(!pw.equals(fe.engNum(pw)));
			}
			
			//��ȭ��ȣ ����
			do{	
				su=0;
				System.out.println("��ȭ��ȣ�� ���� �Ͻðڽ��ϱ�?");
				System.out.println("1.�� 2.�ƴϿ�");
				su = sc.nextInt();	
				
			}while(su<0||su>2);
			
			
			if(su==1){	
				System.out.println("������ ��ȭ��ȣ?");
				tel = sc.next();
			}
			
			//�ּҺ���
			do{	
				su=0;
				System.out.println("�ּҸ� ���� �Ͻðڽ��ϱ�?");
				System.out.println("1.�� 2.�ƴϿ�");
				su = sc.nextInt();	
			}while(su<0||su>2);
			
			if(su==1){	
				System.out.println("������ �ּ�?");
				System.out.println("XX�� XX�� XX��?(ex:��⵵ ���ν� ���ﱸ)");
				add1 = br.readLine();
				System.out.println("XX�� ����?(ex:�Ű��� 29-5)");
				add2 = br.readLine();
			}
			
			int result = dao.updateData(add1, add2, tel, pw, user);
			
			if (result==1) {
				memberCheck();
				System.out.println("ȸ������ ���� ����");
			} else {
				System.out.println("ȸ������ ������ �����߽��ϴ�");
			}
			
		}
		
	}
	
	//�������� Ȯ��
	public void memberCheck(){
		
		FishDTO dto = dao.memeberCheckdata(user);
		
		System.out.println(print.basicPrint(dto));
		
		
	}
	
	//�ֹ�
	public void order() throws Exception{
		
		int result;
		FishDTO dto = new FishDTO();
		
		int pat,shu,pizza,fish,choco;
		String pat2,shu2,pizza2,fish2,choco2;
		
		System.out.println("�ֹ�[��,��ũ��,����,�ؾ�,����]?");
		fe.onlyNum(pat2 = sc.next()); fe.onlyNum(shu2 = sc.next()); fe.onlyNum(pizza2 = sc.next());
		fe.onlyNum(fish2 = sc.next()); fe.onlyNum(choco2 = sc.next());
		
		pat = Integer.parseInt(pat2);shu = Integer.parseInt(shu2);pizza = Integer.parseInt(pizza2);
		fish = Integer.parseInt(fish2);choco = Integer.parseInt(choco2);
		
		dto.setPat(pat);dto.setShu(shu);dto.setPizza(pizza);dto.setFish(fish);dto.setChoco(choco);
		dto.setId(user);
		
		result = dao.orderData(dto);
		
		if(result!=0)
			System.out.println("����!!");
		else
			System.out.println("����");

	}
	
	//�ֹ�Ȯ��
	public void orderCheck(){

		FishDTO dto = dao.orderCheckData(user);

		if (dto.getPat()==0&&dto.getShu()==0&&dto.getPizza()==0&&dto.getFish()==0&&dto.getChoco()==0){
			System.out.println("�ֹ������� �����ϴ�");
		} else{
			dto.setId(user);			
			System.out.println(print.OrderPrint(dto));
		}


	}

	//�α׾ƿ�
	public void logout(){
		user = null;
	}

	//��۸��üũ
	public void todayDelivaryCheck(){
		int result=0;
		List<FishDTO> lists = dao.getTodayDelivaryList();
		
		if(lists!=null){
			
			Iterator<FishDTO> it = lists.iterator();
			System.out.println("���� ��� ����");
			System.out.println("--------------------------");
			while(it.hasNext()){
				FishDTO dto = it.next();
				System.out.println(print.OrderPrint(dto));
				System.out.println("--------------------------");
			}
			result = dao.finishDelivary(lists);
		
			
			
		}
		
	}

	//���� ��ȸ
		public void searchCash() throws Exception{
			
			String start,end;
			FishDTO sum = new FishDTO();
			
			int sum1=0,sum2=0,sum3=0,sum4=0,sum5=0;
			
			
			do{
				System.out.print("��ȸ ���۳�¥ ? ");
				start = sc.next();
			}while(fe.frame(start));
			
			do{
				System.out.print("��ȸ ��������¥ ? ");
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
