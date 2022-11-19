package peyto.ide.data;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AddMessageFieldData {

    private long messageId;
    private int messageFieldOrder;
    private String messageFieldName;
    private String messageFieldDescription;
    private String messageFieldDataType;
    private long messageFieldLength;

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public int getMessageFieldOrder() {
        return messageFieldOrder;
    }

    public void setMessageFieldOrder(int messageFieldOrder) {
        this.messageFieldOrder = messageFieldOrder;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
