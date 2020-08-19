package co.th.ford.tms.aesencrypt;

public class EncryptDecryptPass {
	
	 public static void main(String[] args) throws Exception{
		   String strEndcrypt = "9blAAgcowVhGyspL2SjZGg==", strResultPassword = "";
		   
		   strResultPassword = AESCrypt.decrypt(strEndcrypt);
		   System.out.println("Result Password : " + strResultPassword);
		   
		     }
		}