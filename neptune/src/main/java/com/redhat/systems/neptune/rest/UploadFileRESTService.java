package com.redhat.systems.neptune.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import com.redhat.systems.neptune.dto.FileUploadForm;
import com.redhat.systems.neptune.util.Roles;

@RolesAllowed(Roles.REST_ALL)
@Path("/uploads")
public class UploadFileRESTService {

	private static final Logger logger = Logger.getLogger(ProductRESTService.class);

	private final String UPLOADED_FILE_PATH = "/Users/igalvan/Temp/";

	@POST
	@Path("/fileupload") // Your Path or URL to call this service
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@MultipartForm FileUploadForm form, @Context HttpServletRequest req) {

		String fileName = form.getFileName() == null ? "Unknown" : form.getFileName();

		String completeFilePath = UPLOADED_FILE_PATH + fileName;
		
		Enumeration<String> headers = req.getHeaderNames();

		try {

			Collection<Part> parts = req.getParts();


			// Save the file
			File file = new File(completeFilePath);

			if (!file.exists()) {
				file.createNewFile();
			}

			FileOutputStream fos = new FileOutputStream(file);

			fos.write(form.getFileData());
			fos.flush();
			fos.close();
		} catch (Exception e) {
			logger.error("Error saving file: " + fileName, e);
			return Response.status(500).entity("Error saving file: " + fileName).build();
		}
		// Build a response to return
		return Response.status(200).entity("uploadFile is called, Uploaded file name : " + fileName).build();
	}

}