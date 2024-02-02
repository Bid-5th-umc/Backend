package com.bid.auction.global.aws.s3;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.bid.auction.global.config.AmazonConfig;
import com.bid.auction.domain.product.entity.Uuid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AmazonS3Manager {

	private final AmazonS3 amazonS3;
	private final AmazonConfig amazonConfig;

	public String uploadFile(String keyName, MultipartFile file){
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(file.getContentType());
		metadata.setContentLength(file.getSize());
		try {
			PutObjectResult putObjectResult = amazonS3.putObject(
				new PutObjectRequest(amazonConfig.getBucket(), keyName, file.getInputStream(), metadata));
			log.info("result={}", putObjectResult.getContentMd5());
		}catch (IOException e){
			log.error("error at AmazonS3Manager uploadFile : {}", (Object) e.getStackTrace());
		}


		return amazonS3.getUrl(amazonConfig.getBucket(), keyName).toString();
	}

	public String generatePostImageKeyName(Uuid uuid) {
		return amazonConfig.getPostImagePath() + '/' + uuid.getUuid();
	}

}
