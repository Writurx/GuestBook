package com.persistence;

import com.model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContactDB {

    private final MyConnection conn;
    private PreparedStatement select;
    private PreparedStatement update;
    private ResultSet rs;

    public ContactDB() {
        conn = new MyConnection();
    }

    public int submit(final String name, final String email, final Integer phone) {
        int res = 0;

        try{
            initConn();

            String sql = "INSERT INTO users (name, email, phone) VALUES(?,?,?)";

            update = conn.getConn().prepareStatement(sql);
            update.setString(1,name);
            update.setString(2,email);
            update.setInt(3,phone);

            res = update.executeUpdate();

            update.close();
            conn.getConn();
        }catch (SQLException sqle) {
            System.err.println("Error " + sqle.getMessage());
        }

        return res;
    }

    public ArrayList<Contact> contactList() {

        initConn();

        ArrayList<Contact> contacts = new ArrayList<Contact>();
        try{
            select = conn.getConn().prepareStatement("SELECT * FROM users;");
            rs = select.executeQuery();
            while(rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getInt("id"));
                contact.setName(rs.getString("name"));
                contact.setEmail(rs.getString("email"));
                contact.setPhone(rs.getInt("phone"));

                contacts.add(contact);
            }

        }catch (SQLException sqle) {
            System.err.println("Error con contactList " + sqle.getMessage());
        }

        return contacts;
    }

    public Contact selectContact(final Integer id) {

        initConn();
        Contact contact = null;
        String sql = "SELECT * FROM users WHERE id=(?);";
        try{
            select = conn.getConn().prepareStatement(sql);
            select.setInt(1, id);
            rs = select.executeQuery();
            while(rs.next()) {
                contact = new Contact();
                contact.setId(rs.getInt("id"));
                contact.setName(rs.getString("name"));
                contact.setEmail(rs.getString("email"));
                contact.setPhone(rs.getInt("phone"));
            }
            select.close();
            conn.closeCon();
        }catch (SQLException sqle){
            System.out.println("Error select contact from BD" + sqle.getMessage());
        }
        return contact;
    }

    public int updateContact(Integer id, String name, String email, Integer phone) {
        initConn();

        int res = 0;
        String sql = "UPDATE users SET name=(?), email=(?), phone=(?) WHERE id=(?);";

        try{
            update = conn.getConn().prepareStatement(sql);
            update.setString(1,name);
            update.setString(2,email);
            update.setInt(3,phone);
            update.setInt(4, id);

            res = update.executeUpdate();

            update.close();
            conn.getConn();
        }catch (SQLException sqle){
            System.out.println("Error Update contact" + sqle.getMessage());
        }
        return res;
    }

    public int deleteContact(Integer id) {
        initConn();

        int res = 0;
        String sql = "DELETE FROM users WHERE id=(?);";

        try{
            update = conn.getConn().prepareStatement(sql);
            update.setInt(1, id);

            res = update.executeUpdate();

            update.close();
            conn.getConn();
        }catch (SQLException sqle){
            System.out.println("Error deleted contact" + sqle.getMessage());
        }
        return res;
    }

    private void initConn() {
        if(conn.getConn() == null)
            conn.init();
    }
}
