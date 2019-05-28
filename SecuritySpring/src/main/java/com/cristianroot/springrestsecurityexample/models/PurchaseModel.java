package com.cristianroot.springrestsecurityexample.models;

import com.cristianroot.springrestsecurityexample.entities.Purchase;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.Optional;

public class PurchaseModel {

	private Long id;

	@Column(unique = true)
	private String orderNumber;

	private ClientModel client;

	@Min(1)
	private int quantity;

	private VinylModel vinyl;

	private LocalDate purchaseDate;

	public static PurchaseModel from(Purchase purchase) {
		PurchaseModel purchaseModel = new PurchaseModel();
		purchaseModel.setId(purchase.getId());
		purchaseModel.setClient(ClientModel.from(purchase.getClient()));
		purchaseModel.setOrderNumber(purchase.getOrderNumber());
		purchaseModel.setVinyl(VinylModel.from(purchase.getVinyl()));
		purchaseModel.setQuantity(purchase.getQuantity());
		return purchaseModel;
	}

	public Optional<Long> getId() {
		return Optional.ofNullable(id);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public ClientModel getClient() {
		return client;
	}

	public void setClient(ClientModel client) {
		this.client = client;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public VinylModel getVinyl() {
		return vinyl;
	}

	public void setVinyl(VinylModel vinyl) {
		this.vinyl = vinyl;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
}
