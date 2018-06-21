/*
	create table if not exists users(name varchar(32), fname varchar(32), lname varchar(32), cnp varchar(32), bday int(2), bmonth int (2), byear int(4), email varchar(32), phone varchar(32), address varchar(32), state varchar(32), city varchar(32), pc varchar(32), country varchar(32), borrowed_book varchar(32), borrowed_time varcha(32));

	create table if not exists books(id varchar(32), name varchar(32), author varchar(32), domain varchar(32), year int(4), ph varchar(32), borrowed_varchar varchar(32), borrowed_time varchar(32));

  Mysql not implemented yet!
*/

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*; 
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.*;

public class Main
{
	private String title = "Online Library";	
	private String exit_icon_path = "images/exit.png";
	private String newLine = System.getProperty("line.separator");

	//JFrame/JPanel
	private JFrame frame = new JFrame(title);
	private JPanel panel = new JPanel();

	//Vars
        private int Day = 0, Month = 0, Year = 0; //add variables
        private int mDay = 0, mMonth = 0, mYear = 0; //modify variables
        private int bYear = 0;
        private int bmYear = 0;

	public enum Users_Info
	{
		Name,
		fName,
		lName,
		CNP,
		Birth_Day,
		Birth_Month,
		Birth_Year,
		Email,
		Phone,
		City,
		Address,
		State,
		PC,
		Country,
		Borrowed,
		Borrowed_Time
	}
	public enum Books_Info
	{
		ID,
		Year,
		Name,
		Domain,
		Author,
		PH,
		Borrowed_By,
		Borrowed_Time

	}
	public static class Books
	{
		private String id;
		private String year;
		private String name;
		private String domain;
		private String author;
		private String publishing_house;
		private String borrowed_by;
		private String borrowed_time;
		
		public void set_book(Books_Info Book_Type, String param)
		{
			switch(Book_Type)
			{
				case ID:
					id = param;
					break;
					
				case Year:
					year = param;
					break;
					
				case Name:
					name = param;
					break;
					
				case Domain:
					domain = param;
					break;
					
				case Author:
					author = param;
					break;
					
				case PH:
					publishing_house = param;
					break;
				
				case Borrowed_By:
					borrowed_by = param;
					break;

				case Borrowed_Time:
					borrowed_time = param;
					break;

			}
		}
		public String get_book(Books_Info Book_Type)
		{
			String Book = null;

			switch(Book_Type)
			{
				case ID:
					Book = id;
					break;
				case Year:
					Book = year;
					break;
				case Name:
					Book = name;
					break;
				case Domain:
					Book = domain;
					break;
				case Author:
					Book = author;
					break;
				case PH:
					Book = publishing_house;
					break;

				case Borrowed_By:
					Book = borrowed_by;
					break;

				case Borrowed_Time:
					Book = borrowed_time;
					break;
			}
			return Book;
		}
	}
	
	public static class Users
	{
		private String name;
		private String fname;
		private String lname;
		private String cnp;
		private String birth_day;
		private String birth_month;
		private String birth_year;
		private String email;
		private String phone;
		private String city;
		private String address;
		private String state;
		private String postal_code;
		private String country;
		private String borrowed;
		private String borrowed_time;
						
		public void set_user(Users_Info User_Type, String param)
		{
			switch(User_Type)
			{
				case Name:
					name = param;
					break;

				case fName:
					fname = param;
					break;
					
				case lName:
					lname = param;
					break;
					
				case CNP:
					cnp = param;
					break;
					
				case Birth_Day:
					birth_day = param;
					break;
					
				case Birth_Month:
					birth_month = param;
					break;
					
				case Birth_Year:
					birth_year = param;
					break;
									
				case Email:
					email = param;
					break;						
					
				case Phone:
					phone = param;
					break;	
			
				case Address:
					address = param;
					break;	
								
				case City:
					city = param;
					break;			
					
				case State:
					state = param;
					break;		
							
				case PC:
					postal_code = param;
					break;	
								
				case Country:
					country = param;
					break;
													
				case Borrowed:
					borrowed = param;
					break;
													
				case Borrowed_Time:
					borrowed_time = param;
					break;
			}
		}
		public String get_user(Users_Info User_Type)
		{
			String User = null;

			switch(User_Type)
			{
				case Name:
					User = name;
					break;

				case fName:
					User = fname;
					break;
					
				case lName:
					User = lname;
					break;
					
				case CNP:
					User = cnp;
					break;
					
				case Birth_Day:
					User = birth_day;
					break;
					
				case Birth_Month:
					User = birth_month;
					break;
					
				case Birth_Year:
					User = birth_year;
					break;
					
				case Email:
					User = email;
					break;			
									
				case Phone:
					User = phone;
					break;			

				case Address:
					User = address;
					break;
					
				case City:
					User = city;
					break;
						
				case State:
					User = state;
					break;		
							
				case PC:
					User = postal_code;
					break;	
								
				case Country:
					User = country;
					break;
					
				case Borrowed:
					User = borrowed;
					break;
													
				case Borrowed_Time:
					User = borrowed_time;
					break;
			}
			return User;
		}
	}

	private static void init_gui() 
	{
		Main main = new Main();
		
		List<Users> Objects_Users = new ArrayList<Users>();		
		List<Books> Objects_Books = new ArrayList<Books>();		
		
		Books_Info Book_Type = Books_Info.ID;
		Users_Info User_Type = Users_Info.Name;
		
		GridBagConstraints gbc = new GridBagConstraints();

		JMenuBar menubar = new JMenuBar();
		
		ImageIcon exit_icon = create_image_icon(main.exit_icon_path);

		JMenu main_menu = new JMenu("Main");
		JMenuItem load_main_menu = new JMenuItem("Load Mysql");
		JMenuItem save_main_menu = new JMenuItem("Save Mysql");
		JMenuItem exit_main_menu = new JMenuItem("Exit!", exit_icon);

		JMenu books_menu = new JMenu("Books");
		
		//
		JMenuItem list_books_menu = new JMenuItem("List Books!"); 
		JLabel list_books_label = new JLabel("<html><h1><b>Books List:</b></h1></html>");
		
		DefaultTableModel list_books_model = new DefaultTableModel() 
		{
    			@Override
    			public boolean isCellEditable(int row, int column) 
    			{
       				return false;
    			}
    			public void removeColumn(int column) 
    			{
			    	Vector rows = dataVector;
			        for (Object row : rows) 
			        {
			        	((Vector) row).remove(column);
        			}

	        		columnIdentifiers.remove(column);
        			fireTableStructureChanged();
    			}
		};

        	list_books_model.addColumn("ID");
        	list_books_model.addColumn("Name");
        	list_books_model.addColumn("Author");
        	list_books_model.addColumn("Domain");
        	list_books_model.addColumn("Year");
        	list_books_model.addColumn("PH");
        
		JTable list_books_table = new JTable(list_books_model);      
        		
		JScrollPane scroll_books_table =new JScrollPane(list_books_table);  
		DefaultTableModel list_books_model2 = (DefaultTableModel) list_books_table.getModel();		
		
		//
		JMenuItem add_books_menu = new JMenuItem("Add Book"); 
		JLabel add_books_label = new JLabel("<html><h1><b>Add Books:</b></h1></html>");

		JLabel add_books_id_label = new JLabel("ID:");			
		JLabel add_books_name_label = new JLabel("Name:");
		JLabel add_books_author_label = new JLabel("Author:");
		JLabel add_books_domain_label = new JLabel("Domain:");
		JLabel add_books_year_label = new JLabel("Year:");
		JLabel add_books_ph_label = new JLabel("Publishing House:");
		JLabel add_books_message = new JLabel("*");

		JTextField add_id_books_textfield = new JTextField("ID1");				
		JTextField add_name_books_textfield = new JTextField("name");		
		JTextField add_author_books_textfield = new JTextField("author");
		JTextField add_domain_books_textfield = new JTextField("domain");
		JTextField add_year_books_textfield = new JTextField("1900");
		JTextField add_ph_books_textfield = new JTextField("publishing");

		JButton add_books_create_button = new JButton("Create");
		JButton add_books_clear_button = new JButton("Clear Fields");		

		//
		JMenuItem modify_books_menu = new JMenuItem("Modify Book"); 
		JLabel modify_books_label = new JLabel("<html><h1><b>Modify Books:</b></h1></html>");

		JLabel modify_books_id_label = new JLabel("ID:");			
		JLabel modify_books_name_label = new JLabel("Name:");
		JLabel modify_books_author_label = new JLabel("Author:");
		JLabel modify_books_domain_label = new JLabel("Domain:");
		JLabel modify_books_year_label = new JLabel("Year:");
		JLabel modify_books_ph_label = new JLabel("Publishing House:");
		JLabel modify_books_message = new JLabel("*");

		JTextField modify_id_books_textfield = new JTextField("ID1");				
		JTextField modify_name_books_textfield = new JTextField("name");		
		JTextField modify_author_books_textfield = new JTextField("author");
		JTextField modify_domain_books_textfield = new JTextField("domain");
		JTextField modify_year_books_textfield = new JTextField("1900");
		JTextField modify_ph_books_textfield = new JTextField("publishing");

		JButton modify_books_create_button = new JButton("Modify!");
		JButton modify_books_clear_button = new JButton("Clear Fields");		

		//		
		JMenuItem search_books_menu = new JMenuItem("Search Book"); 
		JLabel search_books_label = new JLabel("<html><h1><b>Search Book:</b></h1></html>");
	
		JLabel search_books_name_label = new JLabel("Name:");
		JLabel search_books_author_label = new JLabel("Author:");
		
		JLabel search_books_message = new JLabel("*");
		
		JTextField search_name_books_textfield = new JTextField(14);
		JTextField search_author_books_textfield = new JTextField(14);				
				
		JButton search_books_name_button = new JButton("Search Name!");
		JButton search_books_author_button = new JButton("Search Author!");
		
		
		//
		JMenuItem delete_books_menu = new JMenuItem("Delete Book"); 
		JLabel delete_books_label = new JLabel("<html><h1><b>Delete Book:</b></h1></html>");

		JLabel delete_books_name_label = new JLabel("Book Name:");
		JLabel delete_books_message = new JLabel("*");

		JTextField delete_name_books_textfield = new JTextField(14);				
		JButton delete_books_button = new JButton("Delete!");
		
		//		
		JMenu users_menu = new JMenu("Users");
		//
		JMenuItem list_users_menu = new JMenuItem("List Users!"); 
		JLabel list_users_label = new JLabel("<html><h1><b>Users List:</b></h1></html>");
		
		DefaultTableModel list_users_model = new DefaultTableModel() 
		{
    			@Override
    			public boolean isCellEditable(int row, int column) 
    			{
       				return false;
    			}
    			public void removeColumn(int column) 
    			{
			    	Vector rows = dataVector;
			        for (Object row : rows) 
			        {
			        	((Vector) row).remove(column);
        			}

	        		columnIdentifiers.remove(column);
        			fireTableStructureChanged();
    			}
		};

        	list_users_model.addColumn("Name");
        	list_users_model.addColumn("FName");
        	list_users_model.addColumn("LName");
        	list_users_model.addColumn("BDay");
        	list_users_model.addColumn("BMonth");
        	list_users_model.addColumn("BYear");
        	list_users_model.addColumn("CNP");
        	list_users_model.addColumn("EMAIL");
        	list_users_model.addColumn("PHONE");
        	list_users_model.addColumn("ADDRESS");
        	list_users_model.addColumn("PC");
        	list_users_model.addColumn("CITY");
        	list_users_model.addColumn("STATE");
        	list_users_model.addColumn("COUNTRY");
        
		JTable list_users_table = new JTable(list_users_model);      
        		
		JScrollPane scroll_users_table =new JScrollPane(list_users_table);  
		DefaultTableModel list_users_model2 = (DefaultTableModel) list_users_table.getModel();

		//
		JMenuItem borrow_users_menu = new JMenuItem("Borrow Book!"); 
		JLabel borrow_users_label = new JLabel("<html><h1><b>Borrow Book:</b></h1></html>");

		JLabel borrow_users_name_label = new JLabel("UserName:");
		JLabel borrow_users_book_label = new JLabel("Book Name:");

		JLabel borrow_users_message = new JLabel("*");

		JTextField borrow_name_users_textfield = new JTextField("name");				
		JTextField borrow_book_users_textfield = new JTextField("book_name");		

		JButton borrow_users_create_button = new JButton("Borrow!");

		//
		JMenuItem return_users_menu = new JMenuItem("Return Book"); 
		JLabel return_users_label = new JLabel("<html><h1><b>Return Book:</b></h1></html>");

		JLabel return_users_name_label = new JLabel("UserName:");
		JLabel return_users_book_label = new JLabel("Book Name:");

		JLabel return_users_message = new JLabel("*");

		JTextField return_name_users_textfield = new JTextField("name");				
		JTextField return_book_users_textfield = new JTextField("book_name");		

		JButton return_users_create_button = new JButton("Return!");

		//Users Add		
		JMenuItem add_users_menu = new JMenuItem("Add User");				 

		JLabel add_users_label = new JLabel("<html><h1><b>Add User:</b></h1></html>");
		JLabel add_users_name_label = new JLabel("UserName:");
		JLabel add_users_fname_label = new JLabel("First Name:");
		JLabel add_users_lname_label = new JLabel("Last Name:");
		JLabel add_users_day_label = new JLabel("Birth-Day:");
		JLabel add_users_month_label = new JLabel("Birth-Month:");
		JLabel add_users_year_label = new JLabel("Birth-Year:");
		JLabel add_users_cnp_label = new JLabel("CNP:");
		JLabel add_users_email_label = new JLabel("E-Mail:");
		JLabel add_users_phone_label = new JLabel("Phone:");
		JLabel add_users_address_label = new JLabel("Address:");
		JLabel add_users_postal_label = new JLabel("Postal Code:");
		JLabel add_users_city_label = new JLabel("City:");
		JLabel add_users_state_label = new JLabel("State:");
		JLabel add_users_country_label = new JLabel("Country:");
		JLabel add_users_message = new JLabel("*");

		JTextField add_name_users_textfield = new JTextField("name");				
		JTextField add_fname_users_textfield = new JTextField("fname");		
		JTextField add_lname_users_textfield = new JTextField("lname");
		JTextField add_cnp_users_textfield = new JTextField("1234567890111");
		JTextField add_day_users_textfield = new JTextField("1");
		JTextField add_month_users_textfield = new JTextField("1");
		JTextField add_year_users_textfield = new JTextField("1901");
		JTextField add_email_users_textfield = new JTextField("email");
		JTextField add_phone_users_textfield = new JTextField("12345");
		JTextField add_address_users_textfield = new JTextField("address");
		JTextField add_postal_users_textfield = new JTextField("postal");
		JTextField add_city_users_textfield = new JTextField("city");
		JTextField add_state_users_textfield = new JTextField("state");
		JTextField add_country_users_textfield = new JTextField("country");

		JButton add_users_create_button = new JButton("Create");
		JButton add_users_clear_button = new JButton("Clear Fields");		
		//
		JMenuItem search_users_menu = new JMenuItem("Search User"); 
		JLabel search_users_label = new JLabel("<html><h1><b>Search User:</b></h1></html>");

		JLabel search_users_name_label = new JLabel("UserName:");
		JLabel search_users_cnp_label = new JLabel("CNP:");

		JLabel search_users_message = new JLabel("*");

		JTextField search_name_users_textfield = new JTextField(14);
		JTextField search_cnp_users_textfield = new JTextField(14);				
				
		JButton search_users_name_button = new JButton("Search Username!");
		JButton search_users_cnp_button = new JButton("Search CNP!");

		//
		JMenuItem delete_users_menu = new JMenuItem("Delete User"); 
		JLabel delete_users_label = new JLabel("<html><h1><b>Delete User:</b></h1></html>");

		JLabel delete_users_name_label = new JLabel("UserName:");
		JLabel delete_users_message = new JLabel("*");

		JTextField delete_name_users_textfield = new JTextField(14);				
		JButton delete_users_button = new JButton("Delete!");

		//
		JMenuItem modify_users_menu = new JMenuItem("Modify User"); 
		JLabel modify_users_label = new JLabel("<html><h1><b>Modify User:</b></h1></html>");
		JLabel modify_users_name_label = new JLabel("UserName:");
		JLabel modify_users_fname_label = new JLabel("First Name:");
		JLabel modify_users_lname_label = new JLabel("Last Name:");
		JLabel modify_users_day_label = new JLabel("Birth-Day:");
		JLabel modify_users_month_label = new JLabel("Birth-Month:");
		JLabel modify_users_year_label = new JLabel("Birth-Year:");
		JLabel modify_users_cnp_label = new JLabel("CNP:");
		JLabel modify_users_email_label = new JLabel("E-Mail:");
		JLabel modify_users_phone_label = new JLabel("Phone:");
		JLabel modify_users_address_label = new JLabel("Address:");
		JLabel modify_users_postal_label = new JLabel("Postal Code:");
		JLabel modify_users_city_label = new JLabel("City:");
		JLabel modify_users_state_label = new JLabel("State:");
		JLabel modify_users_country_label = new JLabel("Country:");
		JLabel modify_users_message = new JLabel("*");

		JTextField modify_name_users_textfield = new JTextField("name");				
		JTextField modify_fname_users_textfield = new JTextField("fname");		
		JTextField modify_lname_users_textfield = new JTextField("lname");
		JTextField modify_cnp_users_textfield = new JTextField("1234567890111");
		JTextField modify_day_users_textfield = new JTextField("1");
		JTextField modify_month_users_textfield = new JTextField("1");
		JTextField modify_year_users_textfield = new JTextField("1901");
		JTextField modify_email_users_textfield = new JTextField("email");
		JTextField modify_phone_users_textfield = new JTextField("12345");
		JTextField modify_address_users_textfield = new JTextField("address");
		JTextField modify_postal_users_textfield = new JTextField("postal");
		JTextField modify_city_users_textfield = new JTextField("city");
		JTextField modify_state_users_textfield = new JTextField("state");
		JTextField modify_country_users_textfield = new JTextField("country");

		JButton modify_users_create_button = new JButton("Modify!");
		JButton modify_users_clear_button = new JButton("Clear Fields");		
		//
		JMenu about_menu = new JMenu("Credits");
				
		main.frame.setLayout(new BorderLayout());
		main.panel.setLayout(new GridBagLayout());	
		//main.panel.setBackground(Color.BLACK);  					
		
		about_menu.addMouseListener(new MouseListener() 
		{
			
			@Override
			public void mousePressed(MouseEvent e) 
			{

			}
		            
			@Override
			public void mouseReleased(MouseEvent e) 
			{

			}
	               
			@Override
			public void mouseExited(MouseEvent e) 
			{

			}
		             
			@Override
			public void mouseEntered(MouseEvent e) 
			{

			}
			
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if(e.getSource() != null)
				{	
					JOptionPane.showOptionDialog(main.frame, 
	 					new JLabel("<html><center>Developed by:</center><br>Papancea Adelin</html>",JLabel.CENTER),
         					"Credits", 
        					JOptionPane.OK_CANCEL_OPTION, 
        					JOptionPane.PLAIN_MESSAGE, 
       						null, 
        					new String[]{"Exit!"},
        					"default");
				}
		                 
			}
		});
				
		menubar.add(main_menu);

		exit_main_menu.setMnemonic(KeyEvent.VK_E);
		
		exit_main_menu.addActionListener((ActionEvent event) -> {
			System.exit(0);
        	});
		main_menu.add(load_main_menu);

		load_main_menu.addActionListener((ActionEvent event) -> {
			System.exit(0);
        	});

		main_menu.add(save_main_menu);

		save_main_menu.addActionListener((ActionEvent event) -> {
			System.exit(0);
        	});
		main_menu.addSeparator();
		main_menu.add(exit_main_menu);
		
		menubar.add(books_menu);
		
		books_menu.add(list_books_menu);
		list_books_menu.addActionListener((ActionEvent event) -> 
		{    			
		       	main.panel.removeAll();

			main.panel.add(scroll_books_table);
			SwingUtilities.updateComponentTreeUI(main.panel);

        	});    
        	    	
		books_menu.add(add_books_menu);
		add_books_menu.addActionListener((ActionEvent event) -> 
		{
		       	main.panel.removeAll();

                	gbc.gridx = 0;
                	gbc.gridy = 0;
			//gbc.weightx = 0;
			gbc.weighty = 0;

            		gbc.insets = new Insets(3, 3, 3, 3);
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.gridwidth = 1;

			main.panel.add(add_books_label, gbc);

			gbc.gridwidth = 3;
			++gbc.gridy;

			main.panel.add(add_books_id_label, gbc);
			gbc.gridx = 1;
			main.panel.add(add_id_books_textfield, gbc);								

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(add_books_name_label, gbc);
			gbc.gridx = 1;
			main.panel.add(add_name_books_textfield, gbc);								

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(add_books_author_label, gbc);
			gbc.gridx = 1;
			main.panel.add(add_author_books_textfield, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(add_books_domain_label, gbc);
			gbc.gridx = 1;
			main.panel.add(add_domain_books_textfield, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(add_books_year_label, gbc);
			gbc.gridx = 1;
			main.panel.add(add_year_books_textfield, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(add_books_ph_label, gbc);
			gbc.gridx = 1;
			main.panel.add(add_ph_books_textfield, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

      			add_books_create_button.addActionListener(new ActionListener() 
      			{
        			public void actionPerformed(ActionEvent e) 
        			{				
	         			if(add_id_books_textfield.getText().equals("") || add_id_books_textfield.getText().contains(" ")
	         			|| add_name_books_textfield.getText().equals("") || add_name_books_textfield.getText().contains(" ")
	         			|| add_author_books_textfield.getText().equals("") || add_author_books_textfield.getText().contains(" ")
	         			|| add_domain_books_textfield.getText().equals("") || add_domain_books_textfield.getText().contains(" ")
	         			|| add_year_books_textfield.getText().equals("") || add_year_books_textfield.getText().contains(" ")
	         			|| add_ph_books_textfield.getText().equals("") || add_ph_books_textfield.getText().contains(" ")
	         			)
	         			{
	         				add_books_message.setText("*Complete all fields!");
	         			}
	     				else
	     				{
	     					if(main.bYear > 0)
	     					{
	     						Books Object = new Books();
							
	     						Object.set_book(Book_Type.ID, add_id_books_textfield.getText());								     						 
	     						Object.set_book(Book_Type.Name, add_name_books_textfield.getText());
	     						Object.set_book(Book_Type.Author, add_author_books_textfield.getText());
	     						Object.set_book(Book_Type.Domain, add_domain_books_textfield.getText());
	     						Object.set_book(Book_Type.Year, add_year_books_textfield.getText());
	     						Object.set_book(Book_Type.PH, add_ph_books_textfield.getText());

        						list_books_model2.addRow(new Object[]
        						{
				        			Object.get_book(Book_Type.ID),
				        			Object.get_book(Book_Type.Name),
				        			Object.get_book(Book_Type.Author),
				        			Object.get_book(Book_Type.Domain),
				        			Object.get_book(Book_Type.Year),
	        						Object.get_book(Book_Type.PH)
        						});
        						
	     						Objects_Books.add(Object);
	     						
	     						add_books_message.setText("*Book was added!");
	     					}
	     					else
	     					{
			         			if(add_year_books_textfield.getText() != null)
			         			{
			         				try 
			         				{
		     							main.bYear = Integer.parseInt(add_year_books_textfield.getText());
		
		     							if(main.bYear < 1)
		     							{
										add_books_message.setText("*Year cannot be < 1!");
										main.bYear = 0;
									}
								}
								catch (NumberFormatException err) 
								{
									add_books_message.setText("*Year must be a number!");
		     							main.bYear = 0;
								}
			         			}
		         			
						}         				
	         			}	         			
        			}	
    			});

      			add_books_clear_button.addActionListener(new ActionListener() 
      			{
        			public void actionPerformed(ActionEvent e) 
        			{
        				if(add_id_books_textfield.getText() != null)
	         			{
	         				add_id_books_textfield.setText(null);
	         			}

	         			if(add_name_books_textfield.getText() != null)
	         			{
	         				add_name_books_textfield.setText(null);
	         			}
	         			
	         			if(add_author_books_textfield.getText() != null)
	         			{
	         				add_author_books_textfield.setText(null);
	         			}

	         			if(add_domain_books_textfield.getText() != null)
	         			{
	         				add_domain_books_textfield.setText(null);
	         			}

		         		if(add_year_books_textfield.getText() != null)
	         			{
	         				add_year_books_textfield.setText(null);
	         			}

	         			if(add_ph_books_textfield.getText() != null)
	         			{
	         				add_ph_books_textfield.setText(null);
	         			}
	         			
	         			main.bYear = 0;
	         			
	         			add_books_message.setText("*Clear Successfully");

        			}
    			});
			main.panel.add(add_books_create_button, gbc);
			gbc.gridx = 1;
			main.panel.add(add_books_clear_button, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(add_books_message, gbc);

			SwingUtilities.updateComponentTreeUI(main.panel);

        	});

		books_menu.add(modify_books_menu);
		modify_books_menu.addActionListener((ActionEvent event) -> 
		{
		       	main.panel.removeAll();

                	gbc.gridx = 0;
                	gbc.gridy = 0;
			//gbc.weightx = 0;
			gbc.weighty = 0;

            		gbc.insets = new Insets(3, 3, 3, 3);
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.gridwidth = 1;

			main.panel.add(modify_books_label, gbc);

			gbc.gridwidth = 3;
			++gbc.gridy;

			main.panel.add(modify_books_id_label, gbc);
			gbc.gridx = 1;
			main.panel.add(modify_id_books_textfield, gbc);								

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(modify_books_name_label, gbc);
			gbc.gridx = 1;
			main.panel.add(modify_name_books_textfield, gbc);								

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(modify_books_author_label, gbc);
			gbc.gridx = 1;
			main.panel.add(modify_author_books_textfield, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(modify_books_domain_label, gbc);
			gbc.gridx = 1;
			main.panel.add(modify_domain_books_textfield, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(modify_books_year_label, gbc);
			gbc.gridx = 1;
			main.panel.add(modify_year_books_textfield, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(modify_books_ph_label, gbc);
			gbc.gridx = 1;
			main.panel.add(modify_ph_books_textfield, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

      			modify_books_create_button.addActionListener(new ActionListener() 
      			{
        			public void actionPerformed(ActionEvent e) 
        			{				
	         			if(modify_id_books_textfield.getText().equals("") || modify_id_books_textfield.getText().contains(" ")
	         			|| modify_name_books_textfield.getText().equals("") || modify_name_books_textfield.getText().contains(" ")
	         			|| modify_author_books_textfield.getText().equals("") || modify_author_books_textfield.getText().contains(" ")
	         			|| modify_domain_books_textfield.getText().equals("") || modify_domain_books_textfield.getText().contains(" ")
	         			|| modify_year_books_textfield.getText().equals("") || modify_year_books_textfield.getText().contains(" ")
	         			|| modify_ph_books_textfield.getText().equals("") || modify_ph_books_textfield.getText().contains(" ")
	         			)
	         			{
	         				modify_books_message.setText("*Complete all fields!");
	         			}
	     				else
	     				{
	     					if(main.bmYear > 0)
	     					{
	     						
	     						for (Books Object: Objects_Books)
   							{
								if(Object.get_book(Book_Type.Name) != null 
								&& Object.get_book(Book_Type.Name).equals(modify_name_books_textfield.getText()))
 								{										
					     				Object.set_book(Book_Type.ID, modify_id_books_textfield.getText());								     						 
					     				Object.set_book(Book_Type.Name, modify_name_books_textfield.getText());
					     				Object.set_book(Book_Type.Author, modify_author_books_textfield.getText());
					     				Object.set_book(Book_Type.Domain, modify_domain_books_textfield.getText());
					     				Object.set_book(Book_Type.Year, modify_year_books_textfield.getText());
					     				Object.set_book(Book_Type.PH, modify_ph_books_textfield.getText());
									break;
 								}
 							}
 		 					while (list_books_model2.getRowCount() > 0) 
							{
				       				list_books_model2.removeRow(0);
							}
							
				   			for (Books Object: Objects_Books)
				   			{
				   				list_books_model2.addRow(new Object[]
				        			{
								       	Object.get_book(Book_Type.ID),
							        	Object.get_book(Book_Type.Name),
							        	Object.get_book(Book_Type.Author),
							        	Object.get_book(Book_Type.Domain),
							        	Object.get_book(Book_Type.Year),
						        		Object.get_book(Book_Type.PH)
				        			});
							}
	     						modify_books_message.setText("*Book was modified!");
	     					}
	     					else
	     					{
			         			if(modify_year_books_textfield.getText() != null)
			         			{
			         				try 
			         				{
		     							main.bmYear = Integer.parseInt(add_year_books_textfield.getText());
		
		     							if(main.bmYear < 1)
		     							{
										modify_books_message.setText("*Year cannot be < 1!");
										main.bmYear = 0;
									}
								}
								catch (NumberFormatException err) 
								{
									modify_books_message.setText("*Year must be a number!");
		     							main.bmYear = 0;
								}
			         			}
		         			
						}         				
	         			}	         			
        			}	
    			});

      			modify_books_clear_button.addActionListener(new ActionListener() 
      			{
        			public void actionPerformed(ActionEvent e) 
        			{
        				if(modify_id_books_textfield.getText() != null)
	         			{
	         				modify_id_books_textfield.setText(null);
	         			}

	         			if(modify_name_books_textfield.getText() != null)
	         			{
	         				modify_name_books_textfield.setText(null);
	         			}
	         			
	         			if(modify_author_books_textfield.getText() != null)
	         			{
	         				modify_author_books_textfield.setText(null);
	         			}

	         			if(modify_domain_books_textfield.getText() != null)
	         			{
	         				modify_domain_books_textfield.setText(null);
	         			}

		         		if(modify_year_books_textfield.getText() != null)
	         			{
	         				modify_year_books_textfield.setText(null);
	         			}

	         			if(modify_ph_books_textfield.getText() != null)
	         			{
	         				modify_ph_books_textfield.setText(null);
	         			}
	         			
	         			main.bmYear = 0;
	         			
	         			modify_books_message.setText("*Clear Successfully");

        			}
    			});
			main.panel.add(modify_books_create_button, gbc);
			gbc.gridx = 1;
			main.panel.add(modify_books_clear_button, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(modify_books_message, gbc);

			SwingUtilities.updateComponentTreeUI(main.panel);

        	});

		books_menu.add(search_books_menu);
		search_books_menu.addActionListener((ActionEvent event) -> 
		{
		       	main.panel.removeAll();          

                	gbc.gridy = 0;
			gbc.weighty = 0;

            		gbc.insets = new Insets(3, 3, 3, 3);
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.gridwidth = 3;
					       	  				
			main.panel.add(search_books_label, gbc);
			++gbc.gridy;
			
			main.panel.add(search_books_name_label, gbc);
			++gbc.gridy;

			main.panel.add(search_name_books_textfield, gbc);
			++gbc.gridy;

			main.panel.add(search_books_name_button, gbc);
			++gbc.gridy;
			
			main.panel.add(search_books_author_label, gbc);
			++gbc.gridy;

			main.panel.add(search_author_books_textfield, gbc);
			++gbc.gridy;

			main.panel.add(search_books_author_button, gbc);									
			++gbc.gridy;
			
			main.panel.add(search_books_message, gbc);	

      			search_books_name_button.addActionListener(new ActionListener() 
      			{
        			public void actionPerformed(ActionEvent e) 
        			{
        				if(Objects_Books.size() == 0)		         			
		         			search_books_message.setText("*No records!");
		         		else
		         		{
		         			if(search_name_books_textfield.getText() == null
		         			|| search_name_books_textfield.getText().contains(" "))
		         			{
		         				search_books_message.setText("*Invalid book name!");
		         			}	
		         			else
		         			{
		         				for (Books Object: Objects_Books)
   							{
								if(Object.get_book(Book_Type.Name) != null 
								&& Object.get_book(Book_Type.Name).equals(search_name_books_textfield.getText()))
 								{
 									search_books_message.setText("<html>*Found book: by name: " + search_name_books_textfield.getText() + "<br>" +
 										"ID: " + Object.get_book(Book_Type.ID) + "<br>" + 
 										"Author: " + Object.get_book(Book_Type.Author) + "<br>" + 
 										"Domain: " + Object.get_book(Book_Type.Domain) + "<br>" + 
 										"Year: " + Object.get_book(Book_Type.Year) + "<br>" + 
 										"Publishing House: " + Object.get_book(Book_Type.PH) + "</html>"
 									);
 									break;   				
   								}
   							}
						}
		         			//
		         		}
        			}
    			});

      			search_books_author_button.addActionListener(new ActionListener() 
      			{
        			public void actionPerformed(ActionEvent e) 
        			{
        				if(Objects_Books.size() == 0)		         			
		         			search_books_message.setText("*No records!");
		         		else
		         		{
		         			if(search_author_books_textfield.getText() == null
		         			|| search_author_books_textfield.getText().contains(" "))
		         			{
		         				search_books_message.setText("*Invalid author name!");
		         			}	
		         			else
		         			{
		         				for (Books Object: Objects_Books)
   							{
								if(Object.get_book(Book_Type.Author) != null 
								&& Object.get_book(Book_Type.Author).equals(search_author_books_textfield.getText()))
 								{
 									search_books_message.setText("<html>*Found book: by author: " + search_author_books_textfield.getText() + "<br>" +
 										"ID: " + Object.get_book(Book_Type.ID) + "<br>" + 
 										"Name: " + Object.get_book(Book_Type.Name) + "<br>" + 
 										"Domain: " + Object.get_book(Book_Type.Domain) + "<br>" + 
 										"Year: " + Object.get_book(Book_Type.Year) + "<br>" + 
 										"Publishing House: " + Object.get_book(Book_Type.PH) + "</html>"
 									);
 									break;   				
   								}
   							}

						}
		         			//
		         		}
        			}
    			});
			
			SwingUtilities.updateComponentTreeUI(main.panel);

        	});
        	
        	books_menu.add(delete_books_menu);
		delete_books_menu.addActionListener((ActionEvent event) -> 
		{
		       	main.panel.removeAll();        

                	gbc.gridy = 0;
			gbc.weighty = 0;

            		gbc.insets = new Insets(3, 3, 3, 3);
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.gridwidth = 3;
			    			
    			main.panel.add(delete_books_label, gbc);
			
			++gbc.gridy;
							
			main.panel.add(delete_books_name_label, gbc);

			++gbc.gridy;

		       	main.panel.add(delete_name_books_textfield, gbc);				

			++gbc.gridy;

      			delete_books_button.addActionListener(new ActionListener() 
      			{
        			public void actionPerformed(ActionEvent e) 
        			{
        				if(Objects_Books.size() == 0)		         			
		         			delete_books_message.setText("*No records!");
		         		else
		         		{
		         			if(delete_name_books_textfield.getText() == null
		         			|| delete_name_books_textfield.getText().contains(" "))
		         			{
		         				delete_books_message.setText("*Invalid book name!");
		         			}	
		         			else
		         			{
		         		   		for (Books Object: Objects_Books)
   							{
								if(Object.get_book(Book_Type.Name) != null 
								&& Object.get_book(Book_Type.Name).equals(delete_name_books_textfield.getText()))
 								{
 									delete_books_message.setText("*Deleted book: " + Object.get_book(Book_Type.Name));
 									Objects_Books.remove(Object);
 									break;   				
   								}
   							}
   							
   							while (list_books_model2.getRowCount() > 0) 
							{
       								list_books_model2.removeRow(0);
							}
							
   							for (Books Object: Objects_Books)
   							{
   								list_books_model2.addRow(new Object[]
        							{
					        			Object.get_book(Book_Type.ID),
					        			Object.get_book(Book_Type.Name),
					        			Object.get_book(Book_Type.Author),
					        			Object.get_book(Book_Type.Domain),
					        			Object.get_book(Book_Type.Year),
		        						Object.get_book(Book_Type.PH)
        							});
        						}
        						
						}
		         			//
		         		}
        			}
    			});

					       	
		       	main.panel.add(delete_books_button, gbc);

			++gbc.gridy;

			main.panel.add(delete_books_message, gbc);

			SwingUtilities.updateComponentTreeUI(main.panel);

        	});

        					
		menubar.add(users_menu);
		
		users_menu.add(list_users_menu);
		list_users_menu.addActionListener((ActionEvent event) -> 
		{    			
		       	main.panel.removeAll();

			main.panel.add(scroll_users_table);
			SwingUtilities.updateComponentTreeUI(main.panel);

        	});
        	
		users_menu.add(add_users_menu);
		add_users_menu.addActionListener((ActionEvent event) -> 
		{
		       	main.panel.removeAll();

                	gbc.gridx = 0;
                	gbc.gridy = 0;
			//gbc.weightx = 0;
			gbc.weighty = 0;

            		gbc.insets = new Insets(3, 3, 3, 3);
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.gridwidth = 1;

			main.panel.add(add_users_label, gbc);

			gbc.gridwidth = 3;
			++gbc.gridy;

			main.panel.add(add_users_name_label, gbc);
			gbc.gridx = 1;
			main.panel.add(add_name_users_textfield, gbc);								

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(add_users_fname_label, gbc);
			gbc.gridx = 1;
			main.panel.add(add_fname_users_textfield, gbc);								

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(add_users_lname_label, gbc);
			gbc.gridx = 1;
			main.panel.add(add_lname_users_textfield, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(add_users_cnp_label, gbc);
			gbc.gridx = 1;
			main.panel.add(add_cnp_users_textfield, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(add_users_day_label, gbc);
			gbc.gridx = 1;
			main.panel.add(add_day_users_textfield, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(add_users_month_label, gbc);
			gbc.gridx = 1;
			main.panel.add(add_month_users_textfield, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(add_users_year_label, gbc);
			gbc.gridx = 1;
			main.panel.add(add_year_users_textfield, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(add_users_email_label, gbc);
			gbc.gridx = 1;
			main.panel.add(add_email_users_textfield, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(add_users_phone_label, gbc);
			gbc.gridx = 1;
			main.panel.add(add_phone_users_textfield, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(add_users_address_label, gbc);
			gbc.gridx = 1;
			main.panel.add(add_address_users_textfield, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(add_users_postal_label, gbc);
			gbc.gridx = 1;
			main.panel.add(add_postal_users_textfield, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(add_users_city_label, gbc);
			gbc.gridx = 1;
			main.panel.add(add_city_users_textfield, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(add_users_state_label, gbc);
			gbc.gridx = 1;
			main.panel.add(add_state_users_textfield, gbc);

			++gbc.gridy;
			gbc.gridx = 0;
			
			main.panel.add(add_users_country_label, gbc);
			gbc.gridx = 1;
			main.panel.add(add_country_users_textfield, gbc);

			++gbc.gridy;			
			gbc.gridx = 0;
			
      			add_users_create_button.addActionListener(new ActionListener() 
      			{
        			public void actionPerformed(ActionEvent e) 
        			{				
	         			if(add_name_users_textfield.getText().equals("") || add_name_users_textfield.getText().contains(" ")
	         			|| add_fname_users_textfield.getText().equals("") || add_fname_users_textfield.getText().contains(" ")
	         			|| add_lname_users_textfield.getText().equals("") || add_lname_users_textfield.getText().contains(" ")
	         			|| add_cnp_users_textfield.getText().equals("") || add_lname_users_textfield.getText().contains(" ")
	         			|| add_day_users_textfield.getText().equals("") || add_day_users_textfield.getText().contains(" ")
	         			|| add_month_users_textfield.getText().equals("") || add_month_users_textfield.getText().contains(" ")
	         			|| add_year_users_textfield.getText().equals("") || add_year_users_textfield.getText().contains(" ")
	         			|| add_email_users_textfield.getText().equals("") || add_email_users_textfield.getText().contains(" ")
	         			|| add_phone_users_textfield.getText().equals("") || add_phone_users_textfield.getText().contains(" ")
	         			|| add_address_users_textfield.getText().equals("") || add_address_users_textfield.getText().contains(" ")
	         			|| add_postal_users_textfield.getText().equals("") || add_postal_users_textfield.getText().contains(" ")
	         			|| add_city_users_textfield.getText().equals("") || add_city_users_textfield.getText().contains(" ")
	         			|| add_state_users_textfield.getText().equals("") || add_state_users_textfield.getText().contains(" ")
	         			|| add_country_users_textfield.getText().equals("") || add_country_users_textfield.getText().contains(" ")
	         			)
	         			{
	         				add_users_message.setText("*Complete all fields!");
	         			}
	     				else
	     				{
	     					if(main.Year > 0 && main.Month > 0 && main.Day > 0)
	     					{
	     						Users Object = new Users();
							
	     						Object.set_user(User_Type.Name, add_name_users_textfield.getText());								     						 
	     						Object.set_user(User_Type.fName, add_fname_users_textfield.getText());
	     						Object.set_user(User_Type.lName, add_lname_users_textfield.getText());
	     						Object.set_user(User_Type.CNP, add_cnp_users_textfield.getText());
	     						Object.set_user(User_Type.Birth_Day, add_day_users_textfield.getText());
	     						Object.set_user(User_Type.Birth_Month, add_month_users_textfield.getText());
	     						Object.set_user(User_Type.Birth_Year, add_month_users_textfield.getText());
	     						Object.set_user(User_Type.Email, add_email_users_textfield.getText());
	     						Object.set_user(User_Type.Phone, add_phone_users_textfield.getText());
	     						Object.set_user(User_Type.Address, add_address_users_textfield.getText());
	     						Object.set_user(User_Type.PC, add_postal_users_textfield.getText());
	     						Object.set_user(User_Type.City, add_city_users_textfield.getText());	     						
	     						Object.set_user(User_Type.State, add_state_users_textfield.getText());
	     						Object.set_user(User_Type.Country, add_country_users_textfield.getText());

							System.out.println(add_cnp_users_textfield.getText());
							System.out.println(Object.get_user(User_Type.CNP));
        						list_users_model2.addRow(new Object[]
        						{
				        			Object.get_user(User_Type.Name),
				        			Object.get_user(User_Type.fName),
				        			Object.get_user(User_Type.lName),
				        			Object.get_user(User_Type.Birth_Day),
				        			Object.get_user(User_Type.Birth_Month),
				        			Object.get_user(User_Type.Birth_Year),
				        			Object.get_user(User_Type.CNP),
				        			Object.get_user(User_Type.Email),
				        			Object.get_user(User_Type.Phone),
				        			Object.get_user(User_Type.Address),
				        			Object.get_user(User_Type.PC),
				        			Object.get_user(User_Type.City),
				        			Object.get_user(User_Type.State),
	        						Object.get_user(User_Type.Country)
        						});
        						
	     						Objects_Users.add(Object);
	     						
	     						add_users_message.setText("*User was added!");
	     					}
	     					else
	     					{
			         			if(add_year_users_textfield.getText() != null)
			         			{
			         				try 
			         				{
		     							main.Year = Integer.parseInt(add_year_users_textfield.getText());
		
		     							if(main.Year < 1900)
										add_users_message.setText("*Year cannot be < 1900!");
		
		     							if(main.Year > 2018)
										add_users_message.setText("*Year cannot be > 2018!");
		
									if(main.Year > 2018 || main.Year < 1900)
										main.Year = 0;
								}
								catch (NumberFormatException err) 
								{
									add_users_message.setText("*Year must be a number!");
		     							main.Year = 0;
								}
			         			}
		         			
			         			if(add_month_users_textfield.getText() != null)
			         			{
			         				try 
			         				{
		     							main.Month = Integer.parseInt(add_month_users_textfield.getText());
		
		     							if(main.Month < 1)
										add_users_message.setText("*Month cannot be < 1!");
		
		     							if(main.Month > 12)
										add_users_message.setText("*Month cannot be > 12!");
		
									if(main.Month > 12 || main.Month < 1)
										main.Month = 0;
								}
								catch (NumberFormatException err) 
								{
									add_users_message.setText("*Month must be a number!");
		     							main.Month = 0;
								}
			         			}
		         			
		         				if(add_day_users_textfield.getText() != null)
		         				{
		         					try 
		        	 				{
	     								main.Day = Integer.parseInt(add_day_users_textfield.getText());
		
		     							if(main.Day < 1)
										add_users_message.setText("*Day cannot be < 1!");
		
		     							if(main.Day > 30)
										add_users_message.setText("*Day cannot be > 30!");
		
									if(main.Day > 30 || main.Day < 1)
										main.Day = 0;
								}
								catch (NumberFormatException err) 
								{
									add_users_message.setText("*Day must be a number!");
	     								main.Day = 0;
								}
		         				}
		         			
		         				if(add_cnp_users_textfield.getText() != null)
		         				{
		         					int length = add_cnp_users_textfield.getText().length(); 
															
	     							if(length != 13)
	     							{
									add_users_message.setText("*CNP must have 13 digits!");
								}
		         				}
						}         				
	         				
	         			}	         			
        			}	
    			});

      			add_users_clear_button.addActionListener(new ActionListener() 
      			{
        			public void actionPerformed(ActionEvent e) 
        			{
        				if(add_name_users_textfield.getText() != null)
	         			{
	         				add_name_users_textfield.setText(null);
	         			}

	         			if(add_fname_users_textfield.getText() != null)
	         			{
	         				add_fname_users_textfield.setText(null);
	         			}
	         			
	         			if(add_lname_users_textfield.getText() != null)
	         			{
	         				add_lname_users_textfield.setText(null);
	         			}

	         			if(add_cnp_users_textfield.getText() != null)
	         			{
	         				add_cnp_users_textfield.setText(null);
	         			}

		         		if(add_day_users_textfield.getText() != null)
	         			{
	         				add_day_users_textfield.setText(null);
	         			}

		         		if(add_month_users_textfield.getText() != null)
	         			{
	         				add_month_users_textfield.setText(null);
	         			}

		         		if(add_year_users_textfield.getText() != null)
	         			{
	         				add_year_users_textfield.setText(null);
	         			}
		
	         			if(add_email_users_textfield.getText() != null)
	         			{
	         				add_email_users_textfield.setText(null);
	         			}

	         			if(add_phone_users_textfield.getText() != null)
	         			{
	         				add_phone_users_textfield.setText(null);
	         			}

	         			if(add_address_users_textfield.getText() != null)
	         			{
	         				add_address_users_textfield.setText(null);
	         			}

	         			if(add_postal_users_textfield.getText() != null)
	         			{
	         				add_postal_users_textfield.setText(null);
	         			}

	         			if(add_city_users_textfield.getText() != null)
	         			{
	         				add_city_users_textfield.setText(null);
	         			}

	         			if(add_state_users_textfield.getText() != null)
	         			{
	         				add_state_users_textfield.setText(null);
	         			}

	         			if(add_country_users_textfield.getText() != null)
	         			{
	         				add_country_users_textfield.setText(null);
	         			}
	         			main.Year = 0;
	         			main.Month = 0;
	         			main.Day = 0;
	         			
	         			add_users_message.setText("*Clear Successfully");

        			}
    			});
			main.panel.add(add_users_create_button, gbc);
			gbc.gridx = 1;
			main.panel.add(add_users_clear_button, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(add_users_message, gbc);

			SwingUtilities.updateComponentTreeUI(main.panel);

        	});

		users_menu.add(modify_users_menu);
		modify_users_menu.addActionListener((ActionEvent event) -> 
		{
		       	main.panel.removeAll();

                	gbc.gridx = 0;
                	gbc.gridy = 0;
			//gbc.weightx = 0;
			gbc.weighty = 0;

            		gbc.insets = new Insets(3, 3, 3, 3);
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.gridwidth = 1;

			main.panel.add(modify_users_label, gbc);

			gbc.gridwidth = 3;
			++gbc.gridy;

			main.panel.add(modify_users_name_label, gbc);
			gbc.gridx = 1;
			main.panel.add(modify_name_users_textfield, gbc);								

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(modify_users_fname_label, gbc);
			gbc.gridx = 1;
			main.panel.add(modify_fname_users_textfield, gbc);								

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(modify_users_lname_label, gbc);
			gbc.gridx = 1;
			main.panel.add(modify_lname_users_textfield, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(modify_users_cnp_label, gbc);
			gbc.gridx = 1;
			main.panel.add(modify_cnp_users_textfield, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(modify_users_day_label, gbc);
			gbc.gridx = 1;
			main.panel.add(modify_day_users_textfield, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(modify_users_month_label, gbc);
			gbc.gridx = 1;
			main.panel.add(modify_month_users_textfield, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(modify_users_year_label, gbc);
			gbc.gridx = 1;
			main.panel.add(modify_year_users_textfield, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(modify_users_email_label, gbc);
			gbc.gridx = 1;
			main.panel.add(modify_email_users_textfield, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(modify_users_phone_label, gbc);
			gbc.gridx = 1;
			main.panel.add(modify_phone_users_textfield, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(modify_users_address_label, gbc);
			gbc.gridx = 1;
			main.panel.add(modify_address_users_textfield, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(modify_users_postal_label, gbc);
			gbc.gridx = 1;
			main.panel.add(modify_postal_users_textfield, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(modify_users_city_label, gbc);
			gbc.gridx = 1;
			main.panel.add(modify_city_users_textfield, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(modify_users_state_label, gbc);
			gbc.gridx = 1;
			main.panel.add(modify_state_users_textfield, gbc);

			++gbc.gridy;
			gbc.gridx = 0;
			
			main.panel.add(modify_users_country_label, gbc);
			gbc.gridx = 1;
			main.panel.add(modify_country_users_textfield, gbc);

			++gbc.gridy;			
			gbc.gridx = 0;
			
      			modify_users_create_button.addActionListener(new ActionListener() 
      			{
        			public void actionPerformed(ActionEvent e) 
        			{
        			        if(Objects_Users.size() == 0)		         			
		         			modify_users_message.setText("*No records!");
		         		else
		         		{
				 		if(modify_name_users_textfield.getText().equals("") || modify_name_users_textfield.getText().contains(" ")
					        || modify_fname_users_textfield.getText().equals("") || modify_fname_users_textfield.getText().contains(" ")
					        || modify_lname_users_textfield.getText().equals("") || modify_lname_users_textfield.getText().contains(" ")
					        || modify_cnp_users_textfield.getText().equals("") || modify_lname_users_textfield.getText().contains(" ")
					        || modify_day_users_textfield.getText().equals("") || modify_day_users_textfield.getText().contains(" ")
					        || modify_month_users_textfield.getText().equals("") || modify_month_users_textfield.getText().contains(" ")
					        || modify_year_users_textfield.getText().equals("") || modify_year_users_textfield.getText().contains(" ")
					        || modify_email_users_textfield.getText().equals("") || modify_email_users_textfield.getText().contains(" ")
					        || modify_phone_users_textfield.getText().equals("") || modify_phone_users_textfield.getText().contains(" ")
					        || modify_address_users_textfield.getText().equals("") || modify_address_users_textfield.getText().contains(" ")
					        || modify_postal_users_textfield.getText().equals("") || modify_postal_users_textfield.getText().contains(" ")
					        || modify_city_users_textfield.getText().equals("") || modify_city_users_textfield.getText().contains(" ")
					        || modify_state_users_textfield.getText().equals("") || modify_state_users_textfield.getText().contains(" ")
					        || modify_country_users_textfield.getText().equals("") || modify_country_users_textfield.getText().contains(" ")
					        )
					        {
					        	modify_users_message.setText("*Complete all fields!");
					         			
					        }		         			
					        else
		         			{
		         				if(main.mYear > 0 && main.mMonth > 0 && main.mDay > 0)
	     						{
	     							for (Users Object: Objects_Users)
   								{
									if(Object.get_user(User_Type.Name) != null 
									&& Object.get_user(User_Type.Name).equals(modify_name_users_textfield.getText()))
 									{										
					     					Object.set_user(User_Type.Name, modify_name_users_textfield.getText());								     						 
					     					Object.set_user(User_Type.fName, modify_fname_users_textfield.getText());
					     					Object.set_user(User_Type.lName, modify_lname_users_textfield.getText());
					     					Object.set_user(User_Type.CNP, modify_cnp_users_textfield.getText());
					     					Object.set_user(User_Type.Birth_Day, modify_day_users_textfield.getText());
					     					Object.set_user(User_Type.Birth_Month, modify_month_users_textfield.getText());
					     					Object.set_user(User_Type.Birth_Year, modify_month_users_textfield.getText());
					     					Object.set_user(User_Type.Email, modify_email_users_textfield.getText());
					     					Object.set_user(User_Type.Phone, modify_phone_users_textfield.getText());
					     					Object.set_user(User_Type.Address, modify_address_users_textfield.getText());
					     					Object.set_user(User_Type.PC, modify_postal_users_textfield.getText());
					     					Object.set_user(User_Type.City, modify_city_users_textfield.getText());	     						
					     					Object.set_user(User_Type.State, modify_state_users_textfield.getText());
					     					Object.set_user(User_Type.Country, modify_country_users_textfield.getText());
										break;
 									}
 								}
 		 						while (list_users_model2.getRowCount() > 0) 
								{
				       					list_users_model2.removeRow(0);
								}
				
				   				for (Users Object: Objects_Users)
				   				{
				   					list_users_model2.addRow(new Object[]
				        				{
									       	Object.get_user(User_Type.Name),
								        	Object.get_user(User_Type.fName),
								        	Object.get_user(User_Type.lName),
								        	Object.get_user(User_Type.Birth_Day),
								        	Object.get_user(User_Type.Birth_Month),
								        	Object.get_user(User_Type.Birth_Year),
							        		Object.get_user(User_Type.CNP),
								        	Object.get_user(User_Type.Email),
								        	Object.get_user(User_Type.Phone),
								        	Object.get_user(User_Type.Address),									        	Object.get_user(User_Type.PC),
								        	Object.get_user(User_Type.City),
								        	Object.get_user(User_Type.State),
						        			Object.get_user(User_Type.Country)
				        				});
				        			}
					     						
					     			modify_users_message.setText("*User was modified!");

 							}	

							else
							{
								if(modify_year_users_textfield.getText() != null)
							        {
							        	try 
							        	{
						     				main.mYear = Integer.parseInt(modify_year_users_textfield.getText());
						
						     				if(main.mYear < 1900)
											modify_users_message.setText("*Year cannot be < 1900!");
						
						     				if(main.mYear > 2018)
											modify_users_message.setText("*Year cannot be > 2018!");
						
										if(main.mYear > 2018 || main.mYear < 1900)
										{
											main.mYear = 0;			
										}
									}
									catch (NumberFormatException err) 
									{
										modify_users_message.setText("*Year must be a number!");
						     				main.mYear = 0;
									}
							        }
										
								if(modify_month_users_textfield.getText() != null)
							        {
							        	try 
							         	{
						     				main.mMonth = Integer.parseInt(modify_month_users_textfield.getText());
						
						     				if(main.mMonth < 1)
											modify_users_message.setText("*Month cannot be < 1!");
						
						     				if(main.mMonth > 12)
											modify_users_message.setText("*Month cannot be > 12!");
						
										if(main.mMonth > 12 || main.mMonth < 1)
										{
											main.mMonth = 0;
										}
									}
									catch (NumberFormatException err) 
									{
										modify_users_message.setText("*Month must be a number!");
						     				main.mMonth = 0;
						     			}
							         }
						         			
						         	if(modify_day_users_textfield.getText() != null)
						         	{
						         		try 
						        	 	{
					     					main.mDay = Integer.parseInt(modify_day_users_textfield.getText());
						
						     				if(main.mDay < 1)
											modify_users_message.setText("*Day cannot be < 1!");
						
						     				if(main.mDay > 30)
											modify_users_message.setText("*Day cannot be > 30!");
						
										if(main.mDay > 30 || main.mDay < 1)
										{
											main.mDay = 0;
										}
									}
									catch (NumberFormatException err) 
									{
										modify_users_message.setText("*Day must be a number!");
					     					main.mDay = 0;
					     				}
						         	}
						         			
						         	if(modify_cnp_users_textfield.getText() != null)
						         	{
						         		int length = modify_cnp_users_textfield.getText().length(); 
																			
					     				if(length != 13)
					     				{
										modify_users_message.setText("*CNP must have 13 digits!");
									}
		         					}
							}	         				
	         				}
	         			}	         			
        			}	
    			});

      			modify_users_clear_button.addActionListener(new ActionListener() 
      			{
        			public void actionPerformed(ActionEvent e) 
        			{
        				if(modify_name_users_textfield.getText() != null)
	         			{
	         				modify_name_users_textfield.setText(null);
	         			}

	         			if(modify_fname_users_textfield.getText() != null)
	         			{
	         				modify_fname_users_textfield.setText(null);
	         			}
	         			
	         			if(modify_lname_users_textfield.getText() != null)
	         			{
	         				modify_lname_users_textfield.setText(null);
	         			}

	         			if(modify_cnp_users_textfield.getText() != null)
	         			{
	         				modify_cnp_users_textfield.setText(null);
	         			}

		         		if(modify_day_users_textfield.getText() != null)
	         			{
	         				modify_day_users_textfield.setText(null);
	         			}

		         		if(modify_month_users_textfield.getText() != null)
	         			{
	         				modify_month_users_textfield.setText(null);
	         			}

		         		if(modify_year_users_textfield.getText() != null)
	         			{
	         				modify_year_users_textfield.setText(null);
	         			}
		
	         			if(modify_email_users_textfield.getText() != null)
	         			{
	         				modify_email_users_textfield.setText(null);
	         			}

	         			if(modify_phone_users_textfield.getText() != null)
	         			{
	         				modify_phone_users_textfield.setText(null);
	         			}

	         			if(modify_address_users_textfield.getText() != null)
	         			{
	         				modify_address_users_textfield.setText(null);
	         			}

	         			if(modify_postal_users_textfield.getText() != null)
	         			{
	         				modify_postal_users_textfield.setText(null);
	         			}

	         			if(modify_city_users_textfield.getText() != null)
	         			{
	         				modify_city_users_textfield.setText(null);
	         			}

	         			if(modify_state_users_textfield.getText() != null)
	         			{
	         				modify_state_users_textfield.setText(null);
	         			}

	         			if(modify_country_users_textfield.getText() != null)
	         			{
	         				modify_country_users_textfield.setText(null);
	         			}
	         			main.mYear = 0;
	         			main.mMonth = 0;
	         			main.mDay = 0;
	         			
	         			modify_users_message.setText("*Clear Successfully");

        			}
    			});
			main.panel.add(modify_users_create_button, gbc);
			gbc.gridx = 1;
			main.panel.add(modify_users_clear_button, gbc);

			++gbc.gridy;
			gbc.gridx = 0;

			main.panel.add(modify_users_message, gbc);

			SwingUtilities.updateComponentTreeUI(main.panel);

        	});
        			
		users_menu.add(search_users_menu);
		search_users_menu.addActionListener((ActionEvent event) -> 
		{
		       	main.panel.removeAll();          

                	gbc.gridy = 0;
			gbc.weighty = 0;

            		gbc.insets = new Insets(3, 3, 3, 3);
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.gridwidth = 3;
					       	  				
			main.panel.add(search_users_label, gbc);
			++gbc.gridy;
			
			main.panel.add(search_users_name_label, gbc);
			++gbc.gridy;

			main.panel.add(search_name_users_textfield, gbc);
			++gbc.gridy;

			main.panel.add(search_users_name_button, gbc);
			++gbc.gridy;
			
			main.panel.add(search_users_cnp_label, gbc);
			++gbc.gridy;

			main.panel.add(search_cnp_users_textfield, gbc);
			++gbc.gridy;

			main.panel.add(search_users_cnp_button, gbc);									
			++gbc.gridy;
			
			main.panel.add(search_users_message, gbc);	

      			search_users_name_button.addActionListener(new ActionListener() 
      			{
        			public void actionPerformed(ActionEvent e) 
        			{
        				if(Objects_Users.size() == 0)		         			
		         			search_users_message.setText("*No records!");
		         		else
		         		{
		         			if(search_name_users_textfield.getText() == null
		         			|| search_name_users_textfield.getText().contains(" "))
		         			{
		         				search_users_message.setText("*Invalid username!");
		         			}	
		         			else
		         			{
		         		   		for (Users Object: Objects_Users)
   							{
								if(Object.get_user(User_Type.Name) != null 
								&& Object.get_user(User_Type.Name).equals(search_name_users_textfield.getText()))
 								{
 									search_users_message.setText("<html>*Found account: by account: " + search_name_users_textfield.getText() + "<br>" +
 										"fName: " + Object.get_user(User_Type.fName) + "<br>" +
 										"lName: " + Object.get_user(User_Type.lName) + "<br>" +
 										"BDay: " + Object.get_user(User_Type.Birth_Day) + "<br>" +
 										"BMonth: " + Object.get_user(User_Type.Birth_Month) + "<br>" +
 										"BYear: " + Object.get_user(User_Type.Birth_Year) + "<br>" +
 										"CNP: " + Object.get_user(User_Type.CNP) + "<br>" +
 										"Email: " + Object.get_user(User_Type.Email) + "<br>" + 
 										"Phone: " + Object.get_user(User_Type.Phone) + "<br>" + 
 										"Address: " + Object.get_user(User_Type.Address) + "<br>" + 
 										"PC: " + Object.get_user(User_Type.PC) + "<br>" + 
 										"City: " + Object.get_user(User_Type.City) + "<br>" + 
 										"State: " + Object.get_user(User_Type.State) + "<br>" + 
 										"Country: " + Object.get_user(User_Type.Country) + "</html>"
 										
 									);
 									break;   				
   								}
   							}
						}
		         			//
		         		}
        			}
    			});

      			search_users_cnp_button.addActionListener(new ActionListener() 
      			{
        			public void actionPerformed(ActionEvent e) 
        			{
        				if(Objects_Users.size() == 0)		         			
		         			search_users_message.setText("*No records!");
		         		else
		         		{
		         			if(search_cnp_users_textfield.getText() == null
		         			|| search_cnp_users_textfield.getText().contains(" "))
		         			{
		         				search_users_message.setText("*Invalid cnp!");
		         			}	
		         			else
		         			{
		         		   		for (Users Object: Objects_Users)
   							{
								if(Object.get_user(User_Type.CNP) != null 
								&& Object.get_user(User_Type.CNP).equals(search_cnp_users_textfield.getText()))
 								{
 									search_users_message.setText("<html>*Found account: by cnp: " + search_cnp_users_textfield.getText() + "<br>" +
 										"Name: " + Object.get_user(User_Type.Name) + "<br>" +
 										"fName: " + Object.get_user(User_Type.fName) + "<br>" +
 										"lName: " + Object.get_user(User_Type.lName) + "<br>" +
 										"BDay: " + Object.get_user(User_Type.Birth_Day) + "<br>" +
 										"BMonth: " + Object.get_user(User_Type.Birth_Month) + "<br>" +
 										"BYear: " + Object.get_user(User_Type.Birth_Year) + "<br>" +
 										"Email: " + Object.get_user(User_Type.Email) + "<br>" + 
 										"Phone: " + Object.get_user(User_Type.Phone) + "<br>" + 
 										"Address: " + Object.get_user(User_Type.Address) + "<br>" + 
 										"PC: " + Object.get_user(User_Type.PC) + "<br>" + 
 										"City: " + Object.get_user(User_Type.City) + "<br>" + 
 										"State: " + Object.get_user(User_Type.State) + "<br>" + 
 										"Country: " + Object.get_user(User_Type.Country) + "</html>"
 										
 									);
 									break;   				
   								}
   							}
						}
		         			//
		         		}
        			}
    			});
			
			SwingUtilities.updateComponentTreeUI(main.panel);

        	});
        	
		users_menu.add(delete_users_menu);
		delete_users_menu.addActionListener((ActionEvent event) -> 
		{
		       	main.panel.removeAll();        

                	gbc.gridy = 0;
			gbc.weighty = 0;

            		gbc.insets = new Insets(3, 3, 3, 3);
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.gridwidth = 3;
			    			
    			main.panel.add(delete_users_label, gbc);
			
			++gbc.gridy;
							
			main.panel.add(delete_users_name_label, gbc);

			++gbc.gridy;

		       	main.panel.add(delete_name_users_textfield, gbc);				

			++gbc.gridy;

      			delete_users_button.addActionListener(new ActionListener() 
      			{
        			public void actionPerformed(ActionEvent e) 
        			{
        				if(Objects_Users.size() == 0)		         			
		         			delete_users_message.setText("*No records!");
		         		else
		         		{
		         			if(delete_name_users_textfield.getText() == null
		         			|| delete_name_users_textfield.getText().contains(" "))
		         			{
		         				delete_users_message.setText("*Invalid username!");
		         			}	
		         			else
		         			{
		         		   		for (Users Object: Objects_Users)
   							{
								if(Object.get_user(User_Type.Name) != null 
								&& Object.get_user(User_Type.Name).equals(delete_name_users_textfield.getText()))
 								{
 									delete_users_message.setText("*Deleted account: " + Object.get_user(User_Type.Name));
 									Objects_Users.remove(Object);
 									break;
 	  			    					//System.out.println(newLine);
   									//System.out.println(Object.get_user(User_Type.Name));
   				
   								}
   							}
   							
   							while (list_users_model2.getRowCount() > 0) 
							{
       								list_users_model2.removeRow(0);
							}

   							for (Users Object: Objects_Users)
   							{
   								list_users_model2.addRow(new Object[]
        							{
					        			Object.get_user(User_Type.Name),
					        			Object.get_user(User_Type.fName),
					        			Object.get_user(User_Type.lName),
					        			Object.get_user(User_Type.Birth_Day),
					        			Object.get_user(User_Type.Birth_Month),
					        			Object.get_user(User_Type.Birth_Year),
				        				Object.get_user(User_Type.CNP),
					        			Object.get_user(User_Type.Email),
					        			Object.get_user(User_Type.Phone),
					        			Object.get_user(User_Type.Address),
					        			Object.get_user(User_Type.PC),
					        			Object.get_user(User_Type.City),
					        			Object.get_user(User_Type.State),
		        						Object.get_user(User_Type.Country)
        							});
        						}
						}
		         			//
		         		}
        			}
    			});

					       	
		       	main.panel.add(delete_users_button, gbc);

			++gbc.gridy;

			main.panel.add(delete_users_message, gbc);

			SwingUtilities.updateComponentTreeUI(main.panel);

        	});
       
       		users_menu.add(borrow_users_menu);
		borrow_users_menu.addActionListener((ActionEvent event) -> 
		{    			
		       	main.panel.removeAll();        

                	gbc.gridy = 0;
			gbc.weighty = 0;

            		gbc.insets = new Insets(3, 3, 3, 3);
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.gridwidth = 3;

			++gbc.gridy;
			main.panel.add(borrow_users_label, gbc);

			++gbc.gridy;
			main.panel.add(borrow_users_name_label, gbc);

			++gbc.gridy;
			main.panel.add(borrow_name_users_textfield, gbc);

			++gbc.gridy;
			main.panel.add(borrow_users_book_label, gbc);

			++gbc.gridy;
			main.panel.add(borrow_book_users_textfield, gbc);

			++gbc.gridy;
		       	main.panel.add(borrow_users_create_button, gbc);

			++gbc.gridy;
			main.panel.add(borrow_users_message, gbc);

      			borrow_users_create_button.addActionListener(new ActionListener() 
      			{
        			public void actionPerformed(ActionEvent e) 
        			{
        				if(Objects_Users.size() == 0 || Objects_Books.size() == 0)		         			
		         			borrow_users_message.setText("*No records!");
		         		else
		         		{
		         			if(borrow_name_users_textfield.getText() == null
		         			|| borrow_name_users_textfield.getText().contains(" "))
		         			{
		         				borrow_users_message.setText("*Invalid username!");
		         			}
		         			else if(borrow_book_users_textfield.getText() == null
		         			|| borrow_book_users_textfield.getText().contains(" "))
		         			{
		         				borrow_users_message.setText("*Invalid Book Name!");
		         			}	
		         			else
		         			{
		         				String name = null;
		         				String book_name = null;
		         				
		         		   		for (Users Object: Objects_Users)
   							{
								if(Object.get_user(User_Type.Name) != null 
								&& Object.get_user(User_Type.Name).equals(borrow_name_users_textfield.getText()))
 								{
 									if(Object.get_user(User_Type.Borrowed) != null)
 									{
		         							borrow_users_message.setText("*User already borrwed a book!");
 									}
 									else
 									{
	 									name = Object.get_user(User_Type.Name);
 									}
 									break;
   				   				}
   							}
   							
   							for (Books Object: Objects_Books)
   							{
								if(Object.get_book(Book_Type.Name) != null 
								&& Object.get_book(Book_Type.Name).equals(borrow_book_users_textfield.getText()))
 								{
 								 	if(Object.get_book(Book_Type.Borrowed_By) != null)
 								 	{		         							
		         							borrow_users_message.setText("*Book was already borrowed!");
									}
									else
									{
	 									book_name = Object.get_book(Book_Type.Name);
 									}
 									break;
   				   				}
   							}
   							if(book_name != null && name != null)
   							{
   								DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
								Date date = new Date();
								
   								for (Users Object: Objects_Users)
   								{
									if(Object.get_user(User_Type.Name) != null 
									&& Object.get_user(User_Type.Name).equals(name))
 									{
	 									Object.set_user(User_Type.Borrowed, book_name);
	 									Object.set_user(User_Type.Borrowed_Time, dateFormat.format(date));
 										break;
   				   					}
   								}
   							
   								for (Books Object: Objects_Books)
   								{
									if(Object.get_book(Book_Type.Name) != null 
									&& Object.get_book(Book_Type.Name).equals(book_name))
 									{ 									
	 									Object.set_book(Book_Type.Borrowed_By, name);
	 									Object.set_book(Book_Type.Borrowed_Time, dateFormat.format(date));
 										break;
   				   					}
   								}
   								borrow_users_message.setText("<html>*Book was borrowed! <br> Name: " + name + "<br>Book Name: " + book_name);


   							}
						}
		         			//
		         		}
        			}
    			});
			
			SwingUtilities.updateComponentTreeUI(main.panel);

        	});    
        	
       		users_menu.add(return_users_menu);
		return_users_menu.addActionListener((ActionEvent event) -> 
		{    			
		       	main.panel.removeAll();        

                	gbc.gridy = 0;
			gbc.weighty = 0;

            		gbc.insets = new Insets(3, 3, 3, 3);
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.gridwidth = 3;

			++gbc.gridy;
			main.panel.add(return_users_label, gbc);

			++gbc.gridy;
			main.panel.add(return_users_name_label, gbc);

			++gbc.gridy;
			main.panel.add(return_name_users_textfield, gbc);

			++gbc.gridy;
			main.panel.add(return_users_book_label, gbc);

			++gbc.gridy;
			main.panel.add(return_book_users_textfield, gbc);

			++gbc.gridy;
		       	main.panel.add(return_users_create_button, gbc);

			++gbc.gridy;
			main.panel.add(return_users_message, gbc);

      			return_users_create_button.addActionListener(new ActionListener() 
      			{
        			public void actionPerformed(ActionEvent e) 
        			{
        				if(Objects_Users.size() == 0 || Objects_Books.size() == 0)		         			
		         			return_users_message.setText("*No records!");
		         		else
		         		{
		         			if(return_name_users_textfield.getText() == null
		         			|| return_name_users_textfield.getText().contains(" "))
		         			{
		         				return_users_message.setText("*Invalid username!");
		         			}
		         			else if(return_book_users_textfield.getText() == null
		         			|| return_book_users_textfield.getText().contains(" "))
		         			{
		         				return_users_message.setText("*Invalid Book Name!");
		         			}	
		         			else
		         			{
		         				String name = null;
		         				String book_name = null;
		         				
		         		   		for (Users Object: Objects_Users)
   							{
								if(Object.get_user(User_Type.Name) != null 
								&& Object.get_user(User_Type.Name).equals(return_name_users_textfield.getText()))
 								{
 									if(Object.get_user(User_Type.Borrowed) == null)
 									{
		         							return_users_message.setText("*User didn't borrwed a book!");
 									}
 									else
 									{
	 									name = Object.get_user(User_Type.Name);
 									}
 									break;
   				   				}
   							}
   							
   							for (Books Object: Objects_Books)
   							{
								if(Object.get_book(Book_Type.Name) != null 
								&& Object.get_book(Book_Type.Name).equals(return_book_users_textfield.getText()))
 								{
 								 	if(Object.get_book(Book_Type.Borrowed_By) == null)
 								 	{		         							
		         							return_users_message.setText("*Book wasnt borrowed!");
									}
									else
									{
	 									book_name = Object.get_book(Book_Type.Name);
 									}
 									break;
   				   				}
   							}
   							if(book_name != null && name != null)
   							{
   								for (Users Object: Objects_Users)
   								{
									if(Object.get_user(User_Type.Name) != null 
									&& Object.get_user(User_Type.Name).equals(name))
 									{
 										if(Object.get_user(User_Type.Borrowed).equals(book_name))
 										{
	 										Object.set_user(User_Type.Borrowed, null);
	 										Object.set_user(User_Type.Borrowed_Time, null);
	 										break;
	 									}
   				   					}
   								}
   							
   								for (Books Object: Objects_Books)
   								{
									if(Object.get_book(Book_Type.Name) != null 
									&& Object.get_book(Book_Type.Name).equals(book_name))
 									{ 	
 										if(Object.get_book(Book_Type.Borrowed_By).equals(name))
 										{								
	 										Object.set_book(Book_Type.Borrowed_By, null);
	 										Object.set_book(Book_Type.Borrowed_Time, null);
 											break;
 										}
   				   					}
   								}
								return_users_message.setText("<html>*Book was returned! <br> Name: " + name + "<br>Book Name: " + book_name);
   							}
						}
		         			//
		         		}
        			}
    			});
			
			SwingUtilities.updateComponentTreeUI(main.panel);

        	});    
        	
		menubar.add(Box.createHorizontalGlue());
		menubar.add(about_menu);
						
		main.frame.setJMenuBar(menubar);
		main.frame.add(main.panel, BorderLayout.NORTH);
		
		main.frame.pack();

		main.frame.setSize(800,600);
		//frame.setMinimumSize(new java.awt.Dimension(800, 600));
		main.frame.setResizable(false);
	  	main.frame.setVisible(true);
	  	main.frame.setLocationRelativeTo(null);
		main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
    	}

	protected static ImageIcon create_image_icon(String path) 
	{
		java.net.URL imgURL = Main.class.getResource(path);
		if (imgURL != null) 
		{
			return new ImageIcon(imgURL);
        	} 
        	else 
        	{
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	public static void main(String[] args) 
	{
	       	javax.swing.SwingUtilities.invokeLater(new Runnable() 
		{
            		public void run() 
            		{
				init_gui();
			}
        	});
    	}
}
