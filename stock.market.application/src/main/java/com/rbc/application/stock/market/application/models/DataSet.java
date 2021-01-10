package com.rbc.application.stock.market.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "`StockMarketDataSet`")

public class DataSet {

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
