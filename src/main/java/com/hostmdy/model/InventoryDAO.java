package com.hostmdy.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class InventoryDAO {

	private final DataSource dataSource;

	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;

	public InventoryDAO(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	private void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public List<History> getHistoryByName(String firstname,String lastname) {
		List<History> historyList = new ArrayList<>();
	    	try {
	    		connection = dataSource.getConnection();
				stmt = connection.createStatement();
				rs = stmt.executeQuery("select * from history where firstname = '" + firstname + "' and lastname = '" + lastname + "' ;");

				while (rs.next()) {
					historyList.add(new History(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("list"), rs.getDate("date").toLocalDate(), rs.getInt("total")));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close();
			}
			return historyList;
	  }
	
	public List<History> getHistory() {
		List<History> historyList = new ArrayList<>();
	    	try {
	    		connection = dataSource.getConnection();
				stmt = connection.createStatement();
				rs = stmt.executeQuery("select * from history;");

				while (rs.next()) {
					historyList.add(new History(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("list"), rs.getDate("date").toLocalDate(), rs.getInt("total")));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close();
			}
			return historyList;
	  }
	
	public  PreparedStatement deleteBuylist(String firstname,String lastname) {
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("delete from buylist where firstname = '" + firstname + "' and lastname = '" + lastname + "';");
			pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return pStmt;
	}
	
	 public int setHistory(History history) {
		  int rowUpdated = 0;
	    	try {
				connection = dataSource.getConnection();
				pStmt = connection.prepareStatement("insert into history "
						+ "(firstname,lastname,list,date,total)"
						+ "values (?,?,?,?,?)");
				pStmt.setString(1, history.getFirstname());
				pStmt.setString(2, history.getLastname());
				pStmt.setString(3, history.getList());
				Date date = Date.valueOf(history.getDate());
				pStmt.setDate(4, date);
				pStmt.setInt(5, history.getTotal());
				rowUpdated = pStmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close();
			}
	    	return rowUpdated;
	  }
	 
	 public  Integer totalAmount(String firstname,String lastname) {
			Integer totalAmount = 0;
			
			try {
				connection = dataSource.getConnection();
				stmt = connection.createStatement();
				rs = stmt.executeQuery("SELECT price from buylist where firstname = '" + firstname + "' and lastname = '" + lastname + "';");
				while (rs.next()) {
					totalAmount += rs.getInt("price");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close();
			}
			return totalAmount;
		}
	 
	public String nameList(String firstname,String lastname) {
		String nameList = "";

		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select name from buylist where firstname = '" + firstname + "' and lastname = '" + lastname + "';");

			while (rs.next()) {

					nameList += rs.getString("name").concat(",");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return nameList;
	}
	
	public int removeMaterial(long id) {
    	int rowUpdated = 0;
    	try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("delete from buyList where id=?;");
					pStmt.setLong(1, id);
			rowUpdated = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
    	return rowUpdated;
    }
	
	public BuyList getMaterialFromBuyList(Long id) {

		BuyList material = null;
		try {
			connection = dataSource.getConnection();

			stmt = connection.createStatement();

			rs = stmt.executeQuery("select * from buylist where id = '" + id + "';");

			while (rs.next()) {
				material = (new BuyList(rs.getLong("id"),rs.getString("firstname"),rs.getString("lastname"), rs.getString("name"), rs.getInt("price"), rs.getString("description"),rs.getString("image"),rs.getString("category"),rs.getInt("instock")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return material;
	}
	
	public List<BuyList> buyList(String firstname,String lastname) {
		List<BuyList> list = new ArrayList<>();

		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from buylist where firstname = '" + firstname + "' and lastname = '" + lastname + "' ;");

			while (rs.next()) {
				list.add(new BuyList(rs.getLong("id"),rs.getString("firstname"),rs.getString("lastname"), rs.getString("name"), rs.getInt("price"), rs.getString("description"),rs.getString("image"),rs.getString("category"),rs.getInt("instock")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	public void updateInstock(Long id) {
		InventoryDAO inventoryDAO = new InventoryDAO(dataSource);
		Inventory inventory = inventoryDAO.getMaterial(id);
		Integer instock= inventory.getInstock() - 1;
		Inventory inv = new Inventory(inventory.getId(), inventory.getName(), inventory.getPrice(), inventory.getDescription(), inventory.getImage(), inventory.getCategory(), instock);
		inventoryDAO.updateMaterial(inv);
	}
	
	public void updateInstockRemove(Long id) {
		
	}
	
	public int insertBuyList(Long id,String firstname,String lastname) {
		int rowEffected = 0;
		InventoryDAO inventoryDAO = new InventoryDAO(dataSource);	
		Inventory inventory = inventoryDAO.getMaterial(id);
		
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("insert into buylist  (id,firstname,lastname,name,price,description,image,category,instock) values (?,?,?,?,?,?,?,?,?);");
			pStmt.setLong(1, id);
			pStmt.setString(2, firstname);
			pStmt.setString(3, lastname);
			pStmt.setString(4, inventory.getName());
			pStmt.setLong(5, inventory.getPrice());
			pStmt.setString(6, inventory.getDescription());
			pStmt.setString(7, inventory.getImage());
			pStmt.setString(8, inventory.getCategory());
	        pStmt.setInt(9, inventory.getInstock());

			rowEffected = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return rowEffected;
	}

	public List<Inventory> showAllList() {
		List<Inventory> list = new ArrayList<>();

		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from inventory;");

			while (rs.next()) {
				list.add(new Inventory(rs.getLong("id"), rs.getString("name"), rs.getInt("price"),
						rs.getString("description"),rs.getString("image"), rs.getString("category"), rs.getInt("instock")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public Inventory getMaterial(Long id) {

		Inventory material = null;
		try {
			connection = dataSource.getConnection();

			stmt = connection.createStatement();

			rs = stmt.executeQuery("select * from inventory where id = '" + id + "';");

			while (rs.next()) {
				material = new Inventory(rs.getLong("id"), rs.getString("name"), rs.getInt("price"), rs.getString("description"), rs.getString("image"), rs.getString("category"), rs.getInt("instock"));			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return material;
	}
	  public int setMaterial(Inventory inventory) {
		  System.out.println("In the inventory DAO setMaterial function");
		  int rowUpdated = 0;
	    	try {
				connection = dataSource.getConnection();
				pStmt = connection.prepareStatement("insert into inventory "
						+ "(name,price,description,image,category,instock)"
						+ "values (?,?,?,?,?,?)");
				pStmt.setString(1, inventory.getName());
				pStmt.setInt(2, inventory.getPrice());
				pStmt.setString(3, inventory.getDescription());
				pStmt.setString(4, inventory.getImage());
				pStmt.setString(5, inventory.getCategory());
				pStmt.setInt(6, inventory.getInstock());
				
				rowUpdated = pStmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close();
			}
	    	return rowUpdated;
	  }
	  
	    public int updateMaterial(Inventory inventory) {
	    	int rowUpdated = 0;
	    	try {
				connection = dataSource.getConnection();
				pStmt = connection.prepareStatement("update inventory set "
						+ "name=?,"
						+ "price=?,"
						+ "description=?,"
						+ "image=?,"
						+ "category=?,"
						+ "instock=? "
						+ "where id = ?;");
				pStmt.setString(1, inventory.getName());
				pStmt.setInt(2, inventory.getPrice());
				pStmt.setString(3, inventory.getDescription());
				pStmt.setString(4, inventory.getImage());
				pStmt.setString(5, inventory.getCategory());
				pStmt.setInt(6, inventory.getInstock());				
				pStmt.setLong(7, inventory.getId());
				
				rowUpdated = pStmt.executeUpdate();	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close();
			}
	    	return rowUpdated;
	    }
	    public int deleteMaterial(long id) {
	    	int rowUpdated = 0;
	    	try {
				connection = dataSource.getConnection();
				pStmt = connection.prepareStatement("delete from inventory where id=?;");
						pStmt.setLong(1, id);
				rowUpdated = pStmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close();
			}
	    	return rowUpdated;
	    }

}
