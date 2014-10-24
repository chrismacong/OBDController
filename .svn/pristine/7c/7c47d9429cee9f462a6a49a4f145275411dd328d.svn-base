package com.fix.obd.jpush.api;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.http.StringUtils;

import com.google.gson.Gson;

/*
 * 通知类型的消息内�?
 */
public class NotifyMessageParams extends MessageParams {
	public class NotifyMsgContent extends MessageParams.MsgContent {
		//不填则默认为 0，使�?极光Push SDK 的默认�?知样式�?
		private int builderId = 0;

		private Map<String, Object> extra = new HashMap<String, Object>();
		
		public int getBuilderId() {
			return builderId;
		}
		public void setBuilderId(int builderId) {
			this.builderId = builderId;
		}
		public Map<String, Object> getExtra() {
			return extra;
		}
		public void setExtra(Map<String, Object> extra) {
			this.extra = extra;
		}
		
		@Override
		public String toString() {
			Gson gson = new Gson();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("n_content", this.getMessage());
			params.put("n_builder_id", String.valueOf(this.getBuilderId()));
			params.put("n_title", this.getTitle());
			params.put("n_extras", this.getExtra());
			return StringUtils.encodeParam(gson.toJson(params));
		}
	}
	
	private NotifyMsgContent msgContent = new NotifyMsgContent();
	public NotifyMsgContent getMsgContent() {
		return this.msgContent;
	}
}
