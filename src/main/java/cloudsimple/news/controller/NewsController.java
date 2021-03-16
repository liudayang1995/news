package cloudsimple.news.controller;


import cloudsimple.news.dao.NewsMapper;
import cloudsimple.news.entity.News;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class NewsController {

    @Autowired
    NewsMapper newsMapper;

    /**
     * 通过文章Id查询文章
     * @param id
     * @return
     */
    @RequestMapping(value = "/news/{id}",method = RequestMethod.GET)
    public News getById(@PathVariable Long id){
        return newsMapper.getNewsByNewsid(id);
    }


    @RequestMapping(value = "/paging/{pageNum}/{limitNum}",method = RequestMethod.GET)
    public String getPagingById(@PathVariable Long pageNum, @PathVariable Long limitNum){
        //通过当前查询页数和分页数计算出查询下标
        Long indexNum = pageNum * (limitNum - 1);
        List<Map<String, Object>> pageNews = newsMapper.getPagingById(indexNum, limitNum);
        //计算总页数
        long pageCount = (long)Math.ceil((double)newsMapper.getCount()/(double)limitNum);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageNews",pageNews);
        jsonObject.put("pageCount",pageCount);
        return JSONObject.toJSONString(jsonObject);
    }

}
