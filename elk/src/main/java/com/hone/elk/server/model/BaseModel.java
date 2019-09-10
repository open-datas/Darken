package com.hone.elk.server.model;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>Elasticsearch的主键</p>
 * @Author hourz
 * @since 2019-07-08
 */
@Data
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 8152048348166952579L;
    //
    protected Long id;
    
}
