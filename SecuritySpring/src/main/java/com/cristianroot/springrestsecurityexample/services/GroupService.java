/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.services;

import com.cristianroot.springrestsecurityexample.exceptions.DuplicatedEntityException;
import com.cristianroot.springrestsecurityexample.exceptions.EntityNotFoundException;
import com.cristianroot.springrestsecurityexample.exceptions.IdRequiredException;
import com.cristianroot.springrestsecurityexample.exceptions.IllegalOperationException;
import com.cristianroot.springrestsecurityexample.models.BandModelSnapshot;
import com.cristianroot.springrestsecurityexample.models.MusicGroupModel;
import com.cristianroot.springrestsecurityexample.models.VinylModelSnapshot;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface GroupService {
	BandModelSnapshot snapshot(BandModelSnapshot bandModelSnapshot) ;
	List<MusicGroupModel> findAll();

	MusicGroupModel findOne(long id) throws EntityNotFoundException;

	MusicGroupModel save(MusicGroupModel musicGroupModel) throws DuplicatedEntityException;

	MusicGroupModel update(long id, MusicGroupModel musicGroupModel) throws EntityNotFoundException, DuplicatedEntityException, IdRequiredException, IllegalOperationException;

	void delete(long id) throws EntityNotFoundException;

}
