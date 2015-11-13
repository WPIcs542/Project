package query_ex;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

public class ExEngineSelect {
	private ExEngineProject exeProject = new ExEngineProject();

	public void open(String joinResult) {
		// useless in pipeline, intentionally left blank
	}

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

	public String[] splitOfTuple(String str) throws UnsupportedEncodingException {
		return str.split(","); // ignores commas inside quotation marks
	}

	public void pipelineExe(String joinResult[]) throws UnsupportedEncodingException {
		exeProject.getNext(joinResult);	
	}

}
