package net.javaguides.springboot.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.springsecurity.ForbiddenException;
import net.javaguides.springboot.springsecurity.model.PathFileRequestData;
import net.javaguides.springboot.springsecurity.model.PathFileResponse;
import net.javaguides.springboot.springsecurity.model.dto.CommentDto;
import net.javaguides.springboot.springsecurity.model.dto.SearchPathFileDto;
import net.javaguides.springboot.springsecurity.model.entity.PathFile;
import net.javaguides.springboot.springsecurity.service.PathFileService;
import net.javaguides.springboot.springsecurity.service.StorageService;
import net.javaguides.springboot.springsecurity.service.UploadFileImpl;
import net.javaguides.springboot.springsecurity.service.UserLogService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("files")
public class FileController {

    private final StorageService storageService;
    private final PathFileService pathFileService;
    private final UploadFileImpl fileDownloadServiceImpl;
    private final UserLogService userLogService;

    @Value("${email}")
    private String email;

    @GetMapping
    public List<PathFile> allFiles( @RequestHeader("token") String token) {
        if(token.isEmpty()){
            throw new ForbiddenException("Token is null");
        }
        return pathFileService.getPathList(email);
    }

    @DeleteMapping("{id}")
    public void deleteFile(@PathVariable("id") Long id, @RequestHeader("token") String token) {
        if(token.isEmpty()){
            throw new ForbiddenException("Token is null");
        }
        pathFileService.deleteFile(id, email);
    }

    @PostMapping
    public void handleFileUpload(@RequestParam("file") MultipartFile file, @RequestHeader("token") String token) {
        if(token.isEmpty()){
            throw new ForbiddenException("Token is null");
        }
        userLogService.saveLog(file.getOriginalFilename(), email);
        storageService.store(file, email);
    }

    @PostMapping("/data")
    public PathFileResponse getData(@RequestBody PathFileRequestData pathFileRequestData, @RequestHeader("token") String token) {
        if(token.isEmpty()){
            throw new ForbiddenException("Token is null");
        }
        return storageService.getDataFile(pathFileRequestData);
    }

    @GetMapping("{id}")
    public ResponseEntity<InputStreamResource> getFile(@PathVariable("id") Long id, @RequestHeader("token") String token) {
        if(token.isEmpty()){
            throw new ForbiddenException("Token is null");
        }
        File file = fileDownloadServiceImpl.getFile(id);
        log.info("Download file: {}", file.getName());
        userLogService.saveLog(id.toString(), email);
        InputStreamResource isResource = fileDownloadServiceImpl.getInputStreamResource(file);
        var fileSystemResource = new FileSystemResource(file);
        String fileName = FilenameUtils.getName(file.getAbsolutePath());
        fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        HttpHeaders headers = getHeader(fileSystemResource, fileName);
        return new ResponseEntity<>(isResource, headers, HttpStatus.OK);
    }

    @GetMapping("privacy/{id}")
    public void changePrivacy(@PathVariable("id") Long id, @RequestHeader("token") String token) {
        if(token.isEmpty()){
            throw new ForbiddenException("Token is null");
        }
        log.info("Change privacy with id: {}", id);
        userLogService.saveLog(id.toString(), email);
        pathFileService.changePrivacy(id, email);
    }

    @PostMapping("search")
    public List<PathFile> sortByName(@RequestBody SearchPathFileDto searchPathFileDto, @RequestHeader("token") String token) {
        if(token.isEmpty()){
            throw new ForbiddenException("Token is null");
        }
        return pathFileService.findByNameAndUsername(searchPathFileDto.getName(), email);
    }


    private HttpHeaders getHeader(FileSystemResource fileSystemResource, String fileName) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        try {
            headers.setContentLength(fileSystemResource.contentLength());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        headers.setContentDispositionFormData("attachment", fileName);
        return headers;
    }

    private void indexPage(String user, Model model) {
        userLogService.saveLog("Request is empty", user);
        model.addAttribute("comment", new CommentDto());
        model.addAttribute("search", new SearchPathFileDto());
    }
}
