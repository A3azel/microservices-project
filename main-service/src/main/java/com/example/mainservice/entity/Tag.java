package com.example.mainservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tag")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tag extends BaseEntity{

    @Column(name = "tag_name")
    private String tagName;

    @Column(name = "active")
    private boolean active;

    @ManyToMany
    @JoinTable(name = "tag_has_user",
    joinColumns = {@JoinColumn(name = "tag_id")},
    inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> userTagSet;

    @ManyToMany(mappedBy = "tagSet")
    private Set<DefaultNews> defaultNewsSet;
}
