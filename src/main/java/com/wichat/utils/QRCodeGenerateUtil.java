package com.wichat.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public final class QRCodeGenerateUtil {

	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;

	private QRCodeGenerateUtil() {
	}

	public static void main(String[] args) {
		try {
			generateCodeToFile("www.baidu.com", "pic", 300, "d:");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @description 生成二维码到输出流
	 * @param content
	 * @param name
	 * @param size
	 * @param response
	 * @throws IOException
	 * @throws WriterException
	 * @author user
	 * @date 2016-09-30 16:04
	 */
	public static void generateCodeToStream(String content, String name, int size, HttpServletResponse response)
			throws IOException, WriterException {
		writeToStream(generater(content, size), "png", response.getOutputStream());
	}

	/**
	 * @description 生成二维码文件
	 * @param content
	 *            内容
	 * @param name
	 *            名称
	 * @param size
	 *            大小
	 * @param path
	 *            路劲
	 * @throws Exception
	 * @author user
	 * @date 2016-09-30 16:07
	 */
	public static void generateCodeToFile(String content, String name, int size, String path) throws Exception {
		writeToFile(generater(content, size), "jpg", new File(path + name + ".jpg"));
	}

	/**
	 * @description 生成二维码
	 * @param content
	 *            内容
	 * @param size
	 *            大小
	 * @return
	 * @throws WriterException
	 */
	private static BitMatrix generater(String content, int size) throws WriterException {
		QRCodeWriter writer = new QRCodeWriter();
		BitMatrix bitMatrix = null;
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, size, size, hints);
		// 去白边
		int[] rec = bitMatrix.getEnclosingRectangle();
		int resWidth = rec[2] + 1;
		int resHeight = rec[3] + 1;
		BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
		resMatrix.clear();
		for (int i = 0; i < resWidth; i++) {
			for (int j = 0; j < resHeight; j++) {
				if (bitMatrix.get(i + rec[0], j + rec[1])) {
					resMatrix.set(i, j);
				}
			}
		}
		return resMatrix;
	}

	/**
	 * 图片生成
	 * 
	 * @param matrix
	 * @return
	 */
	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

	/**
	 * 路劲写入
	 * 
	 * @param matrix
	 * @param format
	 * @param file
	 * @throws IOException
	 */
	public static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, file)) {
			throw new IOException("Could not write an image of format " + format + " to " + file);
		}
	}

	/**
	 * 流写入
	 * 
	 * @param matrix
	 * @param format
	 * @param stream
	 * @throws IOException
	 */
	private static void writeToStream(BitMatrix matrix, String format, OutputStream stream) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, stream)) {
			throw new IOException("Could not write an image of format " + format);
		}
	}
}
