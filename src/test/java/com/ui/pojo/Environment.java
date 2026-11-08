package com.ui.pojo;

public class Environment {
     private String url;
     private int MAX_NUMBER_ATTEMPTS;

	public int getMAX_NUMBER_ATTEMPTS() {
		return MAX_NUMBER_ATTEMPTS;
	}

	public void setMAX_NUMBER_ATTEMPTS(int mAX_NUMBER_ATTEMPTS) {
		MAX_NUMBER_ATTEMPTS = mAX_NUMBER_ATTEMPTS;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
