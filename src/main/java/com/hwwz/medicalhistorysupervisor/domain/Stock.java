package com.hwwz.medicalhistorysupervisor.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/3/003 23:57
 */
@Entity
public class Stock {

	private Integer id;

	private String medicine;

	private Integer stock;

	private Float unitPrice;

	private Float cost;

	private List<MedicineRecord> medicineRecordList;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

	@OneToMany(mappedBy = "stock")
    public List<MedicineRecord> getMedicineRecordList() {
        return medicineRecordList;
    }

    @JsonIgnore
    public void setMedicineRecordList(List<MedicineRecord> medicineRecordList) {
        this.medicineRecordList = medicineRecordList;
    }
}
