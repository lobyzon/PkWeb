package com.loris.print;

import java.awt.Graphics2D;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.loris.domain.Articulo;
import com.loris.domain.Factura;
import com.loris.domain.Params;

public class AbstractPrint {
	protected final int PAGE_HEIGHT = 815;//795 OLD Value
	protected final int PULGADA = 11;
	final int WIDTH_COMENTARIOS = 62;
	final NumberFormat decimalFormat = DecimalFormat.getInstance();
	final String COURIER_FONT = "Courier";
	
	protected String getSpaceCodigo(String codigo) {				
		String space = "";
		for(int i = 10 - codigo.length(); i > 0 ; i--){
			space += " ";
		}
		return space;
	}

	protected String getAmount(BigDecimal amount, int leftPad){
		decimalFormat.setGroupingUsed(false);
		decimalFormat.setMaximumFractionDigits(2);
		decimalFormat.setMinimumFractionDigits(2);
		
		String amountS = decimalFormat.format(amount); 
		
		return StringUtils.leftPad(amountS, leftPad, " ");
	}
	
	protected String getAmountTotales(BigDecimal amount){
		return StringUtils.leftPad(getAmount(amount, 8), 9, " ");
	}
	
	protected String getInt(String value) {		
		return value.substring(0, value.indexOf('.'));
	}
	
	protected boolean printFamilia(Articulo articulo, int i, List<Articulo> articulos) {		
		if(i - 1 >= 0){
			return !articulos.get(i - 1).getFamilia().getCodigo().equals(articulo.getFamilia().getCodigo());
		}
		return true;
	}
	
	protected boolean printMarcaByFamilia(Articulo articulo, int i, List<Articulo> articulos) {		
		if(i - 1 >= 0){
			return !articulos.get(i - 1).getMarca().getId().equals(articulo.getMarca().getId()) || 
				!articulos.get(i - 1).getFamilia().getCodigo().equals(articulo.getFamilia().getCodigo());
		}
		return true;
	}
	
	protected boolean printMarca(Articulo articulo, int i, List<Articulo> articulos) {		
		if(i - 1 >= 0){
			return !articulos.get(i - 1).getMarca().getId().equals(articulo.getMarca().getId());
		}
		return true;
	}
	
	protected void printComentarios(Graphics2D g2d, Factura factura, int rowPos, int columnPos) {
		String comentarios = factura.getComentarios();				
		
		if(StringUtils.isNotBlank(comentarios)){			
			int stringInit = 0;
			for(int row = 0; row < 6; row++){
				if(stringInit < comentarios.length()){
					String linea = "";
					if(stringInit + WIDTH_COMENTARIOS < comentarios.length())
						linea = comentarios.substring(stringInit, stringInit + WIDTH_COMENTARIOS);
					else
						linea = comentarios.substring(stringInit);
					
					if(linea.indexOf("\n") > 0){
						g2d.drawString(linea.substring(0, linea.indexOf("\n")), columnPos, rowPos);
						stringInit += linea.indexOf("\n") + 1;
					}else{
						g2d.drawString(linea, columnPos, rowPos);
						stringInit += WIDTH_COMENTARIOS;
					}
				}
				rowPos += 11;
			}
		}
	}
	
	protected String getCuit(String cuit){
		if(StringUtils.isNotBlank(cuit))
			return cuit.substring(0,2) + "-" + cuit.substring(2,cuit.length()-1) + "-" + cuit.substring(cuit.length()-1);
		return "";
	}
	
	protected String getNormalizedWidthCode(String code, int width){
		return StringUtils.leftPad(code, width, " ");
	}
	
	protected void setCoordinates(Integer xPos, Integer yPos, Integer xValue, Integer yValue, Integer sumando){
		if(xPos != 300 && (yPos + sumando) >= PAGE_HEIGHT){
			yPos = yValue;
			xPos = xValue;					
		}
	}
	
	protected boolean isFinPage(int xPos, int yPos, int sumando){
		if((yPos + sumando) >= PAGE_HEIGHT && xPos == 300)
			return true;
		return false;
	}
	
	protected boolean getIsLaser(String printerName, Params params){
		String laserParam = params.getLaserPrinterName();
		
		if(StringUtils.isNotBlank(laserParam))
			laserParam = laserParam.toUpperCase();
		else
			laserParam = "";
		
		if(printerName.toUpperCase().contains(laserParam))
			return true;
		return false;
	}
	
	protected int getLaserAdjust(boolean laser, int xPos){
		if(laser)
			return xPos - 10;
		return xPos;
	}
	
	public static void main(String[] args) {
		String comentarios = "Esto es una prueba para la impresión de los comentarios. Estoy viendo como corta los comentarios. Sigue la prueba de longitud de caracteres.";
		if(StringUtils.isNotBlank(comentarios)){
			int stringInit = 0;
			for(int row = 0; row < 6; row++){
				if(stringInit < comentarios.length()){
					String linea = "";
					if(stringInit + 62 < comentarios.length())
						linea = comentarios.substring(stringInit, stringInit + 62);
					else
						linea = comentarios.substring(stringInit);
					
					if(linea.indexOf("\n") > 0){
						System.out.println(linea.substring(0, linea.indexOf("\n")));
						stringInit += linea.indexOf("\n") + 1;
					}else{
						System.out.println(linea);
						stringInit += 62;
					}
				}
				//rowPos += 11;
			}
		}
	}
}