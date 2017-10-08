package io.nothing.sample.utility;

 import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
 import android.util.Log;


import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.text.SimpleDateFormat;
 import java.util.Calendar;
 import java.util.Locale;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by aangjnr on 24/06/2017.
 */

public class DbHelper extends SQLiteOpenHelper {


    static final String TAG = DbHelper.class.getSimpleName();
    static final String DB_NAME = "lpg_consumer.db";
    static final int DB_VERSION = 2;

    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " integer";
    private static final String COMMA_SEP = ",";


    private static DbHelper instance;
    Context _context;
    SharedPreferences prefs;
    private SQLiteDatabase db;


    private DbHelper(Context ctx) { //
        super(ctx, DB_NAME, null, DB_VERSION);
        prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        db = this.getWritableDatabase();
        _context = ctx;

    }

    public static synchronized DbHelper getInstance(Context ctx) {
        if (instance == null) {
            instance = new DbHelper(ctx.getApplicationContext());

        }
        return instance;
    }

    public static SecretKey generateKey(String password)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        return new SecretKeySpec(password.getBytes(), "AES");
    }

    public static String encryptPin(String password)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidParameterSpecException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, InvalidKeySpecException {
        //password += uid.substring(0, 10);



   /* Encrypt the message. */
        Cipher cipher = null;
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(password.getBytes(), "AES"));


        String encryption = new String(cipher.doFinal(password.getBytes("UTF-8")));

        Log.d(TAG, "Password key = " + encryption);

        Log.d(TAG, "Encryption = " + encryption);

        return encryption;
    }

    public static String decryptPin(String cipherText)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidParameterSpecException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
    /* Decrypt the message, given derived encContentValues and initialization vector. */
        Cipher cipher = null;


        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(cipherText.getBytes(), "AES"));

        String decryption = new String(cipher.doFinal(cipherText.getBytes()), "UTF-8");

        Log.d(TAG, "Password key = " + decryption);

        Log.d(TAG, "Decryption = " + decryption);


        return decryption;
    }

    public static String encrypt(String strClearText, String uid) throws Exception {
        String strData = "";
/*
        Log.d(TAG, "Encryption key is = " + uid.substring(0, 10));



        try {
            SecretKeySpec skeyspec=new SecretKeySpec(uid.substring(0, 10).getBytes(),"Blowfish");
            Cipher cipher=Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
            byte[] encrypted=cipher.doFinal(strClearText.getBytes());
            strData=new String(encrypted);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }*/
        return strClearText;
    }

    public static String decrypt(String strEncrypted, String uid) throws Exception {
       /* String strData="";

        Log.d(TAG, "Decryption key is = " + uid.substring(0, 10));

        try {
            SecretKeySpec skeyspec=new SecretKeySpec(uid.substring(0, 10).getBytes(),"Blowfish");
            Cipher cipher=Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, skeyspec);
            byte[] decrypted=cipher.doFinal(strEncrypted.getBytes());
            strData=new String(decrypted);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }*/
        return strEncrypted;
    }

    //Todo check on the INT_TYPE

    public synchronized void resetDatabase() {
        //Remove the data from all the tables
    }

    public void beginTransaction() {
        db.beginTransaction();
    }

    public void endTransaction(boolean success) {
        if (success) {
            db.setTransactionSuccessful();
        }
        db.endTransaction();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        onCreate(db);
    }


    Boolean parseBoolean(String value) {
        if (value.equalsIgnoreCase("true"))
            return true;
        else if (value.equalsIgnoreCase("false"))
            return false;
        else return null;
    }

    public String getSystemTime() {
        return String.valueOf(System.currentTimeMillis());
    }

    public String getTime() {

        return new SimpleDateFormat("h:mm a", Locale.getDefault())
                .format(Calendar.getInstance().getTime());

    }

    public String getDate() {
        return new SimpleDateFormat("MMM d", Locale.getDefault())
                .format(Calendar.getInstance().getTime());
    }

    public String getRandomAlphaNumericString() {
        return new BigInteger(130, new SecureRandom()).toString(32);

    }


}
