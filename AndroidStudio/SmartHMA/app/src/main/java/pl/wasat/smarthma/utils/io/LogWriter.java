package pl.wasat.smarthma.utils.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LogWriter {

    private static final String LOG_PATH = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/SMARTHMA_LOGS";

    public void writeToFile(String strToWrite, String fileName) {

        //File root = android.os.Environment.getExternalStorageDirectory();

        File dir = new File(LOG_PATH);
        //noinspection ResultOfMethodCallIgnored
        dir.mkdirs();
        File file = new File(dir, fileName);

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
    }

    public void appendToFile(String strToAppend, String fileName) {

        File logFile = new File(LOG_PATH, fileName);
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

}
