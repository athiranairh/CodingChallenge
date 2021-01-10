package com.rbc.application.stock.market.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * @author athira nair
 * 
 * data types of fields should be updated accordingly in future
 * now for easy handling, kept most of the fields as Strings
 *
 */
@Entity

@Table(name = "`StockMarketDataSet`")

public class DataSet {
	
	@Version
	
	public long version; // to provide version number to client to enable optimistic locking

	@Id
	
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "id")

	public long id;

	@Column(name = "quarter")

	public Integer quarter;

	@Column(name = "stock")

	public String stock;

	@Column(name = "date")

	public String date;

	@Column(name = "open")

	public String open;

	@Column(name = "high")

	public String high;

	@Column(name = "low")

	public String low;

	@Column(name = "close")

	public String close;

	@Column(name = "volume")

	public String volume;

	@Column(name = "percent_change_price")

	public String percent_change_price;

	@Column(name = "percent_chagne_volume_over_last_week")

	public String percent_chagne_volume_over_last_week;

	@Column(name = "previous_weeks_volume")

	public Integer previous_weeks_volume;

	@Column(name = "next_weeks_open")

	public Integer next_weeks_open;

	@Column(name = "next_weeks_close")

	public Integer next_weeks_close;

	@Column(name = "percent_change_next_weeks_price")

	public Integer percent_change_next_weeks_price;

	@Column(name = "days_to_next_dividend")

	public Integer days_to_next_dividend;

	@Column(name = "percent_return_next_dividend")

	public Integer percent_return_next_dividend;

	public DataSet() {
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getQuarter() {
		return quarter;
	}

	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getClose() {
		return close;
	}

	public void setClose(String close) {
		this.close = close;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getPercent_change_price() {
		return percent_change_price;
	}

	public void setPercent_change_price(String percent_change_price) {
		this.percent_change_price = percent_change_price;
	}

	public String getPercent_chagne_volume_over_last_week() {
		return percent_chagne_volume_over_last_week;
	}

	public void setPercent_chagne_volume_over_last_week(String percent_chagne_volume_over_last_week) {
		this.percent_chagne_volume_over_last_week = percent_chagne_volume_over_last_week;
	}

	public Integer getPrevious_weeks_volume() {
		return previous_weeks_volume;
	}

	public void setPrevious_weeks_volume(Integer previous_weeks_volume) {
		this.previous_weeks_volume = previous_weeks_volume;
	}

	public Integer getNext_weeks_open() {
		return next_weeks_open;
	}

	public void setNext_weeks_open(Integer next_weeks_open) {
		this.next_weeks_open = next_weeks_open;
	}

	public Integer getNext_weeks_close() {
		return next_weeks_close;
	}

	public void setNext_weeks_close(Integer next_weeks_close) {
		this.next_weeks_close = next_weeks_close;
	}

	public Integer getPercent_change_next_weeks_price() {
		return percent_change_next_weeks_price;
	}

	public void setPercent_change_next_weeks_price(Integer percent_change_next_weeks_price) {
		this.percent_change_next_weeks_price = percent_change_next_weeks_price;
	}

	public Integer getDays_to_next_dividend() {
		return days_to_next_dividend;
	}

	public void setDays_to_next_dividend(Integer days_to_next_dividend) {
		this.days_to_next_dividend = days_to_next_dividend;
	}

	public Integer getPercent_return_next_dividend() {
		return percent_return_next_dividend;
	}

	public void setPercent_return_next_dividend(Integer percent_return_next_dividend) {
		this.percent_return_next_dividend = percent_return_next_dividend;
	}

	public DataSet(Long id, Integer quarter, String stock, String date, String open, String high, String low,
			String close, String volume, String percent_change_price, String percent_chagne_volume_over_last_week,
			Integer previous_weeks_volume, Integer next_weeks_open, Integer next_weeks_close,
			Integer percent_change_next_weeks_price, Integer days_to_next_dividend,
			Integer percent_return_next_dividend) {
		super();
		this.id = id;
		this.quarter = quarter;
		this.stock = stock;
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
		this.percent_change_price = percent_change_price;
		this.percent_chagne_volume_over_last_week = percent_chagne_volume_over_last_week;
		this.previous_weeks_volume = previous_weeks_volume;
		this.next_weeks_open = next_weeks_open;
		this.next_weeks_close = next_weeks_close;
		this.percent_change_next_weeks_price = percent_change_next_weeks_price;
		this.days_to_next_dividend = days_to_next_dividend;
		this.percent_return_next_dividend = percent_return_next_dividend;
	}

}
