package org.example;

import java.io.File;

public class СheckFolderExists {
    public static Boolean folderCheck(File file) {
        if (file == null) {
            return false;
        }
        if (!file.isDirectory()) {
            return false;
        }
        return true;
    }
}
