package com.peng.utils;

import com.peng.utils.file.MyFileOperator;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.model.StorageClass;
import com.qcloud.cos.region.Region;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

@Data
@Component
public class CosUtil implements Serializable {
//    private String secret;
    @Value("${ten.oss.secretId}")
    private String secretId="AKIDtc6LK9j4B4i6F9oHWXKr8AxUmob9SWKt";
    @Value("${ten.oss.secretKey}")
    private String secretKey="MSJvIPHvKTN1QZT7WfbJm0ygFYbPxGK8";
    @Value("${ten.oss.region}")
    private String region="ap-nanjing";
    @Value("${ten.oss.photoBucket}")
    private String photoBucket="second-1312767185";
    @Value("${ten.oss.path}")
    private String path="https://second-1312767185.cos.ap-nanjing.myqcloud.com";

    public COSClient getCosClient() {
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        COSClient cosClient = new COSClient(cred, clientConfig);
        return cosClient;
    }

    /**
     * 上传文件
     *@Param //MultipartFile:前台传入的文件 FileType:枚举类 根据值不同，实现的功能不同
     * @return //绝对路径和相对路径都OK
     */
    public String uploadFile(MultipartFile file) throws IOException {
        //获得文件名
        String fileName = file.getOriginalFilename();
        //拿到上传文件后缀名
        String fileSuffix= MyFileOperator.getExtensionName(fileName);
        String newFileName= UUID.randomUUID().toString()+"."+fileSuffix;
        //上传至存储桶的名字
        String KEY = "/" + newFileName;
        //存储桶的名字
        String bucket = photoBucket;
        //将图片的具体信息传入ObjectMetadate类
        ObjectMetadata meta=new ObjectMetadata();
        //必须设置该属性
        meta.setContentLength(file.getSize());
        //设置字符编码格式
        meta.setContentEncoding("UTF-8");
        //获得文件后缀名并根据传入的图片格式设置ContentType
        if (".png".equals(newFileName.lastIndexOf("."))){
            meta.setContentType("image/png");
        }else if (".jpg".equals(newFileName.lastIndexOf("."))){
            meta.setContentType("image/jpeg");
        }
        //SDK构造方法,具体参造[SDKAPI](https://help.aliyun.com/document_detail/32008.htm?spm=a2c4g.11186623.2.3.65ac605fhxBPgG)
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, KEY, file.getInputStream(),meta);
        putObjectRequest.setStorageClass(StorageClass.Standard);
        COSClient client = getCosClient();
        try {
            PutObjectResult putObjectResult = client.putObject(putObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 关闭客户端
        client.shutdown();
        //拼接获得存储桶中可访问的地址
        return "https://"+bucket+".cos."+region+".myqcloud.com"+KEY;
    }
}
