package pl.wasat.smarthma.utils.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import pl.wasat.smarthma.helper.Const;

public class FilesWriter {

    public File writeToFile(String strToWrite, String fileName, String path) {

        if (path == null) path = Const.SMARTHMA_PATH_TEMP;

/*        File dir = new File(path);
        if (!dir.exists()) {
            //noinspection ResultOfMethodCallIgnored
            dir.mkdirs();
        }*/
        File file = new File(validateDir(path), fileName);

        try {
            FileOutputStream f = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(f);
            pw.println(strToWrite);
            pw.flush();
            pw.close();
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public void appendLogToFile(String strToAppend, String fileName) {

        //File dir = new File(Const.SMARTHMA_PATH_LOGS);
        //noinspection ResultOfMethodCallIgnored
        //dir.mkdirs();

        File logFile = new File(validateDir(Const.SMARTHMA_PATH_LOGS), fileName);
        if (!logFile.exists()) {
            try {
                //noinspection ResultOfMethodCallIgnored
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            // BufferedWriter for performance, true to set append to file flag
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile,
                    true));
            buf.append(strToAppend);
            //buf.newLine();
            buf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File validateDir(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            //noinspection ResultOfMethodCallIgnored
            dir.mkdirs();
        }
        return dir;
    }

}
