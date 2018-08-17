package com.niit.dao;

import com.niit.model.ProfilePicture;

public interface ProfilePictureDAO 
{

	public boolean savePicture(ProfilePicture profilePicture);
	public ProfilePicture viewProfilePicture(String loginname);
	public boolean updatePicture(ProfilePicture profilePicture);	

}
