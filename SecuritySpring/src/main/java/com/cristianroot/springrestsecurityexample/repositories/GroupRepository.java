/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.repositories;

import com.cristianroot.springrestsecurityexample.entities.MusicGroup;
import com.cristianroot.springrestsecurityexample.models.MusicGroupModel;
import com.cristianroot.springrestsecurityexample.models.VinylModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<MusicGroup, Long> {

	Optional<MusicGroup> findByNameIgnoreCase(String name);
	@Query("select m  from MusicGroup m join m.publishedVinylList v  join v.purcchaseList p  group by m.id order by  sum(p.quantity) desc\")")
	List<MusicGroupModel> findTop5();

}
