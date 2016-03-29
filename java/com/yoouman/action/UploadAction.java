package com.yoouman.action;

import java.io.File;
import java.util.UUID;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import com.yoouman.util.UploadConfigurationRead;
/**
 * 多文件上传类
 * 
 * @author LF
 * 
 * @Time 2013-11-24下午09:26:44
 */
public class UploadAction extends BaseAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File[] upload;// 实际上传文件
	private String[] uploadContentType; // 文件的内容类型
	private String[] uploadFileName; // 上传文件名

	public String execute() throws Exception
	{
		try
		{
			String targetDirectory = ServletActionContext.getServletContext()
					.getRealPath("/"+ UploadConfigurationRead.getInstance().getConfigItem("uploadFilePath").trim());// 获得路径
			for (int i = 0; i < upload.length; i++)
			{
				String fileName = uploadFileName[i];// 上传的文件名
				String type = uploadContentType[i];// 文件类型
				String realName = UUID.randomUUID().toString()+"."+getExt(fileName);// 保存的文件名称，使用UUID+后缀进行保存
				System.out.println();
				File target = new File(targetDirectory, realName);
				session.setAttribute("fileImage", target);
				FileUtils.copyFile(upload[i], target);// 上传至服务器的目录，一般都这样操作，
														// 在把路径写入数据库即可

				session.setAttribute("type", getExt(fileName));
				session.setAttribute("realName",
						realName);
				

				// uploadRealName[i]=UUID.randomUUID().toString();
				// System.out.println("uploadRealName:"+uploadRealName[i]);
				// for(File file:upload)
				// {
				// System.out.println("filename:"+file.getName());
				// }
				// System.out.println("filename:"+fileName);
				// System.out.println("Ext:"+getExt(fileName));
				// System.out.println("Type:"+type);
				// System.out.println(target.getPath());
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			addActionError(e.getMessage());

			return INPUT;
		}

		return SUCCESS;

	}

	public File[] getUpload()
	{
		return upload;
	}

	public void setUpload(File[] upload)
	{
		this.upload = upload;
	}

	public String[] getUploadContentType()
	{
		return uploadContentType;
	}

	public void setUploadContentType(String[] uploadContentType)
	{
		this.uploadContentType = uploadContentType;
	}

	public String[] getUploadFileName()
	{
		return uploadFileName;
	}

	public void setUploadFileName(String[] uploadFileName)
	{
		this.uploadFileName = uploadFileName;
	}

	public static String getExt(String fileName)
	{
		return fileName.substring(fileName.lastIndexOf(".")+1);
	}

}

