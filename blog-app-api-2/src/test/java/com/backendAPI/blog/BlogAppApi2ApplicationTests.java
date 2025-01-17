0package com.backendAPI.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.backendAPI.blog.repositories.UserRepo;

@SpringBootTest
class BlogAppApi2ApplicationTests {

	 @Autowired
	 private UserRepo userRepo;
	 
	@Test
	void contextLoads() {
	}
	
	@Test
	public void repoTest() {
		
		String className = this.userRepo.getClass().getName();
		String packageName = this.userRepo.getClass().getPackageName();
		
		System.out.println(className);
		System.out.println(packageName);
		
	}

}
