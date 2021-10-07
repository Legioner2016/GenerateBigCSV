package com.bigcsv.demo.service;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bigcsv.demo.entity.MyTestEntity;
import com.bigcsv.demo.repository.MyTestEntityRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MyService {

	private final static String[] RESULT_HEADERS = { "id", "idOwner", "title", "data1", "data2" };
	private final static int RECORDS_IN_ONE_FILE = 1000000;

	@Autowired
	private MyTestEntityRepository repository;

	@Transactional(readOnly = true)
	public Stream<MyTestEntity> getAllForOwner(int idOwner) {
		return repository.findAllByOwnerId(idOwner);
	}

	@Transactional(readOnly = true)
	public void streamAll(final OutputStream outputStream, Integer ownerId) {
		try {
			final Stream<MyTestEntity> stream = repository.findAllByOwnerId(ownerId);

			byte[] buff = (Stream.of(RESULT_HEADERS).map(this::prepareObjectToCsv).collect(Collectors.joining(","))
					+ "\n").getBytes(StandardCharsets.UTF_8);
			outputStream.write(buff);

			stream.forEach(entity -> {
				String test = entity.getId() + "," + entity.getOwnerId() + "," + prepareObjectToCsv(entity.getString1())
						+ "," + entity.getInt1() + "," + prepareObjectToCsv(entity.getNumeric1()) + "\n";
				byte[] b = test.getBytes(StandardCharsets.UTF_8);
				try {
					outputStream.write(b);
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			});

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Transactional(readOnly = true)
	public void streamAllZip(final OutputStream outputStream, Integer ownerId) {
		try {
			final ZipOutputStream zipOut = new ZipOutputStream(outputStream);
			final Stream<MyTestEntity> stream = repository.findAllByOwnerId(ownerId);

			byte[] headers = (Stream.of(RESULT_HEADERS).map(this::prepareObjectToCsv).collect(Collectors.joining(","))
					+ "\n").getBytes(StandardCharsets.UTF_8);
			AtomicInteger rows = new AtomicInteger(0);
			AtomicInteger fileNumber = new AtomicInteger(0);
			
			stream.forEach(entity -> {
				if (rows.get() % RECORDS_IN_ONE_FILE == 0) {
					ZipEntry entry = new ZipEntry("sample" + fileNumber.incrementAndGet() + ".csv");
					entry.setComment("part of records data");
					try {
						zipOut.putNextEntry(entry);
						zipOut.write(headers);
					} catch (IOException e) {
						log.error(e.getMessage(), e);
					}
				}
				rows.incrementAndGet();
				
				String test = entity.getId() + "," + entity.getOwnerId() + "," + prepareObjectToCsv(entity.getString1())
						+ "," + entity.getInt1() + "," + prepareObjectToCsv(entity.getNumeric1()) + "\n";
				try {
					byte[] b = test.getBytes(StandardCharsets.UTF_8);
					zipOut.write(b);
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
			});
			zipOut.close();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	private String prepareObjectToCsv(Object object) {
		String value = String.valueOf(object);
		boolean quoted = value.contains(",") || value.contains("\"");
		value = value.replaceAll("\"", "\"\"");
		return quoted ? "\"" + value + "\"" : value;
	}

}
