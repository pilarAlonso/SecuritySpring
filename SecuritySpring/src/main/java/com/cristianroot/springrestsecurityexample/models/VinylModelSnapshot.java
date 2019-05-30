package com.cristianroot.springrestsecurityexample.models;

import com.cristianroot.springrestsecurityexample.constants.VinylSize;
import com.cristianroot.springrestsecurityexample.entities.Vinyl;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VinylModelSnapshot {
	private List<VinylModel> fiveMostSold = new ArrayList<>();
	private long vinilosNumber;
	private Map<VinylSize, Long> viniloLongMap = new HashMap<>();

	public List<VinylModel> getFiveMostSold() {
		return fiveMostSold;
	}

	public void setFiveMostSold(List<VinylModel> fiveMostSold) {
		this.fiveMostSold = fiveMostSold;
	}

	public long getVinilosNumber() {
		return vinilosNumber;
	}

	public void setVinilosNumber(long vinilosNumber) {
		this.vinilosNumber = vinilosNumber;
	}

	public Map<VinylSize, Long> getViniloLongMap() {
		return viniloLongMap;
	}

	public void setViniloLongMap(Map<VinylSize, Long> viniloLongMap) {
		this.viniloLongMap = viniloLongMap;
	}
}
