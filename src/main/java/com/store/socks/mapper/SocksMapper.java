package com.store.socks.mapper;

import com.store.socks.model.Socks;
import org.apache.ibatis.annotations.*;

import java.util.Optional;

@Mapper
public interface SocksMapper {

    @Select("SELECT id, color, cotton_part, quantity FROM socks_balance WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "color", column = "color"),
            @Result(property = "cottonPart", column = "cotton_part"),
            @Result(property = "quantity", column = "quantity")
    })
    Socks selectSocksById(@Param("id") int id);


  //  @Select("SELECT quantity FROM socks_balance WHERE color = #{color} AND cotton_part = #{cottonPart} LIMIT 1")
  //  Optional<Integer> selectSocksByParams(@Param("color") String color, @Param("cottonPart") int cottonPart);

    @Insert("INSERT INTO socks_balance (color, cotton_part, quantity) VALUES (#{color}, #{cottonPart}, #{quantity})")
    void insertSocksEntry(@Param("color") String color, @Param("cottonPart") int cottonPart, @Param("quantity") int quantity);

    @Update("UPDATE socks_balance SET quantity = #{quantity} WHERE color = #{color} AND cotton_part = #{cottonPart}")
    boolean updateSocksQuantityGivenParams(@Param("color") String color, @Param("cottonPart") int cottonPart, @Param("quantity") int quantity);

    @Select("SELECT SUM (quantity) FROM socks_balance WHERE color = #{color} AND cotton_part > #{cottonPart}")
    Optional<Integer> selectQuantityByParamsMoreThan(@Param("color") String color, @Param("cottonPart") int cottonPart);

    @Select("SELECT SUM (quantity) FROM socks_balance WHERE color = #{color} AND cotton_part < #{cottonPart}")
    Optional<Integer> selectQuantityByParamsLessThan(@Param("color") String color, @Param("cottonPart") int cottonPart);

    @Select("SELECT SUM (quantity) FROM socks_balance WHERE color = #{color} AND cotton_part = #{cottonPart}")
    Optional<Integer> selectQuantityByParamsEqual(@Param("color") String color, @Param("cottonPart") int cottonPart);

}
