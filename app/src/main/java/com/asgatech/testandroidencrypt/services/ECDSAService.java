package com.asgatech.testandroidencrypt.services;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.security.*;
import java.util.Base64;

//Elliptic Curve Digital Signature Algorithm
public class ECDSAService {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String sign(String message, PrivateKey senderPrivateKey) throws Exception {
        try {
            Signature ecdsaSign = Signature.getInstance("SHA256withECDSA");
            ecdsaSign.initSign(senderPrivateKey);
            ecdsaSign.update(message.getBytes("UTF-8"));
            byte[] signature = ecdsaSign.sign();
            return Base64.getEncoder().encodeToString(signature);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static boolean verify(String message, String signature, PublicKey senderPublicKey) {
        try {
            Signature ecdsaVerify = Signature.getInstance("SHA256withECDSA");

            ecdsaVerify.initVerify(senderPublicKey);
            ecdsaVerify.update(message.getBytes("UTF-8"));
            return ecdsaVerify.verify(Base64.getDecoder().decode(signature));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
