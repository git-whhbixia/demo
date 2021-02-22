package com.example.demo.task;

import com.example.demo.utils.ZipUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Create by Hercules
 * 2021-01-05 22:01
 */
@Component
public class BakTask {

    /**
     * 每天24点执行
     */
//    @Scheduled(cron = "0 0 0 * * ? *")
    public void work() throws FileNotFoundException {
        String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        System.out.println("bak log task execute " + format);
        //D:\\log 文件夹 .log 备份至\\BackupServer\\log_bak\\
        String srcPath = "D:/log";
        String targetPath = "D:/BackupServer/log_bak/";//可以改成 \\BackupServer\\log_bak\\
        File file = new File(targetPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        List<File> matchFileList = ZipUtil.getMatchFileList(srcPath, ".log");
        FileOutputStream fos1 = new FileOutputStream(new File(targetPath + "log_" + format + ".zip"));
        //输出到目标文件夹压缩
        ZipUtil.toZip(matchFileList, fos1);
        //删除.log文件
        for (File file1 : matchFileList) {
            file1.delete();
        }
        //删除30天前的日志文件
        //30 * 24 * 60 * 60 * 1000
        ZipUtil.deleteLogs(targetPath, 30 * 24 * 60 * 60 * 1000,".log");

    }
}
