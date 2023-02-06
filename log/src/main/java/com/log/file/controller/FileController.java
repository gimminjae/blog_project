package com.log.file.controller;

import com.log.member.entity.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class FileController {
    @Value("${spring.custom.genFileDirPath}")
    private String genFileDirPath;
    @Value("${spring.custom.rootUrl}")
    private String rootUrl;
    @PostMapping("/api/file")
    public ResponseEntity<String> fileUploadInPostForm(@AuthenticationPrincipal MemberContext memberContext, @RequestBody MultipartFile file) {
        String today = LocalDate.now().toString();
        String profileImgRelPath = "member/" + today + "/" + UUID.randomUUID().toString() + ".png";
        File profileImgFile = new File(genFileDirPath + "/" + profileImgRelPath);

        profileImgFile.mkdirs(); // 관련된 폴더가 혹시나 없다면 만들어준다.

        try {
            file.transferTo(profileImgFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.of(Optional.of(rootUrl + "/gen/" + profileImgRelPath));
    }
}
