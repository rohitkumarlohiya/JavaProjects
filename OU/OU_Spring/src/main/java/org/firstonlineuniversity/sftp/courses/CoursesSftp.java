package org.firstonlineuniversity.sftp.courses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.UserInfo;

public class CoursesSftp {
	private static byte[] readMyPrivateKeyFromFile(String filePath) {
		byte[] data = null;
		try {
			Resource resource = new ClassPathResource("ou_cdn_prv.ppk");
			data = IOUtils.toByteArray(resource.getInputStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return data;
	}

	public static boolean execute(String courseKey) throws JSchException,
			SftpException {
		JSch jSch = new JSch();
		final byte[] prvkey = readMyPrivateKeyFromFile(""); // Private key must
															// be
															// byte array
		final byte[] emptyPassPhrase = new byte[0]; // Empty passphrase for now,
													// get real passphrase from
													// MyUserInfo

		jSch.addIdentity("media", // String userName
				prvkey, // byte[] privateKey
				null, // byte[] publicKey
				emptyPassPhrase // byte[] passPhrase
		);
		Session session = jSch.getSession("media", "moon.r12expert.com", 22);
		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		UserInfo ui = new MyUserInfo(); // MyUserInfo implements UserInfo
		session.setUserInfo(ui);
		session.connect();
		Channel channel = session.openChannel("sftp");
		ChannelSftp sftp = (ChannelSftp) channel;
		sftp.connect();

		try {
			try {
				sftp.mkdir("learn/" + courseKey);
				System.out.println("Folder 'learn/" + courseKey + "' created");
			} catch (Exception e) {
				System.out.println("Error in creating folder 'learn/"
						+ courseKey + "'");
			}

			try {
				sftp.mkdir("learn/" + courseKey + "/info");
				System.out.println("Folder 'learn/" + courseKey
						+ "/info' created");
			} catch (Exception e) {
				System.out.println("Error in creating folder 'learn/"
						+ courseKey + "/info'");
			}
			try {
				sftp.mkdir("learn/" + courseKey + "/lectures");
				System.out.println("Folder 'learn/" + courseKey
						+ "/lectures' created");
			} catch (Exception e) {
				System.out.println("Error in creating folder 'learn/"
						+ courseKey + "/lectures'");
			}

			try {
				sftp.mkdir("learn/" + courseKey + "/lectures/captions");
				System.out.println("Folder 'learn/" + courseKey
						+ "/lectures/captions' created");
			} catch (Exception e) {
				System.out.println("Error in creating folder 'learn/"
						+ courseKey + "/lectures/captions'");
			}

			try {
				sftp.mkdir("learn/" + courseKey + "/resources");
				System.out.println("Folder 'learn/" + courseKey
						+ "/resources' created");
			} catch (Exception e) {
				System.out.println("Error in creating folder 'learn/"
						+ courseKey + "/resources'");
			}

			try {
				sftp.mkdir("learn/" + courseKey + "/quiz");
				System.out.println("Folder 'learn/" + courseKey
						+ "/quiz' created");
			} catch (Exception e) {
				System.out.println("Error in creating folder 'learn/"
						+ courseKey + "/quiz'");
			}
		} catch (Exception e1) {
			System.out.println("Something went wrong !");
			e1.printStackTrace();
		}

		sftp.disconnect();
		session.disconnect();
		return true;
	}

	public static boolean upload(String path, String fileName, InputStream file)
			throws JSchException, SftpException {
		JSch jSch = new JSch();
		Resource resource = new ClassPathResource("ou_cdn_prv.ppk");
		// data = IOUtils.toByteArray(resource.getInputStream());
		byte[] prvkey = null;
		try {
			prvkey = IOUtils.toByteArray(resource.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Private key must
			// be
			// byte array
		final byte[] emptyPassPhrase = new byte[0]; // Empty passphrase for now,
													// get real passphrase from
													// MyUserInfo

		jSch.addIdentity("media", // String userName
				prvkey, // byte[] privateKey
				null, // byte[] publicKey
				emptyPassPhrase // byte[] passPhrase
		);
		Session session = jSch.getSession("media", "moon.r12expert.com", 22);
		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		UserInfo ui = new MyUserInfo(); // MyUserInfo implements UserIn
		session.setUserInfo(ui);
		session.connect();
		Channel channel = session.openChannel("sftp");
		ChannelSftp sftp = (ChannelSftp) channel;
		sftp.connect();

		sftp.put(file, path + "/" + fileName);

		/*
		 * try { try { sftp.mkdir("learn/" + courseKey);
		 * System.out.println("Folder 'learn/" + courseKey + "' created"); }
		 * catch (Exception e) {
		 * System.out.println("Error in creating folder 'learn/" + courseKey +
		 * "'"); }
		 * 
		 * try { sftp.mkdir("learn/" + courseKey + "/info");
		 * System.out.println("Folder 'learn/" + courseKey + "/info' created");
		 * } catch (Exception e) {
		 * System.out.println("Error in creating folder 'learn/" + courseKey +
		 * "/info'"); } try { sftp.mkdir("learn/" + courseKey + "/lectures");
		 * System.out.println("Folder 'learn/" + courseKey +
		 * "/lectures' created"); } catch (Exception e) {
		 * System.out.println("Error in creating folder 'learn/" + courseKey +
		 * "/lectures'"); } try { sftp.mkdir("learn/" + courseKey +
		 * "/resources"); System.out.println("Folder 'learn/" + courseKey +
		 * "/resources' created"); } catch (Exception e) {
		 * System.out.println("Error in creating folder 'learn/" + courseKey +
		 * "/resources'"); } } catch (Exception e1) {
		 * System.out.println("Something went wrong !"); e1.printStackTrace(); }
		 */

		sftp.disconnect();
		session.disconnect();
		return true;
	}

	public static boolean delete(String path, String fileName)
			throws JSchException, SftpException {
		JSch jSch = new JSch();
		Resource resource = new ClassPathResource("ou_cdn_prv.ppk");
		// data = IOUtils.toByteArray(resource.getInputStream());
		byte[] prvkey = null;
		try {
			prvkey = IOUtils.toByteArray(resource.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Private key must
			// be
			// byte array
		final byte[] emptyPassPhrase = new byte[0]; // Empty passphrase for now,
													// get real passphrase from
													// MyUserInfo

		jSch.addIdentity("media", // String userName
				prvkey, // byte[] privateKey
				null, // byte[] publicKey
				emptyPassPhrase // byte[] passPhrase
		);
		Session session = jSch.getSession("media", "moon.r12expert.com", 22);
		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		UserInfo ui = new MyUserInfo(); // MyUserInfo implements UserIn
		session.setUserInfo(ui);
		session.connect();
		Channel channel = session.openChannel("sftp");
		ChannelSftp sftp = (ChannelSftp) channel;
		sftp.connect();

		sftp.rm(path + "/" + fileName);
		sftp.disconnect();
		session.disconnect();
		return true;
	}
}
