package com.leapstack.wanglong.mall.dao.domain;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "pms_album_pic")
public class PmsAlbumPic implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    @Column(name = "album_id")
    private Long albumId;

    private String pic;

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
     * @return album_id
     */
    public Long getAlbumId() {
        return albumId;
    }

    /**
     * @param albumId
     */
    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    /**
     * @return pic
     */
    public String getPic() {
        return pic;
    }

    /**
     * @param pic
     */
    public void setPic(String pic) {
        this.pic = pic;
    }
}