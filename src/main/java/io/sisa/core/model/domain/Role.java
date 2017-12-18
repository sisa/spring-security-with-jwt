package io.sisa.core.model.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author isaozturk
 */

@Entity
@Table(name="app_role")
public class Role implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Column(name="role_name")
    @Getter @Setter
    private String roleName;

    @Column(name="description")
    @Getter @Setter
    private String description;

}
