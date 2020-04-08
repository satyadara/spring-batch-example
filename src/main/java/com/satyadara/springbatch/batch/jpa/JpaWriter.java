package com.satyadara.springbatch.batch.jpa;

import com.satyadara.springbatch.model.ItemSecondModel;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;

@Component
public class JpaWriter extends JpaItemWriter<ItemSecondModel> {

    public JpaWriter(EntityManagerFactory entityManagerFactory) {
        this.setEntityManagerFactory(entityManagerFactory);
    }
}
