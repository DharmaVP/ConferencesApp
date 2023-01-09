package ua.com.vp.confapp.controller;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.vp.confapp.command.CommandFactory;
import ua.com.vp.confapp.command.CommandResult;
import ua.com.vp.confapp.command.action.Command;
import ua.com.vp.confapp.exception.CommandException;
import ua.com.vp.confapp.exception.ServiceException;

import static ua.com.vp.confapp.command.constants.WebPages.ERROR_PAGE;


@WebServlet(name = "FrontController", urlPatterns = "/controller")
public class FrontController extends HttpServlet {
    private static final CommandFactory FACTORY = CommandFactory.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(FrontController.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = null;
        CommandResult commandResult = null;

        try {
            command = FACTORY.getCommand(request);
            commandResult = command.execute(request, response);
        } catch (CommandException | ServiceException e) {
            LOGGER.error(e.getMessage());
        }
        doResponse(request, response, commandResult);
    }


    private void doResponse(HttpServletRequest request, HttpServletResponse response, CommandResult commandResult) throws IOException, ServletException {
        String page = ERROR_PAGE;

        if (commandResult != null && commandResult.getPage() != null) {
            page = commandResult.getPage();
            if (commandResult.isRedirect()) {
                response.sendRedirect(request.getContextPath() + page);
            } else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request, response);
            }
        } else {
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}