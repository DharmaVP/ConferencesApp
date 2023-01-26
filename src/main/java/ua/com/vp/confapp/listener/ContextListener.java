package ua.com.vp.confapp.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.vp.confapp.command.action.attendee.RegisterForEventCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ContextListener implements ServletContextListener {
    private static Logger LOGGER = LogManager.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log("Servlet context initialization starts");

        ServletContext servletContext = sce.getServletContext();


        log("Servlet context initialization finished");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log("Servlet context destruction starts");
        // do nothing
        log("Servlet context destruction finished");
    }

    /**
     * Initializes i18n subsystem.
     */


    private void log(String msg) {
        System.out.println("[ContextListener] " + msg);
    }
}
