package com.ArghyaBandyopadhyay.waiterrapi.commentforkotsuggestion.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "CommentForKOTSuggestion")
public class CommentForKOTSuggestion {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    private String commentForKot;
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID menuItemId;

    public CommentForKOTSuggestion() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCommentForKot() {
        return commentForKot;
    }

    public void setCommentForKot(String commentForKot) {
        this.commentForKot = commentForKot;
    }

    public UUID getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(UUID menuItemId) {
        this.menuItemId = menuItemId;
    }

    public CommentForKOTSuggestion(UUID id, String commentForKot, UUID menuItemId) {
        this.id = id;
        this.commentForKot = commentForKot;
        this.menuItemId = menuItemId;
    }
}