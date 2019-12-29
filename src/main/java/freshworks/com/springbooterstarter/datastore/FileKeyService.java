package freshworks.com.springbooterstarter.datastore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class FileKeyService {
	// file path can be fetched from arguments of main method
	File file = new File("C:/freshworks/filedatastore.txt");

	public HashMap<String, pojo> getAllValues() throws CustomizeException {
		BufferedReader reader = null;
		String key, name;
		int age;
		String text = null;
		pojo pj = null;
		HashMap<String, pojo> hmap = new HashMap<String, pojo>();

		try {

			reader = new BufferedReader(new FileReader(file));
			while ((text = reader.readLine()) != null) {
				pj = new pojo();
				key = text.split(",")[0].trim();
				age = Integer.parseInt(text.split(",")[2].trim());
				name = text.split(",")[1].trim();
				pj.setAge(age);
				pj.setName(name);
				pj.setKey(key);
				hmap.put(key, pj);
			}
			reader.close();
		} catch (IOException e) {
			throw new CustomizeException("There is no such file" + e.getMessage());
		}
		return hmap;
	}

	public pojo getKeyValue(String key) throws CustomizeException {
		HashMap<String, pojo> hmap = new HashMap<String, pojo>();
		hmap = getAllValues();
		return hmap.get(key);
	}

	public String addValue(String key, String name, int age) throws CustomizeException {
		HashMap<String, pojo> hmap = new HashMap<String, pojo>();
		hmap = getAllValues();
		BufferedWriter bufferedWriter = null;
		if (hmap.containsKey(key))
			return "the key is already present";
		else {
			try {

				FileWriter fileWriter = new FileWriter(file);
				bufferedWriter = new BufferedWriter(fileWriter);
				pojo pj = null;
				for (HashMap.Entry mapElement : hmap.entrySet()) {
					pj = new pojo();
					pj = (pojo) mapElement.getValue();
					bufferedWriter.append(mapElement.getKey() + "," + pj.getName() + "," + pj.getAge());
					bufferedWriter.append('\n');
				}
				bufferedWriter.append(key + "," + name + "," + age);
				bufferedWriter.close();

			} catch (IOException e) {
				throw new CustomizeException("There is no such file" + e.getMessage());
			}

		}
		return "new key is added";
	}

	public String delete(String key) throws CustomizeException {
		HashMap<String, pojo> hmap = new HashMap<String, pojo>();
		hmap = getAllValues();
		BufferedWriter bufferedWriter = null;
		if (hmap.containsKey(key)) {
			hmap.remove(key);
			try {

				FileWriter fileWriter = new FileWriter(file);
				bufferedWriter = new BufferedWriter(fileWriter);
				pojo pj = null;
				for (HashMap.Entry mapElement : hmap.entrySet()) {
					pj = new pojo();
					pj = (pojo) mapElement.getValue();
					bufferedWriter.append(mapElement.getKey() + "," + pj.getName() + "," + pj.getAge());
					bufferedWriter.append('\n');
				}
				bufferedWriter.close();
			} catch (IOException e) {
				throw new CustomizeException("There is no such file" + e.getMessage());
			}
			return "deleted key is " + key;
		} else
			return "there is no such key";

	}

}
