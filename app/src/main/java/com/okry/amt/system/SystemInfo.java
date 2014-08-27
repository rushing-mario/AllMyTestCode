package com.okry.amt.system;

import android.os.Build;

import java.io.*;
import java.util.regex.Pattern;

/**
 * Created by MR on 2014/4/21.
 */
public class SystemInfo {

    /**
     * 获取手机CPU信息
     *
     * @return
     */
    public static String[] getCpuInfo()
    {
        String str1 = "/proc/cpuinfo";
        String str2 = "";
        String[] cpuInfo = { "", "" };
        String[] arrayOfString;
        try
        {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            for (int i = 2; i < arrayOfString.length; i++)
            {
                cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
            }
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            cpuInfo[1] += arrayOfString[2];
            localBufferedReader.close();
        }
        catch (IOException e)
        {
        }
        return cpuInfo;
    }

    /**
     * 获取CPU核心数
     *
     * @return
     */
    public static int getNumCores()
    {
        // Private Class to display only CPU devices in the directory listing
        class CpuFilter implements FileFilter
        {
            @Override
            public boolean accept(File pathname)
            {
                // Check if filename is "cpu", followed by a single digit number
                if (Pattern.matches("cpu[0-9]", pathname.getName()))
                {
                    return true;
                }
                return false;
            }
        }
        try
        {
            // Get directory containing CPU info
            File dir = new File("/sys/devices/system/cpu/");
            // Filter to only list the devices we care about
            File[] files = dir.listFiles(new CpuFilter());
            // Return the number of cores (virtual CPU devices)
            return files.length;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            // Default to return 1 core
            return 1;
        }
    }

    public static String getCpuABI(){
        return Build.CPU_ABI;
    }
}
