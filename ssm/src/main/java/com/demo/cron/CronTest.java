package com.demo.cron;

import cn.hutool.core.lang.Console;
import cn.hutool.cron.CronUtil;

public class CronTest {

	//如果同步时间修改，则清空定时任务，同时加入新的
	//每天执行一次：0 0 0 */1 * ?
	//每1个小时执行一次：0 0 */1 * * ?
	//每1分钟执行一次：0 0/1 * * * ?
	public static void main(String[] args) {
		CronUtil.setMatchSecond(true);
		CronUtil.start(false);
		CronUtil.getScheduler().clear();
		String id = CronUtil.schedule("0 0/1 * * * ?", new Runnable() {
			public void run() {
				Console.log("task running : 1mins");
			}
		});
	}
}
