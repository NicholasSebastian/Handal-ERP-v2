package com.handalcargo.ui.components;

import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.stream.IntStream;

public class DatePicker extends JPanel {
	
	JComboBox<Integer> date;
	JComboBox<Month> month;
	JComboBox<Integer> year;

	public DatePicker() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setOpaque(false);
		
		LocalDate currentDate = LocalDate.now();
		
		int[] dates = IntStream.rangeClosed(1, 31).toArray();
		Integer[] convertedDates = Arrays.stream(dates).boxed().toArray(Integer[]::new);
		date = new JComboBox<Integer>(convertedDates);
		date.setSelectedItem(currentDate.getDayOfMonth());
		add(date);
		
		month = new JComboBox<>(Month.values());
		month.setSelectedItem(currentDate.getMonth());
		add(month);
		
		int currentYear = currentDate.getYear();
		int[] years = IntStream.rangeClosed(currentYear - 25, currentYear + 25).toArray();
		Integer[] convertedYears = Arrays.stream(years).boxed().toArray(Integer[]::new);
		year = new JComboBox<>(convertedYears);
		year.setSelectedItem(currentYear);
		add(year);
	}
	
	public Date getDate() {
		Integer selectedDate = (Integer) date.getSelectedItem();
		Month selectedMonth = (Month) month.getSelectedItem();
		Integer selectedYear = (Integer) year.getSelectedItem();
		
		String dateString = String.format("%d-%d-%d", selectedYear, selectedMonth.getValue(), selectedDate);
		return Date.valueOf(dateString);
	}
}
