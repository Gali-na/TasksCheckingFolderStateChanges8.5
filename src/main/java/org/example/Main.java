package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main {
    public static void main(String[] args) {

        File file = new File("C:\\Users\\Acer Nitro 5\\Desktop\\English text");
        СheckFolderStatus сheckFolderStatus = new СheckFolderStatus(file);
        сheckFolderStatus.startTrackingFileStatus();
        Thread thread = new Thread(сheckFolderStatus);
        thread.start();
    }


}
