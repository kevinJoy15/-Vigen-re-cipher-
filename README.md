# -Vigen-re-cipher-
Coding a simple Vigenère cipher on java 

Implement a Vigenère cipher in Java to encrypt a message of any length. A Vigenère cipher is an example of a polyalphabetic substitution cipher. To encrypt a message, you first choose a keyword to use and then repeat it until it is the same length as the message you wish to encode. For example, if you choose the keyword LEMON and the message ATTACKATDAWN, you would have:

Message: 	A	T	T	A	C	K	A T	D	A	W	N

Key: 		L	E	M	O	N	L E M	O	N	L	E

Next, each letter of the repeated keyword corresponds to the cipher alphabet (i.e. row) used to code each letter of the message based on the Vigenère square below. The letter in the original text is then replaced by the letter in the corresponding index of the cipher alphabet. That is, for the first two letters of the message example above, 'A' is coded as 'L' i.e. the 1st index of the cipher alphabet 'L', and 'T' is coded as 'X' i.e. the 20th index of cipher alphabet 'E' etc. Repeating these steps the fully coded message is - LXFOPVEFRNHR.

Rules:
Your class should be called VigenereCipher and should use the Cipher interface (available here)
You should use the Vigenère square above to encrypt and decrypt the messages using a given key (both retrieved from a file).
Encrypted messages should be in capital letters to obfuscate the message from anyone intercepting them
Decrypted messages should also be in capital letters
If the character is a letter of the alphabet it should be encrypted based on the above
If the character is not in the alphabet then it should remain unchanged
