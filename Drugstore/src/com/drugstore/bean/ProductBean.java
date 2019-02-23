package com.drugstore.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.drugstore.dao.DAOManufacturer;
import com.drugstore.dao.DAOProduct;
import com.drugstore.domain.Manufacturer;
import com.drugstore.domain.Product;
import com.drugstore.util.JSFUtil;

@ManagedBean(name = "dtBasicViewProduct")
@ViewScoped
public class ProductBean {
	private ArrayList<Product> items;
	private ArrayList<Product> filteredItems;
	
	private Product product;
	
	private ArrayList<Manufacturer> comboManufacturer;
	
	public void loadListing() {
		
		try {
			DAOProduct dp = new DAOProduct();
			items = dp.list();
		} catch(SQLException e) {
			e.printStackTrace();
			JSFUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void prepareNew() {
		try {
			product = new Product();
			
			DAOManufacturer df = new DAOManufacturer();
			
			comboManufacturer = df.list();
		}catch(SQLException e) {
			e.printStackTrace();
			JSFUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void newProduct() {
		try {
			DAOProduct dp = new DAOProduct();
			dp.save(product);
			
			items = dp.list();
			JSFUtil.addSuccessMessage("Product saved successfully.");
		}catch(SQLException e) {
			e.printStackTrace();
			JSFUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void delete() {
		try {
			DAOProduct dp = new DAOProduct();
			
			dp.delete(product);
			
			items = dp.list();

			JSFUtil.addSuccessMessage("Product removed successfully.");
		}catch(SQLException e) {
			e.printStackTrace();
			JSFUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void prepareUpdate() {
		try {
			DAOManufacturer dm = new DAOManufacturer();
			
			comboManufacturer = dm.list();
		}catch(SQLException e) {
			e.printStackTrace();
			JSFUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void update() {
		try {
			DAOProduct dp = new DAOProduct();
			dp.update(product);
			
			items = dp.list();
			
			JSFUtil.addSuccessMessage("Product updated successfully.");
		}catch(SQLException e) {
			e.printStackTrace();
			JSFUtil.addErrorMessage(e.getMessage());
		}
	}

	public ArrayList<Product> getItems() {
		return items;
	}

	public void setItems(ArrayList<Product> items) {
		this.items = items;
	}
	
	public ArrayList<Product> getFilteredItems() {
		return filteredItems;
	}
	
	public void setFilteredItems(ArrayList<Product> filteredItems) {
		this.filteredItems = filteredItems;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public ArrayList<Manufacturer> getComboManufacturer() {
		return comboManufacturer;
	}
	
	public void setComboManufacturer(ArrayList<Manufacturer> comboManufacturer) {
		this.comboManufacturer = comboManufacturer;
	}
}
