package com.xqsight.common.upload.handler;

import com.xqsight.common.upload.file.CommonFile;
import com.xqsight.common.upload.file.CommonFileFilter;
import com.xqsight.common.upload.file.FtpTemplate;
import com.xqsight.common.upload.service.PathResolver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 文件处理抽象类
 *
 * @author wangganggang
 */
public abstract class FileHandler {

    protected Logger logger = LogManager.getLogger(getClass());

    public static LocalFileHandler getLocalFileHandler(PathResolver pathResolver, String prefix) {
        return new LocalFileHandler(pathResolver, prefix);
    }

    public static FtpFileHandler getFileHandler(FtpTemplate ftpTemplate, String prefix) {
        return new FtpFileHandler(ftpTemplate, prefix);
    }

    protected String prefix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public abstract boolean mkdir(String name, String id);

    public abstract boolean rename(String dest, String id);

    public abstract void move(String dest, String[] ids);

    public abstract void move(String dest, String id);

    public abstract void store(String text, String name, String path) throws IOException;

    public abstract void store(MultipartFile file, String path) throws IllegalStateException, IOException;

    public abstract void storeFile(InputStream source, String filename) throws IllegalStateException, IOException;

    public abstract void storeFile(MultipartFile file, String filename) throws IllegalStateException, IOException;

    public abstract void storeFile(File file, String filename) throws IllegalStateException, IOException;

    public abstract void storeFile(List<File> files, List<String> filenames) throws IllegalStateException, IOException;

    public abstract void storeImage(BufferedImage image, String extension, String filename) throws IOException;

    public abstract void storeImages(List<BufferedImage> images, String formatName, List<String> filenames) throws IOException;

    public abstract boolean delete(String[] ids);

    public abstract boolean delete(String id);

    public abstract CommonFile get(String id, String displayPath);

    public abstract InputStream getInputStream(String id);

    public abstract BufferedImage readImage(String id);

    public abstract String getFormatName(String id);

    public abstract List<String> list(String path);

    public abstract List<CommonFile> listFiles(String path, String displayPath);

    public abstract List<CommonFile> listFiles(String search, String path, String displayPath);

    public abstract List<CommonFile> listFiles(CommonFileFilter filter, String path, String displayPath);

}
