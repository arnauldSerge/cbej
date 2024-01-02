package fr.charisma.bvj.rc_bvj.batch.component;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class SheetDataProcessor implements ItemProcessor<List<Object>, List<Object>> {

	@Override
	public List<Object> process(List<Object> item) throws Exception {
		//SheetData  sheetline  = new SheetData();
		//sheetline.setColumn1(null);
		//sheetline.setColumn2(null);
		return item;
	}

}
