package org.zerock.momofit.aws;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;

@Log4j2
@RequiredArgsConstructor

@Service
@PropertySource("classpath:aws.properties")
public class AwsS3FileUploadService {

	private final AmazonS3 amazonS3Client;
	
	@Value("${aws.bucketname}")
	private String s3BucketName;
	
	/*
	 * S3에 저장 된 이미지 URL 불러오기
	 * @Param : S3 Bucket에 저장 된 파일 이름 (파일경로 포함)
	 * @Return : URL 주소
	 */
	public String getFileURL(String fileName) {

		Date expiration = new Date();
		long expTimeMillis = expiration.getTime();
		expTimeMillis += 1000 * 60 * 60;	// 만료시간 1시간 설정
		expiration.setTime(expTimeMillis);
		
		// S3의 모든 객체는 기본적으로 비공개 (객체 소유자만 엑세스 가능)
		// 자신의 보안 자격 증명을 사용하여, 일정시간 동안 객체 다운로드를 허가하는 미리 서명된 URL 생성하도록 한다.
		GeneratePresignedUrlRequest generatePresignedUrlRequest
			= new GeneratePresignedUrlRequest(s3BucketName, fileName)
			.withMethod(HttpMethod.GET)
			.withExpiration(expiration);
		
		// URL을 만들 때, 보안 자격증명을 통해 BucketName과 Object Key(파일경로) 
		// 그리고 HttpMethod, 만료시간을 전달해주어야 한다.
		return amazonS3Client.generatePresignedUrl(generatePresignedUrlRequest).toString();
		
	} // getFileURL
	
	/*
	 * S3에 파일 저장하기
	 * : 파일 저장 시, 
	 * @Param1 : S3 Bucket에 저장 된 파일 이름 (파일경로 포함)
	 * @Param2 : 폴더경로/임시파일명 + 원본파일명
	 * @Return : 저장 된 URL 경로 주소
	 */
	public String uploadObject(MultipartFile multipartFile, String path, String fileName) throws IOException {
		
		String objectKey = path + "/" + fileName;
		String thumbObjectKey = path + "/s_" + fileName;
		
		@Cleanup
		InputStream filePathIS = multipartFile.getInputStream();
		@Cleanup
		InputStream thumbnailIS = this.makeThumbnail(multipartFile);
		
		
		// Content Length 설정 (원본파일)
		ObjectMetadata objectMeta = new ObjectMetadata();
		byte[] bytes = IOUtils.toByteArray(filePathIS);
		objectMeta.setContentLength(bytes.length);
		
		ByteArrayInputStream byteArrayIS = new ByteArrayInputStream(bytes);

		// 원본파일 생성
		this.amazonS3Client.putObject(new PutObjectRequest(s3BucketName, objectKey, byteArrayIS, objectMeta));
		
		// Content Length 설정 (원본파일)
		ObjectMetadata objectMetaThumb = new ObjectMetadata();
		byte[] bytesThumb = IOUtils.toByteArray(thumbnailIS);
		objectMetaThumb.setContentLength(bytesThumb.length);
		
		ByteArrayInputStream byteArrayThumbIS = new ByteArrayInputStream(bytesThumb);
		log.info("썸네일 이미지 Byte Array : {}", byteArrayThumbIS);
		// 썸네일 생성	
		this.amazonS3Client.putObject(new PutObjectRequest(s3BucketName, thumbObjectKey,  byteArrayThumbIS, objectMetaThumb));

		return this.amazonS3Client.getUrl(s3BucketName, objectKey).toString();
		
	} // uploadFile
	
	/*
	 * 파일 삭제
	 * : 선택한 파일과 썸네일 파일을 삭제한다.
	 * @Param : File Full 경로
	 */
	public void deleteObject(String objectKey) {
		log.trace("AWS S3 deleteObject({}) invoked.", objectKey);
		
		// 원본파일 삭제
		this.amazonS3Client.deleteObject(new DeleteObjectRequest(s3BucketName, objectKey));
		
		
		// 썸네일 삭제
		StringBuilder sb = new StringBuilder(objectKey);
		sb.insert(sb.indexOf("/")+1, "s_");
		this.amazonS3Client.deleteObject(new DeleteObjectRequest(s3BucketName, sb.toString()));
		
	} // deleteObject
	
	
	
	/*
	 * 썸네일 이미지를 만드는 코드
	 * @param : multipartFile
	 * @Return 썸네일화 된 이미지 InputStream
	 */
	public InputStream makeThumbnail(MultipartFile multipartFile) throws IOException {
		
		try {
			BufferedImage image = ImageIO.read(multipartFile.getInputStream());
			
			String formatName = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
			
			double getWidth = image.getWidth();
			double getHeight = image.getHeight();
			
			double resizeRatio = getWidth / getHeight;
			
			int resizeHeight = 160;
			int resizeWidth = (int)(resizeRatio*resizeHeight);
			
			BufferedImage thumbnailImage = Thumbnails.of(image).size(resizeWidth, resizeWidth).asBufferedImage();
			
			// OutputStream에 Resize한 Thumbnail 이미지 저장
			@Cleanup
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(thumbnailImage, formatName, os);
			
			// OutputStream을 InputStream으로 변환
			return new ByteArrayInputStream(os.toByteArray());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;

	} // makeThumbnail
	
	
	/*
	 * 저장할 파일경로 얻기
	 */
	public String getPathInfo() {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(today);
	} // getPathInfo
	

} // end class
