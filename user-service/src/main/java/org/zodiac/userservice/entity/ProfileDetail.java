package org.zodiac.userservice.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.zodiac.database.BaseEntity;


@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class ProfileDetail extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
}