package com.minlia.module.bible.endpoint;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.minlia.cloud.body.StatefulBody;
import com.minlia.cloud.body.impl.SuccessResponseBody;
import com.minlia.cloud.constant.ApiPrefix;
import com.minlia.module.bible.body.BibleCreateRequestBody;
import com.minlia.module.bible.body.BibleQueryRequestBody;
import com.minlia.module.bible.body.BibleUpdateRequestBody;
import com.minlia.module.bible.constant.BibleSecurityConstant;
import com.minlia.module.bible.entity.Bible;
import com.minlia.module.bible.entity.BibleItem;
import com.minlia.module.bible.service.BibleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by will on 6/21/17.
 */
@RestController
@RequestMapping(value = ApiPrefix.V1 + "bible")
@Api(tags = "System Bible", description = "数据字典")
@Slf4j
public class BibleEndpoint {

    @Autowired
    private BibleService bibleService;

    @PreAuthorize(value = "hasAnyAuthority('" + BibleSecurityConstant.CREATE + "')")
    @ApiOperation(value = "创建", notes = "创建", httpMethod = "POST", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "create", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public StatefulBody create(@Valid @RequestBody BibleCreateRequestBody body) {
        return SuccessResponseBody.builder().payload(bibleService.create(body)).build();
    }

    @PreAuthorize(value = "hasAnyAuthority('" + BibleSecurityConstant.UPDATE + "')")
    @ApiOperation(value = "更新", notes = "更新", httpMethod = "PUT", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "update", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public StatefulBody update(@Valid @RequestBody BibleUpdateRequestBody body) {
        return SuccessResponseBody.builder().payload(bibleService.update(body)).build();
    }

    @PreAuthorize(value = "hasAnyAuthority('" + BibleSecurityConstant.DELETE + "')")
    @ApiOperation(value = "删除", notes = "删除", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public StatefulBody delete(@PathVariable Long id) {
        bibleService.delete(id);
        return SuccessResponseBody.builder().build();
    }

    @PreAuthorize(value = "hasAnyAuthority('" + BibleSecurityConstant.SEARCH + "')")
    @ApiOperation(value = "根据ID查询", notes = "根据ID查询", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public StatefulBody queryById(@PathVariable Long id) {
        return SuccessResponseBody.builder().payload(bibleService.queryById(id)).build();
    }

    @PreAuthorize(value = "hasAnyAuthority('" + BibleSecurityConstant.SEARCH + "')")
    @ApiOperation(value = "根据CODE查询", notes = "根据CODE查询", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "queryByCode", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public StatefulBody queryByCode(@RequestParam String code) {
        return SuccessResponseBody.builder().payload(bibleService.queryByCode(code)).build();
    }

    @PreAuthorize(value = "hasAnyAuthority('" + BibleSecurityConstant.SEARCH + "')")
    @ApiOperation(value = "根据BODY查询集合", notes = "查询集合", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "queryList", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public StatefulBody queryList(@Valid @RequestBody BibleQueryRequestBody body) {
        return SuccessResponseBody.builder().payload(bibleService.queryList(body)).build();
    }

    @PreAuthorize(value = "hasAnyAuthority('" + BibleSecurityConstant.SEARCH + "')")
    @ApiOperation(value = "根据BODY查询分页", notes = "查询分页", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "queryPage", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public StatefulBody queryPaginated(@PageableDefault Pageable pageable, @RequestBody BibleQueryRequestBody body) {
        PageHelper.startPage(pageable.getOffset(),pageable.getPageSize());
        List<Bible> bibles = bibleService.queryList(body);
        PageInfo<Bible> page = new PageInfo<Bible>(bibles);
        return SuccessResponseBody.builder().payload(page).build();
    }

}

