package fr.charisma.bvj.rc_bvj.service;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
//import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;

import jakarta.annotation.PostConstruct;

public class GoogleSheetsService {
	
	private Sheets sheetsService;

    @PostConstruct
    public void init() throws GeneralSecurityException, IOException {
        InputStream credentials = getClass().getResourceAsStream("/path/to/credentials.json");
        
        GoogleCredentials googleCredential = ServiceAccountCredentials.fromStream(credentials)
            .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS));

        this.sheetsService = new Sheets.Builder(
            GoogleNetHttpTransport.newTrustedTransport(),
            GsonFactory.getDefaultInstance(), new HttpCredentialsAdapter(googleCredential)
        ).setApplicationName("Your Application Name").build();
    }

    public List<List<Object>> readData(String spreadsheetId, String range) throws IOException {
        ValueRange response = sheetsService.spreadsheets().values().get(spreadsheetId, range).execute();
        return response.getValues();
    }

    public void writeData(String spreadsheetId, String range, List<List<Object>> data) throws IOException {
        ValueRange valueRange = new ValueRange().setValues(data);
        sheetsService.spreadsheets().values().update(spreadsheetId, range, valueRange)
            .setValueInputOption("RAW")
            .execute();
    }

}
