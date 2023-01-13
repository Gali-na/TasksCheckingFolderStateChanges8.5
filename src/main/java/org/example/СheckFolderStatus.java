package org.example;
import java.io.File;
import java.util.ArrayList;
public class СheckFolderStatus implements Runnable {
    private ArrayList<String> namesFilesFirstConditions;
    private ArrayList<String> currentConditionFile;
    private ArrayList<String> nameOfFilesThatHaveBeenChanged;
    private File trackingFile;

    public СheckFolderStatus(File file) {
        this.namesFilesFirstConditions = new ArrayList<String>();
        this.currentConditionFile = new ArrayList<String>();
        this.nameOfFilesThatHaveBeenChanged = new ArrayList<>();
        this.trackingFile = file;
    }

    public ArrayList<String> getCurrentConditionFile() {
        return currentConditionFile;
    }

    public ArrayList<String> getNamesFilesFirstConditions() {
        return namesFilesFirstConditions;
    }

    public ArrayList<String> getNameOfFilesThatHaveBeenChanged() {
        return nameOfFilesThatHaveBeenChanged;
    }

    public File getTrackingFile() {
        return trackingFile;
    }

    public boolean startTrackingFileStatus() {
        checkDataExistsInFolder(this.trackingFile);
        namesFilesFirstConditions.addAll(currentConditionFile);
        if ((namesFilesFirstConditions.size() != 0) && (currentConditionFile.size() != 0) && (namesFilesFirstConditions.equals(currentConditionFile))) {
            return true;
        }
        return false;
    }

    public void checkDataExistsInFolder(File file) {
        if (СheckFolderExists.folderCheck(file)) {
            File[] listOfFile = file.listFiles();
            for (int i = 0; i < listOfFile.length; i++) {
                if (listOfFile[i].isFile())
                    currentConditionFile.add(listOfFile[i].getAbsolutePath());
                if (listOfFile[i].isDirectory()) {
                    checkDataExistsInFolder(listOfFile[i]);
                }
            }
        }
    }

    public void checkingForDeletedOrAddedFiles() {
        nameOfFilesThatHaveBeenChanged.add("New Files");
        File file = new File(String.valueOf(this.trackingFile));
        currentConditionFile = new ArrayList<>();
        nameOfFilesThatHaveBeenChanged = new ArrayList<>();
        checkDataExistsInFolder(this.trackingFile);
        for (String name : this.currentConditionFile) {
            if (!this.namesFilesFirstConditions.contains(name)) {
                nameOfFilesThatHaveBeenChanged.add(name);
            }
        }
        nameOfFilesThatHaveBeenChanged.add("Deleted Files");
        for (String name : this.namesFilesFirstConditions) {
            if (!this.currentConditionFile.contains(name)) {
                nameOfFilesThatHaveBeenChanged.add(name);
            }
        }
        namesFilesFirstConditions = currentConditionFile;
        currentConditionFile = new ArrayList<>();
        System.out.println(nameOfFilesThatHaveBeenChanged);

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            checkingForDeletedOrAddedFiles();
        }
    }
}
