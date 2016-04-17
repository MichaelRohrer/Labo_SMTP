/*
 —---------------------------------------------------------------------------------
 Laboratory  : 2
 File        : MailRobot.java
 Author(s)   : Michaël Rohrer, Lucie Steiner
 Date        : 13.04.2016

 Goal        : This class is the main class, it is used to send the prank e-mails
               to the different groups randomly selected according to the config
               file.

 Remark(s)   :

 Compiler    : jdk1.8.0_60
 —---------------------------------------------------------------------------------
*/
package labo02;

import labo02.config.Config;
import labo02.smtp.SmtpCommandContent;
import labo02.smtp.SmtpClient;

public class MailRobot {

    public static void main(String[] args) {

        System.out.println("#######################################################################################\n");

        System.out.println("                             Prank Mail Sender                                           ");

        System.out.println("#######################################################################################\n");

        final String conf = "textSrc/config.txt";

        //Get the configuration
        Config config = new Config(conf);

        SmtpCommandContent smtpCommandContent = new SmtpCommandContent(config.getDomain());
        SmtpClient smtpClient;

        for(int i = 0; i < config.getNbGroup(); ++i){
            //Generate the content of all smtp commands
            smtpCommandContent.generate(config, config.getGrpSize());
            //Generate a new smtp client
            smtpClient = new SmtpClient(config.getSmtpServer(), config.getPort(), smtpCommandContent);
            //Send the e-mails
            smtpClient.send();
        }
    }
}
