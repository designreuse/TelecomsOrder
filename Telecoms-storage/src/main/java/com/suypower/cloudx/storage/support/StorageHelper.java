package com.suypower.cloudx.storage.support;

import com.suypower.cloudx.storage.core.entity.FileOption;
import com.suypower.cloudx.support.util.DateTime;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.MimetypesFileTypeMap;
import java.io.*;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Bingdor on 2015/12/3.
 */
public class StorageHelper {
    private static Logger logger = LoggerFactory.getLogger(StorageHelper.class);

    public static String saveFile(String appKey, String home, FileOption fileOption) throws Exception {
        String basePath = home + "/" + appKey + "/" + DateTime.dateToStr(new Date()) + "/" + fileOption.getDataGroup();
        String fileSuffix = fileOption.getFileName().substring(fileOption.getFileName().lastIndexOf("."));
        String fileName = basePath + "/" + UUID.randomUUID().toString() + fileSuffix;
        if (logger.isDebugEnabled()) {
            logger.debug("开始写入磁盘文件：{}", fileName);
        }
        File homeDir = new File(basePath);
        if (!homeDir.exists()) {
            homeDir.mkdirs();
        }
        File file = new File(fileName);
        file.createNewFile();
        InputStream inputStream = fileOption.getFileStream();
        FileOutputStream fileOutputStream = null;
        if (inputStream != null) {
            fileOutputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            try {
                while (inputStream.read(buffer) > 0) {
                    fileOutputStream.write(buffer);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                inputStream.close();
            } catch (IOException e) {
                logger.error("写入磁盘文件失败：{}", e);
                throw new Exception(e.getMessage());
            }
            if (logger.isDebugEnabled()) {
                logger.debug("写入磁盘文件成功：{}", file.getAbsolutePath());
            }
        } else if (fileOption.getFileBytes() != null && fileOption.getFileBytes().length > 0) {
            byte[] bytes = fileOption.getFileBytes();
            try {
                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(bytes);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("写入磁盘文件失败:{}", e);
            } finally {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            }
        } else {
            throw new Exception("文件数据为空");
        }
        return fileName;
    }

    public static String copyFile(String filePath) throws IOException{
        File file = new File(filePath);
        String newPath = filePath.substring(0,filePath.lastIndexOf("/"))+"/"+UUID.randomUUID().toString()+filePath.substring(filePath.lastIndexOf("."));
        File newFile = new File(newPath);
        if(file.exists()){
            FileUtils.copyFile(file, newFile);
            return newPath;
        }
        throw new IOException("文件不存在");
    }

    public static void moveFile(String oldFile, String newFile) throws IOException {
        File file = new File(oldFile);
//        file.renameTo(new File(newFile));
//        FileUtils.moveFile(file,new File(newFile));
        FileUtils.copyFile(file, new File(newFile));
        FileUtils.forceDelete(file);
    }

    public static void deleteFile(String filePath) throws IOException {
        File file = new File(filePath);
        if(file.exists()){
            FileUtils.forceDelete(file);
        }
    }

    public static void deleteFiles(List<String> filePaths) throws IOException {
        for (String filePath:filePaths){
            File file = new File(filePath);
            if(file.exists()){
                FileUtils.forceDelete(file);
            }
        }
    }


    public static String getContentType(String filePath) {
        File file = new File(filePath);
        return file.exists() ? new MimetypesFileTypeMap().getContentType(file) : null;
    }

    public static String getShaCode(String filePath) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 缓冲区大小
        int bufferSize = 256 * 1024;
        DigestInputStream digestInputStream = null;
        try {
            // 拿到一个SHA1转换器（这里可以换成MD5）
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            // 使用DigestInputStream
            digestInputStream = new DigestInputStream(fileInputStream, messageDigest);
            // read的过程中进行SHA1处理，直到读完文件
            byte[] buffer = new byte[bufferSize];
            while (digestInputStream.read(buffer) > 0) ;
            // 获取最终的MessageDigest
            messageDigest = digestInputStream.getMessageDigest();
            // 拿到结果，也是字节数组，包含16个元素
            byte[] resultByteArray = messageDigest.digest();
            // 把字节数组转换成字符串
            return byteArrayToHex(resultByteArray);
        } catch (Exception e) {
            return null;
        } finally {
            try {
                digestInputStream.close();
                fileInputStream.close();
            } catch (Exception e) {

            }
        }
    }

    private static String byteArrayToHex(byte[] byteArray) {
        // 首先初始化一个字符数组，用来存放每个16进制字符
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
        char[] resultCharArray = new char[byteArray.length * 2];
        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        // 字符数组组合成字符串返回
        return new String(resultCharArray);
    }

    public static Long getFileSize(String filePath) {
        File file = new File(filePath);
        return file.exists() ? file.length() : null;

    }

    public static InputStream readFile(String filePath) throws Exception {
        File file = new File(filePath);
        if (file.exists()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            return fileInputStream;
        }
        return null;
    }
}
