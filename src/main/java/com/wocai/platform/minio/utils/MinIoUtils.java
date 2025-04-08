package com.wocai.platform.minio.utils;

import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * minio工具类
 */
@Component
@Slf4j
public class MinIoUtils {

    @Resource
    private MinioClient minioClient;

    @Value("${minio.endpoint}")
    private String endpoint;

    public String getPublicObjectUrl(String bucketName, String objectName) {
        String result = endpoint + "/" + bucketName + "/" + objectName;
        return result;
    }


    //-------------------------------------存储桶操作------------------------------------


    /**
     * 判断桶是否存在
     *
     * @param bucketName 桶名称
     */
    @SneakyThrows
    public Boolean checkBucket(String bucketName) {
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        return found;
    }

    /**
     * 创建桶
     *
     * @param bucketName 桶名称
     */
    @SneakyThrows
    public void createBucket(String bucketName) {
        boolean found = checkBucket(bucketName);
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    /**
     * 删除桶
     *
     * @param bucketName 桶名称
     */
    @SneakyThrows
    public void removeBucket(String bucketName) {
        minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
    }

    /**
     * 列出所有桶
     */
    @SneakyThrows
    public List<Bucket> listBucket() {
        List<Bucket> bucketList = minioClient.listBuckets();
        for (Bucket bucket : bucketList) {
            log.info(bucket.creationDate() + ", " + bucket.name());
        }
        return bucketList;
    }

    /**
     * 判断文件夹是否存在
     *
     * @param bucketName 桶名称
     * @param prefix     文件夹名字
     * @return
     */
    @SneakyThrows
    public Boolean folderExists(String bucketName, String prefix) {
        Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).prefix(prefix).recursive(false).build());
        for (Result<Item> result : results) {
            Item item = result.get();
            if (item.isDir()) {
                return true;
            }
        }
        return false;
    }

    @SneakyThrows
    public Iterable<Result<Item>> listObjects(String bucketName, String prefix) {
        Iterable<Result<Item>> myObjects = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).prefix(prefix).recursive(false).build());
        for (Result<Item> result : myObjects) {
            Item item = result.get();
            System.out.println(item.lastModified() + ", " + item.size() + ", " + item.objectName());
        }
        return myObjects;
    }

    //-------------------------------------文件对象操作------------------------------------

    /**
     * 获取文件信息
     *
     * @param bucketName 桶名称
     * @param objectName 文件名称
     * @return
     */
    @SneakyThrows
    public StatObjectResponse getObjectInfo(String bucketName, String objectName) {
        return minioClient.statObject(StatObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }

    /**
     * 上传文件
     *
     * @param bucketName 桶名称
     * @param objectName 文件名
     * @param stream     流
     * @param fileSize   文件大小
     * @param type       文件类型
     * @throws Exception
     */
    public void putObject(String bucketName, String objectName, InputStream stream, Long fileSize, String type) throws Exception {
        minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(stream, fileSize, -1).contentType(type).build());
    }

    /**
     * 创建文件夹
     *
     * @param bucketName 桶名称
     * @param path       路径
     */
    @SneakyThrows
    public void createFolder(String bucketName, String path) {
        minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(path).stream(new ByteArrayInputStream(new byte[]{}), 0, -1).build());
    }

    /**
     * 拷贝文件
     *
     * @param bucketName
     * @param objectName
     * @param descBucketName
     * @param destObjectName
     */
    @SneakyThrows
    public void copyObject(String bucketName, String objectName, String descBucketName, String destObjectName) {
        minioClient.copyObject(CopyObjectArgs.builder().bucket(descBucketName).object(destObjectName).
                source(CopySource.builder().bucket(bucketName).object(objectName).build()).build());
    }

    /**
     * 删除对象
     *
     * @param bucketName
     * @param objectName
     */
    @SneakyThrows
    public void removeObject(String bucketName, String objectName) {
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }

    /**
     * 根据tag删除对象
     *
     * @param bucketName
     * @param tagName
     */
    @SneakyThrows
    public void deleteObjectTags(String bucketName, String tagName) {
        minioClient.deleteObjectTags(DeleteObjectTagsArgs.builder().bucket(bucketName).object(tagName).build());
    }


    //-------------------------------------Presigned操作------------------------------------


    /**
     * 获取文件在minio在服务器上的外链
     *
     * @param bucketName 桶名称
     * @param objectName 文件名
     * @return
     */
    @SneakyThrows
    public String getObjectUrl(String bucketName, String objectName) {
        String result = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .method(Method.PUT).bucket(bucketName).object(objectName).build());
        return result;
    }

}
