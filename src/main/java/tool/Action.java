package tool;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//fill the later 
public abstract class Action {

	public abstract void execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception;

}