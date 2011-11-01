package com.lifetrack.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志记录公共类
 * 
 * Use String.format to handle message arguments. Make sure you match the format
 * with the proper number of args. String.format(message, args)
 */
public class LogUtils {

	protected static Logger logger = LoggerFactory.getLogger(LogUtils.class);

	/**
	 * warn级别日志，记录一些错误信息
	 * 
	 * @param args
	 *            需要打印的变量列表
	 * 
	 * */
	public static void warn(Logger logger, String message, Exception e, Object... args) {
		StringBuffer info = new StringBuffer();
		
		//处理好User信息之后进行添加
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
	 * 得到系统当前的运行时间，并格式化
	 * 
	 * @return
	 */
	private static String getSystemDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * 获取异常信息
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
