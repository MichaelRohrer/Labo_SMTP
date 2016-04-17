/*
 —---------------------------------------------------------------------------------
 Laboratory  : 2
 File        : AddressParser.java
 Author(s)   : Michaël Rohrer, Lucie Steiner
 Date        : 13.04.2016

 Goal        : This class is used to parse the file containing the mailing list

 Remark(s)   : The file to parse should contain one mail address on each line

 Compiler    : jdk1.8.0_60
 —---------------------------------------------------------------------------------
*/
package labo02.config;


import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class AddressParser {

    private List<String> mailingList;

    /**
     * Constructo: The file is parsed here
     * @param fname The name of the file to parse
     */
    public AddressParser(String fname){

        BufferedReader br = null;
        mailingList = new ArrayList<>();

        try{
            br = new BufferedReader(new FileReader(new File(fname)));

            try{
                String line;
                //Add each e-mail address to the list
                while((line = br.readLine()) != null){
                    mailingList.add(line);
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
    public List<String> getMailingList(){ return mailingList; }

    /**
     *
     * @return
     */
    public String toString(){

        StringBuilder os = new StringBuilder();

        for(String address : mailingList){
            os.append(address + "\n");
        }

        return os.toString();
    }
}
