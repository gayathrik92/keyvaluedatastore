package freshworks.com.springbooterstarter.datastore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

@Service
public class FileKeyService {
	// file path can be fetched from arguments of main method
	// File file = new File("C:/freshworks/filedatastore.txt");
	static String uname = System.getProperty("user.name");
	static String fileSeparator = System.getProperty("file.separator");
	static File from = new File("src/main/java/filedatastore.txt");
	static File to = new File("C:" + fileSeparator + "Users" + fileSeparator + uname + fileSeparator + "filedatastore.txt");

	public HashMap<String, pojo> getAllValues() throws CustomizeException {
		BufferedReader reader = null;
		String key, name;
		int age;
		String text = null;
		pojo pj = null;
		HashMap<String, pojo> hmap = new HashMap<String, pojo>();
		try {
			reader = new BufferedReader(new FileReader(to));
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

	public String addValue(pojo pjbody) throws CustomizeException, IOException {
		HashMap<String, pojo> hmap = new HashMap<String, pojo>();
		hmap = getAllValues();
		BufferedWriter bufferedWriter = null;
		FileWriter fileWriter = null;
		if (pjbody.getKey() != null && !(pjbody.getKey().equals("")) && !(pjbody.getKey().equals("null"))) {
			if (pjbody.getKey().length() < 32) {
				if (hmap.containsKey(pjbody.getKey()))
					return "the key is already present";
				else {
					try {

						fileWriter = new FileWriter(to);
						bufferedWriter = new BufferedWriter(fileWriter);
						pojo pj = null;
						for (HashMap.Entry mapElement : hmap.entrySet()) {
							pj = new pojo();
							pj = (pojo) mapElement.getValue();
							bufferedWriter.append(mapElement.getKey() + "," + pj.getName() + "," + pj.getAge());
							bufferedWriter.append('\n');
						}
						bufferedWriter.append(pjbody.getKey() + "," + pjbody.getName() + "," + pjbody.getAge());
						bufferedWriter.close();

					} catch (IOException e) {
						throw new CustomizeException("There is no such file" + e.getMessage());
					} finally {
						fileWriter.close();
					}
				}

			} else {
				return "key value cannot be more than 32 chars";
			}
		} else {
			return "key cannot be null";
		}
		return "new key is added";
	}

	public String delete(String key) throws CustomizeException, IOException {
		HashMap<String, pojo> hmap = new HashMap<String, pojo>();
		hmap = getAllValues();
		BufferedWriter bufferedWriter = null;
		FileWriter fileWriter = null;
		if (!(key.equals("")) && !(key.equals("null")) && key != null) {
			if (hmap.containsKey(key)) {
				hmap.remove(key);
				try {

					fileWriter = new FileWriter(to);
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
				} finally {
					fileWriter.close();
				}
				return "deleted key is " + key;
			} else {
				return "there is no such key";
			}

		} else
			return "key cannot be null";

	}
//block to automatically create a filedatastore
	static {
		int l = 0;
		try {
			if (to.createNewFile()) {
				l = 1;
				System.out.println("file is created");
			} else {
				System.out.println("file already exist");
			}
			if (l == 1) {
				FileCopyUtils.copy(from, to);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
