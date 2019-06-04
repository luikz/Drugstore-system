package com.drugstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.drugstore.domain.Manufacturer;
import com.drugstore.factory.ConnectionFactory;

public class DAOManufacturer {
	public void save(Manufacturer m) throws SQLException {
		String sql = "INSERT INTO manufacturer (description) VALUES(?)";

		Connection connection = ConnectionFactory.connect();

		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, m.getDescription());
		ps.executeUpdate();
	}

	public ArrayList<Manufacturer> list() throws SQLException {
		String sql = "SELECT id, description FROM manufacturer ORDER BY description ASC";

		Connection connection = ConnectionFactory.connect();

		PreparedStatement ps = connection.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		ArrayList<Manufacturer> manufacturerList = new ArrayList<Manufacturer>();

		while (rs.next()) {
			Manufacturer m = new Manufacturer();
			m.setId(rs.getLong("id"));
			m.setDescription(rs.getString("description"));
			manufacturerList.add(m);
		}
		
		connection.close();
		ps.close();
		rs.close();
		return manufacturerList;
	}

	public Manufacturer searchById(Manufacturer m) throws SQLException {
		String sql = "SELECT id, description FROM manufacturer WHERE id = ?";

		Connection connection = ConnectionFactory.connect();

		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setLong(1, m.getId());

		ResultSet rs = ps.executeQuery();

		Manufacturer searchingResult = null;

		if (rs.next()) {
			searchingResult = new Manufacturer();
			searchingResult.setId(rs.getLong("id"));
			searchingResult.setDescription(rs.getString("description"));
		}
		connection.close();
		ps.close();
		rs.close();
		
		return searchingResult;
	}

	public ArrayList<Manufacturer> searchByDescription(Manufacturer m) throws SQLException {
		String sql = "SELECT id, description FROM manufacturer WHERE description = ? " + "ORDER BY description ASC";

		Connection connection = ConnectionFactory.connect();

		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, "%" + m.getDescription() + "%");

		ResultSet rs = ps.executeQuery();

		ArrayList<Manufacturer> manufacturerList = new ArrayList<>();

		while (rs.next()) {
			Manufacturer manu = new Manufacturer();
			manu.setId(rs.getLong("id"));
			manu.setDescription("description");

			manufacturerList.add(manu);
		}
		
		connection.close();
		ps.close();
		rs.close();
		return manufacturerList;
	}

	public void update(Manufacturer m) throws SQLException {
		String sql = "UPDATE manufacturer SET description = ? WHERE id = ?";

		Connection connection = ConnectionFactory.connect();

		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, m.getDescription());
		ps.setLong(2, m.getId());
		ps.executeUpdate();
		
		connection.close();
		ps.close();
	}

	public void delete(Manufacturer m) throws SQLException {
		String sql = "DELETE FROM manufacturer where id = ?";

		Connection connection = ConnectionFactory.connect();

		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setLong(1, m.getId());
		ps.executeUpdate();
		
		connection.close();
		ps.close();
	}

	public static void main(String[] args) {
		// Save
		Manufacturer m1 = new Manufacturer();
//		m1.setDescription("DESCRIPTION 1");

//		Manufacturer m2 = new Manufacturer();
//		m2.setDescription("DESCRIPTION 2");

		DAOManufacturer dm = new DAOManufacturer();
//		try {
//			dm.save(m1);
//			dm.save(m2);
//			System.out.println("Manufactures were updated successfully!");
//		} catch (SQLException e) {
//			System.out.println("Manufactures weren't updated successfully!");
//			e.printStackTrace();
//		}

		// List
//		try {
//			ArrayList<Manufacturer> manufacturerList = dm.list();
//			
//			for(Manufacturer m: manufacturerList) {
//				System.out.println("Result:" + m);
//			}
//		} catch (SQLException e) {
//			System.out.println("Occured error while trying to list the manufactures!");
//			e.printStackTrace();
//		}

		// Searching by id
//		m1.setId(3L);
//		m2.setId(5L);	
//		
//		try {
//			Manufacturer m3 = dm.searchById(m1);
//			Manufacturer m4 = dm.searchById(m2);
//			
//			System.out.println("Result 1: " + m3);
//			System.out.println("Result 2: " + m4);
//		} catch (SQLException e) {
//			System.out.println("Couldn't find any manufacturer by id!");
//			e.printStackTrace();
//		}

		// Searching by description
		m1.setDescription("3");

		try {
			ArrayList<Manufacturer> manufacturerList = dm.searchByDescription(m1);

			for (Manufacturer m : manufacturerList) {
				System.out.println("Result: " + m);
			}
		} catch (SQLException e) {
			System.out.println("Couldn't find any manufacturer by description!");
			e.printStackTrace();
		}

		// Update
//		m1.setId(4L);
//		m1.setDescription("Description 4");
//		
//		try {
//			dm.update(m1);
//			System.out.println("Manufactures were updated successfully!");
//		} catch (SQLException e) {
//			System.out.println("Manufactures were not updated!");
//			e.printStackTrace();
//		}

		// Delete
//		m1.setId(1L);
//		m2.setId(2L);
//		
//		try {
//			dm.delete(m1);
//			dm.delete(m2);
//			System.out.println("Manufactures were removed successfully!");
//		} catch (SQLException e) {
//			System.out.println("Error occured while trying to remove manufactures!");
//			e.printStackTrace();
//		}	
	}
}
