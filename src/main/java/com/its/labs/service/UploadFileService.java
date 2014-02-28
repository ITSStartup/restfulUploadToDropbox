package com.its.labs.service;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dropbox.core.DbxException;
import com.its.easyjavadropbox.api.impl.EasyJavaDropBoxServiceImpl;
import com.its.easyjavadropbox.api.interfaces.EasyJavaDropBoxService;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/file")
public class UploadFileService {

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("file")InputStream uploadInputStream,	@FormDataParam("file")FormDataContentDisposition fileDetail) {
		//get File Name E.g: file.txt, myphoto.png 
		String fileName = fileDetail.getFileName();
		saveToDropbox(fileName,uploadInputStream);
		String output = "File upload to:" + fileName ;
		return Response.status(200).entity(output).build();
	}
	
	private void saveToDropbox(String fileName, InputStream uploadInputStream) {
		//here dropbox token
		String token = "DpFkOmSumk0AAAAAAAAAAZHqHcBow3Zb9pSAQhv22FACorQHqBKsMIK4qBd_zBdA";
			
		EasyJavaDropBoxService easyJavaDropBoxService = new EasyJavaDropBoxServiceImpl(token);
		try {
		
			easyJavaDropBoxService.saveToDropbox(fileName, uploadInputStream);
		} catch (DbxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
