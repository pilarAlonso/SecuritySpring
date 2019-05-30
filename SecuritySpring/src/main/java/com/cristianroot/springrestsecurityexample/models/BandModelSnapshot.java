package com.cristianroot.springrestsecurityexample.models;

import com.cristianroot.springrestsecurityexample.entities.MusicGroup;

import java.util.List;

public class BandModelSnapshot {
	private Long numberGroup;
	private List<MusicGroupModel> fiveMostSoldGroup;

	public Long getNumberGroup() {
		return numberGroup;
	}

	public void setNumberGroup(Long numberGroup) {
		this.numberGroup = numberGroup;
	}

	public List<MusicGroupModel> getFiveMostSoldGroup() {
		return fiveMostSoldGroup;
	}

	public void setFiveMostSoldGroup(List<MusicGroupModel> fiveMostSoldGroup) {
		this.fiveMostSoldGroup = fiveMostSoldGroup;
	}
}
