/*
 —---------------------------------------------------------------------------------
 Laboratory  : 2
 File        : ISmtpClient.java
 Author(s)   : Michaël Rohrer, Lucie Steiner
 Date        : 13.04.2016

 Goal        : This interface contains the declaration of the "send" method that
               has to be implemented by the SmtpClient

 Remark(s)   :

 Compiler    : jdk1.8.0_60
 —---------------------------------------------------------------------------------
*/
package labo02.smtp;

public interface ISmtpClient {
    /**
     * This method will be used to globally send an e-mail to the server
     */
    void send();
}
