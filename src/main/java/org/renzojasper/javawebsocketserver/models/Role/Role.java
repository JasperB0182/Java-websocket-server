package org.renzojasper.javawebsocketserver.models.Role;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.renzojasper.javawebsocketserver.models.UserData;

import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, unique = true, nullable = false)
    private ERole roleName;

    @OneToMany(mappedBy = "role")
    @JsonBackReference
    private List<UserData> userDataList;

    public Role(ERole name) {
        this.roleName = name;
    }

    public Role() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ERole getName() {
        return roleName;
    }

    public void setName(ERole name) {
        this.roleName = name;
    }
}
