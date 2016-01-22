package myudfs;

import java.io.IOException;

import org.apache.pig.EvalFunc;
import org.apache.pig.backend.executionengine.ExecException;
import org.apache.pig.data.Tuple;
import org.json.XML;

public class XMLToJson extends EvalFunc<String> {

	@Override
	public String exec(Tuple input) throws IOException {

		if(input == null || input.get(0) == null){
			throw new ExecException("Invalid input tuple, input can't be null or empty.");
		}
		
		Object inputObj = input.get(0);
		if(!String.class.isAssignableFrom(inputObj.getClass())){
			throw new ExecException("Invalid input type for the UDF. "
					+ "Input type: "+inputObj.getClass()
					+ " Supported type: java.lang.String");
		}

		String tupleData = (String.valueOf(inputObj)).trim();
		return XML.toJSONObject(tupleData).toString();
	}

}
