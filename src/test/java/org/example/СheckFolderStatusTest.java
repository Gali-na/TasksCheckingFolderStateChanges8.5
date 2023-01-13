package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class СheckFolderStatusTest {
    private File file;

    @Test
    void getCurrentConditionFile_FileIsNull_CurrentConditionFileHasNotElements() {
        file = null;
        СheckFolderStatus сheckFolderStatus = new СheckFolderStatus(file);
        assertEquals(0, сheckFolderStatus.getCurrentConditionFile().size());
    }

    @Test
    void getCurrentConditionFile_FileIsNotDirectiry_CurrentConditionFileHasNotElements() {
        file = null;
        СheckFolderStatus сheckFolderStatus = new СheckFolderStatus(file);
        assertEquals(0, сheckFolderStatus.getCurrentConditionFile().size());
    }

    @Test
    void getCurrentConditionFile_FileIsDirectiryWith2Files_GetSize2() {
        file = new File("Directory");
        СheckFolderStatus сheckFolderStatus = new СheckFolderStatus(file);
        сheckFolderStatus.startTrackingFileStatus();
        assertEquals(2, сheckFolderStatus.getCurrentConditionFile().size());
    }

    @Test
    void getNamesFilesFirstConditions_FileIsNull_NamesFilesFirstConditionsHasNotElements() {
        file = null;
        СheckFolderStatus сheckFolderStatus = new СheckFolderStatus(file);
        assertEquals(0, сheckFolderStatus.getNamesFilesFirstConditions().size());
    }

    @Test
    void getNamesFilesFirstConditions_FileIsNotDirectory_NamesFilesFirstConditionsHasNotElements() {
        file = new File("txt");
        СheckFolderStatus сheckFolderStatus = new СheckFolderStatus(file);
        assertEquals(0, сheckFolderStatus.getNameOfFilesThatHaveBeenChanged().size());
    }


    @Test
    void getNamesFilesFirstConditions_FileIsDirectiryWith2Files_GetSize2() {
        file = new File("Directory");
        СheckFolderStatus сheckFolderStatus = new СheckFolderStatus(file);
        сheckFolderStatus.startTrackingFileStatus();
        assertEquals(2, сheckFolderStatus.getNamesFilesFirstConditions().size());
    }

    @Test
    void getNameOfFilesThatHaveBeenChanged() {
        file = new File("Directory");
        СheckFolderStatus сheckFolderStatus = new СheckFolderStatus(file);
        сheckFolderStatus.startTrackingFileStatus();
        File fileForDelet = new File("Directory\\txt2.txt");
        fileForDelet.delete();
        сheckFolderStatus.checkingForDeletedOrAddedFiles();
        assertEquals("[Deleted Files, C:\\Users\\Acer Nitro 5\\IdeaProjects\\TasksCheckingFolderStateChanges8.5\\Directory\\txt2.txt]", сheckFolderStatus.getNameOfFilesThatHaveBeenChanged());
    }

    @Test
    void getTrackingFile() {
        file = new File("Directory");
        СheckFolderStatus сheckFolderStatus = new СheckFolderStatus(file);
        assertEquals(file, сheckFolderStatus.getTrackingFile());
    }

    @Test
    void startTrackingFileStatus_SetFileIsNull_GetEmptyCurrentConditionFileAndnamesFilesFirstConditions() {
        file = null;
        СheckFolderStatus сheckFolderStatus = new СheckFolderStatus(file);
        сheckFolderStatus.startTrackingFileStatus();
        assertEquals(0, сheckFolderStatus.getCurrentConditionFile().size());
        assertEquals(0, сheckFolderStatus.getNamesFilesFirstConditions().size());
    }

    @Test
    void startTrackingFileStatus_SetFileIsNotDirectory_GetFalse() {
        file = new File("txt.txt");
        СheckFolderStatus сheckFolderStatus = new СheckFolderStatus(file);
        assertEquals(false, сheckFolderStatus.startTrackingFileStatus());
    }

    @Test
    void startTrackingFileStatus_SetFileIsNull_GetFalse() {
        file = null;
        СheckFolderStatus сheckFolderStatus = new СheckFolderStatus(file);
        assertEquals(false, сheckFolderStatus.startTrackingFileStatus());
    }

    @Test
    void startTrackingFileStatus_SetDirectoryWith2Files_GetTrue() {
        file = new File("Directory");
        СheckFolderStatus сheckFolderStatus = new СheckFolderStatus(file);
        assertEquals(true, сheckFolderStatus.startTrackingFileStatus());
    }

    @Test
    void startTrackingFileStatus_SetDirectoryWith2Files_GetCurrentConditionFileEquels2AndNamesFilesFirstConditionsEguels2() {
        file = new File("Directory");
        СheckFolderStatus сheckFolderStatus = new СheckFolderStatus(file);
        сheckFolderStatus.startTrackingFileStatus();
        assertEquals(2, сheckFolderStatus.getCurrentConditionFile().size());
        assertEquals(2, сheckFolderStatus.getNamesFilesFirstConditions().size());
    }

    @Test
    void startTrackingFileStatus_SetDirectory_GetEmptyCurrentConditionFileAndnamesFilesFirstConditions() {
        file = new File("txt.txt");
        СheckFolderStatus сheckFolderStatus = new СheckFolderStatus(file);
        сheckFolderStatus.startTrackingFileStatus();
        assertEquals(0, сheckFolderStatus.getCurrentConditionFile().size());
        assertEquals(0, сheckFolderStatus.getNamesFilesFirstConditions().size());
    }

    @Test
    void checkDataExistsInFolder_SetFileIsNull_GetEmptyCurrentConditionFile() {
        file = null;
        СheckFolderStatus сheckFolderStatus = new СheckFolderStatus(file);
        сheckFolderStatus.checkDataExistsInFolder(file);
        assertEquals(0, сheckFolderStatus.getCurrentConditionFile().size());
    }

    @Test
    void checkDataExistsInFolder_SetFileIsNotDirectory_GetEmptyCurrentConditionFile() {
        file = new File("txt.txt");
        СheckFolderStatus сheckFolderStatus = new СheckFolderStatus(file);
        сheckFolderStatus.checkDataExistsInFolder(file);
        assertEquals(0, сheckFolderStatus.getCurrentConditionFile().size());
    }

    @Test
    void checkDataExistsInFolder_SetDirectotyWithTwoFiles_GetCurrentConditionFileWithSizeEguals2() {
        file = new File("Directory");
        СheckFolderStatus сheckFolderStatus = new СheckFolderStatus(file);
        сheckFolderStatus.checkDataExistsInFolder(file);
        assertEquals(2, сheckFolderStatus.getCurrentConditionFile().size());
    }

    @Test
    void checkingForDeletedOrAddedFiles() {
        file = new File("Directory");
        СheckFolderStatus сheckFolderStatus = new СheckFolderStatus(file);
        сheckFolderStatus.startTrackingFileStatus();
        File fileForDelet = new File("Directory\\txt2.txt");
        fileForDelet.delete();
        сheckFolderStatus.checkingForDeletedOrAddedFiles();
        assertEquals(true, сheckFolderStatus.getNameOfFilesThatHaveBeenChanged().contains("C:\\Users\\Acer Nitro 5\\IdeaProjects\\TasksCheckingFolderStateChanges8.5\\Directory\\txt2.txt"));
    }
}