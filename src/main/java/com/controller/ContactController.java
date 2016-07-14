package com.controller;

import com.model.Contact;
import com.persistence.ContactDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/ContactController")
public class ContactController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            String name = null;
            String email = null;
            Integer phone = 0;

            if(request.getParameter("name") != null && request.getParameter("name") != "")
                name = request.getParameter("name");

            if(request.getParameter("email") != null && request.getParameter("email") != "")
                email = request.getParameter("email");

            if(request.getParameter("phone") != null && request.getParameter("phone") != "")
                phone = Integer.parseInt(request.getParameter("phone"));

            if(name == null || email == null || phone == 0) {
                request.setAttribute("result", "Not all fields are filled");
                request.getRequestDispatcher("result.jsp").forward(request, response);
            }else {
                new ContactDB().submit(name,email,phone);
                request.setAttribute("result", "data successfully added");
                request.getRequestDispatcher("result.jsp").forward(request, response);
            }
        }finally {

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = null;

        Integer id = 0;

        if(request.getParameter("action") != null && request.getParameter("action") != "")
            action = request.getParameter("action");

        if(request.getParameter("id") != null && request.getParameter("id") != "")
            id = Integer.parseInt(request.getParameter("id"));


        if (action.toUpperCase().equals("E")) {
            Contact contact = new ContactDB().selectContact(id);
            if(contact == null || id == 0){
                request.setAttribute("result", "Contact con id="+id+" not updated");
                request.getRequestDispatcher("result.jsp").forward(request, response);
            }else {
                request.setAttribute("contact", contact);
                request.getRequestDispatcher("update.jsp").forward(request, response);
            }
        }else if (action.toUpperCase().equals("ABC")){
            if(id == 0){
                request.setAttribute("result", "Contact con id="+id+" not updated");
                request.getRequestDispatcher("result.jsp").forward(request, response);
            }else {
                String name = null;
                String email = null;
                Integer phone = 0;

                if(request.getParameter("name") != null && request.getParameter("name") != "")
                    name = request.getParameter("name");

                if(request.getParameter("email") != null && request.getParameter("email") != "")
                    email = request.getParameter("email");

                if(request.getParameter("phone") != null && request.getParameter("phone") != "")
                    phone = Integer.parseInt(request.getParameter("phone"));

                if(name == null || email == null || phone == 0 ||
                        (new ContactDB().updateContact(id, name, email, phone)) <= 0) {
                    request.setAttribute("result", "Contact con id=" + id + " not updated");
                    request.getRequestDispatcher("result.jsp").forward(request, response);
                }else {
                    request.setAttribute("result", "Contact con id=" + id + " updated");
                    request.getRequestDispatcher("result.jsp").forward(request, response);
                }
            }
        }else if(action.toUpperCase().equals("D")) {
            if(id == 0 || ((new ContactDB().deleteContact(id)) <=0)){
                request.setAttribute("result", "Contact con id="+id+" deleted");
                request.getRequestDispatcher("result.jsp").forward(request, response);
            }else {
                request.setAttribute("result", "Contact con id="+id+"Deleted");
                request.getRequestDispatcher("result.jsp").forward(request, response);
            }
        }

    }
}
