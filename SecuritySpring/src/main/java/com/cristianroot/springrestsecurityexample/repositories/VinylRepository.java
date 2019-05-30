/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.repositories;

import com.cristianroot.springrestsecurityexample.constants.VinylSize;
import com.cristianroot.springrestsecurityexample.entities.Vinyl;
import com.cristianroot.springrestsecurityexample.models.VinylModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Repository
public interface VinylRepository extends JpaRepository<Vinyl, Long> {
 Optional<VinylModel> findByNameIgnoreCase(String name);
 @Query("select v from Vinyl v join v.purchaseList p group by v.id order by  sum(p.quantity) desc")
 List<VinylModel> findTop5();
 //@Query("select size,count(v.id) from Vinyl v group by size")
 //Map<VinylSize, Long> viniloLongMap();

}
