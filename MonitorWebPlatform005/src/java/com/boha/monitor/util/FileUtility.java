/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.util;

/**
 *
 * @author aubreymalabie
 */
import com.boha.monitor.dto.transfer.RequestDTO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

public class FileUtility {

    private static final Logger logger = Logger.getLogger(FileUtility.class.getName());

    public static File getFile(String data) {
        Random rand = new Random(System.currentTimeMillis());
        //TODO restore after zip testing
        File dir = MonitorProperties.getTemporaryDir();
        File file = new File(dir, "x" + System.currentTimeMillis() + "_" + rand.nextInt(999999999) + ".data");
        try {
            FileUtils.writeStringToFile(file, data);
        } catch (IOException ex) {
            Logger.getLogger(FileUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return file;
    }
    
  
     public static boolean deleteProjectImageFile(Integer companyID, 
             Integer projectID, String fileName) throws Exception {
        File rootDir = MonitorProperties.getImageDir();
        File ggRoot = new File(rootDir, RequestDTO.COMPANY_DIR + companyID);
        File dir = null;

        dir = new File(ggRoot, RequestDTO.PROJECT_DIR + projectID);
        if (!dir.exists()) {
            return false;
        }

        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.getName().equalsIgnoreCase(fileName)) {
                boolean deleted = file.delete();
                return deleted;
            }
            
        }

        logger.log(Level.OFF, "image deleted: {0}", fileName);

        return false;
    }


}
