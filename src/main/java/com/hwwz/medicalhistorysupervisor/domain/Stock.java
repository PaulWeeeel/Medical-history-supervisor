package com.hwwz.medicalhistorysupervisor.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * @author: Aliweea
 * @date: 2017/12/3/003 23:57
 */
@Entity
public class Stock {

	private String medicine;

	private Integer stock;

	private String unit;

	private float unitPrice;

	private float cost;

	@Id
	@Size(max = 40, message = "药品名称的长度应该不超过20个中文字符")
	public String getMedicine() {
		return medicine;
	}

	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}

	@Min(value = 0, message = "库存不应该小于0")
	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}


	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}
}
