package com.wedu.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wedu.modules.sys.entity.GoodsEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GoodsDao extends BaseMapper<GoodsEntity> {
    @Select("SELECT * FROM goods")
    List<GoodsEntity> getAll();
    List<GoodsEntity> getGoodsByName(@Param("goodsName") String goodsName);


    // 查询商品
    @Select("SELECT * FROM goods WHERE id = #{id}")
    GoodsEntity getById(@Param("id") Long id);

    // 保存商品
    @Insert("INSERT INTO goods (name, original_price, current_price, picture, introduction, is_sale, is_new, addTime, views_count, subcat_id, supercat_id) " +
            "VALUES (#{name}, #{originalPrice}, #{currentPrice}, #{picture}, #{introduction}, #{isSale}, #{isNew}, #{addTime}, #{viewsCount}, #{subcatId}, #{supercatId})")
    int save(GoodsEntity entity);

    // 更新商品
//    @Update("UPDATE goods SET name = #{name}, original_price = #{originalPrice}, current_price = #{currentPrice}, " +
//            "picture = #{picture}, introduction = #{introduction}, is_sale = #{isSale}, is_new = #{isNew}, addTime = #{addTime}, " +
//            "views_count = #{viewsCount}, subcat_id = #{subcatId}, supercat_id = #{supercatId} WHERE id = #{id}")
//    int updateById(GoodsEntity entity);

    // 删除商品
    @Delete("DELETE FROM goods WHERE id = #{id}")
    int removeById(Long id);

    // 如果需要其他查询方法，可以在这里继续添加
}