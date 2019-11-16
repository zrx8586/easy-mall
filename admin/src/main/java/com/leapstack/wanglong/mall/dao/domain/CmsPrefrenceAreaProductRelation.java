package com.leapstack.wanglong.mall.dao.domain;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "cms_prefrence_area_product_relation")
public class CmsPrefrenceAreaProductRelation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    @Column(name = "prefrence_area_id")
    private Long prefrenceAreaId;

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
     * @return prefrence_area_id
     */
    public Long getPrefrenceAreaId() {
        return prefrenceAreaId;
    }

    /**
     * @param prefrenceAreaId
     */
    public void setPrefrenceAreaId(Long prefrenceAreaId) {
        this.prefrenceAreaId = prefrenceAreaId;
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