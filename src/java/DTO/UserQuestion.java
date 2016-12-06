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
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author Ren
 */
public class UserQuestion {

    private int sq_id;
    private int user_id;
    private String answer;

    public UserQuestion() {
        sq_id = -1;
        user_id = -1;
        answer = null;
    }

    public UserQuestion(int sq_id, int user_id, String answer) {
        this.sq_id = sq_id;
        this.user_id = user_id;
        this.answer = answer;
    }

    public int getSq_id() {
        return sq_id;
    }

    public void setSq_id(int sq_id) {
        this.sq_id = sq_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.sq_id;
        hash = 37 * hash + this.user_id;
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
        final UserQuestion other = (UserQuestion) obj;
        if (this.sq_id != other.sq_id) {
            return false;
        }
        if (this.user_id != other.user_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserQuestion{" + "sq_id=" + sq_id + ", user_id=" + user_id + ", answer=" + answer + '}';
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

}
