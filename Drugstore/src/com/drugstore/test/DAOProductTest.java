package com.drugstore.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import com.drugstore.dao.DAOProduct;
import com.drugstore.domain.Manufacturer;
import com.drugstore.domain.Product;

public class DAOProductTest {
	
	@Test
	@Ignore
	public void save() throws SQLException {
		Product p = new Product();
		p.setDescription("Burdock");
		p.setPrice(5.20);
		p.setAmount(14L);
		
		Manufacturer m = new Manufacturer();
		m.setId(5L);
		
		p.setManufacturer(m);
		
		DAOProduct dp = new DAOProduct();
		dp.save(p);
	}
	
	@Test
	@Ignore
	public void list() throws SQLException {
		DAOProduct dp = new DAOProduct();
		ArrayList<Product> productList = dp.list();
		
		for(Product p: productList) {
			System.out.println("Product ID: " + p.getId());
			System.out.println("Product Description: " + p.getDescription());
			System.out.println("Product Price: $" + p.getPrice());
			System.out.println("Product Amount: " + p.getAmount());
			System.out.println("Manufacture's ID: " + p.getManufacturer().getId());
			System.out.println();
		}
	}
	
	@Test
	@Ignore
	public void delete() throws SQLException {
		Product p = new Product();
		p.setId(2L);
		
		DAOProduct dp = new DAOProduct();
		dp.delete(p);
	}
	
	@Test
	public void update() throws SQLException {
		Product p = new Product();
		
		p.setId(3L);
		p.setDescription("OINTMENT with 60 GM");
		p.setPrice(15.30);
		p.setAmount(7L);
		
		Manufacturer m = new Manufacturer();
		m.setId(2L);
		
		p.setManufacturer(m);
		
		DAOProduct dp = new DAOProduct();
		dp.update(p);
	}
}
