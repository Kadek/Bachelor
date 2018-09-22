package engine.Entity;

import java.io.UnsupportedEncodingException;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
        
public class Utils{
    
    private static final Logger log = LoggerFactory.getLogger(LoanGiver.class);
    
    public String generatePrivateKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(512);
        byte[] privateKeyInBytes = keyGen.genKeyPair().getPrivate().getEncoded();
        return Base64.getEncoder().encodeToString(privateKeyInBytes);
    }

    public String generateAESKey() throws NoSuchAlgorithmException {
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(128); // The AES key size in number of bits
        SecretKey secKey = generator.generateKey();
        return Base64.getEncoder().encodeToString(secKey.getEncoded());
    }

    
    public String getPublicKey(final String privateKeyHex) throws NoSuchAlgorithmException, InvalidKeySpecException{
        byte[] keyData = Base64.getDecoder().decode(privateKeyHex);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        RSAPrivateKey privateKey = (RSAPrivateKey) factory.generatePrivate(new PKCS8EncodedKeySpec(keyData));
        RSAPrivateCrtKey privateKeyCert = (RSAPrivateCrtKey)privateKey;

        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(privateKeyCert.getModulus(), privateKeyCert.getPublicExponent());

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
        
    }

    public String RSAencrypt(String publicKeyHex, String information) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException {
        byte[] keyData = Base64.getDecoder().decode(publicKeyHex);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        RSAPublicKey publicKey = (RSAPublicKey) factory.generatePublic(new X509EncodedKeySpec(keyData));
        
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] cipherText = cipher.doFinal(information.getBytes(UTF_8));

        return Base64.getEncoder().encodeToString(cipherText);
    }
    
    public String RSAdecrypt(final String privateKeyHex, final String encryptedString) throws NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, InvalidKeyException, InvalidKeyException, InvalidKeySpecException{
        byte[] keyData = Base64.getDecoder().decode(privateKeyHex);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        RSAPrivateKey privateKey = (RSAPrivateKey) factory.generatePrivate(new PKCS8EncodedKeySpec(keyData));
        
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.DECRYPT_MODE, privateKey);  

        byte[] bytes = Base64.getDecoder().decode(encryptedString);
        return new String(cipher.doFinal(bytes), UTF_8);
    }
    public String AESencrypt(String AESkey, String information) throws NoSuchAlgorithmException, NoSuchPaddingException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeyException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
        byte[] secretKeyInBytes = Base64.getDecoder().decode(AESkey);
        SecretKey secretKey = new SecretKeySpec(secretKeyInBytes, 0, secretKeyInBytes.length, "AES");
        
        Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        IvParameterSpec ivspec = new IvParameterSpec(iv);
        aesCipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
        byte[] byteCipherText = aesCipher.doFinal(information.getBytes());
        
        return Base64.getEncoder().encodeToString(byteCipherText);
    }

    public String AESdecrypt(String AESkey, String information) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
        byte[] secretKeyInBytes = Base64.getDecoder().decode(AESkey);
        byte[] decodedInformation = Base64.getDecoder().decode(information.getBytes());
        SecretKey secretKey = new SecretKeySpec(secretKeyInBytes, 0, secretKeyInBytes.length, "AES");
        
        Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        IvParameterSpec ivspec = new IvParameterSpec(iv);
        aesCipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
        byte[] byteCipherText = aesCipher.doFinal(decodedInformation);
        
        return new String(byteCipherText);
    }
}
