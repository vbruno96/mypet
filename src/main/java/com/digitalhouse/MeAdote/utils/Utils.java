package com.digitalhouse.MeAdote.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

public class Utils {
	public static String uploadImage(MultipartFile image) throws IOException {	
		
		String clientId = System.getenv("IMGURID");
		String clientSecret = System.getenv("IMGURSECRET");
		
		if (clientId == null || clientSecret == null) {
			throw new RuntimeException("Variáveis de ambiente não estão configuradas corretamente");
		}
		
		CloseableHttpClient client = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost("https://api.imgur.com/3/upload.json");
	    
	    List<NameValuePair> params = new ArrayList<NameValuePair>();
	    
	    params.add(new BasicNameValuePair("key", clientSecret));
	    params.add(new BasicNameValuePair("type", "base64"));
	    params.add(new BasicNameValuePair("name", image.getOriginalFilename()));
	    params.add(new BasicNameValuePair("title", image.getName()));
	    params.add(new BasicNameValuePair("image", Base64.getEncoder().encodeToString(image.getBytes())));
	    
	    httpPost.setEntity(new UrlEncodedFormEntity(params));
	    httpPost.setHeader("Authorization", "Client-ID " + clientId);
	    
	    CloseableHttpResponse response = client.execute(httpPost);
	    
		String responseBody = EntityUtils.toString(response.getEntity());
		String imageUrl = responseBody.split("\"link\":\"")[1].split("\",")[0].split("\"}")[0];
		
		return imageUrl;
	}
}
