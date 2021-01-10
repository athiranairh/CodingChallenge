package com.rbc.application.stock.market.application.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rbc.application.stock.market.application.models.DataSet;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * @author athira nair
 * 
 * Utility class to provide some CSV handling methods
 *
 */
public class CommonUtil {

	public static String TYPE = "text/csv";
	static String[] HEADERS = { "Id","quarter", "stock", "date", "open", "high", "low", "close", "volume",
			"percent_change_price", "percent_chagne_volume_over_last_week", "previous_weeks_volume", "next_weeks_open",
			"next_weeks_close", "percent_change_next_weeks_price", "days_to_next_dividend",
			"percent_return_next_dividend" };

	public static boolean hasCsvFormat(MultipartFile file) {
		if (!TYPE.equals(file.getContentType())) {
			return false;
		} else {
			return true;
		}
	}

	public static List<DataSet> csvToDataSet(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<DataSet> dataSets = new ArrayList<DataSet>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			
			for (CSVRecord csvRecord : csvRecords) {
				DataSet dataSet = new DataSet(Long.parseLong(csvRecord.get("Id")), Integer.parseInt(csvRecord.get("quarter")),
						csvRecord.get("stock"), csvRecord.get("date"), csvRecord.get("open"), csvRecord.get("high"),
						csvRecord.get("low"), csvRecord.get("close"), csvRecord.get("volume"),
						csvRecord.get("percent_change_price"), csvRecord.get("percent_chagne_volume_over_last_week"),
						Integer.parseInt(csvRecord.get("previous_weeks_volume")), Integer.parseInt(csvRecord.get("next_weeks_open")),
						Integer.parseInt(csvRecord.get("next_weeks_close")), Integer.parseInt(csvRecord.get("percent_change_next_weeks_price")),
						Integer.parseInt(csvRecord.get("days_to_next_dividend")), Integer.parseInt(csvRecord.get("percent_return_next_dividend")));
				dataSets.add(dataSet);
			}

			return dataSets;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}

}
