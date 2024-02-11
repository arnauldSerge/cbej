package fr.charisma.bvj.rc_bvj.batch.component;

import java.util.List;

import org.springframework.batch.item.ItemReader;

import org.springframework.stereotype.Component;
import fr.charisma.bvj.rc_bvj.service.GoogleSheetsService;
import lombok.Data;
@Data
@Component
public class GoogleSheetDataReader implements ItemReader<List<Object>> {
	
    private final GoogleSheetsService googleSheetsService;
    private final String spreadsheetId;
    private final String range;

    private int currentIndex = 0;
    private List<List<Object>> data;

	 @Override
	    public List<Object> read() throws Exception {
	        if (data == null || currentIndex >= data.size()) {
	            data = googleSheetsService.readData(spreadsheetId, range);
	            currentIndex = 0;
	        }
	        return (currentIndex< data.size()) ? data.get(currentIndex ++):null;
	}
}
