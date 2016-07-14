package com.controller;

import com.persistence.ContactDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/PhoneBookController")
public class PhoneBookController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ContactDB cDb = new ContactDB();
        request.setAttribute("contactList", cDb.contactList());
        request.getRequestDispatcher("contactList.jsp").forward(request,response);

    }
}
