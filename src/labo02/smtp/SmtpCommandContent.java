/*
 —---------------------------------------------------------------------------------
 Laboratory  : 2
 File        : SmtpCommandContent.java
 Author(s)   : Michaël Rohrer, Lucie Steiner
 Date        : 13.04.2016

 Goal        : This class contains all the arguments that will be used with the
               different smtp commands sent to the server. It also formats the
               different arguments to fit the smtp commands.

 Remark(s)   :

 Compiler    : jdk1.8.0_60
 —---------------------------------------------------------------------------------
*/
package labo02.smtp;

import labo02.config.Config;
import labo02.utils.RandomMailingListMaker;

import java.util.List;

public class SmtpCommandContent {

    //Used at the end of each commands
    private static final String endl = "\r\n";

    private String domain;
    private String src;
    private List<String> dst;
    private String data;

    /**
     * Constructor
     * @param domain the domain name
     */
    public SmtpCommandContent(String domain){
        this.domain = domain;
        this.src = null;
        this.dst = null;
        this.data = null;
    }

    /**
     * Constructor used to manually set the contents of the arguments
     * @param domain the domain name
     * @param src the e-mail address of the sender
     * @param dst the list of e-mail address where to send the e-mail
     * @param data the content of the e-mail
     */
    public SmtpCommandContent(String domain, String src, List<String> dst, String data){
        this.domain = domain;
        this.src = src;
        this.dst = dst;
        this.data = data;
    }

    /**
     *
     * @return
     */
    public String getDomain(){ return domain  + endl; }

    /**
     *
     * @return
     */
    public String getSrc(){ return src  + endl; }

    /**
     *
     * @return
     */
    public List<String> getDst(){ return dst; }

    /**
     * This method format the e-mail before return it's contents
     * @return
     */
    public String getData(){
        StringBuilder tmp = new StringBuilder();
        StringBuilder sender = new StringBuilder();

        //Format a string to fill the field "To:"
        tmp.append(dst.get(0));
        for(int i = 1; i < dst.size(); ++i){
            tmp.append(", " + dst.get(i));
        }

        //Fetch the name of the sender into the sender's e-mail address to signe the e-mail
        sender.append(src.substring(0, src.indexOf(".")));
        sender.setCharAt(0, Character.toUpperCase(src.charAt(0)));

        return "From: " + src + "\r\n" + "To: " + tmp + "\r\n" + data  + "\r\n" + sender + "\r\n" + endl + "." + endl;
    }

    /**
     * This method is used to generate the content of the smtp commands's argument with randomly selected data
     * @param config contains all the config data
     * @param grpSize the size of the group
     */
    public void generate(Config config, int grpSize){
        dst = RandomMailingListMaker.makeList(config.getMailingList(), grpSize);
        src = RandomMailingListMaker.selectItem(dst);
        data = RandomMailingListMaker.selectItem(config.getMailList());
    }
}
