package noyau;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SmtpAuthenticator extends Authenticator {
    public SmtpAuthenticator() {

        super();
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        String username = "driouaimed@gmail.com";
        String password = "howtodo25";
        return new PasswordAuthentication(username, password);
    }
}