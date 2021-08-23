package com.cos.photogramstart.service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.Image;
import com.cos.photogramstart.repository.ImageRepository;
import com.cos.photogramstart.dto.auth.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;


    @Transactional(readOnly = true) // 영속성 컨텍스트 변경 감지해서, 더티체킹, flush 반영
    public Page<Image> 이미지스토리(Long principalId, Pageable pageable){

        Page<Image> images = imageRepository.mStroy(principalId, pageable);

        return images;
    }

    @Value("${file.path}")// .yml의 file:path:
    private String uploadFolder;

    @Transactional
    public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails){
        UUID uuid = UUID.randomUUID(); // uuid 네트워크 상에서 고유성이 보장되는 id를 만들기 위한 표준 규약
        String imageFileName = uuid+"_" + imageUploadDto.getFile().getOriginalFilename(); // 1.jpg
        System.out.println("이미지 파일이름: "+ imageFileName);

        Path imageFilePath = Paths.get(uploadFolder + imageFileName);

        //통신, I/O -> 예외가 발생할 수 있음
        try{
            Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }

        // image 테이블에 저장
        Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser());
        Image imageEntity = imageRepository.save(image);

      //    System.out.println(imageEntity);
    }
}
