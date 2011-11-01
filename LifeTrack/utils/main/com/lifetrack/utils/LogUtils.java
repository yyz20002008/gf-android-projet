package com.lifetrack.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ��־��¼������
 * 
 * Use String.format to handle message arguments. Make sure you match the format
 * with the proper number of args. String.format(message, args)
 */
public class LogUtils {

	protected static Logger logger = LoggerFactory.getLogger(LogUtils.class);

	/**
	 * warn������־����¼һЩ������Ϣ
	 * 
	 * @param args
	 *            ��Ҫ��ӡ�ı����б�
	 * 
	 * */
	public static void warn(Logger logger, String message, Exception e, Object... args) {
		StringBuffer info = new StringBuffer();
		
		//�����User��Ϣ֮��������
		//info.append(getUserInfo(UserMgrImpl.getCurrentUserStatic()));
		
		if (message != null) {
			if (args != null && args.length > 0 && args.length == StringUtils.countMatches(message, "%")) {
				info.append(String.format(message, args));
			} else {
				info.append(message);
			}
		}
		if (null != e) {
			info.append("caused by ");
			info.append(getExceptionInfo(e, getSystemDate(), "\n", "\t"));
		}
		
		logger.warn(info.toString());
	}
	
	public static void warn(Logger logger, String message, Object... args) {
		warn(logger, message, null, args);
	}
	
	/**
	 * �õ�ϵͳ��ǰ������ʱ�䣬����ʽ��
	 * 
	 * @return
	 */
	private static String getSystemDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * ��ȡ�쳣��Ϣ
	 */
	public static String getExceptionInfo(Exception e, String systemDate, String newLineToken, String tabToken) {
		StringBuffer info = new StringBuffer();
		info.append(systemDate);
		info.append(tabToken);
		info.append("cause by: ");
		info.append(e.getMessage());
		info.append(newLineToken);
		for (StackTraceElement stackTraceElement : e.getStackTrace()) {
			info.append(systemDate);
			info.append(tabToken);
			info.append(stackTraceElement);
			info.append(newLineToken);
		}
		if (null != e.getCause() && e.getCause() instanceof Exception) {
			info.append(getExceptionInfo((Exception) e.getCause(), systemDate, newLineToken, tabToken));
		}
		return info.toString();
	}
	
}
