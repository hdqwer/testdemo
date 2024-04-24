package com.wedu.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@NoArgsConstructor
//无参构造
@AllArgsConstructor
@TableName("goods")
public class GoodsEntity {
    @TableId(type = IdType.AUTO)
    private Long id;

//    @Column(name = "name")
    private String name;

//    @Column(name = "original_price")
    private Double originalPrice;

//    @Column(name = "current_price")
    private Double currentPrice;

//    @Column(name = "picture")
    private String picture;

//    @Column(name = "introduction")
    private String introduction;

//    @Column(name = "is_sale")
    private Boolean isSale;

//    @Column(name = "is_new")
    private Boolean isNew;

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "addtime")
    @TableField(value = "addtime")
    private Date addTime;

//    @Column(name = "views_count")
    private Integer viewsCount;

//    @Column(name = "subcat_id")
    private Integer subcatId;

//    @Column(name = "supercat_id")
    private Integer supercatId;
}
