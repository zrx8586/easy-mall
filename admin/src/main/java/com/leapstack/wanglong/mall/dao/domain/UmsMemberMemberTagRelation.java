package com.leapstack.wanglong.mall.dao.domain;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "ums_member_member_tag_relation")
public class UmsMemberMemberTagRelation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "tag_id")
    private Long tagId;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return member_id
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * @param memberId
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * @return tag_id
     */
    public Long getTagId() {
        return tagId;
    }

    /**
     * @param tagId
     */
    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }
}