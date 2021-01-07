package com.example.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
    private static final int BUFFER_SIZE = 2 * 1024;
    private static Logger logger = LoggerFactory.getLogger(ZipUtil.class);

    /**
     * 压缩成ZIP 方法1
     *
     * @param srcDir           压缩文件夹路径
     * @param out              压缩文件输出流
     * @param KeepDirStructure 是否保留原来的目录结构,
     *                         true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    public static void toZip(String srcDir, OutputStream out, boolean KeepDirStructure) {
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(out);
            File sourceFile = new File(srcDir);
            compress(sourceFile, zos, sourceFile.getName(), KeepDirStructure);
            long end = System.currentTimeMillis();
            logger.debug("压缩完成，耗时：" + (end - start) + " ms");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：", e);
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("系统异常：", e);
                }
            }
        }
    }

    /**
     * 压缩成ZIP 方法2
     *
     * @param srcFiles 需要压缩的文件列表
     * @param out      压缩文件输出流
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    public static void toZip(List<File> srcFiles, OutputStream out) {
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(out);
            for (File srcFile : srcFiles) {
                byte[] buf = new byte[BUFFER_SIZE];
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
            long end = System.currentTimeMillis();
            logger.debug("压缩完成，耗时：" + (end - start) + " ms");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：", e);
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("系统异常：", e);
                }
            }
        }
    }

    /**
     * 压缩成ZIP 方法2	 *
     *
     * @param srcFiles 需要压缩的文件列表
     * @param out      压缩文件输出流
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    public static void toZip(File[] srcFiles, OutputStream out) {
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(out);
            for (File srcFile : srcFiles) {
                byte[] buf = new byte[BUFFER_SIZE];
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
            long end = System.currentTimeMillis();
            logger.debug("压缩完成，耗时：" + (end - start) + " ms");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：", e);
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("系统异常：", e);
                }
            }
        }
    }

    /**
     * 递归压缩方法
     *
     * @param sourceFile                源文件
     * @param zos                       zip输出流
     * @param name                      压缩后的名称
     * @param ：是否保留原来的目录结构,true:保留目录结构; false：所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws Exception
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name,
                                 boolean KeepDirStructure) {
        byte[] buf = new byte[BUFFER_SIZE];
        try {
            if (sourceFile.isFile()) {
                // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
                zos.putNextEntry(new ZipEntry(name));
                // copy文件到zip输出流中
                int len;
                FileInputStream in = new FileInputStream(sourceFile);
                while ((len = in.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
                // Complete the entry
                zos.closeEntry();
                in.close();
            } else {
                File[] listFiles = sourceFile.listFiles();
                if (listFiles == null || listFiles.length == 0) {
                    // 需要保留原来的文件结构时,需要对空文件夹进行处理
                    if (KeepDirStructure) {
                        // 空文件夹的处理
                        zos.putNextEntry(new ZipEntry(name + "/"));
                        // 没有文件，不需要文件的copy
                        zos.closeEntry();
                    }
                } else {
                    for (File file : listFiles) {
                        // 判断是否需要保留原来的文件结构
                        if (KeepDirStructure) {
                            // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                            // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                            compress(file, zos, name + "/" + file.getName(), KeepDirStructure);
                        } else {
                            compress(file, zos, file.getName(), KeepDirStructure);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：", e);
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("系统异常：", e);
                }

            }
        }
    }

    public static List<File> getMatchFileList(String srcPath, String prefix) {
        List<File> files = new ArrayList();
        if (srcPath == null || prefix == null) {
            return files;
        }
        File srcFile = new File(srcPath);
        File[] listFiles = srcFile.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            return files;
        }
        for (File file : listFiles) {
            if (file.getName().lastIndexOf(prefix) != -1) {
                files.add(file);
            }
        }
        return files;
    }

    public static void deleteLogs(String srcPath, long time, String prefix) {
        if (srcPath == null) {
            return;
        }
        File srcFile = new File(srcPath);
        File[] listFiles = srcFile.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            return;
        }
        for (File file : listFiles) {
            if (file.getName().lastIndexOf(prefix) != -1) {
                long l = System.currentTimeMillis();
//            30 * 24 * 60 * 60 * 1000
                if (l - file.lastModified() > time) {
                    file.delete();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {


        /** 测试压缩方法1 */
        List<File> files = new ArrayList<>();
        File file = new File("D:\\test\\1.txt");
        File file2 = new File("D:\\test\\2.txt");
        File file3 = new File("D:\\test\\3.txt");
        files.add(file);
        files.add(file2);
        files.add(file3);
        FileOutputStream fos1 = new FileOutputStream(new File("D:/mytest01.zip"));
        ZipUtil.toZip(files, fos1);


        List<File> matchFileList = getMatchFileList("D:\\test", ".log");
        System.out.println(matchFileList);
    }
}