package com.twitter.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.twitter.dao.TweetDAO;
import com.twitter.dto.Tweet;

@Component
@Path("/tweet")
public class TweetServiceImpl {

	@Autowired
	private TweetDAO tweetDAO;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/test")
	public String testService() {
		return tweetDAO.test();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{screenName}")
	public List<Tweet> getTweetsByScreenName(@PathParam("screenName") String screenName) {
		return tweetDAO.getTweetsByScreenName(screenName);
	}
}
