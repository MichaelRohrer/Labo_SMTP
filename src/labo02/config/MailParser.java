/*
 —---------------------------------------------------------------------------------
 Laboratory  : 2
 File        : MailParser.java
 Author(s)   : Michaël Rohrer, Lucie Steiner
 Date        : 13.04.2016

 Goal        : This class is used to parse the file which contain the list of e-mail that could be sent

 Remark(s)   : The file has to be written following a given format: every e-mail starts with a line that only contains "<<"
                and ends with a line that only contains ">>"

 Compiler    : jdk1.8.0_60
 —---------------------------------------------------------------------------------
*/
package labo02.config;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MailParser {

    //Indicate the beginning of an e-mail
    private final String begDelimiter = "<<";
    //Indicate the end of an e-mail
    private final String endDelimiter = ">>";
    //Contain the list of e-mail
    private List<String> mailList;

    /**
     * Constructor. The file is parsed here
     * @param fname The name of the file to parse
     */
    public MailParser(String fname){

        BufferedReader br = null;
        boolean readingNewMail = false;
        
        mailList = new ArrayList<>();

        try{
            br = new BufferedReader(new FileReader(new File(fname)));

            try{
                String line;
                StringBuilder mail = new StringBuilder();

                while((line = br.readLine()) != null){
                    //If the parser is at the beginning of a new e-mail
                    if(line.contains(begDelimiter)){
                        readingNewMail = true;
                    }
                    //If the parser is at the end of an e-mail
                    else if(line.contains(endDelimiter) && readingNewMail == true){
                        mailList.add(mail.toString());
                        mail.delete(0, mail.length());
                        readingNewMail = false;
                    }
                    //If the parser is in the middle of an e-mail
                    else{
                        mail.append(line + "\r\n");
                    }
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        finally {
            try{
                br.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @return
     */
    public List<String> getMailList(){
        return mailList;
    }

    /**
     *
     * @return
     */
    public String toString(){
        StringBuilder os = new StringBuilder();

        for(String mail : mailList){
            os.append(mail + "\n");
        }
        return os.toString();
    }
}
