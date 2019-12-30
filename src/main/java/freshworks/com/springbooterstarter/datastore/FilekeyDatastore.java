package freshworks.com.springbooterstarter.datastore;

import java.io.IOException;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class FilekeyDatastore {
	@Autowired
	public FileKeyService fileserv;

	@RequestMapping("/values")
	public HashMap<String, pojo> getAllValues() throws CustomizeException {
		return fileserv.getAllValues();
	}

	@RequestMapping("/values/{key}")
	public pojo getKeyValue(@PathVariable String key) throws CustomizeException {
		return fileserv.getKeyValue(key);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/values/{key}")
	public synchronized String delete(@PathVariable String key) throws CustomizeException, IOException {
		String message = fileserv.delete(key);
		return message;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/values")
	public synchronized String addValue(@RequestBody pojo pj) throws CustomizeException, IOException {

		String message = fileserv.addValue(pj);
		return message;
	}

}
