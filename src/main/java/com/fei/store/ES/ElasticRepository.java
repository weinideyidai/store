package com.fei.store.ES;

import com.fei.store.vo.GoodsVO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticRepository extends ElasticsearchRepository<GoodsVO, Long> {
}
