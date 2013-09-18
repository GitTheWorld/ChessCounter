import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ChessCounter {

	private static int left;
	private static int right;
	private static boolean left_half;
	private static boolean right_half;
	private static String left_string;
	private static String right_string;
	private static String title;
	private static String path;
	
	public static void main(String[] args) {
		
		path=System.getProperty("user.dir");
		left=20;
		right=50;
		left_half=true;
		right_half=false;
		title="";
		left_string="";
		right_string="";
		
		File where = new File(path);
        if(!where.canWrite() || !where.canRead() || !new File(path+"/ChessCounter.jar").exists())
        {
        new_path();
        }
        chose();
        new MyFrame(title,left_string,right_string);
     
	}
	
	
public static void new_path()
{
	JFileChooser chooser = new JFileChooser(); 
	    chooser.setCurrentDirectory(new java.io.File("."));
	    chooser.setDialogTitle("Bitte wählen sie einen Ordner zum Laden und Speichern");
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);
    
	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
             if(chooser.getSelectedFile().canWrite() && chooser.getSelectedFile().canRead())
             {
            	path= chooser.getSelectedFile().getName();
             }else{new_path();}
	      }
	    else {
	    System.exit(0);
	      }
	     
}
	
public static void chose()
{
boolean end = false;
	
	while(!end){
	int result = JOptionPane.showConfirmDialog(null, "Möchten sie einen Spielstand Laden?", "ChessCounter", JOptionPane.YES_NO_CANCEL_OPTION);
	if (result == JOptionPane.YES_OPTION) {

      end = readFile();
	} else if (result == JOptionPane.NO_OPTION) {

	  end = create();
		
	}else{System.exit(0);}
	}
	
	
}
public static boolean create()
{
	String Antwort = JOptionPane.showInputDialog(null, "Title|Links_Name|Rechts_Name|Links_Punktzahl*2|Rechts_Punktzahl*2");
    if(Antwort==null){return false;}
    
      String[] in =  Antwort.split("\\|");
      
     if(in.length!=5){return false;}
     
     
		title=in[0];
		left_string=in[1];
		right_string=in[2];
		
		
		try {
		      left = Integer.parseInt(in[3])/2;
		      right = Integer.parseInt(in[4])/2;
		      if(Integer.parseInt(in[3])%2!=0){left_half=true;}else{left_half=false;}
		      if(Integer.parseInt(in[4])%2!=0){right_half=true;}else{right_half=false;}
		} catch (NumberFormatException e) {
            System.out.println("Problem with number!");
            return false;
		}
     return true;
}
	
public static boolean readFile()	
{
	File f = new File(path);
	File[] fileArray = f.listFiles();
	String[] files = new String[fileArray.length];
	int p=0;
	for(int i=0;fileArray.length>i;i++)
	{
		if(getExtension(fileArray[i].getPath()).equals("save") && fileArray[i].canRead()){
		files[p]=fileArray[i].getName();
		p++;
	}}
	String[] fileFinal = new String[p];
	for(int i=0;p>i;i++)
	{
		fileFinal[i]=files[i];
	}

		
		    String output = (String) JOptionPane.showInputDialog( null,
		              "Bitte den passenden Spielstand auswählen:",
		              "Spielstand Laden",
		              JOptionPane.QUESTION_MESSAGE,
		              null, fileFinal,
		              null);		    
		    if(output==null){return false;}
		    
		    try {
		    FileReader fr = new FileReader(path+"/"+output);
		    BufferedReader br = new BufferedReader(fr);
		    
			String[] zeile = br.readLine().split("\\|");

			br.close();

			if(zeile.length!=7){return false;}
			
			
			title=zeile[0];
			left_string=zeile[1];
			right_string=zeile[2];
			
			
			try {
			      left = Integer.parseInt(zeile[3]);
			      right = Integer.parseInt(zeile[4]);
			} catch (NumberFormatException e) {
	               System.out.println("Problem with number!");
	               return false;
			}
			
			if(zeile[5].equals("true"))
			{
			left_half=true;
			}else{
			left_half=false;
			}
			
			if(zeile[6].equals("true"))
			{
			right_half=true;
			}else{
			right_half=false;
			}
			
			} catch (IOException e) {
               System.out.println("Problem by loading!");
               return false;
			}
		    
		    return true;
}


public static void writeFile()
{
	int result = JOptionPane.showConfirmDialog(null, "Soll das Programm vor dem Beenden noch speichern?", "ChessCounter wird beendet...", JOptionPane.YES_NO_OPTION);
	if (result == JOptionPane.YES_OPTION) {

		
		 java.util.Date date= new java.util.Date();
		 try{
	        PrintWriter pWriter = new PrintWriter(new FileWriter(System.getProperty("user.dir")+"/ChessCounter-"+String.valueOf(new Timestamp(date.getTime()).getTime())+".save"));
	                
	        pWriter.println(title+"|"+left_string+"|"+right_string+"|"+left+"|"+right+"|"+left_half+"|"+right_half);
	        pWriter.flush();
	        pWriter.close();
	        System.out.println("Program is saved!");
	    }catch(IOException ioe){
	        ioe.printStackTrace();
	    } 
		
		System.exit(0);
	} else if (result == JOptionPane.NO_OPTION) {
		System.exit(0);
	}
	
	System.exit(0);
}
public static void leftPlus()
{
if(left_half){left_half=false;left++;}else{left_half=true;}	
}

public static void rightPlus()
{
if(right_half){right_half=false;right++;}else{right_half=true;}	
}

public static void leftMinus()
{
if(!left_half){if(left!=0){left_half=true;left--;}}else{left_half=false;}
}

public static void rightMinus()
{
if(!right_half){if(right!=0){right_half=true;right--;}}else{right_half=false;}	
}

public static String getString_left()
{
String out= String.valueOf(left);
if(left_half){out+="½";}
return out;
}

public static String getString_right()
{
String out= String.valueOf(right);
if(right_half){out+="½";}
return out;
}

public static String getExtension(String s) {
    String ext = "";
    int i = s.lastIndexOf('.');

    if (i > 0 &&  i < s.length() - 1) {
        ext = s.substring(i+1).toLowerCase();
    }
    return ext;
}

}