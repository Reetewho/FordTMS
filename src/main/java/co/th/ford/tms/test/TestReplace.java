package co.th.ford.tms.test;

public class TestReplace {
	public static void main(String[] args) {
		String s1="\n\t\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\t1593453\n\t\t\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t";  
		String replaceString1=s1.replace("\n","");//replaces all occurrences of 'a' to 'e'  
		String replaceString2=replaceString1.replace("\t","");//replaces all occurrences of 'a' to 'e'  
		System.out.println(replaceString2);  
	}
}
