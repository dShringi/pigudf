package myudfs;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.DataBag;
import org.apache.pig.data.Tuple;

public class ConvertToMap extends EvalFunc<Map> {

	@SuppressWarnings("unchecked")
	@Override
	public Map exec(Tuple input) throws IOException {
        DataBag values = (DataBag)input.get(0);
        Map<Object, Object> m = new HashMap();
        for (Iterator<Tuple> it = values.iterator(); it.hasNext();) {
            Tuple t = it.next();
            m.put(t.get(0), t.get(1));
        }
        return m;
    }
	
//	public String exec(Tuple input) throws IOException {
//		DataBag values = (DataBag) input.get(0);
//		StringBuilder sb = new StringBuilder();
//		for (Iterator<Tuple> it = values.iterator(); it.hasNext();) {
//			Tuple t = it.next();
//			String tupleData = "" + t.get(0);
//			List<String> output = new ArrayList<>();
//			
//			int index = -1;
//			for (String str : tupleData.split("\\s")) {
//				if (str.contains("=")) {
//					output.add(str);
//					index++;
//				} else {
//					str = output.get(index) + " " + str;
//					output.set(index, str);
//				}
//			}
//			
//			for (String str : output) {
//				sb.append(str + "#");
//			}
//		}
//		return sb.toString();
//	}

}
