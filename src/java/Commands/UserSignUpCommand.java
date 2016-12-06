/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.UserDao;
import DAO.UserQuestionDao;
import DTO.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Conno
 */
public class UserSignUpCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String forwardToJsp = null;

        if (session.getAttribute("logged_in") == null) {

            String f_name = request.getParameter("f_name");
            String l_name = request.getParameter("l_name");
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("pass");
            String conf_password = request.getParameter("confirm_pass");
            String sQ1 = request.getParameter("select1");
            String sQ2 = request.getParameter("select2");
            String sQ3 = request.getParameter("select3");
            String sQ1Answer = request.getParameter("securityQ1");
            String sQ2Answer = request.getParameter("securityQ2");
            String sQ3Answer = request.getParameter("securityQ3");

            if ((f_name != null && !f_name.equals(""))
                    && (l_name != null && !l_name.equals(""))
                    && (username != null && !username.equals(""))
                    && (email != null && !email.equals(""))
                    && (password != null && !password.equals(""))
                    && (conf_password != null && !conf_password.equals(""))
                    && (sQ1 != null && !sQ1.equals(""))
                    && (sQ2 != null && !sQ2.equals(""))
                    && (sQ3 != null && !sQ3.equals(""))
                    && (sQ1Answer != null && !sQ1Answer.equals(""))
                    && (sQ2Answer != null && !sQ2Answer.equals(""))
                    && (sQ3Answer != null && !sQ3Answer.equals(""))) {
                UserDao userDao = new UserDao("library");
                String[] answers = new String[3];
                answers[0]= sQ1Answer;
                answers[1]= sQ2Answer;
                answers[2]= sQ3Answer;
                
                if (userDao.checkUsername(username) && userDao.checkEmail(email)) {
                    User newUser = new User();
                    newUser.setF_name(f_name);
                    newUser.setL_name(l_name);
                    newUser.setUsername(username);
                    newUser.setEmail(email);

                    boolean emailValid = newUser.validateEmail(email);
                    if (emailValid == false) {
                        session.setAttribute("error", "Invalid email");
                        forwardToJsp = "sign_up.jsp";
                    } else {

                        //checking passwords
                        String valid = newUser.passwordChecker(password, username);
                        if (!password.equals(conf_password)) {
                            session.setAttribute("error", "Passwords do not match");
                            forwardToJsp = "sign_up.jsp";
                        } else {
                            if (!valid.equals("true")) {
                                session.setAttribute("error", valid);
                                forwardToJsp = "sign_up.jsp";
                            } else {
                                int sQ1Id = Integer.parseInt(sQ1);
                                int sQ2Id = Integer.parseInt(sQ2);
                                int sQ3Id = Integer.parseInt(sQ3);
                                if ((sQ1Id == sQ2Id) || (sQ1Id == sQ3Id) || (sQ2Id == sQ3Id)) {
                                    session.setAttribute("error", "Cannot use the same security question more than once");
                                    forwardToJsp = "sign_up.jsp";
                                } else {
                                    
                                    int[] ids = new int[3];
                                    ids[0] = sQ1Id;
                                    ids[1] = sQ2Id;
                                    ids[2] = sQ3Id;
                                    boolean check = false;

                                    do {
                                        check = false;

                                        String salt = User.generateSalt();

                                        if (userDao.checkSalt(salt)) {
                                            newUser.setSalt(salt);
                                            newUser.setPassword(User.generateSaltedHash(password, salt));
                                        } else {
                                            check = true;
                                        }

                                    } while (check);

                                    newUser.setDate(User.getCurrentDate());

                                    if (userDao.addUser(newUser)) {

                                        UserQuestionDao userQDao = new UserQuestionDao("library");
                                        String saltedAnswer = null;
                                        String salt = null;
                                        int addedCount = 0;
                                        for (int i = 0; i < 3; i++) {
                                            do {
                                                check = false;

                                                salt = User.generateSalt();

                                                if (userDao.checkSalt(salt)) {
                                                    newUser.setSalt(salt);
                                                    saltedAnswer = (User.generateSaltedHash(answers[i], salt));
                                                } else {
                                                    check = true;
                                                }

                                            } while (check);
                                            
                                            boolean added = userQDao.addUserQuestionAnswer(ids[i], userDao.getUserId(username, email), saltedAnswer, salt);
                                            if(added==true){
                                                addedCount++;
                                            }
                                        }
                                        if (addedCount<3) {
                                            session.setAttribute("error", "Failed to add your Security Questions, Contact Admin");
                                            forwardToJsp = "sign_up.jsp";
                                        } else {
                                            session.setAttribute("notify", "Account successfully created");
                                            forwardToJsp = "login.jsp";
                                        }
                                    } else {
                                        session.setAttribute("error", "An unkown error occured. Please wait while we try to fix this");
                                        forwardToJsp = "sign_up.jsp";
                                    }
                                }
                            }
                        }
                    }
                } else {
                    session.setAttribute("error", "Username or email already in use. Please choose another");
                    forwardToJsp = "sign_up.jsp";
                }

            } else {
                session.setAttribute("error", "Missing input data. Please try again");
                forwardToJsp = "sign_up.jsp";
            }
        } else {
            session.setAttribute("error", "You are already logged in. Please log out first");
            forwardToJsp = "home.jsp";
        }

        return forwardToJsp;
    }

}
