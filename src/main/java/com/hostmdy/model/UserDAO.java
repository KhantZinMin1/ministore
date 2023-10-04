package com.hostmdy.model;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.hostmdy.crypto.PasswordEncoder;
import com.hostmdy.crypto.PasswordValidator;

public class UserDAO {
	
	private DataSource dataSource;
	private Connection connection;
	private PreparedStatement pStmt;
	private ResultSet rs;

	public UserDAO(DataSource dataSource) {
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
	
	public User getUserByEmail(String email) {
		User user = null;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("select * from user where email = ?;");
			pStmt.setString(1, email);
			rs = pStmt.executeQuery();
			while (rs.next()) {
				user = new User(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), rs.getString("password"), rs.getBoolean("active"), rs.getString("role"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return user;
	}
	
	
	
	public Boolean isUserValid (String email,String password) {
		Boolean valid = false;
		User user = getUserByEmail(email);
		
		if(user == null) {
			return valid;
		}
		
		try {
			if(PasswordValidator.validatePassword(password, user.getPassword()) && user.getActive()) {
				valid = true;
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return valid;
	}
	
	public int register(User user) {
		int rowEffected = 0;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("insert into user "
					+ "(firstname,lastname,email,password,active,role) "
					+ "values(?,?,?,?,?,?);");
			pStmt.setString(1, user.getFirstname());
			pStmt.setString(2, user.getLastname());
			pStmt.setString(3, user.getEmail());
			String encryptedPassword = "";
			try {
			    encryptedPassword = PasswordEncoder.encode(user.getPassword());
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pStmt.setString(4, encryptedPassword);
			pStmt.setBoolean(5, user.getActive());
			pStmt.setString(6, user.getRole());
			rowEffected = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;
	}
	

}
