package gov.guilin.service;

/**
 * 发送短信
 * 
 * @author guilin
 * 
 */
public interface SmsService {

	String send(String mobile, String content, boolean async);

	void sendSmsAndChangeOrderStatusBatch();

}
