/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #5
 * CSE 214 Summer
 * R30 - Charlie Clark
 */

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

public class Email implements Serializable { //Email class
    //Implements serializable to save the email files into a .obj.
    private String to;
    private String cc;
    private String bcc;
    private String subject;
    private String body;
    private GregorianCalendar timestamp;

    /**
     *
     * @param to
     * 'to' person getting sent email
     * @param cc
     * sends to another person other than main recipient.
     * @param bcc
     * sends to another person other than main recipient
     * while keeping it a secret.
     * @param subject
     * subject of the email
     * @param body
     * the email text itself
     * @param timestamp
     * time of the email
     *
     * This constructs the entire email.
     */
    public Email(String to, String cc, String bcc, String subject, String body, GregorianCalendar timestamp){
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.body = body;
        this.timestamp = timestamp;
    }

    /**
     *
     * @return
     *
     * getter and returns bcc
     */
    public String getBcc() {
        return bcc;
    }

    /**
     *
     * @return
     *
     * getter and returns body
     */
    public String getBody() {
        return body;
    }

    /**
     *
     * @return
     *
     * getter and returns cc
     */
    public String getCc() {
        return cc;
    }

    /**
     *
     * @return
     *
     * getter and returns subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     *
     * @return
     *
     * getter and returns to
     */
    public String getTo() {
        return to;
    }

    /**
     *
     * @return
     *
     * getter and returns timestamp
     */
    public GregorianCalendar getTimestamp() {
        return timestamp;
    }

    /**
     *
     * @param bcc
     * bcc
     *
     * sets the bcc
     */
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    /**
     *
     * @param body
     * body of text
     *
     * sets the body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     *
     * @param cc
     * cc
     *
     * sets the cc
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     *
     * @param subject
     * subject of email
     *
     * sets the subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     *
     * @param timestamp
     * timestamp of email
     *
     * sets the timestamp
     */
    public void setTimestamp(GregorianCalendar timestamp) {
        this.timestamp = timestamp;
    }

    /**
     *
     * @param to
     * to
     *
     * sets the to
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     *
     * @return
     *
     * Returns a string that is accessed by Mailbox
     * to show the current email's information.
     */
    public String view(){
        return "To: " + to + "\nCC: " + cc
                + "\nBCC: " + bcc + "\nSubject: " + subject
                + "\nBody: " + body;
    }

    /**
     *
     * @return
     *
     * Helper method which is accessed by Mailbox class.
     * It helps convert the timestamp into a date, which
     * is then formatted into time and date by SimpleDateFormat.
     */
    public Date getDateTime(){
        return timestamp.getTime();
    }
}
