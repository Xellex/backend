package com.example.backend.dto;

import java.util.ArrayList;

public class WinkelwagenDTO {

	private ArrayList<Object[]> winkelwagen;

	public WinkelwagenDTO() {
		winkelwagen = new ArrayList<Object[]>();
	}

	public ArrayList<Object[]> getWinkelwagen() {
		return winkelwagen;
	}

	public void addProduct(ProductDTO product, int hoeveelheid) {
		Object[] newProduct = { product, hoeveelheid };
		this.winkelwagen.add(newProduct);
	}
}
