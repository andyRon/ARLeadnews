package top.andyron.wemedia;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.andyron.common.aliyun.GreenImageScan;
import top.andyron.common.aliyun.GreenTextScan;
import top.andyron.file.service.FileStorageService;

import java.util.Arrays;
import java.util.Map;

@SpringBootTest(classes = WemediaApplication.class)
@RunWith(SpringRunner.class)
public class AliyunTest {

    @Autowired
    private GreenTextScan greenTextScan;

    @Autowired
    private GreenImageScan greenImageScan;

    @Autowired
    private FileStorageService fileStorageService;

    @Test
    public void testScanText() throws Exception {
        Map map = greenTextScan.textScan("我是一个好人，冰毒");
//        System.out.println(map);
    }

    @Test
    public void testScanImage() throws Exception {
//        byte[] bytes = fileStorageService.downLoadFile("http://192.168.0.102:9000/leadnews/2023/12/11/c6e5918ebdc244178c82a4e52f12909e.jpg");
//        Map map = greenImageScan.imageScan(Arrays.asList(bytes));
//        System.out.println(map);
    }
}
