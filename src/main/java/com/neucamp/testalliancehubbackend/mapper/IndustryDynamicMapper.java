package com.neucamp.testalliancehubbackend.mapper;

import com.neucamp.testalliancehubbackend.entity.IndustryDynamic;
import com.neucamp.testalliancehubbackend.entity.dynamicreviewrecordtable;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface IndustryDynamicMapper {
    @Select("<script>" +
            "SELECT * FROM industry_dynamic " +
            "<where>" +
            "<if test='title != null and title != \"\"'>" +
            "AND title LIKE CONCAT('%', #{title}, '%')" +
            "</if>" +
            "<if test='author != null and author != \"\"'>" +
            "AND author LIKE CONCAT('%', #{author}, '%')" +
            "</if>" +
            "</where>" +
            "ORDER BY dynamicId " +
            "LIMIT #{pageSize} OFFSET #{offset}" +
            "</script>")
    List<IndustryDynamic> searchDynamics(Map<String, Object> params);

    @Select("<script>" +
            "SELECT COUNT(*) FROM industry_dynamic " +
            "<where>" +
            "<if test='title != null and title != \"\"'>" +
            "AND title LIKE CONCAT('%', #{title}, '%')" +
            "</if>" +
            "<if test='author != null and author != \"\"'>" +
            "AND author LIKE CONCAT('%', #{author}, '%')" +
            "</if>" +
            "</where>" +
            "</script>")
    int searchTotalCount(Map<String, Object> params);

    @Insert("insert into industry_dynamic " +
            "(dynamicId,publisherId, title, content, summary, author, imageUrl, createTime, auditStatus) " + // 显式列名，用数据库的 auditStatus
            "values(null, #{publisherId}, #{title}, #{content}, #{summary}, #{author}, #{imageUrl}, now(), #{auditStatus})")
    int addDynamic(IndustryDynamic industryDynamic);

    @Insert("insert into dynamicreviewrecordtable values (#{ReviewerID},#{title},#{newsImage},#{content},#{newsSummary},#{author},#{reviewResult})")
    int addReviewRecord(Integer ReviewerID,String title, String newsImage, String content, String newsSummary, String author, Integer reviewResult);

    @Select("select * from industry_dynamic where title=#{Title} and author=#{Author}")
    IndustryDynamic searchreviewdDynamics(dynamicreviewrecordtable dynamicreviewrecordtable);
}
