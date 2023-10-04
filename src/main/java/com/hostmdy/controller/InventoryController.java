package com.hostmdy.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.hostmdy.model.BuyList;
import com.hostmdy.model.History;
import com.hostmdy.model.Inventory;
import com.hostmdy.model.InventoryDAO;
import com.hostmdy.model.User;

/**
 * Servlet implementation class InventoryController
 */

@MultipartConfig

public class InventoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/ministore")
	private DataSource dataSource;

	private InventoryDAO inventoryDAO;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		inventoryDAO = new InventoryDAO(dataSource);
	}

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InventoryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mode = request.getParameter("mode");

		if (mode == null) {
			mode = "LIST";
			}
		switch (mode) {
		case "LIST":
			showList(request, response);
			break;
			
		case "SHOW_FORM":
			showForm(request, response);
			break;
			
		case "CREATE":
			create(request, response);
			break;
			
		case "LOAD":
			loadMaterial(request, response);
			break;
			
		case "UPDATE":
			updateMaterial(request, response);
			break;
			
		case "DELETE":
			deleteMaterial(request, response);
			break;
			
		case "ADD_CART":
			insertMaterial(request, response);
			break;
			
		case "SHOW_CART":
			showCart(request, response);
			break;
			
		case "REMOVE":
			removeItemFromCart(request, response);
			break;
			
		case "BUY":
			buy(request, response);
			break;
			
		case "HISTORY":
			history(request, response);
			break;

		default:
			showList(request, response);
			break;
		
		}	
	}

	private void history(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if (user == null) {
			PrintWriter out = response.getWriter();
			out.print("You need first to login");
		}else {
		
		List<History> historyList = new ArrayList<>();
		if (user.getRole().equals("ROLE_USER")) {
			historyList = inventoryDAO.getHistoryByName(user.getFirstname(), user.getLastname());
		}else { 
			historyList = inventoryDAO.getHistory();
			}	
		request.setAttribute("historyList", historyList );
		RequestDispatcher dispatcher = request.getRequestDispatcher("history-form.jsp");
		dispatcher.forward(request, response);
		}
	}


	private void buy(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		PrintWriter out = response.getWriter();
		if (inventoryDAO.totalAmount(user.getFirstname(),user.getLastname()) == 0) {
			out.print("Choose Material!");
		}else {
		
		String firstname = user.getFirstname();
		String lastname = user.getLastname();
		Integer totalAmount = inventoryDAO.totalAmount(user.getFirstname(),user.getLastname());
		String list = inventoryDAO.nameList(firstname,lastname);
		LocalDate date = LocalDate.now();
		
		inventoryDAO.setHistory(new History(firstname, lastname, list, date, totalAmount));
		inventoryDAO.deleteBuylist(firstname,lastname);
		showCart(request, response);
		}
		
	}


	private void removeItemFromCart(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		Long id = Long.parseLong(request.getParameter("id"));
		System.out.println("id is "+id);
		Inventory inventory = inventoryDAO.getMaterial(id);

		BuyList cartItem=inventoryDAO.getMaterialFromBuyList(id);
		if (inventory != null) {
			//change cartItem to inventoyr item
			Inventory inv = new Inventory(cartItem.getId(),cartItem.getName(), cartItem.getPrice(), cartItem.getDescription(), cartItem.getImage(), cartItem.getCategory(),inventory.getInstock()+1 );	
			inventoryDAO.updateMaterial(inv);
		}else {
			Inventory inv = new Inventory(cartItem.getName(), cartItem.getPrice(), cartItem.getDescription(), cartItem.getImage(), cartItem.getCategory(),cartItem.getInstock() );	
			inventoryDAO.setMaterial(inv);
		}
		
		
//		List<Inventory> inventories = inventoryDAO.showAllList();
//		
//		for (Inventory inventory : inventories) {
//			BuyList material = inventoryDAO.getMaterialFromBuyList(id);
//			//if (material.getName().equals(inventory.getName()) ) {
//			if (material.getId()==(inventory.getId()) ) {
//				Inventory inv = new Inventory(inventory.getId(),material.getName(), material.getPrice(), material.getDescription(), material.getImage(), material.getCategory(),material.getInstock() );
//				inventoryDAO.updateMaterial(inv);
//				break;
//			}else { //out of stock
//				Inventory inv = new Inventory(material.getName(), material.getPrice(), material.getDescription(), material.getImage(), material.getCategory(),material.getInstock() );
//				inventoryDAO.setMaterial(inv);
//				break;
//			}
//		}
		
		int rowEffected = inventoryDAO.removeMaterial(id);
		PrintWriter out = response.getWriter();
		if (rowEffected > 0) {
			showCart(request, response);
		} else
			out.print("Fail to remove material!");
	}


	private void showCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if (user == null) {
			PrintWriter out = response.getWriter();
			out.print("You need first to login");
		}else {
		
		Integer total = inventoryDAO.totalAmount(user.getFirstname(),user.getLastname());
		List<BuyList> buyList = inventoryDAO.buyList(user.getFirstname(),user.getLastname());
		request.setAttribute("buyList", buyList);
		request.setAttribute("total", total);
		RequestDispatcher dispatcher = request.getRequestDispatcher("cart-form.jsp");
		dispatcher.forward(request, response);
		}
	}
	

	private void insertMaterial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if (user == null) {
			response.sendRedirect("login");
		}else {
		
		Long id = Long.parseLong(request.getParameter("id"));
		int rowEffected = inventoryDAO.insertBuyList(id,user.getFirstname(),user.getLastname());
		inventoryDAO.updateInstock(id);
		List<Inventory> inventoryList = inventoryDAO.showAllList();
		for(Inventory inv : inventoryList) {
			if (inv.getInstock()<1) {
				inventoryDAO.deleteMaterial(inv.getId());
			}
		}
		PrintWriter out = response.getWriter();
		if (rowEffected > 0) {
			showList(request, response);
		} else
			out.print("Fail to cart material!");
		}
	}
	


	private void deleteMaterial(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		Long id = Long.parseLong(request.getParameter("id"));

		int rowEffected = inventoryDAO.deleteMaterial(id);
		PrintWriter out = response.getWriter();
		if (rowEffected > 0) {
			showList(request, response);
		} else
			out.print("Fail to delete material!");
	}

	private void addImage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		Part file = request.getPart("image");
		String imageFileName = file.getSubmittedFileName();
		if (!imageFileName.isEmpty()) {
			
		String uploadPath = "C:/JavaEEBatch3WS/ministore/src/main/webapp/images/"+imageFileName;
		
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(uploadPath);
			InputStream is = file.getInputStream();
		    
			byte[] data = new byte[is.available()];
			is.read(data);
			fos.write(data);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(fos != null) {
			fos.close();}
		}
		}
		
	}

	private void create(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//check value for inserted image
		Boolean isadd=true;
		
		String name = request.getParameter("name");
		Integer price = Integer.parseInt(request.getParameter("price"));
		String description = request.getParameter("description");
		Part file = request.getPart("image");
		String imageFileName = file.getSubmittedFileName();
		
		//check before insert into db
		//get all inverntory data
		List<Inventory> inventoryList = inventoryDAO.showAllList();
		request.setAttribute("inventoryList", inventoryList);
		for(Inventory inv: inventoryList) {
			if (inv.getImage().equals(imageFileName))
			{
				isadd=false;
			}
			
		}
		
		if(isadd) {
			addImage(request, response);
			String category = request.getParameter("category");
			Integer instock = Integer.parseInt(request.getParameter("instock"));
			
			Inventory inventory = new Inventory(name, price, description, imageFileName, category, instock);
			int rowEffected = inventoryDAO.setMaterial(inventory);
			
			
			PrintWriter out = response.getWriter();
			if (rowEffected > 0) {
				//showList(request, response);
				showList(request, response);
			} else
				out.print("Fail to create new material!");
			
		}
		else {
			showList(request, response);
			
		}
		
		
	}


	private void showForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("add-material.jsp");
		dispatcher.forward(request, response);
	}


	private void updateMaterial(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		addImage(request, response);
		Long id = Long.parseLong(request.getParameter("id"));
		String name = request.getParameter("name");
		Integer price = Integer.parseInt(request.getParameter("price"));
		String description = request.getParameter("description");
		Part file = request.getPart("image");
		String imageFileName = file.getSubmittedFileName();
		System.out.println("image file Name is "+imageFileName);
		if (imageFileName.isEmpty())
		{
			Inventory inv=inventoryDAO.getMaterial(id);
			imageFileName=inv.getImage();
		}
		String category = request.getParameter("category");
		Integer instock = Integer.parseInt(request.getParameter("instock"));
		
		Inventory inventory = new Inventory(id, name, price, description, imageFileName, category, instock);
		int rowEffected = inventoryDAO.updateMaterial(inventory);
		PrintWriter out = response.getWriter();
		if (rowEffected > 0) {
			showList(request, response);
		} else
			out.print("Fail to update material!");
	}


	private void loadMaterial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Long id = Long.parseLong(request.getParameter("id"));

		Inventory inventory = inventoryDAO.getMaterial(id);
		request.setAttribute("inventory", inventory);
		RequestDispatcher dispatcher = request.getRequestDispatcher("update-material.jsp");
		dispatcher.forward(request, response);
	}


	private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Inventory> inventoryList = inventoryDAO.showAllList();
		request.setAttribute("inventoryList", inventoryList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
