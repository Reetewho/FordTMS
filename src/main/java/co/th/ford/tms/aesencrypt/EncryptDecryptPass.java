package co.th.ford.tms.aesencrypt;

public class EncryptDecryptPass {
	
	 public static void main(String[] args) throws Exception{
		   String strEndcrypt = "Msd5bA8kIpa5MRZzn0blsA==", strResultPassword = "";
		   
		   strResultPassword = AESCrypt.decrypt(strEndcrypt);
		   System.out.println("Result Password : " + strResultPassword);
		   
		     }
		}