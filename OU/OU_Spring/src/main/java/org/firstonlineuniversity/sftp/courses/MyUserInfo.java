package org.firstonlineuniversity.sftp.courses;

import com.jcraft.jsch.UserInfo;

public class MyUserInfo implements UserInfo{

	@Override
	public String getPassphrase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean promptPassphrase(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean promptPassword(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean promptYesNo(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void showMessage(String arg0) {
		// TODO Auto-generated method stub
		
	}

}
