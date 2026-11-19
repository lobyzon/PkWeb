package com.loris.feafip.test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.apache.axis.encoding.Base64;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.loris.feafip.qr.JSONAfipBean;
import com.loris.utils.DateUtils;

public class QRCodeTest {
	final static String url = "https://www.afip.gob.ar/fe/qr/?p=";

	public static void main(String[] args) throws WriterException, IOException {
		JSONAfipBean afipBean = new JSONAfipBean();
		afipBean.setVer(1);
		afipBean.setFecha(DateUtils.convertDateToStringQrCode(new Date()));
		System.out.println("Fecha: " + DateUtils.convertDateToStringQrCode(new Date()));
		afipBean.setCuit(new BigInteger("30000000007"));
		afipBean.setPtoVta(10);
		afipBean.setTipoCmp(1);
		afipBean.setNroCmp(94);
		afipBean.setImporte(new BigDecimal("12100"));
		afipBean.setMoneda("DOL");
		afipBean.setCtz(new BigDecimal("65"));
		afipBean.setTipoDocRec(80);
		afipBean.setNroDocRec(new BigInteger("20000000001"));
		afipBean.setTipoCodAut("E");
		afipBean.setCodAut(new BigInteger("70417054367476"));

		final Gson gson = new Gson();
		String json = gson.toJson(afipBean);

		String base64 = Base64.encode(json.getBytes());
		System.out.println("JSON: " + json);
		System.out.println("JSON base 64: " + url + base64);

		// Zxing QrCode Image
		String qrCodeText = url + base64;
		String filePath = "C:\\PkWEB\\FacturaElectronica\\qrCode.jpg";
		int size = 140;
		String fileType = "jpg";
		File qrFile = new File(filePath);
		createQRImage(qrFile, qrCodeText, size, fileType);
	}

	private static void createQRImage(File qrFile, String qrCodeText, int size, String fileType)
			throws WriterException, IOException {
		// Create the ByteMatrix for the QR-Code that encodes the given String
		Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
		// Make the BufferedImage that are to hold the QRCode
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
		image.createGraphics();

		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		// Paint and save the image using the ByteMatrix
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}
		ImageIO.write(image, fileType, qrFile);
	}
}