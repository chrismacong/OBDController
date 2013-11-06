package com.fix.obd.util;

import java.util.HashMap;

import com.fix.obd.tcp.thread.UploadTerminalDataTask;

public class ThreadMap {
	public static HashMap<String, UploadTerminalDataTask> threadNameMap = new HashMap<String, UploadTerminalDataTask>();
}
