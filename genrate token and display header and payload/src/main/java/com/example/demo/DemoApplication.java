package com.example.demo;

import java.sql.Date;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		Map<String, Object> claims = new HashMap<String, Object>();
		String genaratedJwt = createToken(claims, "Alice");
		System.out.println("Generated Jwt:-" + genaratedJwt);
		displayHeaderAndPayload(genaratedJwt);
	
		
	}
	private static String createToken(Map<String, Object> claims, String subject) {
		   return Jwts.builder().setSubject(subject).
		          setIssuedAt(new Date(System.currentTimeMillis()))
		         .setExpiration(new Date(System.currentTimeMillis() + 1800_000))
		         .signWith(SignatureAlgorithm.HS256, "S3cr3t").compact();
		}
		
	public static void displayHeaderAndPayload(String token)
	{
Base64.Decoder decoder=Base64.getUrlDecoder();
		
		// it is byte form we have to convert to string to readable
		//for the header
		String[] data=token.split("\\.");
		byte[] bytes=decoder.decode(data[0]);
		String str=new String(bytes);  
		System.out.println(str);
		//for payload
		byte[] bytes1=decoder.decode(data[1]);
		String str1=new String(bytes1);  
		System.out.println(str1);
	}

	}

		

	


