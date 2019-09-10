package com.hone.elk.server.model;

import com.hone.common.json.CResult;
import com.hone.elk.server.constant.ElasticsearchConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * <P>业务日志记录</P>
 * @Author hourz
 * @since 2019-06-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Document(indexName = ElasticsearchConstant.BUSINESS_INDEX, type = ElasticsearchConstant.BUSINESS_INDEX_TYPE)
public class BusinessLog extends BaseModel {

    private String userId;
    private String type;
    private CResult result;
    private String message;
    private String errorMessage;

}
