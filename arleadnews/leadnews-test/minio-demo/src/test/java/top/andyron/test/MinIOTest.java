package top.andyron.test;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.andyron.file.service.FileStorageService;
import top.andyron.minio.MinIOApplication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 把list.html文件上传到minio中，并且可以浏览器访问
 * @author andyron
 **/
// TODO
@SpringBootTest(classes = MinIOApplication.class)
@RunWith(SpringRunner.class)
public class MinIOTest {

    @Autowired
    private FileStorageService fileStorageService;
    // 测试自定义starter
    @Test
    public void test() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("/Users/andyron/myfield/tmp/list.html");
        String path = fileStorageService.uploadHtmlFile("", "list.html", fileInputStream);
        System.out.println(path);
    }



    public static void main(String[] args) {

        try {
            FileInputStream fileInputStream = new FileInputStream("/Users/andyron/myfield/tmp/list.html");

            // 1 创建minio链接客户端
            MinioClient minioClient = MinioClient.builder().credentials("minioadmin", "minioadmin")
                    .endpoint("http://192.168.0.102:9000").build();
            // 2 上传
            PutObjectArgs objectArgs = PutObjectArgs.builder()
                    .object("list.html")        // 文件名
                    .contentType("text/html")         // 文件类型
                    .bucket("leadnews")         // 桶名称，与在minio管理界面创建的桶一致
                    // -1 表示上传所有
                    .stream(fileInputStream, fileInputStream.available(), -1)
                    .build();
            minioClient.putObject(objectArgs);

            // 访问路径
            System.out.println("http://192.168.0.102:9000/leadnews/list.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 手动上传 js css
    @Test
    public void t() {
        try {
            FileInputStream fileInputStream = new FileInputStream("/Users/andyron/myfield/tmp/plugins/js/index.js");

            // 1 创建minio链接客户端
            MinioClient minioClient = MinioClient.builder().credentials("minioadmin", "minioadmin")
                    .endpoint("http://192.168.0.102:9000").build();
            // 2 上传
            PutObjectArgs objectArgs = PutObjectArgs.builder()
                    .object("plugins/js/index.js")        // 文件名
                    .contentType("text/js")         // 文件类型
                    .bucket("leadnews")         // 桶名称，与在minio管理界面创建的桶一致
                    // -1 表示上传所有
                    .stream(fileInputStream, fileInputStream.available(), -1)
                    .build();
            minioClient.putObject(objectArgs);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
