package com.fish;

import java.util.InputMismatchException;

public class FishException {
	
	public String engNum(String str) throws Exception{

		//����� ���ڸ� ����
		
		int eng = 0;
		int num = 0;
		
		for(int i=0;i<str.length();i++){
			
			
			char ch = str.charAt(i);
			
			if((ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z')) eng++;
			
			else if(ch>='0'&&ch<='9') num++;
			
		}
		if(eng==0||num==0){
			try {throw new Exception();} 
			catch (Exception e) {
				str = null;
				
			}
		}
		return str;
	}
	
//	--------------------------------------------------------------------------------------
	
	public boolean frame(String str) throws Exception{
		
		String[] temp = str.split("-");
		boolean flag = false;
		
		if(temp.length!=3){
			
			try {
				flag = true;
				throw new Exception();
				
			} catch (Exception e) {
				System.out.println("�Է������� ���� �Է��ϼ���!");
			}			
			
			
		}else{
			
			for(int i=0;i<3;i++){
				
				try{

					int num = Integer.parseInt(temp[i]);
					
					
				} catch (Exception e) {
					System.out.println("���ڸ� �Է��ϼ���.");
					flag = true;
					break;
				}
				
				
			}
			
		}
		return flag;
				
	}
	
//	--------------------------------------------------------------------------------------
	
	
	public void onlyNum(String str) throws Exception{
		
		int su = 0;
		
		for(int i=0;i<str.length();i++){
			
			char ch = str.charAt(i);
			
			if((ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z')){
				
				throw new Exception	("���ڸ� �Է��ϼ���.");
			
			
			}
			
		}
		
	}

}
