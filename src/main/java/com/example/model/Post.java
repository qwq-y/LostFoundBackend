package com.example.model;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Table(name = "post")
@Entity
public class Post {
  @Id
  @GeneratedValue
  private Long id;
  private String itemName;
  private String itemType;
  private String itemDescription;
  private String picture;
  private Long publishTime;
  private Long claimTime;
  private String roughPlace;
  private String detailedPlace;
  private Long publisherId;
  private Long claimantId;
  private Boolean isClaimed;
  private Boolean isHidden;
}
