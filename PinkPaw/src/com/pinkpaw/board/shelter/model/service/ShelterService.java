package com.pinkpaw.board.shelter.model.service;

import java.sql.Connection;
import java.util.List;

import com.pinkpaw.board.shelter.model.dao.ShelterDAO;
import com.pinkpaw.board.shelter.model.vo.Shelter;

import static com.pinkpaw.common.JDBCTemplate.*;

public class ShelterService {

	public List<Shelter> selectShelterList() {
		Connection conn = getConnection();
		List<Shelter> list= new ShelterDAO().selectShelterList(conn);
		close(conn);
		return list;
	}

	public List<Shelter> selectShelterListByCity(String keyword) {
		Connection conn = getConnection();
		List<Shelter> list= new ShelterDAO().selectShelterListByCity(conn, keyword);
		close(conn);
		return list;
	}

}
