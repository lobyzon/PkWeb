package com.loris.feafip.test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.lowagie.text.pdf.BarcodeInter25;


public class CodigoBarrasTest {
	
	public static void main(String[] args) {
		/*
		 * Hola, mas que un procedure es un conjunto de cosas, pero intento explicarte.
			Interleave 2 de 5 no te va a imprimir nada directamente, o sea si tenes el
			string "2013782217301000362013427965354201201120", y lo imprimis con el font
			interleave 2 de 5, te va a imprimir cualquier cosa.
			Tenes que convertir a "2013782217301000362013427965354201201120 ", en una
			serie de caracteres que luego se imprimen con ese font.
			(Esto independientemente del size con que luego lo imprimas)
			En este ejemplo "2013782217301000362013427965354201201120", queda como
			"(D=ÜFAN:0TD=ZÝÏSZ1D;D)", siendo esto ultimo lo que tenes que mandar a
			imprimir.
			
			El procedure de compilacion no te lo adjunto porque es un xpw, pero para
			visual fox, te lo copio aca abajo :
			
			// ------------------------------------------------------
			// * FUNCTION StrToI2of5(tcString) * INTERLEAVED 2 OF 5
			// *------------------------------------------------------
			// * Convierte un string para ser impreso con
			// * fuente True Type "PF Interleaved 2 of 5"
			// * ó "PF Interleaved 2 of 5 Wide"
			// * ó "PF Interleaved 2 of 5 Text"
			// * Solo caracteres numéricos
			// * USO: StrToI2of5('1234567890')
			// * RETORNA: Caracter
			// *------------------------------------------------------
			dbase LOCAL lcStart, lcStop, lcRet, lcCheck, lcCar, lnLong, lnI, lnSum,
			lnAux
			dbase lcStart = CHR(40)
			dbase lcStop = CHR(41)
			dbase lcRet = ALLTRIM([!tcString!])
			// *--- Genero dígito de control
			dbase lnLong = LEN(lcRet)
			dbase lnSum = 0
			dbase lnCount = 1
			dbase FOR lnI = lnLong TO 1 STEP -1
			dbase lnSum = lnSum + VAL(SUBSTR(lcRet,lnI,1)) * IIF(MOD(lnCount,2) =
			0,1,3)
			dbase lnCount = lnCount + 1
			dbase ENDFOR
			dbase lnAux = MOD(lnSum,10)
			dbase lcRet = lcRet + ALLTRIM(STR(IIF(lnAux = 0,0,10 - lnAux)))
			dbase [!digitos!] = lcret
			dbase lnLong = LEN(lcRet)
			// *--- La longitud debe ser par
			dbase IF MOD(lnLong,2) # 0
			dbase lcRet = '0' + lcRet
			dbase lnLong = LEN(lcRet)
			dbase ENDIF
			// *--- Convierto los pares a caracteres
			dbase lcCar = ''
			dbase FOR lnI = 1 TO lnLong STEP 2
			dbase IF VAL(SUBS(lcRet,lnI,2)) < 50
			dbase lcCar = lcCar + CHR(VAL(SUBS(lcRet,lnI,2)) + 48)
			dbase ELSE
			dbase lcCar = lcCar + CHR(VAL(SUBS(lcRet,lnI,2)) + 142)
			dbase ENDIF
			dbase ENDFOR
			// *--- Armo código
			dbase lcRet = lcStart + lcCar + lcStop
			dbase [!barras!] = lcret
			Los parametros de la rule (parm), son: parm(&tcstring,&barras,&factexbar);
			
			&tcstring = Esto es lo que queres que compile, en el ejemplo seria
			"2013782217301000362455552514253"
			&barras = Esto es el string anterior pero "compilado"
			&factexbar = Esto es similar a &tcstring, pero con el agregado del digito
			verificador, los codigos de barrar interleave 2 of 5 usan un ultimo digito
			verificador.
			
			O sea, antes de imprimir, deberias pasar tu string por este procedimiento, y
			entonces en tu form, lo que imprimirias seria, segun este ejemplo: &barras
			
			Una vez que lo ubicas en el form, boton derecho, properties, y destildas el
			autoresize, en font indicas PF interleaved 2 of 5, font style regular, size
			36
			Y luego, como destildaste el autoresize, adapta el ancho y alto de &barras,
			en mi caso, las dimensiones son de 491 x 53.
		 */
		String codigo1 = "3060715959501000565075908353981201502283";
		String codigo2 = "1111111111122333344444444444444555555553";
		String filePathCasa = "E:\\Desarrollo\\ProduccionPK_WEB\\FacturaElectronica\\codigoBarras.gif";
		String filePathTrabajo = "C:\\Sergio\\Desarrollo\\Pk\\Doc FacturaElectronica\\FacturaElectronica\\codigoBarras.gif";
		
		BarcodeInter25 barcodeInter25 = new BarcodeInter25();
		barcodeInter25.setGenerateChecksum(false);
		barcodeInter25.setCode(codigo1);
		barcodeInter25.setBarHeight(53);
		Image barCode = barcodeInter25.createAwtImage(Color.black, Color.white);
		
		BufferedImage bi = new BufferedImage(barCode.getWidth(null), barCode.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bi.createGraphics();
		g2d.drawImage(barCode, 0, 0, null);
		try{
			ImageIO.write(bi, "gif", new File(filePathCasa));
		}
		catch(Exception e){
			System.out.println("cant access file");
		}
	}
}