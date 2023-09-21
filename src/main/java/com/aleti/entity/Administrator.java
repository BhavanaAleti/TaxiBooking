package com.aleti.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor

@PrimaryKeyJoinColumn(name="adminId")
public class Administrator extends User {

}
