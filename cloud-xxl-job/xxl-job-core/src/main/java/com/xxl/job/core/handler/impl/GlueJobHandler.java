package com.xxl.job.core.handler.impl;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.IJobHandler;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * glue job handler
 *
 * @author xuxueli 2016-5-19 21:05:45
 */
public class GlueJobHandler extends IJobHandler {

	private long glueUpdatetime;
	private IJobHandler jobHandler;
	public GlueJobHandler(IJobHandler jobHandler, long glueUpdatetime) {
		this.jobHandler = jobHandler;
		this.glueUpdatetime = glueUpdatetime;
	}
	public long getGlueUpdatetime() {
		return glueUpdatetime;
	}

	@Override
	public void execute() throws Exception {
		XxlJobHelper.log("----------- glue.version:"+ glueUpdatetime +" -----------");
		var zhFormatter = DateTimeFormatter.ofPattern("yyyy MMM dd EE HH:mm:ss", Locale.CHINA);
		System.out.println(zhFormatter.format(ZonedDateTime.now()) + Thread.currentThread().getName() + "=============GlueJobHandler  start ===========");

		jobHandler.execute();

		System.out.println(zhFormatter.format(ZonedDateTime.now()) + Thread.currentThread().getName() + "=============GlueJobHandler  start ===========");
	}

}
