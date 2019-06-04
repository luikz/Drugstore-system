package com.drugstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.drugstore.domain.Manufacturer;
import com.drugstore.domain.Product;
import com.drugstore.factory.ConnectionFactory;

public class DAOProduct {
	public void save(Product p) throws SQLException {
		String sql = "INSERT INTO product (description, price, amount, manufacturer_id)"
				+ "VALUES (?, ?, ?, ?)";
		
		Connection connection = ConnectionFactory.connect();
		
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, p.getDescription());
		ps.setDouble(2, p.getPrice());
		ps.setLong(3, p.getAmount());
		ps.setLong(4, p.getManufacturer().getId());
		
		ps.executeUpdate();
		
		connection.close();
		ps.close();
	}
	
	public ArrayList<Product> list() throws SQLException {
		String sql = "SELECT p.id, p.description, p.price, p.amount, m.id, m.description " + 
				"FROM product p " + 
				"INNER JOIN manufacturer m ON m.id = p.manufacturer_id ";
		
		Connection connection = ConnectionFactory.connect();
		
		PreparedStatement ps = connection.prepareStatement(sql);
				
		ResultSet result = ps.executeQuery();
		
		ArrayList<Product> productList = new ArrayList<Product>();
		
		while(result.next()) {
			Manufacturer m = new Manufacturer();
			m.setId(result.getLong("m.id"));
			m.setDescription(result.getString("m.description"));
			
			Product p = new Product();
			p.setId(result.getLong("p.id"));
			p.setDescription(result.getString("p.description"));
			p.setPrice(result.getDouble("p.price"));
			p.setAmount(result.getLong("p.amount"));
			p.setManufacturer(m);
			
			productList.add(p);
		}
		
		connection.close();
		ps.close();
		result.close();
		return productList;
	}
	
	public void delete(Product p) throws SQLException {
		String sql = "DELETE FROM product WHERE id=?";
		
		Connection connection = ConnectionFactory.connect();
		
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setLong(1, p.getId());
		
		ps.executeUpdate();
		
		connection.close();
		ps.close();
	}
	
	public void update(Product p) throws SQLException {
		String sql = "UPDATE product SET description = ?, price = ?, amount = ?, manufacturer_id = ? "
				+ "Where id = ? ";
		
		Connection connection = ConnectionFactory.connect();
		
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, p.getDescription());
		ps.setDouble(2, p.getPrice());
		ps.setLong(3, p.getAmount());
		ps.setLong(4, p.getManufacturer().getId());
		ps.setLong(5, p.getId());
		
		ps.executeUpdate();
		
		connection.close();
		ps.close();
	}
}
