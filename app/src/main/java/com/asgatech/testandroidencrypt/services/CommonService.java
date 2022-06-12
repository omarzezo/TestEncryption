package com.asgatech.testandroidencrypt.services;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Random;

public class CommonService {
    private static String serverPublicKey = "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAEnwWOUXfFA+fREhmzEzsB9wjx79yz2DpTLnhw3W0kjfy4jwE27V5fiCFs3cb6gXWfVtjqyRr2U9WBqTsx9+DTwA==";

    private static String serverPrivateKey = "MD4CAQAwEAYHKoZIzj0CAQYFK4EEAAoEJzAlAgEBBCDn19WhSGTQypacRxpasiRTVmGMmB1q8OyTo9cMG97Q3Q==";

//    userId: 1
    private static String clientPublicKey = "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAEIrPONfhFl9KiMMaD4ViotZQZDSN3hRbYbZo+l0FRkk3rFQGecUXE3+A3tBEu30le4TnsT80n99DTbXhx7lzhkA==";
    private static String clientPrivateKey = "MD4CAQAwEAYHKoZIzj0CAQYFK4EEAAoEJzAlAgEBBCAUKZWu1bIs+4X5xC3vG47VL2Tz2v8SpWYL6wwhKT2/6w==";

//    userId: 2
//    private static String clientPublicKey = "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAECqWUyZKtQ2dkRHGvjFnMow8LuY6xSwxjj7sN7+/0jyJ0JG2iUWu+2CSRYqhHl+DK2FqiMDs9mvjobeavrc065w==";
//    private static String clientPrivateKey = "MD4CAQAwEAYHKoZIzj0CAQYFK4EEAAoEJzAlAgEBBCBQ3rk2+lZDnvVau8DeokVpANdeNYP6FSOEo+bsQcdolA==";

//        private static String clientPublicKey = "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAECqWUyZKtQ2dkRHGvjFnMow8LuY6xSwxjj7sN7+/0jyJ0JG2iUWu+2CSRYqhHl+DK2FqiMDs9mvjobeavrc065w==";
//    private static String clientPrivateKey = "MD4CAQAwEAYHKoZIzj0CAQYFK4EEAAoEJzAlAgEBBCBOMCSdcHayzJ700AW9QBhfyo8JVLw2vzxmrOPtR/2u0w==";


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static PublicKey getServerPublicKey() {
        return parsePublicKeys(serverPublicKey);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static PrivateKey getServerPrivateKey() {
        return parsePrivateKeys(serverPrivateKey);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static PublicKey getClientPublicKey() {
        return parsePublicKeys(clientPublicKey);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static PrivateKey getClientPrivateKey() {
        return parsePrivateKeys(clientPrivateKey);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static PublicKey parsePublicKeys(String publicKey) {
        try {
            EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            return keyFactory.generatePublic(publicKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static PrivateKey parsePrivateKeys(String privateKey) {
        try {
            EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            return keyFactory.generatePrivate(privateKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String generateRandomKey() {
        return generateRandomKey(10);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String generateRandomKey(int length) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}
