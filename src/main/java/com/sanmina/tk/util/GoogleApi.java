package com.sanmina.tk.util;

import com.google.api.client.googleapis.GoogleUtils;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.sheets.v4.Sheets;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.springframework.stereotype.Component;

@Component
public class GoogleApi {

	private Queue<String> serviceAccountQueue = startQueue();
	private Integer uses = 0;
	private String currentSericceAccount;

	private Queue<String> startQueue() {
		Queue<String> queue = new LinkedList<>();
		queue.add("plant8bot@sanmina-linemonitor.iam.gserviceaccount.com.json");
		queue.add("plant8bot2@sanmina-linemonitor.iam.gserviceaccount.com.json");
		queue.add("plant8bot3@sanmina-linemonitor.iam.gserviceaccount.com.json");
		queue.add("plant8bot4@sanmina-linemonitor.iam.gserviceaccount.com.json");
		queue.add("plant8bot5@sanmina-linemonitor.iam.gserviceaccount.com.json");
		return queue;
	}

	private GoogleCredential initCredential() {
		try {
			List<String> scopes = new ArrayList<>();
			scopes.add("https://www.googleapis.com/auth/userinfo.email");
			scopes.add("https://www.googleapis.com/auth/userinfo.profile");
			scopes.add("https://www.googleapis.com/auth/drive");
			scopes.add("https://www.googleapis.com/auth/drive.readonly");
			scopes.add("https://www.googleapis.com/auth/drive.file");
			scopes.add("https://www.googleapis.com/auth/spreadsheets");
			scopes.add("https://www.googleapis.com/auth/spreadsheets.readonly");
			if (uses == 0 || uses >= 50) {
				currentSericceAccount = serviceAccountQueue.poll();
				serviceAccountQueue.add(currentSericceAccount);
				uses = 0;
			}
			uses++;
			System.out.println("Current Service Account: " + currentSericceAccount + " (" + uses + ")");
			String path = "";
			if (System.getProperty("os.name").toLowerCase().contains("win")) {
				path = "google\\" + currentSericceAccount;
			} else {
				path = "google/" + currentSericceAccount;
			}
			return GoogleCredential.fromStream(getClass().getClassLoader().getResourceAsStream(path))
					.createScoped(scopes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Sheets createSheetsService() throws IOException, GeneralSecurityException {
		HttpTransport httpTransport = newProxyTransport();
		JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();
		// GoogleCredential credential = new GoogleCredential().setAccessToken(token);
		GoogleCredential credential = initCredential();
		return new Sheets.Builder(httpTransport, jsonFactory, credential).setApplicationName("Google-Sheets/0.1")
				.build();
	}

	public Sheets createSheetsService(String token) throws IOException, GeneralSecurityException {
		HttpTransport httpTransport = newProxyTransport();
		JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();
		GoogleCredential credential = new GoogleCredential().setAccessToken(token);
		// GoogleCredential credential = initCredential();
		return new Sheets.Builder(httpTransport, jsonFactory, credential).setApplicationName("Google-Sheets/0.1")
				.build();
	}

	public Drive createDriveService(String token) throws IOException, GeneralSecurityException {
		HttpTransport httpTransport = newProxyTransport();
		JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();
		GoogleCredential credential = new GoogleCredential().setAccessToken(token);
		return new Drive.Builder(httpTransport, jsonFactory, credential).setApplicationName("Google-Drive/0.1").build();
	}

	private HttpTransport newProxyTransport() throws GeneralSecurityException, IOException {
		NetHttpTransport.Builder builder = new NetHttpTransport.Builder();
		builder.trustCertificates(GoogleUtils.getCertificateTrustStore());
		builder.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("gateway.zscalertwo.net", 9480)));
		return builder.build();
	}

}
