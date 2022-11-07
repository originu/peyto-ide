package peyto.ide.data;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ResData<T> {

	private boolean result;
	private String resultCode;
	private String resultMessage;
	private T body;
	
	public ResData() {
	}
	
	public ResData(boolean result, String resultCode, String resultMessage, T body) {
		this.result = result;
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
		this.body = body;
	}
	
	public static <T> ResData<T> success(T body) {
		return new ResData<T>(true, "", "", body);
	}

	public static <T> ResData<T> fail(String resultCode, String resultMessage) {
		return new ResData<T>(false, resultCode, resultMessage, null);
	}
	
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	public T getBody() {
		return body;
	}
	public void setBody(T body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
