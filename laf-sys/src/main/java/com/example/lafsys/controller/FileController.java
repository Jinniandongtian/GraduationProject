package com.example.lafsys.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.lafsys.entity.Files;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lafsys.mapper.FilesMapper;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.lafsys.service.IFilesService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liang
 * @since 2024-03-12
 */
@RestController
@RequestMapping("/file")
    public class FileController {

    @Resource
    private IFilesService filesService;

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Resource
    private FilesMapper filesMapper;
    //新增或更新
    @PostMapping
    public boolean save(@RequestBody Files files){
            return  filesService.saveOrUpdate(files);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
            return filesService.removeById(id);
    }

    // 批量删除接口
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){  //@PathVariable从URI中提取参数值
            return filesService.removeByIds(ids);
    }

    @GetMapping
    public List<Files> findAll() {
            return filesService.list();
    }

//    @GetMapping("/{id}")
//    public Files findOne(@PathVariable Integer id) {
//            return filesService.getById(id);
//    }

    @GetMapping("/page")
    public Page<Files> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
            QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
            return filesService.page(new Page<>(pageNum, pageSize),queryWrapper);
    }

    /**
     * 通过文件的md5查询文件
     * @param md5
     * @return
     */
    private Files getFileByMd5(String md5){
        // 查询文件的md5是否存在
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5",md5);
        List<Files> filesList = filesMapper.selectList(queryWrapper);
        return filesList.size() == 0 ? null : filesList.get(0);
    }

    @GetMapping("/{fileUUID}")
    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        // 根据文件的唯一标识码获取文件
        java.io.File uploadFile = new java.io.File(fileUploadPath + fileUUID);
        // 设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileUUID, StandardCharsets.UTF_8));
        response.setContentType("application/octet-stream");
        // 读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }

    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);

        long size = file.getSize();

        // 定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUID = uuid + StrUtil.DOT + type;
        java.io.File uploadFile = new java.io.File(fileUploadPath + fileUUID);
        // 判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
        java.io.File parentFile = uploadFile.getParentFile();
        if(!parentFile.exists()){
            parentFile.mkdir();
        }

        String url;
        // 上传文件到磁盘
        file.transferTo(uploadFile);
        //获取文件的md5
        String md5 = SecureUtil.md5(uploadFile);
        System.out.println("md5=" + md5);
        // 从数据库查询是否具有相同的记录
        Files dbFiles = getFileByMd5(md5);
        System.out.println("dbFiles=" + dbFiles);
        if(dbFiles != null){
            url = dbFiles.getUrl();
            return url;
        }else {
            // 数据库若不存在重复文件，则不删除刚才上传的文件
            url = "http://localhost:9091/file/" + fileUUID;
        }

        // 存储数据库
        Files saveFile = new Files();
        saveFile.setName(originalFilename);
        saveFile.setType(type);
        saveFile.setSize(size/1024);
        saveFile.setUrl(url);
        saveFile.setMd5(md5);
        filesMapper.insert(saveFile);
        return url;
    }

}
