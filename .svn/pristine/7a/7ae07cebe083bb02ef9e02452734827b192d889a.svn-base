package com.chasel.girl.vo;

import java.io.Serializable;

public class Image extends Base implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String type;
	private String name;
	private String objectName;
	private String memo;
	private String url;
	
	public Image() {
	}

	public Image(int id, String type, String memo) {
		super();
		this.id = id;
		this.type = type;
		this.memo = memo;
	}
	
	public Image(int id, String type, String name, String memo) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.memo = memo;
	}
	
	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getMemo() {
		return memo;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", type=" + type + ", name=" + name + ", objectName=" + objectName + ", memo=" + memo
				+ ", url=" + url + "]";
	}

}
