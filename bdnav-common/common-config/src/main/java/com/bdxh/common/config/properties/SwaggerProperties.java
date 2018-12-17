package com.bdxh.common.config.properties;

import lombok.Data;

@Data
public class SwaggerProperties {

	private String title;

	private String description;

	private String version = "1.0";

	private String license = "Apache License 2.0";

	private String licenseUrl = "http://www.apache.org/licenses/LICENSE-2.0";

	private String contactName = "北斗星航";

	private String contactUrl = "https://github.com/";

	private String contactEmail = "532097775@qq.com";
}
