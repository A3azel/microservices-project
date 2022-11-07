package com.example.mainservice.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

public class DefaultNews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date created;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated")
    private Date updated;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @NotNull
    @Size(max = 64)
    @Column(name = "title")
    private String title;

    @NotNull
    @Size(max = 500)
    @Column(name = "news_info")
    private String newsInfo;

    @Size(max = 256)
    @Column(name = "link")
    private String link;

    @Column(name = "image")
    private byte[] imageBytesArray;

    @Column(name = "active")
    private boolean active;

    @OneToMany
    @Column(name = "source_id")
    private Source source;

    @ManyToMany
    @JoinTable(name = "default_news_has_tags",
    joinColumns = {@JoinColumn(name = "id")},
    inverseJoinColumns = {@JoinColumn(name = "id")})
    private Set<Tag> tagSet;


}
