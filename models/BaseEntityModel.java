package sgsits.cse.dis.administration.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * A base entity with a few meta informations for each dataset like last modifier, creator, last
 * modifed date, creation date.
 *
 * @author Ajinkya Taranekar (ajinkyataranekar@gmail.com)
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntityModel implements Serializable {

  /**
   * The Id.
   */
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  private String id;

  /**
   * The Created date.
   */
  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;

  /**
   * The Created by.
   */
  @CreatedBy
  private String createdBy;

  /**
   * The Last modified date.
   */
  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastModifiedDate;

  /**
   * The Last modified by.
   */
  @LastModifiedBy
  private String lastModifiedBy;
}