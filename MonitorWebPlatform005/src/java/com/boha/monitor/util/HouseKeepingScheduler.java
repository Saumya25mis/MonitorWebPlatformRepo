package com.boha.monitor.util;

import java.io.File;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;

/**
 *
 * @author aubreyM
 */
@Stateless
@Singleton
@Startup
public class HouseKeepingScheduler {

    @Schedule(minute = "*/30", hour = "*")
    public void cleanUp() {
        startDiskCleanup();
    }


    /**
     * Delete temporary files created by the zip process when compressing responses
     */
    private void startDiskCleanup() {
        
        //printMessageHead();
        int count = 0;
        File dir = MonitorProperties.getTemporaryDir();
        if (dir.exists()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                long now = new Date().getTime();
                long cutOff = now - ONE_MINUTE;
                if (file.lastModified() < cutOff) {
                    if (file.delete()) {
                        count++;
                    }
                }
            }
        }

        log.log(Level.INFO, "### Monitor HouseKeeping cleaned up {0} temporary files", count);
        
    }
    private void printMessageHead() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n\n### ##########################################################################\n");
        sb.append("### Monitor Disk Cleanup STARTED: ").append(new Date()).append("\n");
        sb.append("### ##########################################################################\n\n");
        log.log(Level.INFO, sb.toString());
    }
    private final static int ONE_MINUTE = 1000 * 60;
    static final Logger log = Logger.getLogger(HouseKeepingScheduler.class.getName());
//    @Resource
//    TimerService ts;
//    
//
//    //@Schedule(hour = "*", minute = "*", second = "*/10", info = "Every 10 second timer")
//    public void printSchedule() {
//        log.log(Level.OFF, "ProgrammaticTimer Schedule Fired .... ");
//        ts.createTimer(5000, null);
//    }
//    
//    @Timeout
//    public void timeOut() {
//        log.log(Level.OFF, "Programmatic timeout fired ");
//    }
}
