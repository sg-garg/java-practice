package com.sg.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.sg.exception.AdvertisementServiceException;
import com.sun.org.apache.xpath.internal.operations.Bool;

public class AdvertisementServiceUtil {
	private final static Logger logger = Logger
			.getLogger(AdvertisementServiceUtil.class);
	private static ResourceBundle resourceBundle;
	static {
		resourceBundle = ResourceBundle.getBundle("config");

	}

	private AdvertisementServiceUtil() {
	}

	public static String getProperties(String propertyName) {
		String value;
		try {
			value = resourceBundle.getString(propertyName);
		} catch (Exception e) {
			value = "";
			logger.error("Error occured while finding property", e);

		}

		return value;
	}

	public static <T extends Serializable> void serilizeObjects(
			String fileName, T object) {
		List<T> list = deserilizeObjects(fileName);
		try (FileOutputStream fos = new FileOutputStream(fileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			if (list == null)
				list = new ArrayList<T>();
			list.add(object);
			oos.writeObject(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static <K extends Serializable, V extends Serializable> void replaceValueByKeySerilizedMap(
			String fileName, K key, List<V> valueList) {
		Map<K, List<V>> map = deserilizeAsMap(fileName);
		
		try (FileOutputStream fos = new FileOutputStream(fileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			if (map == null)
				map = new HashMap<K, List<V>>();
			
			map.put(key, valueList);
			oos.writeObject(map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static <K extends Serializable, V extends Serializable> void serilizeAsMap(
			String fileName, K key, V value) {
		Map<K, List<V>> map = deserilizeAsMap(fileName);

		try (FileOutputStream fos = new FileOutputStream(fileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			if (map == null)
				map = new HashMap<K, List<V>>();
			
			List<V> list;
			if(map.containsKey(key)){
				 list = map.get(key);
				 list.add(value);
			}else{
				list = new ArrayList<V>();
				list.add(value);
			}
			map.put(key, list);
			oos.writeObject(map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static <K extends Serializable, V extends Serializable> Boolean removeSeriliezedMapValue(
			String fileName, K key, V value) {
		Map<K, List<V>> map = deserilizeAsMap(fileName);
		if(map == null){
			return false;
		}
		Boolean result = false;
		try (FileOutputStream fos = new FileOutputStream(fileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			List<V> list;
			if(map.containsKey(key)){
				 list = map.get(key);
				 Iterator<V> iterator = list.iterator();
				 while (iterator.hasNext()) {
					V v = (V) iterator.next();
					if(v.equals(value)){
						iterator.remove();
						result = true;
					}
				}
				map.put(key, list);
				oos.writeObject(map);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static <K extends Serializable, V extends Serializable> Map<K, List<V>> deserilizeAsMap(
			String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			return null;
		}
		Map<K, List<V>> map = null;
		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			map = (Map<K, List<V>>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return map;

	}

	public static <T extends Serializable> List<T> deserilizeObjects(
			String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			return null;
		}
		List<T> list = null;
		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			list = (List<T>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;

	}
	
	public static String toString(Date date) {
		if (date != null) {
			DateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm");
			return df.format(date);
		}
		return null;
	}
	
	public static Date toDate(String strDate) throws AdvertisementServiceException {
		if (strDate != null) {
			DateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm");
			try {
				return df.parse(strDate);
			} catch (ParseException e) {
				throw new AdvertisementServiceException("INVALID_DATE_FORMAT", "Invalid date input:"+strDate+ ", correct format is MM-dd-yyyy HH:mm");
			}
		}
		return null;
	}
	
	public static void main(String[] args) throws AdvertisementServiceException {
		System.out.println(AdvertisementServiceUtil.getProperties("app.url"));
		System.out.println(new Date());
		System.out.println(toString(new Date()));
		System.out.println(toString(null));
		System.out.println(toDate("02-02-2016 20:24"));
	}

}
