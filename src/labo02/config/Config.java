/*
 —---------------------------------------------------------------------------------
 Laboratory  : 2
 File        : Config.java
 Author(s)   : Michaël Rohrer, Lucie Steiner
 Date        : 13.04.2016

 Goal        : This class is used to parse the file containing the configuration,
               it is also used as a container for the configuration options, the
               list of e-mails that could be sent and the mailing list

 Remark(s)   : This class call the AddressParser and the MailParser to parse the
               corresponding file

 Compiler    : jdk1.8.0_60
 —---------------------------------------------------------------------------------
*/
package labo02.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Config {

    Logger LOG = Logger.getLogger(Config.class.getName());

    private int port;
    private String domain;
    private String smtpServer;
    private int nbGroup = 0;
    private int grpSize = 0;

    private MailParser mails;
    private AddressParser addresses;

    private final String mailList;
    private final String mailingList;


    /**
     * Constructor: All the configuration files are parsed here
     * @param conf The name of the config file
     */
    public Config(String conf){

        FileInputStream is = null;
        //Used to parse the config file
        Properties properties = new Properties();

        try{
            is = new FileInputStream(conf);
            //Load the property of the config file
            properties.load(is);
            is.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        //Fetch the different information from the config file
        port = Integer.parseInt(properties.getProperty("port"));
        domain = properties.getProperty("domain");
        smtpServer = properties.getProperty("smtpServer");
        nbGroup = Integer.parseInt(properties.getProperty("nbGroup"));
        grpSize = Integer.parseInt(properties.getProperty("grpSize"));
        mailList = properties.getProperty("mailList");
        mailingList = properties.getProperty("mailingList");


        //Parse the file containing the list of e-mails
        mails = new MailParser(mailList);

        //Parse the file containing the mailing list
        addresses = new AddressParser(mailingList);

        //Check that the configuration is valid
        if(nbGroup <= 0){
            LOG.log(Level.INFO, "Configuration Error : " +
                    "nbGroup should be > 0");
            System.exit(-1);
        }

        if(grpSize < 3 || grpSize > addresses.getMailingList().size()){
            LOG.log(Level.INFO, "Configuration Error : " +
                    "grpSize should be > 3 and " +
                    "grpSize should be <= to the size of the mailing list");
            System.exit(-1);
        }
    }

    /**
     *
     * @return
     */
    public int getPort(){
        return port;
    }

    /**
     *
     * @return
     */
    public String getDomain() {
        return domain;
    }

    /**
     *
     * @return
     */
    public String getSmtpServer() {
        return smtpServer;
    }

    /**
     *
     * @return
     */
    public int getGrpSize() {
        return grpSize;
    }

    /**
     *
     * @return
     */
    public int getNbGroup() {
        return nbGroup;
    }

    /**
     *
     * @return
     */
    public List<String> getMailingList() {
        return addresses.getMailingList();
    }

    /**
     *
     * @return
     */
    public List<String> getMailList() {
        return mails.getMailList();
    }
}
