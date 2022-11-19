package peyto.ide.data;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UpdateApplicationData {

    private long applicatoinId;

    private String applicationName;
    private String applicationDescription;

    public long getApplicatoinId() {
        return applicatoinId;
    }

    public void setApplicatoinId(long applicatoinId) {
        this.applicatoinId = applicatoinId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationDescription() {
        return applicationDescription;
    }

    public void setApplicationDescription(String applicationDescription) {
        this.applicationDescription = applicationDescription;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
