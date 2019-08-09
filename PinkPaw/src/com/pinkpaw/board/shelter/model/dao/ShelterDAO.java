package com.pinkpaw.board.shelter.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.pinkpaw.board.shelter.model.vo.Shelter;

import static com.pinkpaw.common.JDBCTemplate.*;

public class ShelterDAO {
	
	private Properties prop = new Properties();
	
	public ShelterDAO() {
		try {
			String fileName = ShelterDAO.class.getResource("/sql/board/shelter-query.properties").getPath();
			prop.load(new FileReader(fileName));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Shelter> selectShelterList(Connection conn) {
		
		List<Shelter> list = new ArrayList<Shelter>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectShelterList");
		
		try{
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Shelter s = new Shelter();
				s.setShelterNo(rset.getInt("shelter_no"));
				s.setShelterCenterName(rset.getString("shelter_center_name"));
				s.setShelterInsName(rset.getString("shelter_ins_name"));
				s.setShelterKind(rset.getString("shelter_kind"));
				s.setShelterAnimalKind(rset.getString("shelter_animal_kind"));
				s.setShelterRdnmAddress(rset.getString("shelter_rdnm_address"));
				s.setShelterInmAddress(rset.getString("shelter_inm_address"));
				s.setShelterLatitude(rset.getDouble("shelter_latitude")+"");
				s.setShelterHardress(rset.getDouble("shelter_hardress")+"");
				s.setShelterCenterDate(rset.getString("shelter_center_date"));
				s.setShelterWdOpen(rset.getString("shelter_wd_open"));
				s.setShelterWdEnd(rset.getString("shelter_wd_end"));
				s.setShelterWkOpen(rset.getString("shelter_wk_open"));
				s.setShelterWkEnd(rset.getString("shelter_wk_end"));
				s.setShelterHoliday(rset.getString("shelter_holiday"));
				s.setShelterPhone(rset.getString("shelter_phone"));
				s.setPlace(rset.getString("place"));
				list.add(s);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return list;
	}

	public List<Shelter> selectShelterListByCity(Connection conn, String keyword) {
		List<Shelter> list = new ArrayList<Shelter>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectShelterListByCity");
		
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+keyword+"%");
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Shelter s = new Shelter();
				s.setShelterNo(rset.getInt("shelter_no"));
				s.setShelterCenterName(rset.getString("shelter_center_name"));
				s.setShelterInsName(rset.getString("shelter_ins_name"));
				s.setShelterKind(rset.getString("shelter_kind"));
				s.setShelterAnimalKind(rset.getString("shelter_animal_kind"));
				s.setShelterRdnmAddress(rset.getString("shelter_rdnm_address"));
				s.setShelterInmAddress(rset.getString("shelter_inm_address"));
				s.setShelterLatitude(rset.getDouble("shelter_latitude")+"");
				s.setShelterHardress(rset.getDouble("shelter_hardress")+"");
				s.setShelterCenterDate(rset.getString("shelter_center_date"));
				s.setShelterWdOpen(rset.getString("shelter_wd_open"));
				s.setShelterWdEnd(rset.getString("shelter_wd_end"));
				s.setShelterWkOpen(rset.getString("shelter_wk_open"));
				s.setShelterWkEnd(rset.getString("shelter_wk_end"));
				s.setShelterHoliday(rset.getString("shelter_holiday"));
				s.setShelterPhone(rset.getString("shelter_phone"));
				s.setPlace(rset.getString("place"));
				list.add(s);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return list;
	}

}
