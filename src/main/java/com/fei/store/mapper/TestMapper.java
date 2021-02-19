package com.fei.store.mapper;

import com.fei.store.common.utils.PageVO;
import com.fei.store.vo.TestVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TestMapper {

    @Select("<script>" +
            "SELECT * FROM test " +
            "<where> " +
            " <if test=\"key.name!=null and key.name!=''\"> " +
            "and name like  CONCAT('%', #{key.name}, '%')" +
            "</if> " +
            "</where>" +
            "<if test=\"sortBy!=null and sortBy!=''\"> " +
            "ORDER BY #{sortBy} #{desc}" +
            "</if>" +
            "</script>")
    List<TestVO> findAllByPage(PageVO pageVO);
}
