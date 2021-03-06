package com.minlia.cloud.query.specification.batis.body;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.minlia.cloud.body.Body;
import com.minlia.cloud.query.body.QueryRequestBody;
import com.minlia.cloud.query.specification.batis.QueryCondition;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @param <PAYLOAD>
 */
public class ApiQueryRequestBody<PAYLOAD extends QueryRequestBody> implements Body {

    /**
     * 搜索时后台指定的条件集
     */
    @JsonIgnore
    private List<QueryCondition> conditions = Lists.newArrayList();

    @ApiModelProperty(value = "搜索具体请求体(包括各搜索字段的实际请求体)")
    private PAYLOAD payload;

    public PAYLOAD getPayload() {
        return payload;
    }

    public void setPayload(PAYLOAD payload) {
        this.payload = payload;
    }

    public ApiQueryRequestBody() {
    }


    public List<QueryCondition> getConditions() {
        return conditions;
    }

    public void setConditions(List<QueryCondition> conditions) {
        this.conditions = conditions;
    }
}
