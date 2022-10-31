package org.zerock.momofit.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import lombok.extern.log4j.Log4j2;

@Configuration
@EnableWebMvc
@PropertySource("classpath:aws.properties")
@Log4j2
public class AWSConfiguration implements WebMvcConfigurer {

	@Value("${aws.accesskey}")
	private String accessKey;
	@Value("${aws.secretkey}")
	private String secretKey;
	
	/*
	 * Amazone S3 웹서비스에 엑세스하기 위한 Client 연결 생성
	 * -. AmazoneS3 인터페이스 생성
	 */
	@Bean
	public BasicAWSCredentials AwsCredentials() {
		BasicAWSCredentials AwsCreds = new BasicAWSCredentials(accessKey, secretKey);
		return AwsCreds;
	} // AwsCredentials
	
	/*
	 * AmazoneS3 연결 Client
	 */
	@Bean
	public AmazonS3 AwsS3Client() {
		
		AmazonS3 amazonS3Builder = AmazonS3ClientBuilder.standard()
				.withRegion(Regions.AP_NORTHEAST_2)
				.withCredentials(new AWSStaticCredentialsProvider(this.AwsCredentials()))
				.build();
		
		return amazonS3Builder;
	}
	
	
} // end class
