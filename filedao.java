package com.cloudstorage.dao;

import com.cloudstorage.model.FileModel;
import java.util.List;

public interface FileDAO {
    int saveFile(FileModel file);
    FileModel getFileById(int fileId, int userId);  // Updated to accept userId
    List<FileModel> getFilesByUser(int userId);
    boolean updateFile(FileModel file);
    boolean deleteFile(int fileId);
    boolean setFilePrivacy(int fileId, boolean isPrivate);
}
