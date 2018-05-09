package com.gitnavi.springboot.springbootsimpledemo.controller;

import com.gitnavi.springboot.springbootsimpledemo.util.FastDFSClientWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Api(value = "FastDFSController RESTful", description = "上传文件 REST API")
@RestController
@RequestMapping("/api/fastdfs")
public class FastDFSController {

    @Resource
    private FastDFSClientWrapper dfsClient;

    @ApiOperation(value = "上传文件", notes = "上传文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件本体", paramType = "body"),
    })
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> upload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<>();
        String fileUrl = dfsClient.uploadFile(file);
        map.put("file_url", fileUrl);
        return ResponseEntity.ok(map);
    }
}
