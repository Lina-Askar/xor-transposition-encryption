# XOR and Transposition Encryption – Java Project

A custom encryption system developed in Java using a combination of XOR operation, transposition, and SHA-256 hashing to ensure both confidentiality and integrity of data. Developed as a group project for IT course.

## Features
- Generate random binary keys for encryption
- Encode plaintext to binary and apply XOR operation
- Add a transposition layer for added encryption complexity
- Compute SHA-256 hash for integrity checking
- Decrypt encrypted data and validate against original hash

## Technologies Used
- Java
- SHA-256 (Java Security API)

## Files
- `XORAndTranspositionEncryption.java`: Contains full implementation of encryption, decryption, hashing, and integrity verification

## How to Run
1. Open the file in any Java IDE (e.g., IntelliJ IDEA, Eclipse, NetBeans)
2. Compile and run `XORAndTranspositionEncryption.java`
3. The console will display:
   - Generated Key
   - Encrypted text
   - SHA-256 hash of original text
   - Decrypted text
   - Integrity verification result

## Team Members
- Lina Mansour Askar  
- Nawf Saud Almutairi  
- Shahad Khalid Albalawi  
- Atheer Abdullah Alrashidi  
- Sara Saleh Alali

## References
- NIST. (2023). [Advanced Encryption Standard (AES)](https://doi.org/10.6028/NIST.FIPS.197-upd1)  
- [XOR Cipher – Wikipedia](https://en.wikipedia.org/wiki/XOR_cipher)  
- [GeeksForGeeks – XOR Encryption](https://www.geeksforgeeks.org/xor-encryption-shifting-plaintext/)  
- [Crypto Stack Exchange – XOR and Cipher Stream](https://crypto.stackexchange.com/questions/55708/xor-and-cipher-stream)
