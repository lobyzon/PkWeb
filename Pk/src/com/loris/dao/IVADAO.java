package com.loris.dao;

import com.loris.domain.IVA;

public interface IVADAO {
	public IVA getIVAInscripto();
	public IVA getIVANoInscripto();
	public void saveUpdateIVA(IVA iva);	
}
