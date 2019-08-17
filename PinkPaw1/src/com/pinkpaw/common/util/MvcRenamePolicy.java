package com.pinkpaw.common.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MvcRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File oldFile) {
		File newFile = null;
		
		do {
			//년월일_시분초밀리초_난수3자리
			//20190724_170000123_999.png
			long currentTime = System.currentTimeMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
			int rndNum = new Random().nextInt(999)+1;
			
			//확장자명 가져오기
			String oldName = oldFile.getName();
			String ext = "";
			int dot = oldName.lastIndexOf(".");
			//확장자가 있다면
			if(dot > -1) {
				ext = oldName.substring(dot);//.png
			}
			//새이름 부여
			String newName = sdf.format(new Date(currentTime))
							+"_"+rndNum+ext;
			
			//부모디렉토리와 파일명을 가지고 파일 객체 생성
			newFile = new File(oldFile.getParent(), newName);
			System.out.println("oldFile@MvcRenamePolicy="+oldFile.getName());
			System.out.println("newFile@MvcRenamePolicy="+newFile.getName());
			
		} while(!createNewFile(newFile));
		
		return newFile;
	}

	/**
	 * File객체의 createNewFile메소드는
	 * - 파일이 존재하지 않으면, 파일을 생성하고, true를 리턴함.
	 * - 파일이 존재하면, 파일을 생성하지 않고, false를 리턴함.
	 * @param f
	 * @return
	 */
	private boolean createNewFile(File f) {
		try {
			return f.createNewFile();
		}
		catch (IOException ignored) {
			return false;
		}
	}

}
