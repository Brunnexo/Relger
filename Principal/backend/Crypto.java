package backend;

import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Crypto {

	private static final String encryptionKey  = "ABCDEFGHIJKLMNOP";
	private static final String characterEncoding = "UTF-8";
	private static final String cipherTransformation = "AES/CBC/PKCS5PADDING";
	private static final String aesEncryptionAlgorithem = "AES";

	public static String criptografar(String texto) {
		
		String encryptedText = "";
		
		try {
			Cipher cipher = Cipher.getInstance(cipherTransformation);
			byte[] chave = encryptionKey.getBytes(characterEncoding);
			SecretKeySpec secretKey = new SecretKeySpec(chave, aesEncryptionAlgorithem);
			IvParameterSpec ivparameterspec = new IvParameterSpec(chave);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameterspec);
			byte[] cipherText = cipher.doFinal(texto.getBytes("UTF8"));
			Base64.Encoder encoder = Base64.getEncoder();
			encryptedText = encoder.encodeToString(cipherText);

		} catch (Exception E) {
			System.err.println("Encrypt Exception : "+E.getMessage());
		}
		
		return encryptedText;
	}

	public static String descriptogragar(String encryptedText) {
		
		String decryptedText = "";
		
		try {
			Cipher cipher = Cipher.getInstance(cipherTransformation);
			byte[] key = encryptionKey.getBytes(characterEncoding);
			SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
			IvParameterSpec ivparameterspec = new IvParameterSpec(key);
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivparameterspec);
			Base64.Decoder decoder = Base64.getDecoder();
			byte[] cipherText = decoder.decode(encryptedText.getBytes("UTF8"));
			decryptedText = new String(cipher.doFinal(cipherText), "UTF-8");

		} catch (Exception E) {
			System.err.println("decrypt Exception : "+E.getMessage());
		}
		
		return decryptedText;
	}

	public static void main(String[] args) {
		System.out.println("Criptografia");
		
		System.out.printf("Entre com o texto a ser criptografado: ");
		
		Scanner sc = new Scanner(System.in);
		String texto = sc.nextLine();


		String criptografado   = criptografar(texto);
		String descriptografado  = descriptogragar(criptografado);

		System.out.println("Texto digitado: "+ texto);
		System.out.println("Texto criptografado: "+ criptografado);
		System.out.println("Texto descriptografado: "+ descriptografado);
		sc.close();
	}   
}