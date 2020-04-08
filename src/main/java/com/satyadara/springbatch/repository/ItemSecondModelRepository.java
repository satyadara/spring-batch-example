package com.satyadara.springbatch.repository;

import com.satyadara.springbatch.model.ItemSecondModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemSecondModelRepository extends JpaRepository<ItemSecondModel, Long> {
}
