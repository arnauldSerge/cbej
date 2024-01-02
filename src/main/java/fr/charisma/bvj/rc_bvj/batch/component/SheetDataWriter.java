package fr.charisma.bvj.rc_bvj.batch.component;

import java.util.List;

import org.springframework.batch.item.Chunk;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import fr.charisma.bvj.rc_bvj.service.GoogleSheetsService;
import lombok.Data;
@Component
@Data
public class SheetDataWriter implements ItemWriter<List<Object>> {
	//private final SheetDataRepository  sheetDataRepository;
	private final GoogleSheetsService googleSheetsService;
	private final String targetSpreadSheetId;
	private final String targetRange;

	/**@Override
	public void write(Chunk<? extends SheetData> chunk) throws Exception {
		//TODO send sms
		//write in file
		sheetDataRepository.saveAll(chunk);
		
	}*/
	

	@SuppressWarnings("unchecked")
	@Override
	public void write(Chunk<? extends List<Object>> chunk) throws Exception {
		// TODO Auto-generated method stub
		googleSheetsService.writeData(targetSpreadSheetId, targetRange, (List<List<Object>>) chunk.getItems());
		
	}

}
