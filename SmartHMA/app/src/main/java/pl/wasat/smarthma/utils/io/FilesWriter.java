/*
 * Copyright (c) 2016.  SmartHMA ESA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.wasat.smarthma.utils.io;

import android.net.Uri;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import pl.wasat.smarthma.helper.Const;

/**
 * The type Files writer.
 */
public class FilesWriter {

    /**
     * Write to file file.
     *
     * @param strToWrite the str to write
     * @param fileName   the file name
     * @return the file
     */
    public File writeToFile(String strToWrite, String fileName) {
        return writeToFile(strToWrite, fileName, null);
    }

    /**
     * Write to file file.
     *
     * @param strToWrite the str to write
     * @param fileName   the file name
     * @param path       the path
     * @return the file
     */
    public File writeToFile(String strToWrite, String fileName, String path) {

        if (path == null) path = Const.SMARTHMA_PATH_TEMP;
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

    /**
     * Validate dir file.
     *
     * @param path the path
     * @return the file
     */
    public static File validateDir(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            //noinspection ResultOfMethodCallIgnored
            dir.mkdirs();
        }
        return dir;
    }

    /**
     * Append log to file.
     *
     * @param strToAppend the str to append
     * @param fileName    the file name
     */
    public void appendLogToFile(String strToAppend, String fileName) {

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
            buf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write to uri uri.
     *
     * @param strToWrite the str to write
     * @param fileName   the file name
     * @return the uri
     */
    public Uri writeToUri(String strToWrite, String fileName) {
        return Uri.fromFile(writeToFile(strToWrite, fileName));
    }

}
