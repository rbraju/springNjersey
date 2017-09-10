package com.twitter.dao;

import java.util.Base64;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twitter.dto.BearerToken;
import com.twitter.dto.Tweet;
import com.twitter.util.JerseyClientFactory;

@Service
public class TweetDAO {

	@Value("${endpoint}")
	private String endpoint;

	@Value("${oauth.path}")
	private String oauthPath;

	@Value("${timeline.path}")
	private String timelinePath;

	@Value("${consumer.key}")
	private String consumerKey;

	@Value("${consumer.secret}")
	private String consumerSecret;

	private Client client;

	public String test() {
		System.out.println("Endpoint  : " + endpoint);
		System.out.println("timeline  : " + timelinePath);
		return "{ \"text\": \"test response\"}";
	}

	/**
	 * Retrieve tweets from user timeline
	 * 
	 * @param screenName
	 * @return - List of tweets
	 */
	public List<Tweet> getTweetsByScreenName(String screenName) {
		client = JerseyClientFactory.getClient();
		List<Tweet> tweets = null;
		try {
			String bearerToken = getBearerToken();

			// Retrieve tweets from user timeline using screenName
			Response response = client.target(endpoint).path(timelinePath).queryParam("screen_name", screenName)
					.queryParam("exclude_replies", true).request(MediaType.APPLICATION_JSON)
					.header("Authorization", "Bearer " + bearerToken).get();
			tweets = response.readEntity(new GenericType<List<Tweet>>() {
			});
		} catch (Exception e) {
			System.out.println("Error occurred while retrieving tweets for : " + screenName);
			e.printStackTrace();
		}
		return tweets;
	}

	/**
	 * Generate and return the access token based on the given key and secret
	 * 
	 * @param consumerKey
	 * @param secret
	 * @return Access token
	 */
	private String getBearerToken() {
		String credentials = consumerKey + ":" + consumerSecret;
		String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

		Response response = client.target(endpoint).path(oauthPath).queryParam("grant_type", "client_credentials")
				.request(MediaType.APPLICATION_JSON).header("Authorization", "Basic " + encodedCredentials).post(null);

		BearerToken bearerToken = response.readEntity(BearerToken.class);
		return bearerToken.getAccessToken();
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public void setOauthPath(String oauthPath) {
		this.oauthPath = oauthPath;
	}

	public void setTimelinePath(String timelinePath) {
		this.timelinePath = timelinePath;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
