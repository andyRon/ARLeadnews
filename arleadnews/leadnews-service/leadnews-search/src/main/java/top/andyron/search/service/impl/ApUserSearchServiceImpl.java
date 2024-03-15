package top.andyron.search.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.andyron.model.common.dtos.ResponseResult;
import top.andyron.model.common.enums.AppHttpCodeEnum;
import top.andyron.model.search.dto.HistorySearchDto;
import top.andyron.model.user.pojos.ApUser;
import top.andyron.search.mapper.ApUserSearchMapper;
import top.andyron.search.pojos.ApUserSearch;
import top.andyron.search.service.ApUserSearchService;
import top.andyron.utils.thread.AppThreadLocalUtil;

import java.util.Date;
import java.util.List;


@Service
@Transactional
@Slf4j
public class ApUserSearchServiceImpl implements ApUserSearchService {


    @Autowired
    private MongoTemplate mongoTemplate;
    /**
     * 保存用户搜索记录
     *
     * @param keyword
     * @param userId
     */
    @Override
    @Async
    public void insert(String keyword, Integer userId) {
        // 1 查询当前用户搜索的关键词
        Query query = Query.query(Criteria.where("userId").is(userId).and("keyword").is(keyword));
        ApUserSearch apUserSearch = mongoTemplate.findOne(query, ApUserSearch.class);

        // 2 存在则更新最新时间
        if (apUserSearch != null) {
            apUserSearch.setCreatedTime(new Date());
            mongoTemplate.save(apUserSearch);
            return;
        }

        // 3 不存在则新增, 判断当前历史记录是否超过10条记录
        apUserSearch = new ApUserSearch();
        apUserSearch.setUserId(userId);
        apUserSearch.setKeyword(keyword);
        apUserSearch.setCreatedTime(new Date());

        Query q = Query.query(Criteria.where("userId").is(userId));
        q.with(Sort.by(Sort.Direction.DESC, "createdTime"));
        List<ApUserSearch> apUserSearchList = mongoTemplate.find(q, ApUserSearch.class);
        if (apUserSearchList == null || apUserSearchList.size() < 10) {
            mongoTemplate.save(apUserSearch);
        } else {
            ApUserSearch lastUserSearch = apUserSearchList.get(apUserSearchList.size() - 1);
            mongoTemplate.findAndReplace(Query.query(Criteria.where("id").is(lastUserSearch.getId())), apUserSearch);
        }

    }

    /**
     * 查询搜索历史
     *
     * @return
     */
    @Override
    public ResponseResult findUserSearch() {
        // 获取当前用户
        ApUser user = AppThreadLocalUtil.getUser();
        if(user == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }

        // 根据用户查询数据，按照时间倒序
        List<ApUserSearch> apUserSearches = mongoTemplate.find(Query.query(Criteria.where("userId")
                .is(user.getId())).with(Sort.by(Sort.Direction.DESC, "createdTime")), ApUserSearch.class);
        return ResponseResult.okResult(apUserSearches);
    }

    /**
     * 删除历史记录
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult delUserSearch(HistorySearchDto dto) {
        // 1.检查参数
        if(dto.getId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        // 2.判断是否登录
        ApUser user = AppThreadLocalUtil.getUser();
        if(user == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }

        // 3.删除
        mongoTemplate.remove(Query.query(Criteria.where("userId").is(user.getId())
                .and("id").is(dto.getId())),ApUserSearch.class);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }


}
