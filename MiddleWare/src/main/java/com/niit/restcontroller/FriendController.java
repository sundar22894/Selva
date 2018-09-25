package com.niit.restcontroller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.FriendDAO;
import com.niit.model.Friend;
import com.niit.model.UserDetails;

@RestController
public class FriendController 
{
	@Autowired
	FriendDAO friendDAO;
	

	@PostMapping(value="/sendFriendRequest")
	public ResponseEntity<String> sendFriendRequest(@RequestBody Friend friends)
	{
		if(friendDAO.sendFriendRequest(friends))
		{
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("success",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/showFriendList")
	public ResponseEntity<List<Friend>> showFriendList(HttpSession session)
	{
		String loginname=((UserDetails)session.getAttribute("userDetails")).getLoginname();
		List<Friend> listFriends=friendDAO.viewFriendRequests(loginname);
		
		if(listFriends.size()>0)
		{
			return new ResponseEntity<List<Friend>>(listFriends,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Friend>>(listFriends,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/showPendingFriendRequest")
	public ResponseEntity<List<Friend>> showPendingFriendRequest(HttpSession session)
	{
		String loginname=((UserDetails)session.getAttribute("userDetails")).getLoginname();
		List<Friend> pendingFriendRequest=friendDAO.viewPendingFriendRequests(loginname);
		
		if(pendingFriendRequest.size()>0)
		{
			return new ResponseEntity<List<Friend>>(pendingFriendRequest,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Friend>>(pendingFriendRequest,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/showSuggestedFriend")
	public ResponseEntity<List<UserDetails>> showSuggestedFriend(HttpSession session)
	{
		String loginname=((UserDetails)session.getAttribute("userDetails")).getLoginname();
		List<UserDetails> showSuggestedFriend=friendDAO.viewSuggestedFriendRequests(loginname);
		
		if(showSuggestedFriend.size()>0)
		{
			return new ResponseEntity<List<UserDetails>>(showSuggestedFriend,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<UserDetails>>(showSuggestedFriend,HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping(value="/acceptFriendRequest/{friendID}")
	public ResponseEntity<String> acceptFriendRequest(@PathVariable("friendID") int friendId)
	{
		if(friendDAO.acceptFriendRequest(friendId))
		{
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("failure",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/deleteFriendRequest/{friendID}")
	public ResponseEntity<String> deleteFriendRequest(@PathVariable("friendID") int friendId)
	{
		if(friendDAO.deleteFriendRequest(friendId))
		{
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("failure",HttpStatus.NOT_FOUND);
		}
	}

	
	
	
}
