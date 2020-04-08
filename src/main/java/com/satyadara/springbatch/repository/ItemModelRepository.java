package com.satyadara.springbatch.repository;

import com.satyadara.springbatch.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemModelRepository extends JpaRepository<ItemModel, Long> {
}
