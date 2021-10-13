package com.epam.scanner;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.RecursiveAction;

public class PathScanner extends RecursiveAction implements Callable<Long> {

    private final Path path;
    private final PathSummery pathSummery;

    public PathScanner(final Path path) {
        this.path = path;
        this.pathSummery = new PathSummery();
    }

    public PathScanner(final Path path, final PathSummery pathSummery) {
        this.path = path;
        this.pathSummery = pathSummery;
    }

    @Override
    protected void compute() {
        if (!Files.isReadable(path)) {
            pathSummery.increaseNotAccessible();
            return;
        }
        if (Files.isDirectory(path)) {
            asyncDirectoryAnalyzer(path);
        } else {
            executeFileInfo(path);
        }
    }

    private void asyncDirectoryAnalyzer(final Path path) {
        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(path)) {
            pathSummery.increaseFolders();
            List<PathScanner> pathScannerList = new ArrayList<>();
            for (Path currentPath : dirStream) {
                pathScannerList.add(new PathScanner(currentPath, pathSummery));
            }
            invokeAll(pathScannerList);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void executeFileInfo(final Path path) {
        try {
            if (Files.isHidden(path)) {
                pathSummery.increaseHiddenFiles();
            } else {
                pathSummery.increaseFiles();
            }
            pathSummery.increaseSize(Files.size(path));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public PathSummery getPathSummery() {
        return pathSummery;
    }

    @Override
    public Long call() throws Exception {
        return pathSummery.getAllPaths();
    }
}
