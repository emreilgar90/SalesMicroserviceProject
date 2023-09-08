package com.emre.repository.entity;

import com.emre.utility.BaseEntity;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Document
public class Sale extends BaseEntity {
    @Id
    Long id;

}
