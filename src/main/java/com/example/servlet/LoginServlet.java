package com.example.servlet;

import com.example.Users;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object attr = req.getSession().getAttribute("user");

        RequestDispatcher requestDispatcher;
        if (attr == null){
            requestDispatcher = req.getRequestDispatcher("user/hello.jsp");
        }else {
            requestDispatcher = req.getRequestDispatcher("login.jsp");
        }
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("password");

        if (login != null && pass != null && !login.equals("") && !pass.equals("")){

            if (Users.getInstance().getUsers().contains(login) && pass.trim().length() != 0) {
                req.getSession().setAttribute("user", login);
                resp.sendRedirect("user/hello.jsp");
            }
        } else {
            resp.sendRedirect("login.jsp");
        }

    }
}
