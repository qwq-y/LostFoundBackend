package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Table(name = "user_")
@Entity
public class User {
  @Id
  @GeneratedValue
  private Long id;
  private String password;
  private String varchar;
}
