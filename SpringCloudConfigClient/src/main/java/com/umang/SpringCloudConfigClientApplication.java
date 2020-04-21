package com.umang;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringCloudConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigClientApplication.class, args);
	}
	
	public void setEnv(Environment e) {
		System.out.println(e.getProperty("msg"));
	}

}

@RefreshScope
@RestController
class MessageRestController{
	
	@Value("${msg:Config server is not working. Please check}")
	private String msg;
	
	@GetMapping("/msg")
	public String getMsg() {
		return this.msg;
	}
	
}