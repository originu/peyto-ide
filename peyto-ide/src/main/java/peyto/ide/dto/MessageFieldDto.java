package peyto.ide.dto;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class MessageFieldDto {

    private long messageFieldId;
    private long messageId;
    private int messageFieldType;		// 100: Request, 200: Response
    private int messageFieldOrder;
    private int messageFieldDepth;
    private String messageFieldName;
    private String messageFieldDescription;
    private String messageFieldDataType;
    private long messageFieldLength;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;

    public long getMessageFieldId() {
        return messageFieldId;
    }

    public void setMessageFieldId(long messageFieldId) {
        this.messageFieldId = messageFieldId;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public int getMessageFieldOrder() {
        return messageFieldOrder;
    }

	public int getMessageFieldType() {
		return messageFieldType;
	}

	public void setMessageFieldType(int messageFieldType) {
		this.messageFieldType = messageFieldType;
	}
	
    public void setMessageFieldOrder(int messageFieldOrder) {
        this.messageFieldOrder = messageFieldOrder;
    }

	public int getMessageFieldDepth() {
		return messageFieldDepth;
	}

	public void setMessageFieldDepth(int messageFieldDepth) {
		this.messageFieldDepth = messageFieldDepth;
	}

    public String getMessageFieldName() {
        return messageFieldName;
    }

    public void setMessageFieldName(String messageFieldName) {
        this.messageFieldName = messageFieldName;
    }

    public String getMessageFieldDescription() {
        return messageFieldDescription;
    }

    public void setMessageFieldDescription(String messageFieldDescription) {
        this.messageFieldDescription = messageFieldDescription;
    }

    public String getMessageFieldDataType() {
        return messageFieldDataType;
    }

    public void setMessageFieldDataType(String messageFieldDataType) {
        this.messageFieldDataType = messageFieldDataType;
    }

    public long getMessageFieldLength() {
        return messageFieldLength;
    }

    public void setMessageFieldLength(long messageFieldLength) {
        this.messageFieldLength = messageFieldLength;
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
