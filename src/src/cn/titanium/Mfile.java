/**   
* @Project: cdh4
* @Title: Mfile.java
* @Package com.madhouse.devops.cdh4
* @Description: TODO
* @author sunyu
* @date Jun 11, 2014 1:31:45 PM
* @version V1.0   
*/

package cn.titanium;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
 * @author sunyu
 *
 */
public class Mfile {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		FileSpliter fs = new FileSpliter("/home/sunyu/syslog");
		String[] paths = fs.split();
		
		
		FileEncryptor fe1 = new FileEncryptor(paths[0],"shanghai");
		String file1 = fe1.encryt(true);
		
		FileEncryptor fe2 = new FileEncryptor(paths[1],"shanghai");
		String file2 = fe2.encryt(true);
		
		
		
		FileDencryptor fd1 = new FileDencryptor(file1,"shanghai");
		String part1 = fd1.dencrypt(true);
		
		FileDencryptor fd2 = new FileDencryptor(file2,"shanghai");
		String part2 = fd2.dencrypt(true);
		
		System.out.println(part1);
		System.out.println(part2);
		
		FileCombiner fc = new FileCombiner(part1, part2);
		System.out.print(fc.combiner());
		
		
		DBManager db = new DBManager("127.0.0.1",3306,"meebox","meebox","meebox");
		db.conncect();
		String filelist[] = db.query("select * from file");
		System.out.print("id:" + filelist[0] + " filename:" + filelist[1]);
		
		db.insert("test");
		
	}

}
