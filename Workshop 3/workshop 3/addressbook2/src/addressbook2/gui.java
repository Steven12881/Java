package addressbook2;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class gui {

	
	//private JButton trigger;
	
	private int recordCounter=0;
	private JFrame frame;
	private JTextField name;
	private JTextField street;
	private JTextField city;
	private JTextField state;
	private JTextField zip;
	private static Database db;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui window = new gui();
					window.frame.setVisible(true);
					
					db  = new Database("c:\\temp\\Address.dat");
					boolean opened=db.open();
					if (!opened){
						System.out.println("Issue with file name or system");
						return;
					}
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public gui() {
		initialize();
	}
	
	
	private void clearForm(){

	 	name.setText("");
	 	street.setText("");
	 	city.setText("");
	 	state.setText("");
	 	zip.setText("");
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 829, 373);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(46, 45, 69, 20);
		frame.getContentPane().add(lblName);
		
		name = new JTextField();
		name.setBounds(140, 42, 271, 26);
		
		
		frame.getContentPane().add(name);
		name.setColumns(10);
		
		JLabel lblStreet = new JLabel("Street");
		lblStreet.setBounds(46, 100, 69, 20);
		frame.getContentPane().add(lblStreet);
		
		street = new JTextField();
		street.setBounds(140, 97, 271, 26);
		frame.getContentPane().add(street);
		street.setColumns(10);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(46, 155, 69, 20);
		frame.getContentPane().add(lblCity);
		
		city = new JTextField();
		city.setBounds(140, 152, 138, 26);
		frame.getContentPane().add(city);
		city.setColumns(10);
		
		JLabel lblState = new JLabel("State");
		lblState.setBounds(308, 155, 69, 20);
		frame.getContentPane().add(lblState);
		
		state = new JTextField();
		state.setBounds(392, 152, 106, 26);
		frame.getContentPane().add(state);
		state.setColumns(10);
		
		JLabel lblZip = new JLabel("Zip");
		lblZip.setBounds(527, 155, 69, 20);
		frame.getContentPane().add(lblZip);
		
		zip = new JTextField();
		zip.setBounds(609, 152, 146, 26);
		frame.getContentPane().add(zip);
		zip.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener(){  
			 public void actionPerformed(ActionEvent e){  
				 if (name.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "Name cannot be blank", "Address Book", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
			
				 else if (db.checkIfExists(name.getText(),false,recordCounter))  {
						JOptionPane.showMessageDialog(null, "Duplicate record exists.", "Address Book", JOptionPane.INFORMATION_MESSAGE);
						return;
				
				 }
			      
			      
			      
				 
				 recordCounter=0;
				 	Record r = new Record();
				 	boolean success=true;
				try{ 	
				 	r.setName(name.getText().trim());
				 	r.setStreet(street.getText().trim());
				 	r.setCity(city.getText().trim());
				 	r.setState(state.getText().trim());
				 	r.setZip(zip.getText().trim());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Cannot add record - exceeded number of characters", "Address Book", JOptionPane.INFORMATION_MESSAGE);
					success = false;
				}
				  if (success){
				 	db.addRecord(r);
				 	
				 	JOptionPane.showMessageDialog(null, "Added the record", "Address Book", JOptionPane.INFORMATION_MESSAGE);
				  
				 	clearForm();
				 
				  } 
				 	//	String recStr=db.readRecord(0);
				 //	System.out.println("=>" + recStr);
				 
				 
				 
			         }  
			     });  
		btnAdd.setBounds(46, 240, 83, 29);
		frame.getContentPane().add(btnAdd);
		
		JButton btnFirst = new JButton("First");
		btnFirst.addActionListener(new ActionListener(){  
			 public void actionPerformed(ActionEvent e){  
			      
				  recordCounter=0;
				   Record rec = db.readRecord(0);
			       gui.populate(rec,name,street,city,state,zip);
			         }  
			         
			     });  
		
		
		
		
		
		btnFirst.setBounds(151, 240, 83, 29);
		frame.getContentPane().add(btnFirst);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener(){  
			 public void actionPerformed(ActionEvent e){  
			     recordCounter++; 
				try {
			     Record rec = db.readRecord(recordCounter);
			       gui.populate(rec,name,street,city,state,zip);
			         }  
			 catch (Exception e2){
				 recordCounter--;
		    	 JOptionPane.showMessageDialog(null, "You are on the last record. You cannot go next.", "Address Book", JOptionPane.INFORMATION_MESSAGE); 
			 }	
			 }
			     });  
		
		
		
		btnNext.setBounds(255, 240, 83, 29);
		frame.getContentPane().add(btnNext);
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.addActionListener(new ActionListener(){  
			 public void actionPerformed(ActionEvent e){  
			     recordCounter--; 
			     if (recordCounter<0){
			    	 recordCounter=0;
			    	 JOptionPane.showMessageDialog(null, "You are on the first record. You cannot go previous.", "Address Book", JOptionPane.INFORMATION_MESSAGE); 
			     }
				 Record rec = db.readRecord(recordCounter);
			       gui.populate(rec,name,street,city,state,zip);
			         }  
			         
			     });  
		
		
		
		btnPrevious.setBounds(368, 240, 115, 29);
		frame.getContentPane().add(btnPrevious);
		
		JButton btnLast = new JButton("Last");
		btnLast.addActionListener(new ActionListener(){  
			 public void actionPerformed(ActionEvent e){  
				 int numRecs = db.getNumRecords();
			     //recordCounter++; 
				try {
			     Record rec = db.readRecord(numRecs-1);
			     recordCounter=numRecs-1;
			     gui.populate(rec,name,street,city,state,zip);
			         }  
			 catch (Exception e2){
				 recordCounter--;
		    	 JOptionPane.showMessageDialog(null, "You are on the last record. You cannot go next.", "Address Book", JOptionPane.INFORMATION_MESSAGE); 
			 }	
			 }
			     }); 
		
		
		
		btnLast.setBounds(513, 240, 83, 29);
		frame.getContentPane().add(btnLast);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener(){  
			 public void actionPerformed(ActionEvent e){  
				try {
					if (name.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "Name cannot be blank", "Address Book", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					 else if (db.checkIfExists(name.getText(),true,recordCounter))  {
							JOptionPane.showMessageDialog(null, "Duplicate record exists.  Cannot Update", "Address Book", JOptionPane.INFORMATION_MESSAGE);
							return;
					
					 }
				      
					
					
				 	Record r = new Record();
				 	boolean success=true;
				try{ 	
				 	r.setName(name.getText().trim());
				 	r.setStreet(street.getText().trim());
				 	r.setCity(city.getText().trim());
				 	r.setState(state.getText().trim());
				 	r.setZip(zip.getText().trim());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Cannot add record - exceeded number of characters", "Address Book", JOptionPane.INFORMATION_MESSAGE);
					success = false;
				}
				  if (success){
				 	db.updateRecord(r,recordCounter);
				    clearForm();
				  }
					
					
					
					
					
					
					
					
					
					
					
					
					//Record rec = db.readRecord(recordCounter);
			       //gui.populate(rec,name,street,city,state,zip);
			         }  
			 catch (Exception e2){
				 recordCounter--;
		    	 JOptionPane.showMessageDialog(null, "You are on the last record. You cannot go next.", "Address Book", JOptionPane.INFORMATION_MESSAGE); 
			 }	
			 }
			     });  
		
		
		
		
		btnUpdate.setBounds(624, 240, 115, 29);
		frame.getContentPane().add(btnUpdate);
		
		
		
		
		
	}
	
	private static void populate(Record rec, JTextField name, JTextField street,JTextField city, JTextField state, JTextField zip){
		name.setText(rec.getName());
  		street.setText(rec.getStreet());
  		city.setText(rec.getCity());
  		state.setText(rec.getState());
  		zip.setText(rec.getZip());
	}
	
}
