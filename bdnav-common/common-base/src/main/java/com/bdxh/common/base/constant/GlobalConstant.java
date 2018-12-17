package com.bdxh.common.base.constant;

/**
 * @description: 全局常量
 * @author: xuyuan
 * @create: 2018-12-17 14:25
 **/
public class GlobalConstant {

	public static final int M_SIZE = 1024;
	public static final long FILE_MAX_SIZE = 5 * 1024 * 1024;
	public static final String UNKNOWN = "unknown";

	public static final String X_FORWARDED_FOR = "X-Forwarded-For";
	public static final String X_REAL_IP = "X-Real-IP";
	public static final String PROXY_CLIENT_IP = "Proxy-Client-IP";
	public static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
	public static final String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
	public static final String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";

	public static final String LOCALHOST_IP = "127.0.0.1";
	public static final String LOCALHOST_IP_16 = "0:0:0:0:0:0:0:1";
	public static final int MAX_IP_LENGTH = 15;

	public static final String DEV_PROFILE = "dev";
	public static final String TEST_PROFILE = "test";
	public static final String PRO_PROFILE = "pro";

	public static final String ROOT_PREFIX = "bdnav";

	public static final int EXCEPTION_CAUSE_MAX_LENGTH = 2048;
	public static final int EXCEPTION_MESSAGE_MAX_LENGTH = 2048;

	public interface Number {
		int THOUSAND_INT = 1000;
		int HUNDRED_INT = 100;
		int ONE_INT = 1;
		int TWO_INT = 2;
		int THREE_INT = 3;
		int FOUR_INT = 4;
		int FIVE_INT = 5;
		int SIX_INT = 6;
		int SEVEN_INT = 7;
		int EIGHT_INT = 8;
		int NINE_INT = 9;
		int TEN_INT = 10;
		int SIXTEEN_INT = 16;
	}


	public static final class Symbol {

		private Symbol() {
		}

		public static final String COMMA = ",";
		public static final String SPOT = ".";
		public final static String UNDER_LINE = "_";
		public final static String PER_CENT = "%";
		public final static String AT = "@";
		public final static String PIPE = "||";
		public final static String SHORT_LINE = "-";
		public final static String SPACE = " ";
		public static final String SLASH = "/";
		public static final String MH = ":";

	}

	public enum PaymentTypeEnum {

		ONLINE_PAY(1, "在线支付");

		PaymentTypeEnum(int code, String value) {
			this.code = code;
			this.value = value;
		}

		private String value;
		private int code;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public static PaymentTypeEnum getEnumByCode(int code) {
			PaymentTypeEnum paymentTypeEnum=null;
			for (PaymentTypeEnum value : values()) {
				if (value.getCode() == code) {
					paymentTypeEnum=value;
					break;
				}
			}
			return paymentTypeEnum;
		}
	}
}
