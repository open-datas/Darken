package com.hone.elk.server.dao;

import com.hone.elk.server.model.BusinessLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import javax.annotation.Resource;

/**
 * BusinessRepository
 *
 * @Author hourz
 * @since 2019-07-08
 */
@Resource
public interface BusinessRepository extends ElasticsearchRepository<BusinessLog,String> {

    /**
     * 查询业务日志
     * @param id
     * @return
     */
    BusinessLog queryBusinessLogById(String id);
}
