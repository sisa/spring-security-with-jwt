package io.sisa.core.model.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author isaozturk
 */

@Data
@Entity
@Table(name="user_role")
public class UserRole implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id")
    private Long userId;

    @Column(name="role_id")
    private Long roleId;

}
