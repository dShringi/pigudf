package myudfs;

import java.util.ArrayList;
import java.util.List;

import org.apache.pig.backend.executionengine.ExecException;
import org.apache.pig.data.Tuple;
import org.apache.pig.data.TupleFactory;

public class TestUDF {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		List list = new ArrayList();
		list.add("act=permitted app=http dvc=192.168.100.40 dst=124.153.64.106 dhost=radarfeed.moneycontrol.com dpt=80 src=172.16.217.171 spt=52283 suser=LDAP://172.16.202.140 OU\\=Users,OU\\=MAHAPE,OU\\=India,DC\\=mastek,DC\\=com/Suyash Trivedi destinationTranslatedPort=24977 rt=1445819061000 in=923 out=1273 requestMethod=GET requestClientApplication=Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36 reason=- cs1Label=Policy cs1=role-8**NikhilRG_Allowed cs2Label=DynCat cs2=0 cs3Label=ContentType cs3=text/html; charset\\=UTF-8 cn1Label=DispositionCode cn1=1028 cn2Label=ScanDuration cn2=3 request=http://radarfeed.moneycontrol.com/mcradar/processing.php?q_a\\=d&ep55014&callback\\=LTD");
		Tuple tuple = TupleFactory.getInstance().newTuple(list);
		try {
			String tupleData = ""+tuple.get(0);
			if(tupleData!=null && !tupleData.isEmpty()){
				List<String> processInput = processInput(tupleData.split("\\s"));
				StringBuilder sb = new StringBuilder();
				for(String str: processInput){
					sb.append(str+"#");
				}
				System.out.println(sb);
			}
		} catch (ExecException e) {
			e.printStackTrace();
		}
	}
	
	private static List<String> processInput(String[] input){
		List<String> output = new ArrayList<>();
		int index = -1;
		for(String str: input){
			if(str.contains("=")){
				output.add(str);
				index++;
			} else {
				if(index!=-1){
					str = output.get(index) + " " +str;
					output.set(index, str);
				}
			}
		}
		return output;
	}
}
