package com.drugstore.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.drugstore.dao.DAOManufacturer;
import com.drugstore.domain.Manufacturer;
import com.drugstore.util.JSFUtil;

@ManagedBean(name = "dtBasicView")
@ViewScoped
public class ManufacturerBean {
	private Manufacturer manufacturer;
	private ArrayList<Manufacturer> items;
	private ArrayList<Manufacturer> filteredItems;
	
	@PostConstruct
	public void prepareResearch() {
		try {
			DAOManufacturer dm = new DAOManufacturer(); 
			items = dm.list();
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void prepareNew() {
		manufacturer = new Manufacturer();
	}
	
	public void newManufacturer() {
		try {
			DAOManufacturer dm = new DAOManufacturer();
			dm.save(manufacturer);
			items = dm.list(); 
			
			JSFUtil.addSuccessMessage("Manufacturer successfully saved.");
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void delete() {
		
		try {
			DAOManufacturer dm = new DAOManufacturer();
			dm.delete(manufacturer);
			items = dm.list();
			
			JSFUtil.addSuccessMessage("Manufacturer removed successfully.");
		} catch(SQLException e) {
			e.printStackTrace();
			JSFUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void update() {
	    
		try{
			DAOManufacturer dm = new DAOManufacturer();
			dm.update(manufacturer);
			items = dm.list();
			
			JSFUtil.addSuccessMessage("Manufacturer updated successfully.");
		} catch(SQLException e) {
			e.printStackTrace();
			JSFUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public Manufacturer getManufacturer() {
		return manufacturer;
	}
	
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	
	public ArrayList<Manufacturer> getItems() {
		return items;
	}

	public void setItems(ArrayList<Manufacturer> items) {
		this.items = items;
	}

	public ArrayList<Manufacturer> getFilteredItems() {
		return filteredItems;
	}

	public void setFilteredItems(ArrayList<Manufacturer> filteredItems) {
		this.filteredItems = filteredItems;
	}
}
