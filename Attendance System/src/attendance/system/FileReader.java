/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.system;
import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class FileReader {
    private final File STfile=new File("D:\\engineering\\kolya\\third computer\\SW Enginering\\project\\codes\\students.txt");
    private final File INSTfile=new File("D:\\engineering\\kolya\\third computer\\SW Enginering\\project\\codes\\Instructor.txt");
    StringTokenizer tokenizer;
    String filecontent="";
    int counter=0;
    void STfileToDB(DATABASE DB)
    {
        String Email,Password;
        int STGroup=0;
        try
        {
            Scanner scan=new Scanner(STfile);
            while(scan.hasNextLine())
            {
            filecontent=filecontent.concat(scan.nextLine() + "\n");

            }
            tokenizer= new StringTokenizer(filecontent , "///");
            counter = tokenizer.countTokens()-1;
            while(counter>0)
            {
                Email=tokenizer.nextToken();
                Password=tokenizer.nextToken();
                STGroup=Integer.parseInt(tokenizer.nextToken());
                DB.insertST_data(Email,Password,STGroup);
               System.out.println(Email + Password + STGroup);
               counter-=3;
            }
        }
        catch(FileNotFoundException e)
        {
          System.out.println("file not found");
        }
    }
    ArrayList<Integer> INSTfileToDB(DATABASE DB)
    {
        ArrayList<Integer> Grouparr=new ArrayList<>();
        String Email,Password,emailtemp="";
        try
        {
            Scanner scan=new Scanner(INSTfile);
            while(scan.hasNextLine())
            {
            filecontent=filecontent.concat(scan.nextLine() + "\n");
            }
            tokenizer= new StringTokenizer(filecontent , "///");
            counter = tokenizer.countTokens()-1;
            while(counter>0)
            {
                int groupCounter=0;
                 
                if (emailtemp=="")
                Email=tokenizer.nextToken();
                else Email=emailtemp ;
                Password=tokenizer.nextToken();
                System.out.println(Email);
                System.out.println(Password);
             //  DB.insertINST_data(Email,Password);
                try{
                    while(true)
                    {emailtemp=tokenizer.nextToken();    
                       Grouparr.add(Integer.parseInt(emailtemp));
                       groupCounter++; 
                    }
                }
                catch(NumberFormatException e){
                    System.out.println("");
                }
                int temp =groupCounter;
                 System.out.println(Grouparr.size());
                while(temp!=0){
                    temp--;
                    System.out.println(Grouparr.get(temp));
                    DB.insert_INSTgroups(Email,Grouparr.get(temp));
                }
                
               counter= counter-2-groupCounter;
               Grouparr.clear();
            }
        }
        catch(FileNotFoundException e)
        {
          System.out.println("file not found");
        }
        return Grouparr;
    }
}
