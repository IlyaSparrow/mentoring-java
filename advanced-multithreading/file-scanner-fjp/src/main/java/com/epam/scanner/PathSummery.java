package com.epam.scanner;

import java.util.Objects;

public class PathSummery {

    private long folderCount = -1;
    private long fileCount;
    private long hiddenFiles;
    private long notAccessible;
    private long allPaths;
    private Long size;

    public long getFolderCount() {
        return folderCount;
    }

    public void setFolderCount(final long folderCount) {
        this.folderCount = folderCount;
    }

    public long getFileCount() {
        return fileCount;
    }

    public void setFileCount(final long fileCount) {
        this.fileCount = fileCount;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(final Long size) {
        this.size = size;
    }

    public void increaseFolders() {
        folderCount++;
    }

    public void increaseFiles() {
        fileCount++;
    }

    public void increaseSize(final Long adjustSize) {
        if (Objects.isNull(size)) {
            this.size = 0L;
        }
        this.size += adjustSize;
    }

    public void increaseHiddenFiles() {
        hiddenFiles++;
    }

    public void increaseNotAccessible() {
        notAccessible++;
    }

    public long getHiddenFiles() {
        return hiddenFiles;
    }

    public void setHiddenFiles(long hiddenFiles) {
        this.hiddenFiles = hiddenFiles;
    }

    public long getNotAccessible() {
        return notAccessible;
    }

    public void setNotAccessible(long notAccessible) {
        this.notAccessible = notAccessible;
    }

    public long getAllPaths() {
        return folderCount + fileCount + hiddenFiles + notAccessible;
    }

    public void setAllPaths(long allPaths) {
        this.allPaths = allPaths;
    }
}
