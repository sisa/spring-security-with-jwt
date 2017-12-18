package io.sisa.core.model.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author isaozturk
 */

@Data
@Entity
@Table(name = "city")
public class City implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

}
