/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.unibe.cde.geonet.kernel.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import jeeves.utils.Log;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Adrian Weber, Centre for Development and Environment, University of
 * Bern
 */
public class Md5PasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence cs) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(cs.toString().getBytes("UTF-8"));
            return "md5:" + Md5PasswordEncoder.byteToHex(md.digest());
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Log.error(Log.JEEVES, ex);
        }
        return null;
    }

    @Override
    public boolean matches(CharSequence rawSequence, String encodedPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(rawSequence.toString().getBytes("UTF-8"));
            return ("md5:" + Md5PasswordEncoder.byteToHex(md.digest())).equals(encodedPassword);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Log.error(Log.JEEVES, ex);
        }
        return false;
    }

    /**
     *
     * @param hash
     * @return
     *
     * Copied from http://stackoverflow.com/a/9071224/1829038
     */
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

}
