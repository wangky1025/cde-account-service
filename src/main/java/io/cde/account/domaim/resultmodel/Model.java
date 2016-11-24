package io.cde.account.domaim.resultmodel;

/**
 * @author lcl
 * @createDate 2016年11月14日上午11:06:17
 * 返回数据的模型实体
 */
public class Model {
	/**
	 * 返回结果对象
	 */
	private Object result;
	/**
	 * 返回错误对象
	 */
	private Error error;
	
	public Object getResult() {
		return result;
	}
	
	public void setResult(Object result) {
		this.result = result;
	}
	
	public Error getError() {
		return error;
	}
	
	public void setError(Error error) {
		this.error = error;
	}
}
