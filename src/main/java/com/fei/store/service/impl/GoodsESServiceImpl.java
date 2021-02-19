package com.fei.store.service.impl;

import com.fei.store.ES.ElasticRepository;
import com.fei.store.common.utils.PageVO;
import com.fei.store.service.GoodsESService;
import com.fei.store.vo.GoodsVO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class GoodsESServiceImpl implements GoodsESService {

    @Autowired
    private ElasticRepository elasticRepository;

    @Override
    public Page<GoodsVO> findAllByPage(PageVO pageVO) {
        // 1.创建查询对象, 查询所有数据
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        // 2、判断查询参数
        if (!StringUtils.isEmpty(pageVO.getKey().get("tid"))) {
            MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("tid", pageVO.getKey().get("tid"));
            boolQuery.must(matchQuery);
        }
        if (!StringUtils.isEmpty(pageVO.getKey().get("bid"))) {
            MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("bid", pageVO.getKey().get("bid"));
            boolQuery.must(matchQuery);
        }
        if (!StringUtils.isEmpty(pageVO.getKey().get("cid"))) {
            MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("cid", pageVO.getKey().get("cid"));
            boolQuery.must(matchQuery);
        }
        if (!StringUtils.isEmpty(pageVO.getKey().get("sid"))) {
            MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("sid", pageVO.getKey().get("sid"));
            boolQuery.must(matchQuery);
        }
        if (!StringUtils.isEmpty(pageVO.getKey().get("name"))) {
            FuzzyQueryBuilder matchQuery = QueryBuilders.fuzzyQuery("name", pageVO.getKey().get("name"));
            boolQuery.must(matchQuery);
        }
        // 3、调用查询接口
        Pageable pageable= PageRequest.of(pageVO.getPage()-1,pageVO.getRows());
        Page<GoodsVO> page = elasticRepository.search(boolQuery, pageable);

        return page;
    }

    @Override
    public void saveOrUpdate(GoodsVO goodsVO) {
        elasticRepository.save(goodsVO);
    }

    @Override
    public void delete(GoodsVO goodsVO) {
        elasticRepository.delete(goodsVO);
    }
}
