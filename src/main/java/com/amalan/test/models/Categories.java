package com.amalan.test.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Categories {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer id;
	public String name;
	public String shortDescription;
	public String description;
	@Enumerated(EnumType.ORDINAL)
	public Status status;
    @CreationTimestamp
    private LocalDateTime createDateTime; 
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
}