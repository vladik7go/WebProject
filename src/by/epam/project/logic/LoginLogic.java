package by.epam.project.logic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import by.epam.project.dao.DaoPerson;

public class LoginLogic {
	public static Logger log = Logger.getLogger(LoginLogic.class);

	public int checkLogin(String enterLogin, String enterPass) {
		DaoPerson dao = new DaoPerson();
		String passwordHash = getHash(enterPass);
		return dao.checkLogin(enterLogin, passwordHash);

	}

	/*
	 * This method takes password(string), transform this string to array of
	 * bytes. Next, feeding this array to the md5 algorithm. Md5 returns array
	 * of bytes(encrypted due to the internal logic) i.e. md5 returns a Hash of
	 * our password. So, nobody knows password, except user. We store only hash
	 * of this password.
	 */
	public String getHash(String str) {

		MessageDigest md5; // variable with reference to MessageDigest class.
		StringBuffer hexString = new StringBuffer();

		try {
			// Gaining an object type MessageDigest
			md5 = MessageDigest.getInstance("md5");

			md5.reset();
			// handling password(String) by MessageDigest object(md5).
			// String.getBytes() - returns string with array of bytes.
			md5.update(str.getBytes());

			// perform md5 algorithm and return an array of bytes.
			byte messageDigest[] = md5.digest();

			// building a string with with Hex hasheCode
			for (int i = 0; i < messageDigest.length; i++) {
				hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
			}

		} catch (NoSuchAlgorithmException e) {
			log.error(e);

		}

		return hexString.toString();
	}

}
