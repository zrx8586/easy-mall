package com.leapstack.wanglong.mall.dao.domain;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "pms_product_category_attribute_relation")
public class PmsProductCategoryAttributeRelation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    @Column(name = "product_category_id")
    private Long productCategoryId;

    @Column(name = "product_attribute_id")
    private Long productAttributeId;

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
     * @return product_category_id
     */
    public Long getProductCategoryId() {
        return productCategoryId;
    }

    /**
     * @param productCategoryId
     */
    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    /**
     * @return product_attribute_id
     */
    public Long getProductAttributeId() {
        return productAttributeId;
    }

    /**
     * @param productAttributeId
     */
    public void setProductAttributeId(Long productAttributeId) {
        this.productAttributeId = productAttributeId;
    }
}