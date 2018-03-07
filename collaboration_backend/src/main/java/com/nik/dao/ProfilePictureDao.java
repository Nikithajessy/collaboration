package com.nik.dao;

import com.nik.model.ProfilePicture;

public interface ProfilePictureDao {
void uploadProfilePicture(ProfilePicture profilePicture);
ProfilePicture getProfilePicture(String email);
}
