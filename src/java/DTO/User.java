/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author Conno
 */
public class User {

    private final int user_id;
    private String username;
    private String email;
    private String password;
    private String salt;
    private String f_name;
    private String l_name;
    private String date;
    private boolean is_admin;

    public User() {
        user_id = -1;
        username = null;
        email = null;
        password = null;
        salt = null;
        f_name = null;
        l_name = null;
        date = null;
        is_admin = false;
    }

    public User(int user_id, String username, String email, String password,
            String salt, String f_name, String l_name, String date, boolean is_admin) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.f_name = f_name;
        this.l_name = l_name;
        this.date = date;
        this.is_admin = is_admin;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.user_id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.user_id != other.user_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "user_id=" + user_id + ", username=" + username
                + ", email=" + email + ", password=" + password
                + ", salt=" + salt + ", f_name=" + f_name
                + ", l_name=" + l_name + ", date=" + date
                + ", is_admin=" + is_admin + '}';
    }

    public static String generateSalt() {

        //Create SecureRandom and Base64Encoder objects
        SecureRandom secRand = new SecureRandom();
        Base64.Encoder enc = Base64.getEncoder();

        //Create salt array to house the bytes
        byte[] salt = new byte[32];

        //Generate bytes
        secRand.nextBytes(salt);

        //Return salt as string
        return enc.encodeToString(salt);
    }

    public static String generateSaltedHash(String password, String salt) {

        Base64.Encoder enc = Base64.getEncoder();
        byte[] hash = null;
        KeySpec spec = new PBEKeySpec(password.toCharArray(),
                salt.getBytes(), 65563, 256);
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            hash = keyFactory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("No such algorithm found");
        } catch (InvalidKeySpecException ex) {
            System.out.println("Key spec chosen is invalid");
        }

        return enc.encodeToString(hash);
    }

    public static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();

        return dateFormat.format(cal.getTime());
    }

    public String passwordChecker(String pass, String username) {
        String valid = null;

        //checking length is within limits and does not contain they're username
        if (pass.length() >= 9 && pass.length() <= 20) {
            //check it contains a number, uppercase + lowercase letter and a non
            if (!pass.contains(username)) {

                //alphabetic character
                boolean numCheck = false;
                boolean upperCheck = false;
                boolean lowerCheck = false;
                boolean charCheck = false;

                String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[~|-@#$%^&+=]).{9,20}";
                boolean checkPass = pass.matches(pattern);
                if (checkPass == false) {
                    valid = "Password does not meet the requirements \n "
                            + "Password must contain at least one "
                            + "-UPPERCASE letter "
                            + "-lowercase letter "
                            + "-number "
                            + "-special character e.g.(*[~|-@#$%^&+=])";
                } else {
                    valid = "true";
                }
            } else {
                valid = "Password must not contain you're username";

            }
        } else {
            valid = "Password must be between 9 to 20 characters long";
        }

        return valid;
    }
}
