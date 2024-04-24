package com.wedu.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
//无参构造
@AllArgsConstructor
@TableName("category")
public class CategoryEntity {
    private int id;
    private String name;
    private int parentId;
    private Date addtime;


}
