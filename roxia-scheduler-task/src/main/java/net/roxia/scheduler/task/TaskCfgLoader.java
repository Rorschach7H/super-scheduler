package net.roxia.scheduler.task;

import net.roxia.scheduler.SchedulerConfig;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: TaskCfgLoader
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-13 11:07:31
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-13    meixiaoxi       v1.0.0           创建
 */
public class TaskCfgLoader {

    public static SchedulerConfig load(String cfgPath, String log4jPath) {

        Properties conf = new Properties();
        File file = new File(cfgPath);

        try {
            conf.load(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new CfgException("can not find " + cfgPath);
        } catch (IOException e) {
            throw new CfgException("Read " + cfgPath + " error.", e);
        }
        if (new File(log4jPath).exists()) {
            //  log4j 配置文件路径
            PropertyConfigurator.configure(log4jPath);
        }
        return buildSchedulerConfig(conf);
    }

    public static SchedulerConfig buildSchedulerConfig(Properties properties) {
        SchedulerConfig config = new SchedulerConfig();
        for (Object key : properties.keySet()) {
            config.put(key, properties.getProperty(key.toString()));
        }
        return config;
    }
}
