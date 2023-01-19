package com.example.backend.dto;

import java.util.ArrayList;

import com.example.backend.model.Product;

public class WinkelwagenDTO {

	private ArrayList<Object[]> winkelwagen;

	public WinkelwagenDTO() {
		winkelwagen = new ArrayList<Object[]>();
	}

	public ArrayList<Object[]> getWinkelwagen() {
		return winkelwagen;
	}

	public void addProduct(CreateProductDTO product, int hoeveelheid) {
		Object[] newProduct = { product, hoeveelheid };
		this.winkelwagen.add(newProduct);
	}
}
