package ua.com.vp.confapp.utils;


import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.vp.confapp.exception.ServiceException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class EmailNotifier {
    private final static Logger logger = LogManager.getLogger(EmailNotifier.class);
    private static final String PATH_CONFIG = "mail.properties";
    private static final String USER = "mail.user";
    private static final String PASSWORD = "mail.password";

    public void send(String sendTo, String subject, String messageToSend) throws ServiceException {
        Properties properties = loadProperties(PATH_CONFIG);
        // for mailtrap.io
        String from = "myconferences.world@conf.com";
        final String user = properties.getProperty(USER);
        final String password = properties.getProperty(PASSWORD);

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(sendTo));
            message.setSubject(subject);
            message.setContent(messageToSend, "text/html");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.error("Error sending email!", e);
            // throw new ServiceException(e);
        }
    }

    public void sendWelcomeLetter(String sendTo) throws ServiceException {
        final String WELCOME_LETTER = getWelcomeContent();
        if (WELCOME_LETTER == null) return;
        send(sendTo, "Welcome to the Conferences World!", WELCOME_LETTER);
    }

//    public void sendWarningLetter(String sendTo) throws ServiceException {
//        final String WARNING_LETTER = getWarningContent();
//        send(sendTo, "KKKK", WARNING_LETTER);
//    }

    private Properties loadProperties(String fileName) {
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.log(Level.FATAL, "Reading file error", e);
            throw new RuntimeException(e);
        }
        return properties;
    }

    private String getWelcomeContent() {
        Path path = null;


        try {
            URL url = getClass().getClassLoader().getResource("..\\..\\template\\email\\welcome_letter.html");
            if (url == null) {
                logger.log(Level.FATAL, "URL is null");
                return null;
            }
            path = Paths.get(getClass().getClassLoader().getResource("..\\..\\template\\email\\welcome_letter.html").toURI());
        } catch (Exception e) {
            logger.log(Level.FATAL, "URI error", e);
        }
        return readFromFile(path);
    }


//    private String getWarningContent(){
//        return readFromFile("/template/email/warning_letter.html");
//    }

    private String readFromFile(Path path) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(path.toString()))) {
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str);
            }
            System.out.println(contentBuilder.toString());
        } catch (IOException e) {
            logger.log(Level.FATAL, "Reading file error", e);
        }
        return contentBuilder.toString();
    }
}
