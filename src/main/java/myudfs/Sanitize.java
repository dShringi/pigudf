package myudfs;

import java.util.ArrayList;
import java.util.List;

import org.apache.pig.EvalFunc;
import org.apache.pig.backend.executionengine.ExecException;
import org.apache.pig.data.Tuple;

public class Sanitize extends EvalFunc<String> {
	
	@Override
	public String exec(Tuple input) throws ExecException {
		
		if(input == null || input.get(0) == null){
			throw new ExecException("Invalid input tuple, input can't be null or empty.");
		}
		
		Object inputObj = input.get(0);
		if(!String.class.isAssignableFrom(inputObj.getClass())){
			throw new ExecException("Invalid input type for the UDF. "
					+ "Input type: "+inputObj.getClass()
					+ " Supported type: java.lang.String");
		}
		
		String tupleData = ("" + inputObj).trim();
		StringBuilder sb = new StringBuilder();
		List<String> output = new ArrayList<>();
		
		if(!tupleData.contains("=")) {
			return tupleData;
		}
		
		int index = -1;
		for (String str : tupleData.split("\\s")) {
			if (str.contains("=") && !str.contains(",")) {
				output.add(str);
				index++;
			} else {
				str = output.get(index) + " " + str;
				output.set(index, str);
			}
		}

		for (String str : output) {
			sb.append(str + "#");
		}
		
		String result = sb.toString();
		return result.substring(0, result.length()-1);
	}

}