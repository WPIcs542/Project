package query_ex;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

 /**
  *  this class is for get the tuple satisfy our condition 
  * @author Fangyu Lin; Hongzhang Cheng; Zhaojun Yang
  * @date November/04/2015
  */
public class ExEngineSelect {
	private ExEngineProject exeProject = new ExEngineProject();

	public void open(String joinResult) {
		// useless in pipeline, intentionally left blank
	}
/**
 * this function is for getting the tuple which satisfy our our condition, it get the string
 *  from the object exenginejoin and then split it to a string array, then transform some
 *  of the elements in some attribute to double then compare based on them, at last pipeline
 *  it to exengineproject
 * 
 * @param tupleResult
 * @throws UnsupportedEncodingException
 */
	public void getNext(String tupleResult) throws UnsupportedEncodingException {
		String tuple[] = splitOfTuple(tupleResult);
		Double countryP = Double.parseDouble(tuple[6]);
		Double cityP = Double.parseDouble(tuple[19]);
		if (cityP > 0.4 * countryP) {
			pipelineExe(tuple);
		}

	}

	public void close() {
		// useless in pipeline, intentionally left blank
	}
/**
 * this is the function used for split the string based on commas
 * @param str
 * @return
 * @throws UnsupportedEncodingException
 */
	public String[] splitOfTuple(String str) throws UnsupportedEncodingException {
		return str.split(","); // ignores commas inside quotation marks
	}
/**
 * this is the function connect the exeproject with the object in this class
 * @param joinResult
 * @throws UnsupportedEncodingException
 */
	public void pipelineExe(String joinResult[]) throws UnsupportedEncodingException {
		exeProject.getNext(joinResult);	
	}

}
