package com.epam.scanner;

import me.tongfei.progressbar.ProgressBar;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class Application {

    public static void main(String... args) {
        System.out.println("Enter directory or file path: ");

        Scanner sc = new Scanner(System.in);

        final String path = sc.nextLine();

        Path rootPath = Paths.get(path);

        ForkJoinPool pool = new ForkJoinPool();
        PathScanner pathScanner = new PathScanner(rootPath);
        pool.invoke(pathScanner);

        final PathSummery pathSummery = pathScanner.getPathSummery();
        System.out.println("Files: " + pathSummery.getFileCount());
        System.out.println("Folders: " + pathSummery.getFolderCount());
        System.out.println("Hidden files: " + pathSummery.getHiddenFiles());
        System.out.println("Not accessible paths: " + pathSummery.getNotAccessible());
        System.out.println("Size: " + pathSummery.getSize());
    }
}
