package com.nik.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nik.dao.FriendDao;
import com.nik.model.ErrorClazz;
import com.nik.model.Friend;
import com.nik.model.User;

@Controller
public class FriendController {
	@Autowired
private FriendDao friendDao;
	
	@RequestMapping(value="/suggestedusers",method=RequestMethod.GET)
	public ResponseEntity<?> getSuggestedUsersList(HttpSession session){
	String email=(String)session.getAttribute("loginId");
	if(email==null){
		ErrorClazz error=new ErrorClazz(4,"Unauthrozied access.. Please login");
		return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
	}
	List<User> suggestedUsers=friendDao.listOfSuggestedUsers(email);
	return new ResponseEntity<List<User>>(suggestedUsers,HttpStatus.OK);
	
	}
	@RequestMapping(value="/addfriend",method=RequestMethod.POST)
	public ResponseEntity<?> addFriendRequest(@RequestBody User toUser,HttpSession session){
		String email=(String)session.getAttribute("loginId");
		if(email==null){
			ErrorClazz error=new ErrorClazz(4,"Unauthrozied access.. Please login");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		Friend friend=new Friend();
		friend.setFromId(email);
		friend.setToId(toUser.getEmail());
		friend.setStatus('P');//Pending
		friendDao.addFriendRequest(friend);
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
		
	}
	
		@RequestMapping(value="/pendingrequests",method=RequestMethod.GET)
			public ResponseEntity<?> pendingRequests(HttpSession session){
			String email=(String)session.getAttribute("loginId");
			if(email==null){
				ErrorClazz error=new ErrorClazz(4,"Unauthrozied access.. Please login");
				return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED); //2nd callback function
			}
			List<Friend> friendRequests=friendDao.getAllPendingRequests(email);
			return new ResponseEntity<List<Friend>>(friendRequests,HttpStatus.OK);
	 }
		
		
	@RequestMapping(value="/updatependingrequest",method=RequestMethod.PUT)
	public ResponseEntity<?> updatePendingRequest(@RequestBody Friend friend,HttpSession session){
	 String email=(String)session.getAttribute("loginId");
		if(email==null){
			ErrorClazz error=new ErrorClazz(4,"Unauthrozied access.. Please login");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED); //2nd callback function
		}
		friendDao.updateFriendRequest(friend);//friend status is updated to 'A' or 'D'
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
		}
	
	
		@RequestMapping(value="/friends",method=RequestMethod.GET)
		public ResponseEntity<?> listOfFriends(HttpSession session){
		String email=(String)session.getAttribute("loginId");
		if(email==null){
			ErrorClazz error=new ErrorClazz(4,"Unauthrozied access.. Please login");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED); //2nd callback function
		}
			
		List<User> friends=friendDao.listOfFriends(email);
		return new ResponseEntity<List<User>>(friends,HttpStatus.OK);
	}
}