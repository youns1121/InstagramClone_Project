package com.cos.photogramstart.dto.auth.image;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ImageUploadDto {

    private MultipartFile file;
    private String caption;

}
