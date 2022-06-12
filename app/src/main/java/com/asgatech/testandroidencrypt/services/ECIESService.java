package com.asgatech.testandroidencrypt.services;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.bouncycastle.jce.spec.IEKeySpec;
import org.bouncycastle.jce.spec.IESParameterSpec;

import javax.crypto.BadPaddingException;
//import javax.crypto.Cipher;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

//Elliptic Curve Integrated Encryption Scheme (ECIES).
public class ECIESService {

    public static byte[] decrypt(byte[] cipherText, PublicKey senderPublicKey, PrivateKey receiverPrivateKey) throws Exception {
        // get ECIES cipher objects
        Cipher bcipher = Cipher.getInstance("ECIES");

        //  generate derivation and encoding vectors
        byte[] d = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};
        byte[] e = new byte[]{8, 7, 6, 5, 4, 3, 2, 1};
        IESParameterSpec param = new IESParameterSpec(d, e, 256);

        // decrypt the text using the private key
        bcipher.init(Cipher.DECRYPT_MODE, new IEKeySpec(receiverPrivateKey, senderPublicKey), param);
        return bcipher.doFinal(cipherText);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static byte[] decrypt(String encryptedMessage, PublicKey senderPublicKey, PrivateKey receiverPrivateKey) throws Exception {
        return decrypt(Base64.getDecoder().decode(encryptedMessage), senderPublicKey, receiverPrivateKey);
    }


    public static byte[] encrypt(String message, PublicKey receiverPublicKey, PrivateKey senderPrivateKey) throws Exception {
        // get ECIES cipher objects
        Cipher acipher = Cipher.getInstance("ECIES");
//        Cipher acipher = Cipher.getInstance("AES");
//        Cipher acipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");

        //  generate derivation and encoding vectors
        byte[] d = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};
        byte[] e = new byte[]{8, 7, 6, 5, 4, 3, 2, 1};
        IESParameterSpec param = new IESParameterSpec(d, e, 256);

        // encrypt the plaintext using the public key
        acipher.init(Cipher.ENCRYPT_MODE, new IEKeySpec(senderPrivateKey, receiverPublicKey), param);
        Log.e("doFinale>>",acipher.doFinal(message.getBytes()).toString());
        return acipher.doFinal(message.getBytes());
    }

    public static byte[] encrypt(String message, String stringKey) throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String algorithm = "AES/CBC/PKCS5Padding";
        Cipher acipher = Cipher.getInstance(algorithm);
        acipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(stringKey.getBytes("UTF-8"), algorithm));
        Log.e("doFinale>>",acipher.doFinal(message.getBytes()).toString());
        return acipher.doFinal(message.getBytes());

    }

}