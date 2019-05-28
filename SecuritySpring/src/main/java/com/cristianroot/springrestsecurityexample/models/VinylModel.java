/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.models;

import com.cristianroot.springrestsecurityexample.constants.VinylSize;
import com.cristianroot.springrestsecurityexample.entities.Vinyl;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

public class VinylModel {

	private Long id;
    @Valid
	private MusicGroupModel group;

	@NotNull
	@Min(1)
	private Double price;

	@NotNull
	@Size(min = 2)
	private String name;
	@NotNull
	private VinylSize vinylSize;

	public VinylSize getVinylSize() {
		return vinylSize;
	}

	public void setVinylSize(VinylSize vinylSize) {
		this.vinylSize = vinylSize;
	}

	public static VinylModel from(Vinyl vinyl) {
		VinylModel vinylModel = new VinylModel();
		vinylModel.setId(vinyl.getId());
		vinylModel.setName(vinyl.getName());
		vinylModel.setPrice(vinyl.getPrice());
		vinylModel.setGroup(MusicGroupModel.from(vinyl.getMusicGroup()));
		vinylModel.setVinylSize(vinyl.getSize());
		return vinylModel;
	}

	public Optional<Long> getId() {
		return Optional.ofNullable(id);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MusicGroupModel getGroup() {
		return group;
	}

	public void setGroup(MusicGroupModel group) {
		this.group = group;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
