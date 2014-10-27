package org.kay.framework.util;

import org.apache.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPUtils {

	private static final Logger LOGGER = Logger.getLogger(FTPUtils.class);
	
	public static FTPClient loginFTPServerWithDefaultConfig() {
		
		InputStream inStream = ClassLoader.getSystemResourceAsStream("config.properties");
		
		Properties props = new Properties();
		
		try {
			props.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String ftpServerName = props.getProperty("ftpServerName");
		int ftpPort = Integer.parseInt(props.getProperty("ftpPort"));
		String ftpUser = props.getProperty("ftpUser");
		String ftpPassWord = props.getProperty("ftpPassWord");
		
		return loginFTPServer(ftpServerName, ftpPort, ftpUser, ftpPassWord);
		
	}
	
	/**
	 * Login Ftp Servers
	 */
	public static FTPClient loginFTPServer(String server, int port, String username, String password)  {
		
		FTPClient ftpClient = new FTPClient();
		
		establishConnection(ftpClient, server, port); 
		
		validateUserLogin(ftpClient, username, password);
		
		setFtpServerConfig(ftpClient);
		
		return ftpClient;
	}

	private static void setFtpServerConfig(FTPClient ftpClient) {

		try {
			// Set Passive Mode
			ftpClient.enterLocalPassiveMode(); 
			
			// Set Control Port Encoding
			ftpClient.setControlEncoding("ISO-8859-1");
			
			// Output Control Port Encoding
			LOGGER.info("FtpServer Control Port Encoding: " + ftpClient.getControlEncoding());
			
			// Output System Type
			LOGGER.info("Remote system: " + ftpClient.getSystemType());
			
		} catch (IOException e) {
			LOGGER.info("Error setting Ftp Server config.", e);
		}
		
	}

	private static void validateUserLogin(FTPClient ftpClient, String username, String password) {
		try {
			// User login
			boolean loginFlag = ftpClient.login(username, password);
			LOGGER.info(getLoginStatusInfo(username, loginFlag));
		} catch (IOException e) {
			LOGGER.info(getLoginStatusInfo(username, false), e);
		} 
	}

	private static String getLoginStatusInfo(String username, boolean loginFlag) {
		StringBuffer content = new StringBuffer();
		content.append("Login User: ");
		content.append(username);
		content.append(" LoginStatus: ");
		content.append(loginFlag ? "Success" : "Failure");
		return content.toString();
	}

	private static void establishConnection(FTPClient ftpClient, String server, int port) {
		try {
			// Attempt to establish connection with given server ip and port.
			if (port > 0) {			
				ftpClient.connect(server, port);
			} else {
				ftpClient.connect(server);
			}
			
			// Get reply code 
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				LOGGER.info("FTP server refused connection.");
			}
			
			LOGGER.info("Connected to " + server + ":" + (port > 0 ? port : ftpClient.getDefaultPort()));
			
		} catch (IOException e) {
			LOGGER.info("Cannot connect to server.", e);
		} 
	}

	/**
	 * Upload file to FTP Server
	 * Usage:
	 * 		FTPClient ftpClient = FTPUtil.loginFTPServer("127.0.0.1", 21, "kay", "123");
	 * 		FileInputStream is = new FileInputStream("D:\\工伤资料\\工伤指引.xls");
	 *		boolean flag = FTPUtil.uploadFTPServer(ftpClient, "\\报销文档\\工伤资料\\", "工伤指引.xls", is);
	 * @param ftpClient		
	 * @param parentPath	FTP服务器存放文件的父路径
	 * @param filename		文件名
	 * @param is			文件的读入流
	 * @return true--成功 false--失败
	 */
	public static boolean uploadFTPServer(FTPClient ftpClient, String  parentPath, String filename, InputStream is) {
		try {
			// Creat directories
			String[] dirs = parentPath.split("\\\\", 0);
			for (String dir : dirs) {
				ftpClient.makeDirectory(convertWithControlEncoding(ftpClient, dir));
				ftpClient.changeWorkingDirectory(convertWithControlEncoding(ftpClient, dir));
			}

			// Change working directory
			Boolean flagCWD = ftpClient.changeWorkingDirectory("\\" + convertWithControlEncoding(ftpClient, parentPath));
			
			if (flagCWD) {		
				
				// Config transfer info
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
				ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE); 
				
				// Store file
				Boolean flagSF = ftpClient.storeFile(convertWithControlEncoding(ftpClient, filename), is);
				if (!flagSF) {
					LOGGER.info("Cannot store file '" + filename + "'.");
				} 
			} else {
				LOGGER.info("Change working directory to '" + parentPath + "' failure.");
			}
			
			// Close Connection
			is.close();
			ftpClient.disconnect();
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} 
		
		// Upload successfully
		return true;
	}

	/**
	 * 设置控制端口交互的编码格式   	
	 * 警告:如果客户端发送的指令内容的编码格式和服务器控制端口设置的编码格式不符,
	 * 则会导致交互失败。 使用getControlEncoding()方法获取编码格式。
	 * @param ftpClient
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static String convertWithControlEncoding(FTPClient ftpClient, String str) {
		String ret = "";
		try {
			String platformEncoding = System.getProperty("sun.jnu.encoding");
			String controlEncoding = ftpClient.getControlEncoding();
			ret = new String(str.getBytes(platformEncoding), controlEncoding);
		} catch (UnsupportedEncodingException e) {
			LOGGER.info("Covert with control encoding error.", e);
		}
		return ret;
	}
	
	/**
	 * Delete file from FTP Server
	 * @param ftpClient 
	 * @param pathname	FTP服务器存放文件的路径 例如：MyDocuments\Program Files\test.xls
	 * @return true--成功 false--失败
	 */
	public static boolean deleteFTPServer(FTPClient ftpClient, String pathname) {
		boolean flag = false;
		try {
			flag = ftpClient.deleteFile(convertWithControlEncoding(ftpClient, pathname));
			ftpClient.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
}
