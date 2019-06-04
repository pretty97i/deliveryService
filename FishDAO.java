package com.fish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.db.DBConn;


public class FishDAO {

	//회원가입
	public int insertData(FishDTO dto){

		int result = 0;
		Connection conn=DBConn.getConnection();
		Statement stmt = null;
		String sql;

		try {
			conn.setAutoCommit(false);
			sql = String.format("insert into basicmember@link_test (id,name,gender,age) "
					+ "values('%s','%s',%d, ceil((months_between(sysdate,'%s')/12+1)))"
					,dto.getId(),dto.getName(),dto.getGender(),dto.getBirth());
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);

			
			sql = String.format("insert into detailmember@link_test (id,pwd,birth,addr1,addr2,tel) values('%s','%s','%s','%s','%s','%s')"
					,dto.getId(),dto.getPwd(),dto.getBirth(),dto.getAdd1(),dto.getAdd2(),dto.getTel());
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			
			sql = String.format("insert into delivarydata@link_test (id) values('%s')",dto.getId());
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
			sql = String.format("insert into orderdata@link_test (id) values('%s')",dto.getId());
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
			conn.commit();
			conn.setAutoCommit(true);
			
			stmt.close();
			
		} catch (Exception e) {
			
			System.out.println(e.toString());
			try {conn.rollback();stmt.close();} catch (Exception e2) {}
			try {DBConn.close();} catch (Exception e2) {}
		}

		return result;

	}

	//로그인
	public String getLists(String id){

		Connection conn=DBConn.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		String str=null;

		try {

			sql = "select pwd from detailmember@link_test where id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if(rs.next()){
				str = rs.getString("pwd");
			}
			rs.close();
			pstmt.close();

		
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return str;
	}
	
	//회원탈퇴
	public int deleteData(String id){

			Connection conn = DBConn.getConnection();
			Statement stmt = null;
			String sql;
			int result=0;

			try {
				conn.setAutoCommit(false);

				sql = String.format("delete delivarydata@link_test where id = '%s'",id);
				stmt = conn.createStatement();
				result = stmt.executeUpdate(sql);

				sql = String.format("delete orderdata@link_test where id = '%s'",id);
				stmt = conn.createStatement();
				result = stmt.executeUpdate(sql);

				sql = String.format("delete detailmember@link_test where id = '%s'",id);
				stmt = conn.createStatement();
				result = stmt.executeUpdate(sql);

				sql = String.format("delete basicmember@link_test where id = '%s'",id);
				stmt = conn.createStatement();
				result = stmt.executeUpdate(sql);

				conn.commit();
				conn.setAutoCommit(true);
				
				stmt.close();
			} catch (Exception e) {
				System.out.println(e.toString());
				try {conn.rollback();stmt.close();} catch (Exception e2) {}
				try {DBConn.close();} catch (Exception e2) {}
			}

			return result;


		}
		
	//회원정보 수정
	public int updateData(String add1,String add2,String tel,String pwd,String id){
		
		Connection conn=DBConn.getConnection();
		PreparedStatement pstmt = null;
		String sql;
		int result =0;
		
		try {
			
			conn.setAutoCommit(false);
			
			if(add1!=null){
				sql = "update detailmember@link_test set addr1=?, addr2=? where id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, add1);
				pstmt.setString(2, add2);
				pstmt.setString(3, id);
				result = pstmt.executeUpdate();
				
				pstmt.close();
			}
			
			if(tel!=null){
				sql = "update detailmember@link_test set tel=? where id=?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, tel);
				pstmt.setString(2, id);
				result = pstmt.executeUpdate();
				
				pstmt.close();
			}
			if(pwd!=null){
				sql = "update detailmember@link_test set pwd=? where id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, pwd);
				pstmt.setString(2, id);
				result = pstmt.executeUpdate();
				
				pstmt.close();
			}
			
			conn.commit();
			conn.setAutoCommit(true);
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
			try {conn.rollback();pstmt.close();DBConn.close();} catch (Exception e2) {}
		}
		
		return result;
		
		
	}
	
	//주문
	public int orderData(FishDTO dto){
		
		int result = 0;
		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = "update orderdata@link_test set pat=?,shu=?,pizza=?,fish=?,choco=? where id=?";
			pstmt = conn.prepareStatement(sql);
			int sum = dto.getPat()+dto.getShu()+dto.getPizza()+dto.getFish()+dto.getChoco();
			
			pstmt.setInt(1, dto.getPat());
			pstmt.setInt(2, dto.getShu());
			pstmt.setInt(3, dto.getPizza());
			pstmt.setInt(4, dto.getFish());
			pstmt.setInt(5, dto.getChoco());
			pstmt.setString(6, dto.getId());

			result = pstmt.executeUpdate();
			pstmt.close();
			
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
			try {pstmt.close();DBConn.close();} catch (Exception e2) {}
		}
		
		return result;
		
	}
	
	//주문 확인
	public FishDTO orderCheckData(String id){
		
		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		String str=null;
		
		FishDTO dto = null;
		
		try {
			
			sql="select to_char(delidate,'yyyy-mm-dd'),pat,shu,pizza,fish,choco "
					+ "from delivarydata@link_test A, orderdata@link_test B "
							+ "where a.id = b.id and a.id= ?";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				dto = new FishDTO();
				
				dto.setDate(rs.getString(1)); 
				dto.setPat(rs.getInt(2));
				dto.setShu(rs.getInt(3));
				dto.setPizza(rs.getInt(4));
				dto.setFish(rs.getInt(5));
				dto.setChoco(rs.getInt(6));
			}
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
			try {rs.close();pstmt.close();DBConn.close();} catch (Exception e2) {}
		}
		
		return dto;
		
	}
	
	//배송날짜유무 확인
	public String getSetDate(String id){
		
		Connection conn=DBConn.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		String str=null;
		
		try {

			sql = "select delidate from delivarydata@link_test where id= ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				str = rs.getString("delidate");
			}
			rs.close();pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
			try {rs.close();pstmt.close();DBConn.close();} catch (Exception e2) {}
		}
		return str;
				
	}
	
	//배송날짜설정
	public int getDelidate(FishDTO dto){
		
		Connection conn=DBConn.getConnection();
		Statement stmt = null;
		String sql;
		int result=0;
		
		
		try {
						
			sql =  String.format("update delivarydata@link_test set delidate='%s' where id = '%s'",dto.getDate(),dto.getId());
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
			try {stmt.close();DBConn.close();} catch (Exception e2) {}
		}
		
		return result;
		
	}

	//회원정보확인
	public FishDTO memeberCheckdata(String id) {

		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		String str = null;
		FishDTO dto = null;
		

		try {

			sql = "select A.id,name,gender,age,to_char(birth,'yyyy-mm-dd') birth,addr1,addr2,tel "
					+ "from basicmember@link_test A, detailmember@link_test B "
					+ "where A.id = B.id and A.id= ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				dto = new FishDTO();
				dto.setId(id);
				dto.setName(rs.getString("name"));
				dto.setGender(rs.getInt("gender"));
				dto.setAge(rs.getInt("age"));
				dto.setBirth(rs.getString("birth"));
				dto.setAdd1(rs.getString("addr1"));
				dto.setAdd2(rs.getString("addr2"));
				dto.setTel(rs.getString("tel"));

			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return dto;

	}
	
	//배송완료
	public int finishDelivary(List<FishDTO> lists){
		int result = 0;
		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		int max = 0;
		
		Iterator<FishDTO> it = lists.iterator();
		while(it.hasNext()){
			FishDTO dto = it.next();
			//id, delidate, pat, shu, pizza, fish, choco
			if(dto.getPat()==0&&dto.getShu()==0&&dto.getPizza()==0&&dto.getFish()==0&&dto.getChoco()==0){
				System.out.println(dto.getId()+"님은 주문내역이 없습니다");
				try {
					sql = "update delivarydata@link_test set delidate=sysdate+7 where id=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, dto.getId());
					
					result = pstmt.executeUpdate();
					
					pstmt.close();
					
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			}else{
			
				try {
					conn.setAutoCommit(false);
					sql = "select nvl(max(delino),0) from sales@link_test";
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					if(rs.next()){
						max = rs.getInt(1);
					}
					pstmt.close();
					rs.close();
					
					sql = "insert into sales@link_test (delino,id,delidate,pat,shu,pizza,fish,choco) "
							+ "values (?,?,?,?,?,?,?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, max+1);
					pstmt.setString(2, dto.getId());
					pstmt.setString(3, dto.getDate());
					pstmt.setInt(4, dto.getPat());
					pstmt.setInt(5, dto.getShu());
					pstmt.setInt(6, dto.getPizza());
					pstmt.setInt(7, dto.getFish());
					pstmt.setInt(8, dto.getChoco());
					
					
					pstmt.executeUpdate();
					
					
					pstmt.close();
					
					sql = "update delivarydata@link_test set delidate=sysdate+7 where id=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, dto.getId());
					
					result = pstmt.executeUpdate();
					
					pstmt.close();
					conn.commit();
					conn.setAutoCommit(true);
					
					
				} catch (Exception e) {
					System.out.println(e.toString());
					try {conn.rollback();pstmt.close();DBConn.close();} catch (Exception e2) {}
				}
			}
		}
			return result;
	}
	//배송목록체크
	public List<FishDTO> getTodayDelivaryList(){
		
		Connection conn = DBConn.getConnection();
		List<FishDTO> lists = new ArrayList<FishDTO>();
		Statement stmt = null;
		ResultSet rs = null;
		String sql;
		
		
		try {
			sql = "select a.id, to_char(delidate,'yyyy-mm-dd'), pat, shu, pizza, fish, choco "
					+ "from orderdata@link_test a, delivarydata@link_test b "
					+ "where a.id=b.id and delidate=to_char(sysdate,'yyyy-mm-dd')";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				
				FishDTO dto = new FishDTO();
				
				dto.setId(rs.getString(1));
				dto.setDate(rs.getString(2));
				dto.setPat(rs.getInt(3));
				dto.setShu(rs.getInt(4));
				dto.setPizza(rs.getInt(5));
				dto.setFish(rs.getInt(6));
				dto.setChoco(rs.getInt(7));
				
				lists.add(dto);
			}
			rs.close();
			stmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
			try {rs.close();stmt.close();DBConn.close();} catch (Exception e2) {}
		}
		return lists;
	}

	//기간 매출조회
	public List<FishDTO> getCashList(String start,String end){
		
		List<FishDTO> lists = new ArrayList<FishDTO>();
		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select pat,shu,pizza,fish,choco from sales@link_test"
					+ " where delidate >= ? and delidate <= ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, start);
			pstmt.setString(2, end);
			rs = pstmt.executeQuery();
			while(rs.next()){
				FishDTO dto = new FishDTO();
				dto.setPat(rs.getInt(1));
				dto.setShu(rs.getInt(2));
				dto.setPizza(rs.getInt(3));
				dto.setFish(rs.getInt(4));
				dto.setChoco(rs.getInt(5));
				
				lists.add(dto);
			}
			
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;
	}
	
	
	
}
