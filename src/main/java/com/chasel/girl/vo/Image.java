package com.chasel.girl.vo;

import java.io.Serializable;

public class Image extends Base implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int typeId;
	private String type;
	private String name;
	private double width;
	private double height;
	private String objectName;
	private String memo;
	private double textHeight;
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
	
	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getTextHeight() {
		return textHeight;
	}

	public void setTextHeight(double textHeight) {
		this.textHeight = textHeight;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", typeId=" + typeId + ", type=" + type + ", name=" + name + ", width=" + width
				+ ", height=" + height + ", objectName=" + objectName + ", memo=" + memo + ", textHeight=" + textHeight
				+ ", url=" + url + "]";
	}

}
