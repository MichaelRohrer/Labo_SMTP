/*
 —---------------------------------------------------------------------------------
 Laboratory  : 2
 File        : SmtpCommands.java
 Author(s)   : Michaël Rohrer, Lucie Steiner
 Date        : 13.04.2016

 Goal        : This enum contains all the smtp commands used by the smtp client.

 Remark(s)   :

 Compiler    : jdk1.8.0_60
 —---------------------------------------------------------------------------------
*/


package labo02.smtp;

/**
 * Created by michael on 06.04.16.
 */
public enum SmtpCommands {
    HELO("HELO "),
    MAIL_FROM("MAIL FROM: "),
    RCPT_TO("RCPT TO: "),
    DATA("DATA\r\n"),
    QUIT("QUIT\r\n");

    private String command;

    /**
     * Constructor
     * @param command
     */
    SmtpCommands(String command){
        this.command = command;
    }

    /**
     *
     * @return
     */
    public String toString(){
        return command;
    }
}
