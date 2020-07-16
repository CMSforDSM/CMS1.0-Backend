package com.cms.api.domain.file.controller;

import com.cms.api.domain.file.dto.UploadResponseDto;
import com.cms.api.domain.file.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("/files")
@RestController
public class FileController {

    private final S3Service s3Service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UploadResponseDto upload(@RequestPart MultipartFile file) throws Exception {
        String contentType = file.getContentType().split("/")[0];
        String url = s3Service.upload(file);

        return UploadResponseDto.builder()
                .media_type(contentType)
                .file_link(url)
                .build();

    }
}
