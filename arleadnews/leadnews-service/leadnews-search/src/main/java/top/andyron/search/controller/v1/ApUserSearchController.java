package top.andyron.search.controller.v1;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.andyron.model.common.dtos.ResponseResult;
import top.andyron.model.search.dto.HistorySearchDto;
import top.andyron.search.service.ApUserSearchService;

@Api(tags = "搜索历史")
@RestController
@RequestMapping("/api/v1/history")
public class ApUserSearchController {

    @Autowired
    private ApUserSearchService apUserSearchService;

    @PostMapping("/load")
    public ResponseResult findUserSearch(){
        return apUserSearchService.findUserSearch();
    }

    @PostMapping("/del")
    public ResponseResult delUserSearch(@RequestBody HistorySearchDto dto){
        return apUserSearchService.delUserSearch(dto);
    }
}
