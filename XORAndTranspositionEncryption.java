/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package xorandtranspositionencryption;



import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class XORAndTranspositionEncryption {

    // Generate a binary key
    public static String generateKey(int blockSize) {
        Random random = new Random();
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < blockSize; i++) {
            key.append(random.nextBoolean() ? "1" : "0");
        }
        return key.toString();
    }

    // Convert a text string to binary representation
    public static String textToBinary(String text) {
        StringBuilder binary = new StringBuilder();
        for (char c : text.toCharArray()) {
        String binaryChar = 
                String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');
            binary.append(binaryChar);
        }
        return binary.toString();
    }
// XOR operation between two binary strings
    public static String xorOperation(String binaryBlock, String key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < binaryBlock.length(); i++) {
            char bit = (binaryBlock.charAt(i) == key.charAt(i)) ? '0' : '1';
            result.append(bit);
        }
        return result.toString();
    }

    //  transposition function (e.g., reverse the bits in the block)
    public static String transposeEncrypt(String binaryBlock) {
        return new StringBuilder(binaryBlock).reverse().toString();
    }

   
    // Convert binary representation back to text
    public static String binaryToText(String binary) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < binary.length(); i += 8) {
            String byteString = binary.substring(i, i + 8);
            char c = (char) Integer.parseInt(byteString, 2);
            text.append(c);
        }
        return text.toString();
    }

    

    // Encrypt the text
    public static String encrypt(String plainText, String key) {
        String binaryText = textToBinary(plainText);
        StringBuilder cipherText = new StringBuilder();
        int blockSize = key.length();

        // Process each block
        for (int i = 0; i < binaryText.length(); i += blockSize) {
            String block = 
                    binaryText.substring(i, Math.min(i + blockSize, binaryText.length()));

            // Pad the last block if it's shorter than the block size
            if (block.length() < blockSize) {
                block = String.format("%-" + blockSize + "s", block).replace(' ', '0');
            }

            // XOR and transpose
            String xorResult = xorOperation(block, key);
            String transposedResult = transposeEncrypt(xorResult);
            cipherText.append(transposedResult);
        }

        return cipherText.toString();
    }

    // Decrypt the cipher text
    public static String decrypt(String cipherText, String key) {
        StringBuilder binaryText = new StringBuilder();
        int blockSize = key.length();

        // Process each block
        for (int i = 0; i < cipherText.length(); i += blockSize) {
            String block = cipherText.substring(i, i + blockSize);

            // Reverse transpose and XOR
            String transposedBlock = transposeDecrypt(block);
            String xorResult = xorOperation(transposedBlock, key);
            binaryText.append(xorResult);
        }

        return binaryToText(binaryText.toString());
    }
    
public static String transposeDecrypt(String binaryBlock) {
        return new StringBuilder(binaryBlock).reverse().toString();
    }
    // Apply SHA-256 hash for integrity
    public static String applyHash(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // Main function to test the encryption, decryption, and integrity check
    public static void main(String[] args) {
        String plainText = "mansour";
        int blockSize = 4;
        String key = generateKey(blockSize);

        System.out.println("Generated Key: " + key);
        System.out.println("Plain Text: " + plainText);

        // Encrypt the text
        String cipherText = encrypt(plainText, key);
        System.out.println("Cipher Text: " + cipherText);

        // Compute hash for integrity
        String integrityHash = applyHash(plainText);
        System.out.println("Integrity Hash (SHA-256): " + integrityHash);

        // Decrypt the text
        String decryptedText = decrypt(cipherText, key);
        System.out.println("Decrypted Text: " + decryptedText);

        // Verify integrity by comparing hashes
        String decryptedHash = applyHash(decryptedText);
        System.out.println("Decrypted Text Hash: " + decryptedHash);

        if (integrityHash.equals(decryptedHash)) {
            System.out.println("Integrity Verified: Decrypted text matches original text.");
        } else {
            System.out.println("Integrity Verification Failed: Decrypted text does not match original text.");
        }
    }
}
