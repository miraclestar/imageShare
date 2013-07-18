package com.imageshare;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;

/**
 * 使用URLConnection下载文件或图片并保存到本地。
 * 
 */
public class URLConnectionDownloader {
	public static void main(String[] args) throws Exception {

	}

	/**
	 * 下载文件到本地
	 * 
	 * @param urlString
	 *            被下载的文件地址
	 * @param filename
	 *            本地文件名
	 * @throws Exception
	 *             各种异常
	 */
	public static String download(String urlString, String filename) throws Exception {

		String md5 = "";
		// 构造URL
		URL url = new URL(urlString);
		// 打开连接
		URLConnection con = url.openConnection();
		// 输入流
		InputStream is = con.getInputStream();
		// 1K的数据缓冲
		byte[] bs = new byte[2048];
		// 读取到的数据长度
		int len;
		// 输出的文件流
		OutputStream os = new FileOutputStream(filename);
		MessageDigest messagedigest = MessageDigest.getInstance("MD5");
		// 开始读取
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
			messagedigest.update(bs, 0, len);
		}
		md5 = MD5Util.bufferToHex(messagedigest.digest());
		// 完毕，关闭所有链接
		os.close();
		is.close();
		return md5;
	}
}