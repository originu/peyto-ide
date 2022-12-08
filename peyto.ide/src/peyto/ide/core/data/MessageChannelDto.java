package peyto.ide.core.data;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class MessageChannelDto {

	private long messageChannelId;
	private long applicationId;
	private String messageChannelName;
	private String messageChannelDescription;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;

	public long getMessageChannelId() {
		return messageChannelId;
	}

	public void setMessageChannelId(long messageChannelId) {
		this.messageChannelId = messageChannelId;
	}

	public long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	public String getMessageChannelName() {
		return messageChannelName;
	}

	public void setMessageChannelName(String messageChannelName) {
		this.messageChannelName = messageChannelName;
	}

	public String getMessageChannelDescription() {
		return messageChannelDescription;
	}

	public void setMessageChannelDescription(String messageChannelDescription) {
		this.messageChannelDescription = messageChannelDescription;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}