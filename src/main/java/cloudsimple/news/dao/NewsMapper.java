package cloudsimple.news.dao;

import cloudsimple.news.entity.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface NewsMapper {

    public News getNewsByNewsid(Long newsid);

    public List<Map<String,Object>> getPagingById(@Param("indexNum") Long indexNum, @Param("limitNum") Long limitNum);

    public Long getCount();
}
