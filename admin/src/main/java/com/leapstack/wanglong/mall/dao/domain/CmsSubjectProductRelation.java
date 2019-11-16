package com.leapstack.wanglong.mall.dao.domain;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "cms_subject_product_relation")
public class CmsSubjectProductRelation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "product_id")
    private Long productId;

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
     * @return subject_id
     */
    public Long getSubjectId() {
        return subjectId;
    }

    /**
     * @param subjectId
     */
    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * @return product_id
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * @param productId
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }
}