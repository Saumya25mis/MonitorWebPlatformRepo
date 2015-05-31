package com.boha.monitor.util;

import java.io.File;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

/**
 *
 * @author aubreyM
 */
@Stateless
public class HouseKeepingScheduler {

    @Schedule(minute = "*/30", hour = "*")
    public void cleanUp() {
        startDiskCleanup();
    }


    
    private void startDiskCleanup() {
        
        log.log(Level.INFO, "### Monitor Platform checking for expired temporary files");
        int count = 0;
        File dir = MonitorProperties.getTemporaryDir();
        if (dir.exists()) {
            File[] files = dir.listFiles();
            log.log(Level.INFO, "### startDiskCleanup - temporary files found: {0}", files.length);
            for (File file : files) {
                long now = new Date().getTime();
                long cutOff = now - FIVE_MINUTES;
                if (file.lastModified() < cutOff) {
                    printMessageHead();
                    log.log(Level.INFO, "### startDiskCleanup - deleting temporary file dated: {0}", 
                            new Date(file.lastModified()));
                    boolean OK = file.delete();
                    if (OK) {
                        count++;
                    }
                }
            }
        }

        log.log(Level.INFO, "### Monitor HouseKeeping cleaned up {0} temporary files", count);
        try {
            if (count > 10) {
                //platformUtil.addErrorStore(PlatformUtil.SIGNIFICANT_EVENT,
                  //      "MGGolf temporary files cleaned up: " + count, "HouseKeeper");
            }
        } catch (Exception e) {

        }
    }
    private void printMessageHead() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n\n### ##########################################################################\n");
        sb.append("### SmartCity Disk Cleanup STARTED: ").append(new Date()).append("\n");
        sb.append("### ##########################################################################\n\n");
        log.log(Level.INFO, sb.toString());
    }
    private final static int FIVE_MINUTES = 1000 * 60 * 5;
    static final Logger log = Logger.getLogger(HouseKeepingScheduler.class.getName());
}
