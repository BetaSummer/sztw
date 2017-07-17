package betahouse.core.Base;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Yxm on 2017/7/7.
 */
public class BaseFile {
    //指定存储文件夹
    private  static final String FOLDER = "File";

    public int upload(MultipartFile prefile, String folderName) {
        try {
            String folderPath = FOLDER + File.separator + folderName + File.separator;
            File f = new File(folderPath);
            if (!f.exists()) {
                f.mkdirs();
            }
            BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(new File(folderPath + prefile.getOriginalFilename())));
            out.write(prefile.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 1;
        }
        return 0;
    }

    public int upload(MultipartFile preFile, String folderName, String afterFileName, boolean isKeepSuffix) {
        try {
            String folderPath = FOLDER + File.separator + folderName + File.separator;
            File f = new File(folderPath);
            if (!f.exists()) {
                f.mkdirs();
            }
            if (isKeepSuffix) {
                String[] arr = preFile.getOriginalFilename().split("\\.");
                afterFileName += "." + arr[arr.length - 1];
            }
            BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(new File(folderPath + afterFileName)));
            out.write(preFile.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 1;
        }
        return 0;
    }

    public int download(HttpServletResponse response, String folderName, String fileName){
        File file = new File(FOLDER+File.separator+folderName+File.separator+fileName);
        if (file.exists()) {
            response.setContentType("application/force-download");// 设置下载后不打开
            response.addHeader("Content-Disposition","attachment;fileName=" +  fileName);// 设置下载后文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                bis.close();
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
                return 1;
            }
            return 0;
        }
        return 1;
    }

    public int download(HttpServletResponse response, String folderName, String fileName, String targetName){
        File file = new File(FOLDER+File.separator+folderName+File.separator+fileName);
        if (file.exists()) {
            response.setContentType("application/force-download");// 设置下载后不打开
            response.addHeader("Content-Disposition","attachment;fileName=" +  targetName);// 设置下载后文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                bis.close();
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
                return 1;
            }
            return 0;
        }
        return 1;
    }

    public int delete(String folderName, String fileName){
        try {
            File file = new File(FOLDER + File.separator + folderName + File.separator + fileName);
            file.delete();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return 1;
        }
        return 0;
    }

}
