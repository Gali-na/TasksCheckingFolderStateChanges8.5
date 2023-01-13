package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class СheckFolderExistsTest {
    @Test
    void folderCheck_FileIsNull_GetFalse() {
        File file = null;
        assertEquals(false, СheckFolderExists.folderCheck(file));
    }

    @Test
    void folderCheck_FileIsNotDirectory_GetFalse() {
        File file = new File("txt.txt");
        assertEquals(false, СheckFolderExists.folderCheck(file));
    }

    @Test
    void folderCheck_FileIsDirectoryIsNotNull_GetTrue() {
        File file = new File("C:\\Users\\Acer Nitro 5\\Desktop\\New English text");
        assertEquals(true, СheckFolderExists.folderCheck(file));
    }
}
