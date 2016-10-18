package servlet;

import entity.User;
import entity.Users;
import org.xml.sax.SAXException;
import xml.ParserXML;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.*;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanya on 16.10.2016.
 */

public class AuthorizationServlet extends HttpServlet {
    Users users;
    List<User> list;


    public void getUsersFromXML() {

        try {
            list = new ArrayList<>();
            users = new Users();
            users = new ParserXML().readXml();
            if (users != null)
                list = users.getUser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }


    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


        getUsersFromXML();
        String method = req.getParameter("method");

        String email = req.getParameter("email"), pass = req.getParameter("password");
        boolean result = false;
        if (method.equals("sign")) {

            if (list != null) {
                for (User user : list) {
                    if (user.getEmail().equals(email)) {
                        result = true;
                        break;
                    }
                }
                if (result == true) {
                    req.setAttribute("error_sign", "Пользователь с данным email уже существует.");

                } else {

                    User user = new User();
                    user.setEmail(req.getParameter("email"));
                    user.setFirstName(req.getParameter("first_name"));
                    user.setLastName(req.getParameter("last_name"));
                    user.setPassword(req.getParameter("password"));
                    list.add(user);
                    ParserXML x = new ParserXML();
                    try {
                        x.writeXmlUsingDOM(new Users(list));
                        req.setAttribute("error_sign", "Вы удачно зарегистрированы");

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JAXBException e) {
                        e.printStackTrace();
                    }


                }
                req.getRequestDispatcher("index.jsp").forward(req, res);

            }
        } else {

            String fname = null, lname = null;
            if (list != null) {
                for (User user : list) {
                    if (email.equals(user.getEmail()) && pass.equals(user.getPassword())) {
                        fname = user.getFirstName();
                        lname = user.getLastName();

                        result = true;
                        break;
                    }
                }

            }
            if (result == true) {
                req.setAttribute("name", fname + " " + lname);
                req.getRequestDispatcher("login.jsp").forward(req, res);
            } else {
                req.setAttribute("error_login", "Проверьте введенный login и пароль");
                req.getRequestDispatcher("index.jsp").forward(req, res);

            }
        }

    }

}
