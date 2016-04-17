/*
 —---------------------------------------------------------------------------------
 Laboratory  : 2
 File        : SmtpClient.java
 Author(s)   : Michaël Rohrer, Lucie Steiner
 Date        : 13.04.2016

 Goal        : This class is used to send e-mails. It implements the smtp commands
               used to send smtp requests to an smtp server.

 Remark(s)   :

 Compiler    : jdk1.8.0_60
 —---------------------------------------------------------------------------------
*/
package labo02.smtp;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SmtpClient implements ISmtpClient{

    final static Logger LOG = Logger.getLogger(SmtpClient.class.getName());

    private String dst;
    private int port;

    //Contain all the argument of the smtp command
    private SmtpCommandContent smtpCommandContent;

    private Socket socket = null;
    private OutputStreamWriter os = null;
    private BufferedReader is = null;

    /**
     * Constructor
     * @param dst the address of the smtp server
     * @param port the number of the port on which to connect
     * @param smtpCommandContent contain all the argument for the different smtp commands
     */
    public SmtpClient(String dst, int port, SmtpCommandContent smtpCommandContent){
        this.dst = dst;
        this.port = port;
        this.smtpCommandContent = smtpCommandContent;
    }

    /**
     * This method is used to log the answer of the server
     * @throws IOException
     */
    private void logAnswer() throws IOException{
        String line;
        line = is.readLine();
        LOG.log(Level.INFO, line);
    }

    /**
     * This method is used to bind the client to the server
     * @throws IOException
     */
    private void bind() throws IOException{
        socket = new Socket(InetAddress.getByName(dst), port);
        os = new OutputStreamWriter(socket.getOutputStream());
        is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        logAnswer();
    }

    /**
     * This method is used to give the domain name to the server
     * @throws IOException
     */
    private void helo() throws IOException{
        LOG.log(Level.INFO, SmtpCommands.HELO.toString() + smtpCommandContent.getDomain());
        os.write(SmtpCommands.HELO.toString() + smtpCommandContent.getDomain());
        os.flush();
        logAnswer();
    }

    /**
     * This method is used to give the sender's e-mail address to the server
     * @throws IOException
     */
    private void mailFrom() throws IOException{
        LOG.log(Level.INFO, SmtpCommands.MAIL_FROM.toString() + smtpCommandContent.getSrc());
        os.write(SmtpCommands.MAIL_FROM.toString() + smtpCommandContent.getSrc());
        os.flush();
        logAnswer();
    }

    /**
     * This method is used to give the receiver's e-mail address to the server
     * @throws IOException
     */
    private void rcptTo() throws IOException{

        for(String address : smtpCommandContent.getDst()){
            LOG.log(Level.INFO, SmtpCommands.RCPT_TO.toString() + address + "\r\n");
            os.write(SmtpCommands.RCPT_TO.toString() + address + "\r\n");
            os.flush();
            logAnswer();
        }
    }

    /**
     * This method is used to send the data to the server
     * @throws IOException
     */
    private void data() throws IOException{
        LOG.log(Level.INFO, SmtpCommands.DATA.toString());
        os.write(SmtpCommands.DATA.toString());
        os.flush();
        logAnswer();
        LOG.log(Level.INFO, smtpCommandContent.getData());
        os.write(smtpCommandContent.getData());
        os.flush();
        logAnswer();
    }

    /**
     * This method is used to terminate the connection with the server
     * @throws IOException
     */
    private void quit() throws IOException{
        LOG.log(Level.INFO, SmtpCommands.QUIT.toString());
        os.write(SmtpCommands.QUIT.toString());
        os.flush();
        logAnswer();
    }

    /**
     * This method is used to globally send an e-mail to the server
     */
    public void send(){

        try{
            bind();
            helo();
            mailFrom();
            rcptTo();
            data();
            quit();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                socket.close();
                os.close();
                is.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
