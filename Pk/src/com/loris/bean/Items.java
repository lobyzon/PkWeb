package com.loris.bean;

import java.util.HashMap;

import com.loris.dao.ClienteDAO;
import com.loris.dao.IVADAO;
import com.loris.domain.IVA;
import com.loris.domain.ItemFactura;

public class Items {	
	
	private HashMap<String, ItemFactura> itemsFactura;
	private IVA currentIVA;
	
	public Items(){
		this.itemsFactura = new HashMap<String, ItemFactura>();
	}
	
	public HashMap<String, ItemFactura> getItemsFactura() {
		return itemsFactura;
	}

	public void setItemsFactura(HashMap<String, ItemFactura> itemsFactura) {
		this.itemsFactura = itemsFactura;
	}

	public IVA getCurrentIVA(Integer idCliente, ClienteDAO clienteDAO, IVADAO ivaDAO) {
		if(this.currentIVA == null){
			if("S".equals(clienteDAO.getCliente(idCliente).getResponsableInsc())){
				this.currentIVA = ivaDAO.getIVAInscripto();
			}
			if("N".equals(clienteDAO.getCliente(idCliente).getResponsableInsc())){
				this.currentIVA = ivaDAO.getIVANoInscripto();
			}
		}
		return currentIVA;
	}

	public void setCurrentIVA(IVA currentIVA) {
		this.currentIVA = currentIVA;
	}	
}