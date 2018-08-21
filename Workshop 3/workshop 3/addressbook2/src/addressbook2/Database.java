package addressbook2;

import java.io.*;

public class Database {

	private String fileName;
	
	private RandomAccessFile db;
	private File file;
	
	
	private int recordNumber = 0;
	Database(String fileName){
		this.fileName = fileName;
		db=null;
		recordNumber = 0;
	}
	
	public boolean open(){
		if (fileName.equals(""))
			return false;
		
		try {
	
			file = new File(fileName);
			
			
			db = new RandomAccessFile(file, "rw");	
			
		} catch (IOException x) {
		    System.out.println("Cannot create/open file I/O Exception: " + x);
		    return false;
		}
		
		
		return true;
	}

	public int getNumRecords(){
		long fileSize=file.length();
		return (int)(fileSize / (long)Record.SIZE);
	}
	
	public void close(){
		try{
			db.close();
		} catch (IOException x) {
		    System.out.println("Cannot close file I/O Exception: " + x);
		  
		}
	}

	public boolean updateRecord(Record rec, int recordNumber){
		try{
			db.seek(recordNumber*Record.SIZE); 
			//add the address
			db.writeUTF(rec.toString());
			
		} catch (IOException x) {
		    System.out.println("Cannot update record: " + x);
		    return false;
		  
		}
		return true;
	}
	
	public boolean addRecord(Record rec){
		
		try{
			//go to end of file
			long fileLength = file.length();
			db.seek(fileLength); //go to end of file
			//add the address
			db.writeUTF(rec.toString());
			
		} catch (IOException x) {
		    System.out.println("Cannot add to file: " + x);
		    return false;
		  
		}
		return true;
	}
	
	
	public Record readRecord(long number){
		
		Record r=null;
		try{
			db.seek(number*Record.SIZE); //go to position in file
			
			//add the address
			 String str= db.readUTF();
			  r = new Record();
			 r.extractFromString(str);
			 
			
		} catch (IOException x) {
		   // System.out.println("Cannot read from file: " + x);
		    return null;
		  
		}
		return r;
		
	}

	public boolean checkIfExists(String name, boolean ignoreCurrentRecord,long recordCounter){
		boolean found = false;
         for (int i=0;i<this.getNumRecords();i++){

        	 if (ignoreCurrentRecord){  //if this is true, 
        		 if (recordCounter==i){
        			// System.out.println("Trying to ingnore i=" + i + " and current record = " + recordCounter);
        			 continue; //skip
        		 }
        	 }
        	 
        	 Record r = this.readRecord(i);
        	 if (r.getName().contentEquals(name)){
        		 found = true;
        		 break;
        	 }
         }
		
		return found;
	}
	
	
	
}

