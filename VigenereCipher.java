import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;  
import java.util.Scanner;

public class VigenereCipher implements Cipher
{
    // this function is responsible for reading the message file and breaking each message into a string array
    public static String[] readfile(String file) throws IOException       
    {
        try 
        {
            List<String> stringlist = new ArrayList<String>();  // making arraylist to store messages
            stringlist = Files.readAllLines(Paths.get(file));
            String[] fileArray = stringlist.toArray(new String[0]);         // converts the arraylist messages into an array
            return fileArray;       // returns the array
        } 
        catch (FileNotFoundException e) {
        }
        return null;
    }

    public static String readkey(String key)        // used to read the key from the file
    {
        try 
        {
            File obj = new File(key);             // file object created for the key file
            Scanner read = new Scanner(obj);  
            String data ="";
            while (read.hasNextLine()) 
            {
                data = read.nextLine();
            } 
            read.close();

            return data;        // returns the key as a string
        } 
        catch (FileNotFoundException e) {
            return null;
        }
    }

    public static String key_generator(int charcnt, String key)        // this function is responsible for creating the new key with message length
    {
        int i = 0;
        key = key.toUpperCase();            // key to upper case
        String final_key = key;

        while(final_key.length() <= charcnt)           // checks until the length of the key matches the message length
        {
            if(i == key.length())                 // if the index goes to the last character of the key it resets back to 0
                i = 0;
            
            final_key = final_key + key.charAt(i);          // concats to form the new key
            i = i + 1;
        }
        return final_key;           // returns the new key

    }

    public String encrypt(String message_filename, String key_filename) // encrypt method using the formula to calculate the encryptted text
	{
        
        try
        {
            String encrypted_string = "";                       // final encrypted text

            String in_key = readkey(key_filename);                      // string version of key using function 
            String messageFile[] = readfile(message_filename);      // string array with alll messages
            
            int charcnt = 0;                // counts the number of total characters in the message file
            for(int x = 0; x < messageFile.length; x++)
            {
                charcnt  = charcnt + messageFile[x].length();           // nummber of characters
            }

            String message = "";
            int cnt = 0;        

            String key = key_generator(charcnt, in_key);
            for(int a = 0; a < messageFile.length; a++)
            {
                message = messageFile[a].toUpperCase();         // gets the first message on the ath line

                char val;
                
                for (int i = 0; i < message.length(); i++, cnt++)           //loops through each character of the message
                {
                    if(message.charAt(i) < 65 || message.charAt(i) > 90)        // checks if the character is not a letter and hence adds it to the string
                        encrypted_string = encrypted_string + message.charAt(i);
                    else
                    {  
                        int x = ((message.charAt(i) + key.charAt(cnt)) % 26) + 65;      // formula applied to find encrypted character
                        val = (char)(x);            // conversion to letter from ascii value
                        encrypted_string = encrypted_string + val;          // concating it to a string so as to make it a final value
                    }
                }
                if(a!= messageFile.length-1)
                    encrypted_string = encrypted_string + "\n";         // adds new line to each encrpyted message
                cnt = cnt +1;                   // done to cycle through each value
            }
            return encrypted_string;        // returns the last message
        }
        catch(Exception e)
        {
            return null;
        }

    }

	public String decrypt(String message_filename, String key_filename)  // decrypt method using the formula to calculate the decrypted text
    {
        try
        {
            String decrypted_string = "";                       // final encrypted text

            String in_key = readkey(key_filename);    
            String messageFile[] = readfile(message_filename);

            int charcnt = 0;        // counts the number of total characters in the message file
            for(int x = 0; x < messageFile.length; x++)
            {
                charcnt  = charcnt + messageFile[x].length();    // nummber of characters
            }

            String message = "";
            int cnt = 0;

            for(int a = 0; a < messageFile.length; a++)
            {
                message = messageFile[a].toUpperCase();         // gets the first message on the ath line

                String key = key_generator(charcnt, in_key);
                char val;

                for (int i = 0 ; i < message.length() && i < key.length(); i++, cnt++)
                {
                    if(message.charAt(i) < 65 || message.charAt(i) > 90)                // checks if the character is not a letter and hence adds it to the string
                        decrypted_string = decrypted_string + message.charAt(i);
                    else
                    {  
                        int x = ((message.charAt(i) - key.charAt(cnt) + 26) % 26) + 65;     // formula applied to find encrypted character
                        val = (char)(x);
                        decrypted_string = decrypted_string + val;
                    }
                }
                if(a!= messageFile.length-1)
                    decrypted_string = decrypted_string + "\n";
                cnt = cnt +1;
            }
            
            return decrypted_string;                // returns the last end value
        }   
        catch(Exception e){
        }
        return null;
    }
}